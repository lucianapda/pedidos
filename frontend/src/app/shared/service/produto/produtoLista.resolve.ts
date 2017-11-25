import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {ProdutoLista} from '../../../produto-lista/ProdutoLista';


@Injectable()
export class ProdutoListaResolve implements Resolve<any> {
  constructor(
    private http: HttpClient
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ProdutoLista>{
    return  this.http
      .get<ProdutoLista>('http://localhost:8080/rest/product/all', {
        headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
      });
  }
}
