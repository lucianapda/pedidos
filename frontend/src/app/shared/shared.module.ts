import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdutoListaResolve } from './service/produto/produtoLista.resolve';
import {ProdutoResolve} from './service/produto/produto.resolve';
import {UsuarioListaResolve} from './service/usuario/usuarioLista.resolve';
import {UsuarioResolve} from './service/usuario/usuario.resolve';

@NgModule({
  imports: [CommonModule],
  declarations: [],
  exports: [],
  providers: [ProdutoListaResolve, ProdutoResolve, UsuarioListaResolve, UsuarioResolve]
})
export class SharedModule {}
