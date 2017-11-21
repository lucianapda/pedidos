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

  constructor(private http: HttpClient) { }

  ngOnInit() {    
    this.http
    .get('http://localhost:8080/rest/product/all', {
      headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
    })
    .subscribe((response:ProdutoLista) =>{
      this.produtoLista = response.productList;
    });
  }
}
