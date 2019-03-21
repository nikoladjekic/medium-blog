import { UserDetails } from './../models/userDetails.model';
import { HttpClient, HttpResponse, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  getAllUsersDetailsUrl = 'http://localhost:8080/medium/user/list';
  deleteUserByUsernameUrl = 'http://localhost:8080/medium/user/';
  getUserDetailsByUsernameUrl = 'http://localhost:8080/medium/user/details/';
  addANewUserUrl = 'http://localhost:8080/medium/register/user';
  editUserUrl = 'http://localhost:8080/medium/user/details/';

 
  constructor(private http: HttpClient) { }
 

  getAllUserDetails(): Observable<UserDetails[]> {
     return this.http.get<UserDetails[]>(this.getAllUsersDetailsUrl);
    
  }

  deleteUserByUsername(user: string): Observable<HttpResponse<object>> {
    return this.http.delete<HttpResponse<object>>(this.deleteUserByUsernameUrl + `${user}`);
  }

  getUserDetailsByUsername(username: string): Observable<UserDetails> {
    return this.http.get<UserDetails>(this.getUserDetailsByUsernameUrl + username);
  }

  addANewUser(userAndUserDetailsObj: any): Observable<HttpResponse<object>> {
    return this.http.post<HttpResponse<object>>(this.addANewUserUrl, userAndUserDetailsObj).pipe(catchError(this.handleLoginErr));
  }

  edditUser(userDetailsObj): Observable<HttpResponse<object>> {
    return this.http.put<HttpResponse<object>>(this.editUserUrl, userDetailsObj);
  }

  handleLoginErr(err: HttpErrorResponse) {
    return throwError('Bad user details');
  }
}

