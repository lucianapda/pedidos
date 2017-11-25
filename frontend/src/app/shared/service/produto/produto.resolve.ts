import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {Produto} from '../../../produto/Produto';


@Injectable()
export class ProdutoResolve implements Resolve<any> {
  constructor(
    private http: HttpClient
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<Produto>{
    if (route.paramMap.get('produtoId') == '0'){
      return null;
    }
    let endPoint = "http://localhost:8080/rest/product/{produtoId}";
    endPoint = endPoint.replace('{produtoId}', route.paramMap.get('produtoId'));
    return  this.http
      .get<Produto>(endPoint, {
        headers: new HttpHeaders().set('authorization', 'Bearer ' + localStorage.getItem('token')),
      });
  }
}
