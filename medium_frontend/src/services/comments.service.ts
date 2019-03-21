import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { BlogComment } from 'src/models/blogComment.model';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {
  getAllPendingCommentsUrl = 'http://localhost:8080/medium/comment/admin/';
  deleteCommentUrl = 'http://localhost:8080/medium/comment/admin/';
  approveCommentUrl = 'http://localhost:8080/medium/comment/admin/approve/';
  getCommentsForSpecificBlogUrl = 'http://localhost:8080/medium/comment/blog/';
  postCommentUrl = 'http://localhost:8080/medium/comment/';

  constructor(private http: HttpClient) { }

  getAllPendingComments(): Observable<BlogComment[]> {
    return this.http.get<BlogComment[]>(this.getAllPendingCommentsUrl).pipe(catchError(this.hendleCommentErr));
  }

  deleteComment(value: string): Observable<HttpResponse<object>> {
    return this.http.delete<HttpResponse<object>>(this.deleteCommentUrl + value);
  }

  approveComment(value: string): Observable<HttpResponse<object>> {
    return this.http.get<HttpResponse<object>>(this.approveCommentUrl + value);
  }

  getCommentsForSpecificBlog(blogId: string): Observable<BlogComment[]> {
    return this.http.get<BlogComment[]>(this.getCommentsForSpecificBlogUrl + blogId);
  }

  postComment(comment: BlogComment): Observable<HttpResponse<object>> {
    return this.http.post<HttpResponse<object>>(this.postCommentUrl, comment);
  }

  hendleCommentErr() {
    return throwError('No more comments to approve');
  }
}
