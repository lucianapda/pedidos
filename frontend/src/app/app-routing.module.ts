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


const routes: Routes = [  
  { path: '', redirectTo:'login', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'usuario/:usuarioId', component: UsuarioComponent },
  { path: 'pedido', component: PedidoComponent },
  { path: 'caixa', component: CaixaComponent },  
  { path: 'produtoLista', component: ProdutoListaComponent },
  { path: 'produto', component: ProdutoComponent },
  { path: 'usuarioLista', component: UsuarioListaComponent }  
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  declarations: []
})
export class AppRoutingModule { }
