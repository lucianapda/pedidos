import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {Usuario} from "./Usuario"
import {TipoUsuario} from "./TipoUsuario";

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class UsuarioComponent implements OnInit {

  public usuario: Usuario = null;
  public listaTipoUsuario: Array<TipoUsuario> = [];

  constructor() {
    this.usuario = new Usuario("","","","");
  }

  ngOnInit() {
    this.listaTipoUsuario.push(new TipoUsuario("ADMIN", "Administrador"))
    this.listaTipoUsuario.push(new TipoUsuario("CAIXA", "Caixa"))
    this.listaTipoUsuario.push(new TipoUsuario("ATENDENTE", "Atendente"))
    this.listaTipoUsuario.push(new TipoUsuario("COZINHA", "Cozinheiro"))
  }
  
  salvar(){
    console.log(this.usuario);
  }

}
