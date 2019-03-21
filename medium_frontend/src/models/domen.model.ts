import { Blog } from './blog.model';
export interface Domain {
    domainId: number;
    name: string;
    picture: string;
    blogs: Blog[];
}
