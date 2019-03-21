import { LogcounterService } from './../../services/logcounter.service';
import { UsersService } from './../../services/users.service';
import { UserDetails } from './../../models/userDetails.model';
import { BlogsService } from './../../services/blogs.service';
import { ActivatedRoute } from '@angular/router';
import { Blog } from './../../models/blog.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-this-authors-blogs',
  templateUrl: './this-authors-blogs.component.html',
  styleUrls: ['./this-authors-blogs.component.css']
})
export class ThisAuthorsBlogsComponent implements OnInit {
  usernameId: string;
  myBlogs: Blog[];
  author: UserDetails;

  constructor(private route: ActivatedRoute,
              private blogsService: BlogsService,
              private userService: UsersService) { }

  ngOnInit() {
    this.route.params.subscribe(resp => {
      this.usernameId = resp.id;
    });

    this.userService.getUserDetailsByUsername(this.usernameId).subscribe(resp => this.author = resp);
    this.blogsService.getAllBlogsByOneAuthor(this.usernameId).subscribe(resp => this.myBlogs = resp);
  }

}
