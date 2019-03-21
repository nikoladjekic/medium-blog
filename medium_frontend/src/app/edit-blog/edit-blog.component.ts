import { Domain } from './../../models/domen.model';
import { Keyword } from './../../models/keyword.model';
import { ActivatedRoute } from '@angular/router';
import { Blog } from './../../models/blog.model';
import { BlogsService } from './../../services/blogs.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Tag } from 'src/models/tag.model';
import { TagsService } from 'src/services/tags.service';
import { KeywordsService } from 'src/services/keywords.service';
import { DomenService } from 'src/services/domen.service';

@Component({
  selector: 'app-edit-blog',
  templateUrl: './edit-blog.component.html',
  styleUrls: ['./edit-blog.component.css']
})
export class EditBlogComponent implements OnInit {
  thisBlog: Blog;
  thisBlogId: string;
  newBlogForm = new FormGroup({
    title: new FormControl(),
    subtitle: new FormControl(),
    picture: new FormControl(),
    text: new FormControl(),
    domain: new FormControl(),
    keywords: new FormControl(),
    tags: new FormControl()
  });
  postString = '';
  keywordsArr: Array<any> = [];
  tagsArr: Array<any> = [];
  allTags: Tag[];
  allKeywords: Keyword[];
  allDomains: Domain[];


  constructor(private blogsService: BlogsService,
    private route: ActivatedRoute,
    private tagsService: TagsService,
    private keywordsService: KeywordsService,
    private domainService: DomenService) { }

  ngOnInit() {
    this.route.params.subscribe(resp => {
      this.thisBlogId = resp.id;
    });
    this.tagsService.getAllTags().subscribe(res => this.allTags = res);
    this.keywordsService.getAllKeywords().subscribe(res => this.allKeywords = res);
    this.domainService.getAllDomens().subscribe(res => this.allDomains = res);
    this.blogsService.getBlogById(this.thisBlogId).subscribe(resp => {
      this.thisBlog = resp;
      this.newBlogForm.get('title').setValue(this.thisBlog.title);
      this.newBlogForm.get('subtitle').setValue(this.thisBlog.subtitle);
      this.newBlogForm.get('picture').setValue(this.thisBlog.picture);
      this.newBlogForm.get('text').setValue(this.thisBlog.text);
      this.newBlogForm.get('domain').setValue(this.thisBlog.domain);
      this.newBlogForm.get('keywords').setValue(this.thisBlog.keywords);
      this.newBlogForm.get('tags').setValue(this.thisBlog.tags);
    });
  }

  onSubmit() {
    let keyids: number[] = this.newBlogForm.get('keywords').value;
    let tagids: number[] = this.newBlogForm.get('tags').value;


    for (let i = 0; i < keyids.length; i++) {
      let keyword: any = {
        keywordId: keyids[i]
      }
      this.keywordsArr.push(keyword);
    }
    tagids.forEach(elem => {
      let tag: any = {
        tagId: elem
      }
      this.tagsArr.push(tag);
    });

    let blog = {
      blogId: this.thisBlogId,
      domain: { domainId: this.thisBlog.domain.domainId },
      subtitle: this.newBlogForm.get('subtitle').value,
      text: this.newBlogForm.get('text').value,
      title: this.newBlogForm.get('title').value,
      picture: this.newBlogForm.get('picture').value,
      userDetails: {
        usersUsername: this.thisBlog.userDetails.usersUsername,
      },
      keywords: this.keywordsArr,
      tags: this.tagsArr,
    };
    this.blogsService.postNewBlog(blog).subscribe(resp => {
      if (resp.status === 201) {
        this.postString = 'Your blog was posted';
      } else {
        this.postString = 'Something went wrong';
      }
    });
    this.newBlogForm.reset();
  }
}
