import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { BloggerOptionsComponent } from './blogger-options/blogger-options.component';
import { WriteNewBlogComponent } from './write-new-blog/write-new-blog.component';
import { MyBlogsComponent } from './my-blogs/my-blogs.component';


const bloggerOptionsModuleRoutes: Routes = [
    {
        path: 'bloggerOptions',
        component: BloggerOptionsComponent,
        children: [
            {
                path: 'bloggerOptions/writeNewBlog',
                component: WriteNewBlogComponent
            },
            {
                path: 'bloggerOptions/myBlogs',
                component: MyBlogsComponent
            }
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(bloggerOptionsModuleRoutes)
    ],
    declarations: [],
    exports: [
        RouterModule
    ]
})
export class BloggerOptionsRoutingModule { }
