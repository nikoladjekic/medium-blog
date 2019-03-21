import { Blog } from './blog.model';
export interface BlogComment {
    commentId: number;
    approved: boolean;
    commentator: string;
    date: string;
    text: string;
    blog: Blog;
}
