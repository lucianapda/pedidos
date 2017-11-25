import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { RouterModule, Routes } from '@angular/router';
import { CaixaComponent } from './caixa/caixa.component';
import { PedidoComponent } from './pedido/pedido.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { LoginComponent } from './login/login.component';
import { ProdutoComponent } from './produto/produto.component';
import { ProdutoListaComponent } from './produto-lista/produto-lista.component';
import { UsuarioListaComponent } from './usuario-lista/usuario-lista.component';
import {ProdutoListaResolve} from "./shared/service/produto/produtoLista.resolve";
import {ProdutoResolve} from './shared/service/produto/produto.resolve';


const routes: Routes = [  
  { path: '', redirectTo:'login', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'produtoLista', component: ProdutoListaComponent,resolve: {
    produtoListaResolve: ProdutoListaResolve
  } },
  { path: 'produto/:produtoId', component: ProdutoComponent,resolve: {
    produtoResolve: ProdutoResolve
  } },
  { path: 'usuarioLista', component: UsuarioListaComponent },
  { path: 'usuario/:usuarioId', component: UsuarioComponent },
  { path: 'pedido', component: PedidoComponent },
  { path: 'caixa', component: CaixaComponent }  
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  declarations: []
})
export class AppRoutingModule { }
