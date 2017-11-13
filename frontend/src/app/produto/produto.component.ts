import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {Produto} from './Produto';

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

  constructor() { }

  ngOnInit() {
  }

  salvar(){
    console.log(this.produto);
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
