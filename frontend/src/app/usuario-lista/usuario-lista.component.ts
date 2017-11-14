import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {Usuario} from "../usuario/Usuario";

@Component({
  selector: 'app-usuario-lista',
  templateUrl: './usuario-lista.component.html',
  styleUrls: ['./usuario-lista.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class UsuarioListaComponent implements OnInit {
  
  public usuarioLista: Array<Usuario> = [];

  constructor() { }

  ngOnInit() {
    if (localStorage.getItem('usuario')){
      this.usuarioLista = JSON.parse(localStorage.getItem('usuario'));
    }
  }

}
