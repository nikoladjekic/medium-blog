import { LogCounter } from './../models/logcounter.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LogcounterService {

  viewCounterUrl = 'http://localhost:8080/medium/logcounter/';
  getAllLogsUrl = 'http://localhost:8080/medium/logcounter/list';
  getNumberOfViewsUrl = 'http://localhost:8080/medium/logcounter/blog/count/';

  constructor(private http: HttpClient) { }

  addView(value: any) {
    return this.http.post(this.viewCounterUrl, value);
  }

  getAllLogs(): Observable<LogCounter[]> {
    return this.http.get<LogCounter[]>(this.getAllLogsUrl);
  }

  getNumberOfViews(blogId: string): Observable<number> {
    return this.http.get<number>(this.getNumberOfViewsUrl + blogId);
  }

}
