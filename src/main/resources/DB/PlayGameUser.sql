-- auto-generated definition
create table PLAY_GAME_USER
(
    USER_ID      VARCHAR2(10),
    SOCIAL_NO          VARCHAR2(50),
    USER_NAME    VARCHAR2(50),
    PHONE_NUMBER VARCHAR2(50),
    VERIFY       VARCHAR2(10),
    CREATED      DATE,
    UPDATED      DATE,
    NICK_NAME    VARCHAR2(150)
)
/

create unique index PLAY_GAME_USER_UNIQ_INDEX
    on PLAY_GAME_USER (PHONE_NUMBER)
/

