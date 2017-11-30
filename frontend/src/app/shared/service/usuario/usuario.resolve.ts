import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {Usuario} from "../../../usuario/Usuario";


@Injectable()
export class UsuarioResolve implements Resolve<any> {
  constructor(
    private http: HttpClient
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<Usuario>{
    if (route.paramMap.get('usuarioId') == '0'){
      return null;
    }
    let endPoint = "http://localhost:8080/rest/user/{usuarioId}";
    endPoint = endPoint.replace('{usuarioId}', route.paramMap.get('usuarioId'));
    return  this.http
      .get<Usuario>(endPoint, {
        headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
      });
  }
}
