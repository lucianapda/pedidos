import { Component } from '@angular/core';
import { NavController} from 'ionic-angular';
import { HttpClient } from '@angular/common/http';
import {LoginDTO} from './LoginDTO';
import {Token} from './Token';
import {HomePage} from '../home/home';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {

  public login: LoginDTO = new LoginDTO("","");

  constructor(public navCtrl: NavController, private http: HttpClient) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad LoginPage');
  }

  entrar(){
    this.http.post('http://192.168.43.129:8080/login', this.login)
    .subscribe((response:Token) => {      
      localStorage.setItem('token', response.token);
      this.navCtrl.push(HomePage);
    });
  }

}
