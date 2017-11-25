import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {Produto} from "../produto/Produto";
import { ActivatedRoute } from '@angular/router';

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

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.produtoLista = this.route.snapshot.data.produtoListaResolve.productList || [];
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

  paginar($event:any){    
    this.pagina = $event - 1;
    this.carregaUsuario();
  }
}
