import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Produto} from "../produto/Produto";
import {ProdutoLista} from './ProdutoLista';

@Component({
  selector: 'app-produto-lista',
  templateUrl: './produto-lista.component.html',
  styleUrls: ['./produto-lista.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProdutoListaComponent implements OnInit {
  
  public produtoLista: Array<Produto> = [];
  public produtoListaCarregado: Array<Produto> = [];
  public quatidadeRegistro: number;
  public registroCarregado:number = 5;
  public pagina:number = 0;

  constructor(private http: HttpClient) { }

  ngOnInit() {    
    if (!localStorage.getItem('token')){      
      window.location.href = '/login';
    }else{
    this.http
    .get('http://localhost:8080/rest/product/all', {
      headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
    })
    .subscribe((response:ProdutoLista) =>{      
      this.produtoListaCarregado = response.productList;
      this.quatidadeRegistro = this.produtoListaCarregado.length;
      this.carregaUsuario();
    });
  }
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

  paginar($event:any){    
    this.pagina = $event - 1;
    this.carregaUsuario();
  }
}
