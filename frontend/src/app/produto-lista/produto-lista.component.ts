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

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.produtoLista = this.route.snapshot.data.produtoListaResolve.productList || [];
  }
}
