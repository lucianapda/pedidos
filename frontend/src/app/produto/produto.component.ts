import { Component, OnInit, ViewEncapsulation, ViewContainerRef } from '@angular/core';
import {Produto} from './Produto';
import {ToastsManager} from "ng2-toastr/ng2-toastr";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Location } from '@angular/common';

@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProdutoComponent implements OnInit {

  public produto: Produto = new Produto("", 0);
  public maxNumero = 1000;
  public minNumero = 0;

  constructor(public toast: ToastsManager, public vcr:ViewContainerRef, public location: Location, private http: HttpClient) {
    this.toast.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
  }

  salvar(){
    this.http
    .post('http://localhost:8080/rest/product', this.produto, {
      headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
    })
    .subscribe((response) =>{      
      this.toast.success("Sucesso", "O produto foi cadastrado");
    },((error)=>{
      this.toast.error(error.error.message);
    }));
  }

  cancelar(){
    this.location.back();
  }

  verificaNumeroMaiorPermitido(){
    return this.produto.price > this.maxNumero;
  }

  verificaNumeroMenorPermitido(){
    return this.produto.price < this.minNumero;
  }

  verificaErro(componente){
    if (componente.required){
      return componente.required && (componente.touched || componente.dirty);
    }
    return false;
  }

  eValidoFormulario(form){
    return form.invalid || this.verificaNumeroMenorPermitido() || this.verificaNumeroMaiorPermitido();
  }
}
