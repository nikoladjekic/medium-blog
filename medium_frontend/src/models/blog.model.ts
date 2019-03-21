import { LogCounter } from './logcounter.model';
import { BlogComment } from './blogComment.model';
import { Keyword } from './keyword.model';
import { UserDetails } from './userDetails.model';
import { Domain } from './domen.model';
import { User } from './user.model';
import { Tag } from './tag.model';
export interface Blog {
    blogId: number;
    date: string;
    subtitle: string;
    text: string;
    title: string;
    picture: string;
    domain: Domain;
    userDetails: UserDetails;
    keywords: Keyword[];
    tags: Tag[];
    comments: BlogComment[];
    logcounters: LogCounter[];
}
