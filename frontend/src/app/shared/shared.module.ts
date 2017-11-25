import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdutoListaResolve } from './service/produto/produtoLista.resolve';
import {ProdutoResolve} from './service/produto/produto.resolve';

@NgModule({
  imports: [CommonModule],
  declarations: [],
  exports: [],
  providers: [ProdutoListaResolve, ProdutoResolve]
})
export class SharedModule {}
