-- auto-generated definition
create table PLAY_GAME_INFO
(
    ID                  NUMBER not null
        primary key,
    USER_ID             VARCHAR2(15),
    PLAYER_NICK_NAME    VARCHAR2(150),
    PLAYER_PHONE_NUMBER VARCHAR2(50),
    GAME_NUMBER         VARCHAR2(50),
    GAME_ROUND          NUMBER,
    DRAW_RESULT         NUMBER,
    MATCH_NUMBER        VARCHAR2(30),
    CREATED             DATE,
    UPDATED             DATE
)
/
