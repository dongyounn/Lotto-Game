-- auto-generated definition
create table PLAY_GAME_RESULT
(
    ID            NUMBER not null
        primary key,
    NORMAL_NUMBER VARCHAR2(50),
    BONUS_NUMBER  NUMBER,
    STATUS        VARCHAR2(15),
    PLAYER_NO     NUMBER,
    CREATED       DATE,
    UPDATED       DATE
)
/

