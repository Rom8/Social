DROP SEQUENCE  USER_SEQ;
DROP SEQUENCE  ACCESS_CIRCLE_SEQ;
DROP TABLE PERSON;
DROP TABLE ACCESS_CIRCLE;
DROP TABLE RELATION;
DROP TABLE PROPERTY;


CREATE SEQUENCE  USER_SEQ  MINVALUE 1 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 300 CACHE 20 NOORDER  NOCYCLE ;
CREATE SEQUENCE  ACCESS_CIRCLE_SEQ  MINVALUE 1 MAXVALUE 999999999999999 INCREMENT BY 1 START WITH 2100 CACHE 20 NOORDER  NOCYCLE ;



CREATE TABLE PERSON (
		PERSON_ID NUMBER(12 , 0) NOT NULL,
		EMAIL VARCHAR2(40) NOT NULL,
		FIRST_NAME VARCHAR2(30) NOT NULL,
		MIDDLE_NAME VARCHAR2(40),
		LAST_NAME VARCHAR2(40),
		BIRTHDAY DATE,
		HOMETOWN VARCHAR2(40),
		HASH NUMBER(12 , 0),
		SALT NUMBER(12 , 0)
	);

ALTER TABLE PERSON ADD CONSTRAINT PERSON_UNIQUE_EMAIL UNIQUE (EMAIL);
ALTER TABLE PERSON ADD CONSTRAINT PERSON_PK PRIMARY KEY (PERSON_ID);





CREATE TABLE ACCESS_CIRCLE (
		CIRCLE_ID NUMBER(16 , 0) NOT NULL,
		PERSON_ID NUMBER(12 , 0),
		NAME VARCHAR2(40),
		ENABLED NUMBER(1 , 0)
	);
ALTER TABLE ACCESS_CIRCLE ADD CONSTRAINT ACCESS_CIRCLE_PK PRIMARY KEY (CIRCLE_ID);
ALTER TABLE ACCESS_CIRCLE ADD CONSTRAINT PERSON_CIRCLE_NAME_UK UNIQUE (PERSON_ID, NAME);
ALTER TABLE ACCESS_CIRCLE ADD CONSTRAINT ACCESS_CIRCLE_PERSON_FK FOREIGN KEY (PERSON_ID)
	REFERENCES PERSON (PERSON_ID)
	ON DELETE CASCADE;


	
	
	
CREATE TABLE RELATION (
		TARGET_PERSON_ID NUMBER(12 , 0) NOT NULL,
		ACCESS_CIRCLE_ID NUMBER(16 , 0) NOT NULL
	);
ALTER TABLE RELATION ADD CONSTRAINT RELATION_PK PRIMARY KEY (TARGET_PERSON_ID, ACCESS_CIRCLE_ID);
ALTER TABLE RELATION ADD CONSTRAINT RELATION_PERSON_FK FOREIGN KEY (TARGET_PERSON_ID)
	REFERENCES PERSON (PERSON_ID)
	ON DELETE CASCADE;
ALTER TABLE RELATION ADD CONSTRAINT RELATION_CIRCLE_FK FOREIGN KEY (ACCESS_CIRCLE_ID)
	REFERENCES ACCESS_CIRCLE (CIRCLE_ID)
	ON DELETE CASCADE;

	
	
	
	
CREATE TABLE PROPERTY (
		CIRCLE_ID NUMBER(16 , 0) NOT NULL,
		NAME VARCHAR2(40) NOT NULL,
		VALUE VARCHAR2(100)
	);
ALTER TABLE PROPERTY ADD CONSTRAINT PROPERTY_PK PRIMARY KEY (CIRCLE_ID, NAME);
ALTER TABLE PROPERTY ADD CONSTRAINT PROPERTY_ACCESS_CIRCLE_FK FOREIGN KEY (CIRCLE_ID)
	REFERENCES ACCESS_CIRCLE (CIRCLE_ID)
	ON DELETE CASCADE;

commit;