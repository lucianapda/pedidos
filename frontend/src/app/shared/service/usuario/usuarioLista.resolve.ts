import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {UsuarioLista} from "../../../usuario-lista/UsuarioLista";


@Injectable()
export class UsuarioListaResolve implements Resolve<any> {
  constructor(
    private http: HttpClient
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<UsuarioLista>{
    return  this.http
      .get<UsuarioLista>('http://localhost:8080/rest/user/all', {
        headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
      });
  }
}
