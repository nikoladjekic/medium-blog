import { Keyword } from './../models/keyword.model';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KeywordsService {
  getAllKeywrodsUrl = 'http://localhost:8080/medium/keyword/list';
  deleteKeywordUrl = 'http://localhost:8080/medium/keyword/';
  addKeywordUrl = 'http://localhost:8080/medium/keyword/';

  constructor(private http: HttpClient) { }

  getAllKeywords(): Observable<Keyword[]> {
    return this.http.get<Keyword[]>(this.getAllKeywrodsUrl);
  }

  deleteKeyword(keywordId: string): Observable<HttpResponse<object>> {
    return this.http.delete<HttpResponse<object>>(this.deleteKeywordUrl + keywordId);
  }

  addKeyword(keyword: Keyword): Observable<HttpResponse<object>> {
    return this.http.post<HttpResponse<object>>(this.addKeywordUrl, keyword );
  }
}
