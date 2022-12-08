DROP TABLE IF EXISTS USER_PARTNER_MAPPING;
CREATE TABLE USER_PARTNER_MAPPING (
    USER_ID         bigint not null,
    PARTNER_ID        bigint not null,
    primary key (USER_ID, PARTNER_ID)
);