import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {ToastModule} from "ng2-toastr/ng2-toastr";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './/app-routing.module';
import { RouterModule } from '@angular/router';
import { CaixaComponent } from './caixa/caixa.component';
import { PedidoComponent } from './pedido/pedido.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { LoginComponent } from './login/login.component';
import { ProdutoComponent } from './produto/produto.component';
import { ProdutoListaComponent } from './produto-lista/produto-lista.component';
import { UsuarioListaComponent } from './usuario-lista/usuario-lista.component';
import { KzPaginacaoComponent} from './paginacao/paginacao';
// import { PedidoListaComponent } from './pedido-lista/pedido-lista.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CaixaComponent,
    PedidoComponent,
    UsuarioComponent,
    LoginComponent,
    ProdutoComponent,
    ProdutoListaComponent,
    UsuarioListaComponent,
    KzPaginacaoComponent
    // PedidoListaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ToastModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
