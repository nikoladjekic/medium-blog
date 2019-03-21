import { UserDetails } from './../../models/userDetails.model';
import { Blog } from './../../models/blog.model';
import { BlogsService } from './../../services/blogs.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-blogs',
  templateUrl: './my-blogs.component.html',
  styleUrls: ['./my-blogs.component.css']
})
export class MyBlogsComponent implements OnInit {
  allMyBlogs: Blog[];
  deleteString = '';

  constructor(private blogsService: BlogsService) { }

  ngOnInit() {
    this.getAllMyBlogs();
  }

  getAllMyBlogs() {
    let user: UserDetails = JSON.parse(localStorage.getItem('currentUser'));
    this.blogsService.getAllBlogsByOneAuthor(user.usersUsername).subscribe(res => this.allMyBlogs = res);
  }

  deleteMyBlog(blogId: string) {
    this.blogsService.deleteBlog(blogId).subscribe( res => {
      if (res.status === 400) {
        this.deleteString = 'Not Found';
      } else {
        this.deleteString = 'You deleted your blog';
      }
      this.getAllMyBlogs();
    });
  }

}
