import { LogcounterService } from './../../services/logcounter.service';
import { BlogComment } from 'src/models/blogComment.model';
import { CommentsService } from './../../services/comments.service';
import { BlogsService } from './../../services/blogs.service';
import { ActivatedRoute } from '@angular/router';
import { Blog } from 'src/models/blog.model';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-blog-details',
  templateUrl: './blog-details.component.html',
  styleUrls: ['./blog-details.component.css']
})
export class BlogDetailsComponent implements OnInit {
  id: string;
  ourBlog: Blog;
  comments: BlogComment[];
  commentForm = new FormGroup({
    commentator: new FormControl(),
    text: new FormControl('', Validators.required)
  });
  res: any;
  postCommentString = '';

  constructor(private route: ActivatedRoute,
              private blogsService: BlogsService,
              private commentService: CommentsService,
              private logCounterService: LogcounterService) { }

  ngOnInit() {
    this.route.params.subscribe(resp => {
      this.id = resp.id;
    });

    this.blogsService.getBlogById(this.id).subscribe(resp => {
      this.ourBlog = resp;
    });

    this.commentService.getCommentsForSpecificBlog(this.id).subscribe(resp => this.comments = resp);

    this.logCounterService.addView({blog: {blogId: this.id}}).subscribe();
  }

  onSubmit() {
    let commentObj: any = {
      blog: {
        blogId: this.ourBlog.blogId,
      },
      commentator: this.commentForm.get('commentator').value,
      text: this.commentForm.get('text').value
    }
    this.commentService.postComment(commentObj).subscribe(
      res => {
        this.res = res;
        this.commentForm.reset();
      },
      err => {
        if (err) {
          this.postCommentString = 'Something went wrong, please try again.';
        }
      });
  }
}
