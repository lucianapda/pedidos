import { Component, OnInit, ViewEncapsulation, ViewContainerRef } from '@angular/core';
import {Usuario} from "./Usuario"
import {TipoUsuario} from "./TipoUsuario";
import {ToastsManager} from "ng2-toastr/ng2-toastr";
import { Location } from '@angular/common';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class UsuarioComponent implements OnInit {

  public usuario: Usuario = new Usuario("","","","");
  public listaTipoUsuario: Array<TipoUsuario> = [];

  constructor(public toast: ToastsManager, public vcr:ViewContainerRef, public location: Location) {
    this.toast.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
    this.listaTipoUsuario.push(new TipoUsuario("ADMIN", "Administrador"));
    this.listaTipoUsuario.push(new TipoUsuario("CAIXA", "Caixa"));
    this.listaTipoUsuario.push(new TipoUsuario("ATENDENTE", "Atendente"));
    this.listaTipoUsuario.push(new TipoUsuario("COZINHA", "Cozinheiro"));
  }
  
  salvar(){
    let usuarioLista: Array<Usuario> = [];
    if (localStorage.getItem('usuario')){
      usuarioLista = JSON.parse(localStorage.getItem('usuario'));
    }
    usuarioLista.push(this.usuario);
    console.log(usuarioLista);
    localStorage.setItem('usuario', JSON.stringify(usuarioLista));
    this.toast.success("Sucesso", "O produto foi cadastrado");
  }

  cancelar(){
    this.location.back();
  }

}
