import { AdminLogCounterComponent } from './admin-log-counter/admin-log-counter.component';
import { MenageKeywordsTagsComponent } from './menage-keywords-tags/menage-keywords-tags.component';
import { AddNewUserComponent } from './add-new-user/add-new-user.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdminOptionsComponent } from './admin-options/admin-options.component';
import { CreateDomenComponent } from './create-domen/create-domen.component';
import { ListUsersComponent } from './list-users/list-users.component';
import { ListBlogsComponent } from './list-blogs/list-blogs.component';
import { ApproveCommentsComponent } from './approve-comments/approve-comments.component';

const adminOptionsModuleRoutes: Routes = [
    {
        path: 'adminOptions',
        component: AdminOptionsComponent,
        children: [
            {
                path: 'createDomen',
                component: CreateDomenComponent
            },
            {
                path: 'listUsers',
                component: ListUsersComponent
            },
            {
                path: 'listBlogs',
                component: ListBlogsComponent
            },
            {
                path: 'approveComments',
                component: ApproveCommentsComponent
            },
            {
                path: 'menageKeywordsAndTags',
                component: MenageKeywordsTagsComponent
            },
            {
                path: 'adminLogCounter',
                component: AdminLogCounterComponent
            }
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(adminOptionsModuleRoutes)
    ],
    declarations: [],
    exports: [
        RouterModule
    ]
})
export class AdminOptionsRoutingModule { }