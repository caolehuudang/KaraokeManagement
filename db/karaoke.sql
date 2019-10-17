
CREATE TABLE "T_VIP" 
(
	"PK_ID" NUMBER(10,0) NOT NULL,
	"LEVEL_USER" VARCHAR2(50)
);
ALTER TABLE "T_VIP" ADD CONSTRAINT "PK_T_VIP" PRIMARY KEY ("PK_ID") ENABLE;


CREATE TABLE "T_USER"
(
	"PK_ID" NUMBER(10,0) NOT NULL,
	"USER_NAME" VARCHAR2(50) NOT NULL,
	"PASSWORD" VARCHAR2(100) NOT NULL,
	"EMAIL" VARCHAR2(50),
	"FULLNAME" VARCHAR2(100),
	"IMAGE" VARCHAR2(100),
	"STATUS" VARCHAR2(10),
	"ROLE" VARCHAR2(10),
	"FK_VIP_ID" NUMBER(10,0)
);
ALTER TABLE "T_USER" ADD CONSTRAINT "PK_T_USER" PRIMARY KEY ("PK_ID") ENABLE;

ALTER TABLE "T_USER" ADD CONSTRAINT "FK_USER_VIP" FOREIGN KEY (FK_VIP_ID) REFERENCES T_VIP(PK_ID);

ALTER TABLE "T_USER"  ADD CONSTRAINT uniqueUserName UNIQUE(USER_NAME);


CREATE TABLE "T_ROOM"
(
	"PK_ID" NUMBER(10) NOT NULL,
	"NUMBER_ROOM" NUMBER(10),
	"CAPACITY" NUMBER(10),
	"STATUS" VARCHAR(10),
	"PRICE" NUMBER(30),
	"IMAGE" VARCHAR2(100)
);
ALTER TABLE "T_ROOM" ADD CONSTRAINT "PK_T_ROOM" PRIMARY KEY ("PK_ID") ENABLE;


CREATE TABLE "T_ORDER"(
	"PK_ID" NUMBER(10) NOT NULL,
	"START_TIME" TIMESTAMP,
	"END_TIME" TIMESTAMP,
	"TOTAL_PRICE" NUMBER(10,0),
	"FK_ID_USER" NUMBER(10,0),
	"FK_ID_ROOM" NUMBER(10,0)
);
ALTER TABLE "T_ORDER" ADD CONSTRAINT "PK_T_ORDER" PRIMARY KEY ("PK_ID") ENABLE;

ALTER TABLE T_ORDER ADD CONSTRAINT "FK_USER_ORDER" FOREIGN KEY (FK_ID_USER) REFERENCES T_USER(PK_ID);

ALTER TABLE T_ORDER ADD CONSTRAINT "FK_ROOM_ORDER" FOREIGN KEY (FK_ID_ROOM) REFERENCES T_ROOM(PK_ID);


CREATE TABLE "T_CATEGORY"
(
	"PK_ID" NUMBER(10,0) NOT NULL,
	"NAME" VARCHAR2(50) NOT NULL,
	"STATUS" VARCHAR2(50)
);
ALTER TABLE "T_CATEGORY" ADD CONSTRAINT "PK_T_CATEGORY" PRIMARY KEY ("PK_ID") ENABLE;


CREATE TABLE "T_ITEM"
(
	"PK_ID" NUMBER(10,0) NOT NULL,
	"NAME" VARCHAR2(50) NOT NULL,
	"PRICE" NUMBER(10),
	"STATUS" VARCHAR2(10),
	"FK_ID_CATEGORY" NUMBER(10)
);
ALTER TABLE "T_ITEM" ADD CONSTRAINT "PK_T_ITEM" PRIMARY KEY ("PK_ID") ENABLE;

ALTER TABLE T_ITEM ADD CONSTRAINT "FK_CATEGORY_ITEM" FOREIGN KEY (FK_ID_CATEGORY) REFERENCES T_CATEGORY(PK_ID);


CREATE TABLE  "T_ORDER_ITEM"
(
	"PK_ID" NUMBER(10,0),
	"FK_ID_ORDER" NUMBER(10,0),
	"FK_ID_ITEM" NUMBER(10,0)
);
ALTER TABLE "T_ORDER_ITEM" ADD CONSTRAINT "PK_T_ORDER_ITEM" PRIMARY KEY ("PK_ID") ENABLE;

ALTER TABLE T_ORDER_ITEM ADD CONSTRAINT "FK_ORDER_ITEM_ORDER" FOREIGN KEY (FK_ID_ORDER) REFERENCES T_ORDER(PK_ID);

ALTER TABLE T_ORDER_ITEM ADD CONSTRAINT "FK_ORDER_ITEM_ITEM" FOREIGN KEY (FK_ID_ITEM) REFERENCES T_ITEM(PK_ID);

 