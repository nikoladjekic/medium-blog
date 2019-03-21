import { Domain } from './../../models/domen.model';
import { DomenService } from './../../services/domen.service';
import { Blog } from 'src/models/blog.model';
import { BlogsService } from './../../services/blogs.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-all-blogs-in-domain',
  templateUrl: './all-blogs-in-domain.component.html',
  styleUrls: ['./all-blogs-in-domain.component.css']
})
export class AllBlogsInDomainComponent implements OnInit {
  domainId: string;
  blogs: Blog[];
  domain: Domain;

  constructor(private route: ActivatedRoute,
              private blogsService: BlogsService,
              private domainService: DomenService) { }

  ngOnInit() {
    this.route.params.subscribe(resp => {
      this.domainId = resp.id;
    });

    this.blogsService.getBlogsByDomen(this.domainId).subscribe( res => this.blogs = res);
    this.domainService.getDomainById(this.domainId).subscribe(res => this.domain = res);
  }

}
