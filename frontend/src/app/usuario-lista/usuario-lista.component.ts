import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Usuario} from "../usuario/Usuario";
import {UsuarioLista} from './UsuarioLista';

@Component({
  selector: 'app-usuario-lista',
  templateUrl: './usuario-lista.component.html',
  styleUrls: ['./usuario-lista.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class UsuarioListaComponent implements OnInit {
  
  public usuarioLista: Array<Usuario> = [];

  constructor(private http: HttpClient) { }

  ngOnInit() {
    if (localStorage.getItem('usuario')){
      this.usuarioLista = JSON.parse(localStorage.getItem('usuario'));
    }
    this.http
    .get('http://localhost:8080/rest/user/all', {
      headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
    })
    .subscribe((response:UsuarioLista) =>{
      this.usuarioLista = response.userList;
    });
  }

}
