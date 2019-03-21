import { Component, OnInit } from '@angular/core';
import { AuthService } from './../../services/auth.service';
import { Router } from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;
  form = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });
  errMsg;

  constructor(private serv: AuthService, private router: Router) { }

  get getUsername() {
    return this.form.get('username');
  }

  get getPassword() {
    return this.form.get('password');
  }

  ngOnInit() {
  }

  takeUsernameValue(value: string) {
    this.username = value;
  }

  takePasswordValue(value: string) {
    this.password = value;
  }

  logInUser() {
    this.serv.logIn(this.username, this.password).subscribe((user) => {
      if (user.role === 'ADMIN') {
        localStorage.setItem('currentUser', JSON.stringify(user));
        const credString = this.username + ':' + this.password;
        const credentials = btoa(credString);
        localStorage.setItem('cred', credentials);
        this.router.navigate(['adminOptions']);
      } else if (user.role === 'EMPLOYEE') {
        localStorage.setItem('currentUser', JSON.stringify(user));
        const credString = this.username + ':' + this.password;
        const credentials = btoa(credString);
        localStorage.setItem('cred', credentials);
        this.router.navigate(['bloggerOptions']);
      }
    }, err => this.errMsg = err
    );
  }

  logOutUser() {
    localStorage.removeItem('currentUser');
    this.serv.logout();
    this.router.navigate(['login']);
  }



}
