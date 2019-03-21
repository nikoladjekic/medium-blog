import { Blog } from './blog.model';
export interface Tag {
    tagId: number;
    tag: string;
    blogs: Blog[];
}
