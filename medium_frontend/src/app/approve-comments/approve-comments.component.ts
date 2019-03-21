import { BlogsService } from './../../services/blogs.service';
import { Blog } from 'src/models/blog.model';
import { CommentsService } from './../../services/comments.service';
import { BlogComment } from 'src/models/blogComment.model';
import { Component, OnInit, HostListener } from '@angular/core';

@Component({
  selector: 'app-approve-comments',
  templateUrl: './approve-comments.component.html',
  styleUrls: ['./approve-comments.component.css']
})
export class ApproveCommentsComponent implements OnInit {

  pendingComments: BlogComment[];
  errMsg = '';

  constructor(private commentService: CommentsService, private blogService: BlogsService) { }

  isEmpty(): boolean {
    if (this.pendingComments.length < 1) {
      return false;
    }
    return true;
  }

  ngOnInit() {
    this.getComments();
  }

  getComments() {
    this.commentService.getAllPendingComments().subscribe( res => {
      this.pendingComments = res;
    }, err => this.errMsg = err);
  }

  deleteComment(comId: string) {
    this.commentService.deleteComment(comId).subscribe( () => this.getComments());
  }

  approveComment(comId: string) {
    this.commentService.approveComment(comId).subscribe( () => {
      this.getComments();
    });
  }

  @HostListener('window:beforeunload')
  clearStorage() {
    localStorage.clear();
  }

}
