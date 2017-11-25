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
  encapsulation: ViewEncapsulation.Emulated
})
export class CaixaComponent implements OnInit {

  public mesa:number;
  public pedidos:Pedidos;
  public pagarPedido:Pagar;

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
      console.log(this.pedidos);
    });
  }

  pagar(){
    this.pagarPedido = new Pagar(this.pedidos.mesa, this.pedidos.orderId);
    this.http
    .put('http://localhost:8080/rest/order', this.pagarPedido, {
      headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
    })
    .subscribe((response) =>{      
      this.toast.success("Sucesso", "O produto foi cadastrado");
    });
  }

}
