import { BlogsService } from './../../services/blogs.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Domain } from 'src/models/domen.model';
import { DomenService } from 'src/services/domen.service';
import { KeywordsService } from './../../services/keywords.service';
import { TagsService } from './../../services/tags.service';
import { Keyword } from './../../models/keyword.model';
import { Component, OnInit } from '@angular/core';
import { Tag } from 'src/models/tag.model';

@Component({
  selector: 'app-write-new-blog',
  templateUrl: './write-new-blog.component.html',
  styleUrls: ['./write-new-blog.component.css']
})
export class WriteNewBlogComponent implements OnInit {
  allTags: Tag[];
  allKeywords: Keyword[];
  allDomains: Domain[];
  newBlogForm = new FormGroup({
    title: new FormControl('', Validators.required),
    subtitle: new FormControl('', Validators.required),
    picture: new FormControl('', Validators.required),
    text: new FormControl('', Validators.required),
    domain: new FormControl('', Validators.required),
    keywords: new FormControl('', Validators.required),
    tags: new FormControl('', Validators.required)
  });
  postString = '';
  keywordsArr: Array<any> = [];
  tagsArr: Array<any> = [];

  constructor(private blogsService: BlogsService,
    private tagsService: TagsService,
    private keywordsService: KeywordsService,
    private domainService: DomenService) { }

  newUserErrorMsgs = {
    title: [
      { type: 'required', message: 'Title is required' }
    ],
    subtitle: [
      { type: 'required', message: 'Subtitle is required' }
    ],
    picture: [
      { type: 'required', message: 'Picture url is required' }
    ],
    domain: [
      { type: 'required', message: 'Domain is required' }
    ],
    keywords: [
      { type: 'required', message: 'Keywords are required' }
    ],
    tag: [
      { type: 'required', message: 'Tags are required' }
    ],
    text: [
      { type: 'required', message: 'Text is required' }
    ],
  };

  ngOnInit() {
    this.tagsService.getAllTags().subscribe(res => this.allTags = res);
    this.keywordsService.getAllKeywords().subscribe(res => this.allKeywords = res);
    this.domainService.getAllDomens().subscribe(res => this.allDomains = res);
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
      subtitle: this.newBlogForm.get('subtitle').value,
      text: this.newBlogForm.get('text').value,
      title: this.newBlogForm.get('title').value,
      picture: this.newBlogForm.get('picture').value,
      domain: {
        domainId: this.newBlogForm.get('domain').value
      },
      userDetails: {
        usersUsername: JSON.parse(localStorage.getItem('currentUser')).usersUsername,
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
