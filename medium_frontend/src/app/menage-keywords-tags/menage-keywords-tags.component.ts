import { FormControl } from '@angular/forms';
import { TagsService } from './../../services/tags.service';
import { Keyword } from './../../models/keyword.model';
import { KeywordsService } from './../../services/keywords.service';
import { Component, OnInit } from '@angular/core';
import { Tag } from 'src/models/tag.model';

@Component({
  selector: 'app-menage-keywords-tags',
  templateUrl: './menage-keywords-tags.component.html',
  styleUrls: ['./menage-keywords-tags.component.css']
})
export class MenageKeywordsTagsComponent implements OnInit {
  allKeywords: Keyword[];
  allTags: Tag[];

  addKeywrodForm = new FormControl();
  addTagForm = new FormControl();

  constructor(private keywordsService: KeywordsService,
              private tagsService: TagsService) { }

  ngOnInit() {
    this.getKeywords();
    this.getTags();
  }

  getKeywords() {
    this.keywordsService.getAllKeywords().subscribe( res => this.allKeywords = res);
  }

  getTags() {
    this.tagsService.getAllTags().subscribe( res => this.allTags = res);
  }

  deleteKeyword(keywordId: string) {
    this.keywordsService.deleteKeyword(keywordId).subscribe( () => this.getKeywords());
  }

  addKeyword() {
    let keywordObj: Keyword = {
      keywordId: null,
      word: this.addKeywrodForm.value,
      blogs: null
    }

    this.keywordsService.addKeyword(keywordObj).subscribe( () => this.getKeywords());
    this.addKeywrodForm.reset();
  }

  addTag() {
    let tagObj: Tag = {
      tagId: null,
      tag: this.addTagForm.value,
      blogs: null
    }

    this.tagsService.addTag(tagObj).subscribe( () => this.getTags());
    this.addTagForm.reset();
  }

  deleteTag(tagId: string) {
    this.tagsService.deleteTag(tagId).subscribe( () => this.getTags());
  }
}
