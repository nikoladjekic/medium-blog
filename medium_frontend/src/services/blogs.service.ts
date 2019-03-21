import { HttpResponse, HttpHeaders } from '@angular/common/http';
// Service that gets the data related to blogs from the spring rest api
import { Blog } from 'src/models/blog.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BlogsService {
  // backends endpoint for getting blogs from all domens for the initial homepage
  getBlogsForHomeUrl = 'http://localhost:8080/medium/domain/home';
  // backends endpoint for getting blogs based on the blogs id in the database
  getBlogsByDomenUrl = 'http://localhost:8080/medium/blog/domain/';
  // backends endpoint to get one blog by its id
  getBlogByIdUrl = 'http://localhost:8080/medium/blog/';
  // get all blogs that exist
  getAllBlogsUrl = 'http://localhost:8080/medium/blog/list';
  // delete blog url
  deteBlogUrl = 'http://localhost:8080/medium/blog/';
  // get all blogs by one author
  getAllBlogsByOneAuthorUrl = 'http://localhost:8080/medium/blog/user/';
  // url for posting a blog
  submitBlogUrl = 'http://localhost:8080/medium/blog/';
  // main filter
  mainFilterurl = 'http://localhost:8080/medium/blog/search/';
  // data filter
  dateFilterUrl = 'http://localhost:8080/medium/blog/';

  // injecting the HttpClient as a dependency
  constructor(private http: HttpClient) { }

  // method for geting blogs from all domens for the initial homepage
  getBlogsForHome() {
    return this.http.get<Blog[]>(this.getBlogsForHomeUrl);
  }

  // method for getting blogs based on the blogs id in the database
  getBlogsByDomen(value: string): Observable<Blog[]> {
    return this.http.get<Blog[]>(this.getBlogsByDomenUrl + `${value}`);
  }
  // method that gets a specific blog by its id
  getBlogById(value: string): Observable<Blog> {
    return this.http.get<Blog>(this.getBlogByIdUrl + `${value}`);
  }
  // method that gets all the blogs in the database
  getAllBlogs(): Observable<Blog[]> {
    return this.http.get<Blog[]>(this.getAllBlogsUrl);
  }
  // method that delets a blog by its id
  deleteBlog(blogId: string): Observable<HttpResponse<object>> {
    return this.http.delete<HttpResponse<object>>(this.deteBlogUrl + blogId);
  }
  // method that gets all the blogs by one author
  getAllBlogsByOneAuthor(userid): Observable<Blog[]> {
    return this.http.get<Blog[]>(this.getAllBlogsByOneAuthorUrl + userid);
  }
  // method that post the blog provided to it
  postNewBlog(blog: any): Observable<HttpResponse<object>> {
    return this.http.post<HttpResponse<object>>(this.submitBlogUrl, blog, {observe: 'response'});
  }
  // gets all the blogs that align with the search params
  getBlogsBySearch(value: string): Observable<Blog[]> {
    return this.http.get<Blog[]>(this.mainFilterurl + value);
  }
  // gets all the blogs between the dates passed to the method
  getBlogsByDate(dateOne: string, dateTwo: string): Observable<Blog[]> {
    return this.http.get<Blog[]>(this.dateFilterUrl + dateOne + '/dates/' + dateTwo);
  }
}
