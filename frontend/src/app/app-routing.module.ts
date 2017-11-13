import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HomeComponent} from './home/home.component';
import { RouterModule, Routes } from '@angular/router';
import {CaixaComponent} from './caixa/caixa.component';
import {PedidoComponent} from './pedido/pedido.component';
import {UsuarioComponent} from './usuario/usuario.component';
import {LoginComponent} from './login/login.component';
import {ProdutoComponent} from './produto/produto.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'usuario', component: UsuarioComponent},
  {path: 'pedido', component: PedidoComponent},
  {path: 'caixa', component: CaixaComponent},
  {path: 'login', component: LoginComponent},
  {path: 'produto', component: ProdutoComponent}
]

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  declarations: []
})
export class AppRoutingModule { }
