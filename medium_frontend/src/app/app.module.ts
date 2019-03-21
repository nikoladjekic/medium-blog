import { HttpConfigInterceptor } from './../interceptors/httpconfig.interceptor';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS, HttpInterceptor } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AdminOptionsComponent } from './admin-options/admin-options.component';
import { BloggerOptionsComponent } from './blogger-options/blogger-options.component';
import { HomePageComponent } from './home-page/home-page.component';
import { CreateDomenComponent } from './create-domen/create-domen.component';
import { ListUsersComponent } from './list-users/list-users.component';
import { AdminOptionsRoutingModule } from './adminOptions-routing.module';
import { ListBlogsComponent } from './list-blogs/list-blogs.component';
import { ApproveCommentsComponent } from './approve-comments/approve-comments.component';
import { WriteNewBlogComponent } from './write-new-blog/write-new-blog.component';
import { MyBlogsComponent } from './my-blogs/my-blogs.component';
import { BloggerOptionsRoutingModule } from './bloggerOptions-routing.moudle';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BlogDetailsComponent } from './blog-details/blog-details.component';
import { AddNewUserComponent } from './add-new-user/add-new-user.component';
import { ThisAuthorsBlogsComponent } from './this-authors-blogs/this-authors-blogs.component';
import { AllDomainsComponent } from './all-domains/all-domains.component';
import { MenageKeywordsTagsComponent } from './menage-keywords-tags/menage-keywords-tags.component';
import { AdminLogCounterComponent } from './admin-log-counter/admin-log-counter.component';
import { EditBlogComponent } from './edit-blog/edit-blog.component';
import { AllBlogsInDomainComponent } from './all-blogs-in-domain/all-blogs-in-domain.component';
import { FooterComponent } from './footer/footer.component';
import { EditBloggerComponent } from './edit-blogger/edit-blogger.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminOptionsComponent,
    BloggerOptionsComponent,
    HomePageComponent,
    CreateDomenComponent,
    ListUsersComponent,
    ListBlogsComponent,
    ApproveCommentsComponent,
    WriteNewBlogComponent,
    MyBlogsComponent,
    BlogDetailsComponent,
    AddNewUserComponent,
    ThisAuthorsBlogsComponent,
    AllDomainsComponent,
    MenageKeywordsTagsComponent,
    AdminLogCounterComponent,
    EditBlogComponent,
    AllBlogsInDomainComponent,
    FooterComponent,
    EditBloggerComponent
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AdminOptionsRoutingModule,
    BloggerOptionsRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: HttpConfigInterceptor, multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule {
  
 }
