import { Component, OnInit, ViewEncapsulation, ViewContainerRef } from '@angular/core';
import {Usuario} from "./Usuario"
import {TipoUsuario} from "./TipoUsuario";
import {ToastsManager} from "ng2-toastr/ng2-toastr";
import { Location } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute  } from '@angular/router';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class UsuarioComponent implements OnInit {

  public usuario: Usuario = new Usuario("","","","");
  public listaTipoUsuario: Array<TipoUsuario> = [];
  public isEditModel: boolean = false;

  constructor(public route: ActivatedRoute , public toast: ToastsManager, public vcr:ViewContainerRef, public location: Location, private http: HttpClient) {
    this.toast.setRootViewContainerRef(vcr);
  }

  ngOnInit() {    
    if (this.route.snapshot.data.usuarioResolve == null){
      this.isEditModel = true;
    }else{
      this.usuario =  this.route.snapshot.data.usuarioResolve;
    }   
    this.listaTipoUsuario.push(new TipoUsuario("ADMIN", "Administrador"));
    this.listaTipoUsuario.push(new TipoUsuario("WAITER", "Caixa"));
    this.listaTipoUsuario.push(new TipoUsuario("ATTENDANT_BOX", "Atendente"));
    this.listaTipoUsuario.push(new TipoUsuario("COOK", "Cozinheiro"));
  }
  
  salvar(){    
    if (this.isEditModel){
      this.http
      .put('http://localhost:8080/rest/user', this.usuario, {
        headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
      })
      .subscribe(() =>{
        this.toast.success("Sucesso", "O produto foi cadastrado");
      },((error)=>{
        this.toast.error(error.error.message);
      }));
    }else{
      this.http
      .post('http://localhost:8080/rest/user', this.usuario, {
        headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
      })
      .subscribe((response) =>{
        console.log(response);
        this.toast.success("Sucesso", "O produto foi cadastrado");
      }, ((error)=>{
        this.toast.error(error.error.message);
      }));
    }    
  }

  cancelar(){
    this.location.back();
  }

}
