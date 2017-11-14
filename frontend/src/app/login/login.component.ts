import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {Login} from './Login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {
  
  public login: Login = new Login("","");

  constructor() { }

  ngOnInit() {
  }
  
  entrar(){
    console.log(this.login);
  }

}
