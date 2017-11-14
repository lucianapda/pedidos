import { Component, OnInit, ViewEncapsulation, ViewContainerRef } from '@angular/core';
import {Produto} from './Produto';
import {ToastsManager} from "ng2-toastr/ng2-toastr";
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

  constructor(public toast: ToastsManager, public vcr:ViewContainerRef, public location: Location) {
    this.toast.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
  }

  salvar(){
    let produtoLista: Array<Produto> = [];
    if (localStorage.getItem('produto')){
      produtoLista = JSON.parse(localStorage.getItem('produto'));
    }
    produtoLista.push(this.produto);
    console.log(produtoLista);
    localStorage.setItem('produto', JSON.stringify(produtoLista));
    this.toast.success("Sucesso", "O produto foi cadastrado");
  }

  cancelar(){
    this.location.back();
  }

  verificaNumeroMaiorPermitido(){
    return this.produto.preco > this.maxNumero;
  }

  verificaNumeroMenorPermitido(){
    return this.produto.preco < this.minNumero;
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
