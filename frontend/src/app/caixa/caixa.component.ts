import { Component, OnInit, ViewEncapsulation, ViewContainerRef } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Location } from '@angular/common';
import {ToastsManager} from "ng2-toastr/ng2-toastr";
import {Pedidos} from "./Pedidos";
import {Pagar} from "./Pagar";

@Component({
  selector: 'app-caixa',
  templateUrl: './caixa.component.html',
  styleUrls: ['./caixa.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class CaixaComponent implements OnInit {

  public mesa:number;
  public pedidos:Pedidos = null;
  public pagarPedido:Pagar;
  public valorTotal:number = 0;

  constructor(private http: HttpClient, public toast: ToastsManager, public vcr:ViewContainerRef, public location: Location,) { 
    this.toast.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
  }

  buscaPedido(){
    let endPoint = 'http://localhost:8080/rest/order/' + this.mesa;
    this.http
    .get(endPoint, {
      headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
    })
    .subscribe((response:Pedidos) =>{
      this.pedidos = response;
      for (let i = 0; i < this.pedidos.orderArray.length;i++){
        let valor = this.pedidos.orderArray[i].produto.price;
        let quantidade = this.pedidos.orderArray[i].quantidade;
        this.valorTotal +=  valor * quantidade; 
      }
    },((error)=>{
      this.toast.error(error.error.message);
    }));
  }

  pagar(){
    this.pagarPedido = new Pagar(this.pedidos.mesa, this.pedidos.orderId);
    this.http
    .put('http://localhost:8080/rest/order', this.pagarPedido, {
      headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
    })
    .subscribe((response) =>{      
      this.toast.success("Sucesso", "O produto foi cadastrado");
      this.pedidos = null;
    });
  }

}
