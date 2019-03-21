import { EditBloggerComponent } from './edit-blogger/edit-blogger.component';
import { AllBlogsInDomainComponent } from './all-blogs-in-domain/all-blogs-in-domain.component';
import { EditBlogComponent } from './edit-blog/edit-blog.component';
import { AllDomainsComponent } from './all-domains/all-domains.component';
import { ThisAuthorsBlogsComponent } from './this-authors-blogs/this-authors-blogs.component';
import { AddNewUserComponent } from './add-new-user/add-new-user.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { HomePageComponent } from './home-page/home-page.component';
import { BlogDetailsComponent } from './blog-details/blog-details.component';

const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'blog/:id', component: BlogDetailsComponent},
  {path: 'addNewUsers', component: AddNewUserComponent},
  {path: 'author/:id', component: ThisAuthorsBlogsComponent},
  {path: 'allDomains', component: AllDomainsComponent},
  {path: 'editBlog/:id', component: EditBlogComponent},
  {path: 'allBlogsInDomain/:id', component: AllBlogsInDomainComponent},
  {path: 'editBlogger/:id', component: EditBloggerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
