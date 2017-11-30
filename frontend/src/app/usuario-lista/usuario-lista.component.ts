import { Component, OnInit, ViewEncapsulation, ViewContainerRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Usuario} from "../usuario/Usuario";
import {UsuarioLista} from './UsuarioLista';
import { Location } from '@angular/common';
import {ToastsManager} from "ng2-toastr/ng2-toastr";
import { HttpClient, HttpHeaders } from '@angular/common/http';

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

  constructor(private route: ActivatedRoute, public location: Location, private http: HttpClient, public toast: ToastsManager, public vcr:ViewContainerRef) { 
    this.toast.setRootViewContainerRef(vcr);
  }

  ngOnInit() {    
    if (!localStorage.getItem('token')){      
      window.location.href = '/login';
    }else{
      this.usuarioListaCarregada = this.route.snapshot.data.usuarioListaResolve.userList || [];
        this.quantidadeRegistro = this.usuarioListaCarregada.length;      
        this.carregaUsuario();      
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

  removerUsuario(usuarioId){    
      let endPoint = "http://localhost:8080/rest/user/{usuarioId}";
      endPoint = endPoint.replace('{usuarioId}', usuarioId);
      this.http
      .delete(endPoint, {
        headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
      })
      .subscribe((response) =>{                        
        this.toast.success("Sucesso", "O usuario foi removido");
        this.removeUsuarioLista(usuarioId);
      },((error)=>{
        this.toast.error(error.error.message);
      }));    
  }

  paginar($event:any){    
    this.pagina = $event - 1;
    this.carregaUsuario();
  }

  private removeUsuarioLista(usuarioId){
    for (let i = 0; i < this.usuarioListaCarregada.length; i++){
      let usuario:Usuario = this.usuarioListaCarregada[i];
      if (usuarioId == usuario.id){        
        this.usuarioListaCarregada.splice(1, i);        
      }
    }
    for (let y = 0; y < this.usuarioLista.length; y++){
      let usuario:Usuario = this.usuarioLista[y];
      if (usuarioId == usuario.id){        
        this.usuarioLista.splice(1, y);        
      }
    }
  }

}
