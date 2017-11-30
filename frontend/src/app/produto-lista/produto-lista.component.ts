import { Component, OnInit, ViewEncapsulation, ViewContainerRef } from '@angular/core';
import {Produto} from "../produto/Produto";
import { ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {ToastsManager} from "ng2-toastr/ng2-toastr";

@Component({
  selector: 'app-produto-lista',
  templateUrl: './produto-lista.component.html',
  styleUrls: ['./produto-lista.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProdutoListaComponent implements OnInit {

  public produtoLista: Array<Produto> = [];
  public produtoListaCarregado: Array<Produto> = [];
  public quantidadeRegistro: number = 0;
  public registroCarregado:number = 5;
  public pagina:number = 0;

  constructor(private route: ActivatedRoute, private http: HttpClient, public toast: ToastsManager, public vcr:ViewContainerRef) { 
    this.toast.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
    this.produtoListaCarregado = this.route.snapshot.data.produtoListaResolve.productList || [];
    this.quantidadeRegistro = this.produtoListaCarregado.length;
    this.carregaUsuario();  
  }

  carregaUsuario(){
    this.produtoLista = [];
    for (let i = (this.pagina * this.registroCarregado); i < (this.pagina * this.registroCarregado) + this.registroCarregado; i++){
      if (i >= this.produtoListaCarregado.length){
        break;
      }
      this.produtoLista.push(this.produtoListaCarregado[i]);
    }
  }

  removerProduto(produtoId){    
    let endPoint = "http://localhost:8080/rest/product/{produtoId}";
    endPoint = endPoint.replace('{produtoId}', produtoId);
    this.http
    .delete(endPoint, {
      headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
    })
    .subscribe(() =>{
      this.toast.success("Sucesso", "O produto foi removido");
      this.removeProdutoLista(produtoId);
    },((error)=>{
      this.toast.error(error.error.message);
    }));
  }

  private removeProdutoLista(produtoId){
    for (let i = 0; i < this.produtoListaCarregado.length; i++){
      let produto:Produto = this.produtoListaCarregado[i];
      if (produtoId == produto.id){        
        this.produtoListaCarregado.splice(1, i);        
      }
    }
    for (let y = 0; y < this.produtoLista.length; y++){
      let produto:Produto = this.produtoLista[y];
      if (produtoId == produto.id){        
        this.produtoLista.splice(1, y);        
      }
    }
  }

  paginar($event:any){    
    this.pagina = $event - 1;
    this.carregaUsuario();
  }
}
