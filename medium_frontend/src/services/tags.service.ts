import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tag } from 'src/models/tag.model';

@Injectable({
  providedIn: 'root'
})
export class TagsService {
  getAllTagsUrl = 'http://localhost:8080/medium/tag/list';
  addtagUrl = 'http://localhost:8080/medium/tag/';
  deleteTagUrl = 'http://localhost:8080/medium/tag/';

  constructor(private http: HttpClient) { }

  getAllTags(): Observable<Tag[]> {
    return this.http.get<Tag[]>(this.getAllTagsUrl);
  }

  addTag(tag: Tag): Observable<HttpResponse<object>> {
    return this.http.post<HttpResponse<object>>(this.addtagUrl, tag);
  }

  deleteTag(tagId: string): Observable<HttpResponse<object>> {
    return this.http.delete<HttpResponse<object>>(this.deleteTagUrl + tagId);
  }
}
