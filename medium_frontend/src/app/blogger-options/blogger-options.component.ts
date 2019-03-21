import { AuthService } from './../../services/auth.service';
import { Blog } from './../../models/blog.model';
import { BlogsService } from './../../services/blogs.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-blogger-options',
  templateUrl: './blogger-options.component.html',
  styleUrls: ['./blogger-options.component.css']
})
export class BloggerOptionsComponent implements OnInit {
  blogger = JSON.parse(localStorage.getItem('currentUser'));

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {

  }

  logout() {
    this.authService.logout().subscribe(() => {
      localStorage.removeItem('currentUser');
      localStorage.removeItem('cred');
    });
    this.router.navigate(['login']);
  }

}
