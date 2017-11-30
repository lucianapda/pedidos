import { Component, OnInit, ViewEncapsulation, ViewContainerRef } from '@angular/core';
import {Produto} from './Produto';
import {ToastsManager} from "ng2-toastr/ng2-toastr";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProdutoComponent implements OnInit {

  public produto: Produto = new Produto(0,"", 0);
  public maxNumero = 1000;
  public minNumero = 0;
  public moneyValue: number = 0;
  private isEditMode: boolean = false;

  constructor(public toast: ToastsManager, public vcr:ViewContainerRef, public location: Location, private http: HttpClient, private route: ActivatedRoute) {
    this.toast.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
    if (this.route.snapshot.data.produtoResolve !== null){
      this.isEditMode = true;
      this.produto = this.route.snapshot.data.produtoResolve;  
    }
  }

  salvar(){
    if (this.isEditMode){
      this.http
        .put('http://localhost:8080/rest/product', this.produto, {
          headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
        })
        .subscribe(() =>{
          this.toast.success("Sucesso", "O produto foi cadastrado");
        },((error)=>{
          this.toast.error(error.error.message);
        }));
    }else{
      this.http
        .post('http://localhost:8080/rest/product', this.produto, {
          headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
        })
        .subscribe(() =>{
          console.log("produto novo");
          this.toast.success("Sucesso", "O produto foi cadastrado");
        },((error)=>{
          this.toast.error(error.error.message);
        }));  
    }
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
