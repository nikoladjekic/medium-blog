import { Blog } from './blog.model';
export interface Keyword {
    keywordId: number;
    word: string;
    blogs: Blog[];
}