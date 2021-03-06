OPTIONS (ERRORS=50)
LOAD DATA 
INFILE 'C:\Users\nikola.djekic\Desktop\Medium\COMMENTS_DATA_TABLE.ldr' "str '{EOL}'"
APPEND
CONTINUEIF NEXT(1:1) = '#'
INTO TABLE "MEDIUM_FINAL"."COMMENTS"
FIELDS TERMINATED BY'|'
OPTIONALLY ENCLOSED BY '"' AND '"'
TRAILING NULLCOLS ( 
"COMMENT_ID" ,
L_0 FILLER char,
"TEXT" LOBFILE( L_0) TERMINATED BY EOF NULLIF L_0 = 'null',
"date" DATE "YYYY-MM-DD HH24:MI:SS" ,
"APPROVED" ,
"COMMENTATOR" CHAR (64),
"BLOG_BLOG_ID" )
