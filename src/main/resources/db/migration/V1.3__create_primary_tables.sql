DROP TABLE IF EXISTS USER_PROFESSION;
CREATE TABLE USER_PROFESSION (
    ID              bigserial not null,
    NAME	        varchar(255) not null,
    primary key (ID)
);

DROP TABLE IF EXISTS USER_COUNTRY;
CREATE TABLE USER_COUNTRY (
    ID              bigserial not null,
    NAME	        varchar(255) not null,
    CODE	        varchar(8),
    UNIQUE (NAME),
    primary key (ID)
);

DROP TABLE IF EXISTS USER_DISTRICT;
CREATE TABLE USER_DISTRICT (
    ID              bigserial not null,
    NAME	        varchar(255) not null,
    CODE	        varchar(8),
    COUNTRY_ID      bigint not null,
    primary key (ID)
);

DROP TABLE IF EXISTS USER_PROFILE;
CREATE TABLE USER_PROFILE (
    ID              bigserial not null,
    USER_ID         VARCHAR(255) NOT NULL UNIQUE,
    FIRST_NAME	    varchar(255) not null,
    LAST_NAME	    varchar(255),
    EMAIL	        varchar(128) not null,
    PHONE	        varchar(32),
    DATE_OF_BIRTH   timestamp not null,
    PROFESSION_ID	int,
    DISTRICT_ID	    int,
    COUNTRY_ID      int,
    STATUS	        BOOLEAN DEFAULT TRUE,

    oauth_user_id     bigint,

    CREATE_TIME	    timestamp NOT NULL,
    EDIT_TIME       timestamp,
    INTERNAL_VERSION bigint default 1,

    primary key (ID)
);

DROP TABLE IF EXISTS USER_QUESTION;
CREATE TABLE USER_QUESTION (
    ID              bigserial not null,
    USER_ID         bigint,
    DESCRIPTION     VARCHAR(255) NOT NULL,
    ELABORATION	        varchar(255) not null,

    ANSWER_OPTION_ID bigint not null,
    STATUS	        BOOLEAN DEFAULT TRUE,

    CREATE_TIME	    timestamp NOT NULL,
    EDIT_TIME       timestamp,
    INTERNAL_VERSION bigint default 1,
    primary key (ID)
);

DROP TABLE IF EXISTS QUESTION_OPTION;
CREATE TABLE QUESTION_OPTION (
    ID              bigserial not null,
    VALUE	        varchar(255) not null,
    primary key (ID)
);

DROP TABLE IF EXISTS QUESTION_OPTION_MAPPING;
CREATE TABLE QUESTION_OPTION_MAPPING (
    QUESTION_ID     bigint not null,
    OPTION_ID       bigint not null,
    primary key (QUESTION_ID, OPTION_ID)
);

DROP TABLE IF EXISTS USER_COMMENT;
CREATE TABLE USER_COMMENT (
    ID              bigserial not null,
    DESCRIPTION     VARCHAR(255) NOT NULL,
    USER_ID         bigint not null,
    QUESTION_ID     bigint not null,
    STATUS	        BOOLEAN DEFAULT TRUE,

    CREATE_TIME	    timestamp NOT NULL,
    EDIT_TIME       timestamp,
    INTERNAL_VERSION bigint default 1,

    primary key (ID)
);


DROP TABLE IF EXISTS USER_GROUP;
CREATE TABLE USER_GROUP (
    ID              bigserial not null,
    GROUP_NAME      VARCHAR(255) NOT NULL,
    CREATOR_ID      bigint not null,
    STATUS	        BOOLEAN DEFAULT TRUE,
    primary key (ID)
);

DROP TABLE IF EXISTS USER_GROUP_MAPPING;
CREATE TABLE USER_GROUP_MAPPING (
    ID              bigserial not null,
    USER_ID         bigint not null,
    GROUP_ID        bigint not null,
    MEMBER_ROLE	    int,
    primary key (ID)
);

DROP TABLE IF EXISTS USER_TEST_HISTORY;
CREATE TABLE USER_TEST_HISTORY (
    ID                      bigserial not null,
    TEST_NAME               VARCHAR(255) NOT NULL,
    USER_ID                 bigint not null,
    TOTAL_QUESTIONS         int,
    TOTAL_CORRECT           int,
    TOTAL_WRONG             int,
    DID_NOT_TOUCH           int,
    TEST_START_TIME         timestamp,
    TEST_END_TIME           timestamp,
    STATUS	        BOOLEAN DEFAULT TRUE,
    primary key (ID)
);

DROP TABLE IF EXISTS USER_TEST_HISTORY_DETAILS;
CREATE TABLE USER_TEST_HISTORY_DETAILS (
    ID                      bigserial not null,
    TEST_HISTORY_ID         bigint not null,
    QUESTION_ID             bigint not null,
    ANSWER_OPTION_ID	    bigint,
    primary key (ID)
);

