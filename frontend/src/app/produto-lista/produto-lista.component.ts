import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {Produto} from "../produto/Produto";

@Component({
  selector: 'app-produto-lista',
  templateUrl: './produto-lista.component.html',
  styleUrls: ['./produto-lista.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProdutoListaComponent implements OnInit {
  
  public produtoLista: Array<Produto> = [];

  constructor() { }

  ngOnInit() {
    if (localStorage.getItem('produto')){
      this.produtoLista = JSON.parse(localStorage.getItem('produto'));
    }
  }
}
