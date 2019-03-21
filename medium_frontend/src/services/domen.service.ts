// service for getting data about domens from the spring backend
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Domain } from 'src/models/domen.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DomenService {
  // routes to backed endpoints
  getAllDomensUrl = 'http://localhost:8080/medium/domain/list';
  addANewDomenUrl = 'http://localhost:8080/medium/domain/';
  removeADomenUrl = '';
  getDomainByIdUrl = 'http://localhost:8080/medium/domain/';

  // injecting the http client as a dependency
  constructor(private http: HttpClient) { }

  // method for getting all the existing domes from the backend
  getAllDomens(): Observable<Domain[]> {
    return this.http.get<Domain[]>(this.getAllDomensUrl);
  }

  // method for adding a new domen
  addANewDomen(domen: Domain): Observable<HttpResponse<object>> {
    return this.http.post<HttpResponse<object>>(this.addANewDomenUrl, domen, {observe: 'response'});
  }

  // method for removing a domen
  removeADomen(domen: Domain) {
    return this.http.delete(this.removeADomenUrl + `/${domen.domainId}`);
  }

  getDomainById(value: string): Observable<Domain> {
    return this.http.get<Domain>(this.getDomainByIdUrl + value);
  }
}
