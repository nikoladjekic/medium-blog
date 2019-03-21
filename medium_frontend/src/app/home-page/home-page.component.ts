import { LogcounterService } from './../../services/logcounter.service';
import { FormControl } from '@angular/forms';
import { KeywordsService } from './../../services/keywords.service';
// This component displays the navigation bar that is dynamically filled with the domens existing in the database which is proided
// the the spring backend. The component also renders the blogs based on the domen that is selected in the navbar
import { BlogsService } from './../../services/blogs.service';
import { DomenService } from './../../services/domen.service';
import { Component, OnInit, OnChanges } from '@angular/core';
import { Blog } from 'src/models/blog.model';
import { Domain } from './../../models/domen.model';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  // list of all domens that exist in the database provided by spring
  domenList: Domain[];
  // a list of blogs to be displayed
  blogList: Blog[];
  // array of number for the navbar
  arrayNum = [1, 2, 3, 4];
  // header img
  headerImg = 'https://s3.amazonaws.com/coursestorm/live/media/5ed4812c185011e99f2d0ae8dd811046';
  headerDefault = 'https://s3.amazonaws.com/coursestorm/live/media/5ed4812c185011e99f2d0ae8dd811046';
  // header title
  headerTitleDefault = 'WELCOME';
  headerTitle = 'WELCOME';
  // main filter form control
  mainFilter = new FormControl('');
  fromDate = new FormControl();
  toDate = new FormControl();

  // dependency injection of the services this component needs
  constructor(private domenService: DomenService,
              private blogsService: BlogsService) { }

  // on the initialisation of the component all the domens from the database are retrieved to fill the nav bar
  // and a list of all the newest blogs from each domen
  ngOnInit() {
    this.domenService.getAllDomens().subscribe(responce => this.domenList = responce);
    this.blogsService.getBlogsForHome().subscribe(responce => this.blogList = responce);
  }

  // gets all the blogs based on the selected domen from the navbar
  getBlogsForSpecificDomen(value: string) {
    this.blogsService.getBlogsByDomen(value).subscribe(responce => this.blogList = responce);
  }

  getHome() {
    this.blogsService.getBlogsForHome().subscribe(responce => this.blogList = responce);
  }

  setHeaderImg(value: string) {
    this.headerImg = value;
  }

  setHeaderDeffoult() {
    this.headerImg = this.headerDefault;
  }

  setTitle(value: string) {
    this.headerTitle = value;
  }

  setTitleDefault() {
    this.headerTitle = this.headerTitleDefault;
  }

  mainSearch() {
    this.blogsService.getBlogsBySearch(this.mainFilter.value).subscribe(res => this.blogList = res);
  }
  
  dateSearch() {
    this.blogsService.getBlogsByDate(this.fromDate.value, this.toDate.value).subscribe(res => this.blogList = res);
  }


}
