--------------------------------------------------------
--  Ref Constraints for Table COMMENTS
--------------------------------------------------------

  ALTER TABLE "MEDIUM_FINAL"."COMMENTS" ADD CONSTRAINT "COMMENTS_BLOG_FK" FOREIGN KEY ("BLOG_BLOG_ID")
	  REFERENCES "MEDIUM_FINAL"."BLOG" ("BLOG_ID") ON DELETE CASCADE ENABLE;
