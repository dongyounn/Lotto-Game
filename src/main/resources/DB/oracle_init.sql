DROP TABLE PLAY_GAME_RESULT ;
DROP TABLE PLAY_GAME_INFO;
DROP SEQUENCE PLAY_GAME_RESULT_SEQ;

CREATE SEQUENCE PLAY_GAME_RESULT_SEQ MINVALUE 2 ;


create table PLAY_GAME_INFO
(
    ID                  NUMBER not null
        primary key,
    PLAYER_PHONE_NUMBER VARCHAR2(50),
    GAME_NUMBER         VARCHAR2(50),
    GAME_ROUND          NUMBER,
    DRAW_RESULT         NUMBER,
    MATCH_NUMBER        VARCHAR2(30),
    CREATED             DATE,
    UPDATED             DATE
)
;

create table PLAY_GAME_RESULT
(
    ID            NUMBER not null
        primary key,
    REGULAR_NUMBER VARCHAR2(50),
    BONUS_NUMBER  NUMBER,
    STATUS        VARCHAR2(15),
    PLAYER_NO     NUMBER,
    CREATED       DATE,
    UPDATED       DATE
);

insert into PLAY_GAME_RESULT (CREATED, UPDATED, REGULAR_NUMBER, BONUS_NUMBER,PLAYER_NO, STATUS, id)
values (sysdate, sysdate, null,null, 0,'ACTIVE', 1);