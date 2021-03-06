--------------------------------------------------------
--  Constraints for Table BLOG
--------------------------------------------------------

  ALTER TABLE "MEDIUM_FINAL"."BLOG" MODIFY ("TEXT" NOT NULL ENABLE);
  ALTER TABLE "MEDIUM_FINAL"."BLOG" MODIFY ("BLOG_ID" NOT NULL ENABLE);
  ALTER TABLE "MEDIUM_FINAL"."BLOG" MODIFY ("TITLE" NOT NULL ENABLE);
  ALTER TABLE "MEDIUM_FINAL"."BLOG" MODIFY ("date" NOT NULL ENABLE);
  ALTER TABLE "MEDIUM_FINAL"."BLOG" MODIFY ("DOMAIN_DOMAIN_ID" NOT NULL ENABLE);
  ALTER TABLE "MEDIUM_FINAL"."BLOG" ADD CONSTRAINT "BLOG_PK" PRIMARY KEY ("BLOG_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
