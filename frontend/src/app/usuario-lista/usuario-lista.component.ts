import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Usuario} from "../usuario/Usuario";
import {UsuarioLista} from './UsuarioLista';
import { Location } from '@angular/common';

@Component({
  selector: 'app-usuario-lista',
  templateUrl: './usuario-lista.component.html',
  styleUrls: ['./usuario-lista.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class UsuarioListaComponent implements OnInit {
  
  public usuarioListaCarregada: Array<Usuario> = [];
  public usuarioLista: Array<Usuario> = [];  
  public quantidadeRegistro: number = 0;
  public registroCarregado:number = 5;
  public pagina:number = 0;

  constructor(private http: HttpClient, public location: Location) { }

  ngOnInit() {    
    if (!localStorage.getItem('token')){      
      window.location.href = '/login';
    }else{
      this.http
      .get('http://localhost:8080/rest/user/all', {
        headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
      })
      .subscribe((response:UsuarioLista) =>{
        this.usuarioListaCarregada = response.userList;
        this.quantidadeRegistro = this.usuarioListaCarregada.length;      
        this.carregaUsuario();
      });
    }    
  }

  buscaTipoUsuario(tipo){
    switch (tipo){
      case 'ADMIN': return 'Administrador';
      case 'WAITER': return 'Caixa';
      case 'ATTENDANT_BOX': return 'Atendente';
      case 'COOK': return 'Cozinheiro';
    }
  }

  carregaUsuario(){
    this.usuarioLista = [];
    for (let i = (this.pagina * this.registroCarregado); i < (this.pagina * this.registroCarregado) + this.registroCarregado; i++){
      if (i >= this.usuarioListaCarregada.length){
        break;
      }
      this.usuarioLista.push(this.usuarioListaCarregada[i]);
    }
  }

  paginar($event:any){    
    this.pagina = $event - 1;
    this.carregaUsuario();
  }

}
