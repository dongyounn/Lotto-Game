--User 데이터 생성
insert into play_game_user (CREATED, UPDATED, nick_name, phone_number, social_no, user_name, user_id)
values (sysdate, sysdate, '신동동', 'n69PjBl/BF3RV+hXk2yAxA==', 'Pxos1FID5LUMz8lxcT9Drg==', 'FnhfT5GTwr1kPS3zYmYfPQ==', 'GU000099')
;

insert into PLAY_GAME_RESULT (CREATED, UPDATED, NORMAL_NUMBER, PLAYER_NO, STATUS, id)
values (sysdate, sysdate, null, 0,'ACTIVE', 1)