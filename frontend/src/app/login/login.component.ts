import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Login} from './Login';
import {Token} from './Token';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {
  
  public login: Login = new Login("","");

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }
  
  entrar(){    
    this.http.post('http://localhost:8080/login', this.login)
    .subscribe((response:Token) => {      
      localStorage.setItem('token', response.token);
      window.location.href = '/home';
    });
  }

}
