-- DROP TABLE IF EXISTS PLAY_GAME_USER CASCADE;
DROP TABLE IF EXISTS PLAY_GAME_RESULT CASCADE;
DROP TABLE IF EXISTS STORE_INFO CASCADE;

-- DROP SEQUENCE IF EXISTS USER_ID_SEQ;
-- DROP SEQUENCE IF EXISTS RESERVATION_ID_SEQ;
-- DROP SEQUENCE IF EXISTS STORE_ID_SEQ;
--
--
-- DROP SEQUENCE IF EXISTS USER_ID_SEQ;
-- DROP SEQUENCE IF EXISTS RESERVATION_ID_SEQ;
-- DROP SEQUENCE IF EXISTS STORE_ID_SEQ;
-- DROP SEQUENCE IF EXISTS API_HISTORY_SEQ;
--
-- CREATE SEQUENCE USER_ID_SEQ;
--
--
-- CREATE SEQUENCE user_seq;
-- CREATE SEQUENCE STORE_ID_SEQ;
CREATE SEQUENCE PLAY_GAME_RESULT_SEQ MINVALUE 2 ;
-- CREATE SEQUENCE TRANSACTION_SEQ ;
-- CREATE SEQUENCE PLAY_GAME_HISTORY_SEQ;

create table PLAY_GAME_INFO
(
    ID                  NUMBER not null
        primary key,
--     USER_ID             VARCHAR2(15),
--     PLAYER_NICK_NAME    VARCHAR2(150),
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
--
-- -- auto-generated definition
-- create table PLAY_GAME_USER
-- (
--     USER_ID      VARCHAR2(10),
--     SOCIAL_NO          VARCHAR2(50),
--     USER_NAME    VARCHAR2(50),
--     PHONE_NUMBER VARCHAR2(50),
--     VERIFY       VARCHAR2(10),
--     CREATED      DATE,
--     UPDATED      DATE,
--     NICK_NAME    VARCHAR2(150)
-- );
--
-- create unique index PLAY_GAME_USER_UNIQ_INDEX
--     on PLAY_GAME_USER (PHONE_NUMBER);
--

-- insert
--         into
--             play_game_user
--             (CREATED, UPDATED, nick_name, phone_number, social_no, user_name, user_id)
--         values
--             (sysdate, sysdate, ?, ?, ?, ?, ?)