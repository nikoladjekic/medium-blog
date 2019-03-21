import { catchError } from 'rxjs/operators';
import { UserDetails } from './../models/userDetails.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  logInUrl = 'http://localhost:8080/medium/loginUser';
  logOutUrl = 'http://localhost:8080/medium/logout';

  constructor(private http: HttpClient) { }

  logIn(username: string, password: string) {
    const credString = username + ':' + password;
    const credentials = btoa(credString);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + credentials
      })
    };

    return this.http.post<UserDetails>(this.logInUrl, { usersUsername: username }, httpOptions).pipe(catchError(this.handleLoginErr));
  }

  handleLoginErr(err: HttpErrorResponse) {
    return throwError('Bad log in details');
  }

  logout(): Observable<HttpResponse<object>> {
    return this.http.get<HttpResponse<object>>(this.logOutUrl);
  }

}
