import { Blog } from './blog.model';
export interface LogCounter {
    logcounterid: number;
    date: string;
    ipadress: string;
    blog: Blog;
}