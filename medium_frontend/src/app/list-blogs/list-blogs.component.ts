import { Blog } from 'src/models/blog.model';
import { BlogsService } from './../../services/blogs.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-blogs',
  templateUrl: './list-blogs.component.html',
  styleUrls: ['./list-blogs.component.css']
})
export class ListBlogsComponent implements OnInit {

  allBlogs: Blog[];
  deleteString = '';

  constructor(private blogsSerice: BlogsService) { }

  ngOnInit() {
    this.getAllBlogs();
  }

  getAllBlogs() {
    this.blogsSerice.getAllBlogs().subscribe( resp => this.allBlogs = resp );
  }

  deleteBlog(blogId: string) {
    this.blogsSerice.deleteBlog(blogId).subscribe( resp => {
      if (resp.status === 404) {
        this.deleteString = 'The blog was not found';
      } else {
        this.deleteString = 'The blog was deleted';
      }
      this.getAllBlogs();
    });
  }

}
