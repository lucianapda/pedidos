import { Component, OnInit } from '@angular/core';
import { NavController, AlertController } from 'ionic-angular';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Pedidos} from './Pedidos';
import {PedidoProduto} from './PedidoProduto';


import {Produto} from './Produto';
import {ProdutoLista} from './ProdutoLista';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage implements OnInit{

  produtoList: Array<Produto> = [];
  codProduto: string = "";
  produtoSelecionadoLista: Array<PedidoProduto> = [];
  pedidos: Pedidos;
  mesa:number;
  quantidadeProduto:number;

  constructor(public navCtrl: NavController, private http: HttpClient, public alertCtrl: AlertController) {

  }

  ngOnInit() {
    this.http
    .get('http://localhost:8080/rest/product/all', {
      headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
    })
    .subscribe((response:ProdutoLista) =>{
      this.produtoList = response.productList;
    });
  }

  verificar(){
    let produtoSelecionado: Produto;
    for (let i = 0; i < this.produtoList.length; i++){
      if (parseInt(this.codProduto) == this.produtoList[i].id){
        produtoSelecionado = this.produtoList[i];
      }
    }
    let alert = this.alertCtrl.create({
      message: 'Valor do produto:' + produtoSelecionado.price + "R$",      
      subTitle: 'Produto:' + produtoSelecionado.name,
      buttons: ['OK']
    });
    alert.present();
  }

  salvar(){    
    this.pedidos = new Pedidos(this.produtoSelecionadoLista, this.mesa, 0);
    console.log(this.pedidos);
  }

  adicionar(){    
    for (let i = 0; i < this.produtoList.length; i++){
      if (parseInt(this.codProduto) == this.produtoList[i].id){
        this.produtoSelecionadoLista.push(new PedidoProduto(this.produtoList[i], 0, this.quantidadeProduto));
      }
    }    
  }

  removeLista(index){
    this.produtoSelecionadoLista.splice(index, 1);
  }
}
