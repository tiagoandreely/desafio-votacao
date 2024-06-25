delete from votes;
ALTER TABLE votes ALTER COLUMN ID RESTART WITH 1;
delete from sessions;
ALTER TABLE sessions ALTER COLUMN ID RESTART WITH 1;
delete from voting_agendas;
ALTER TABLE voting_agendas ALTER COLUMN ID RESTART WITH 1;
delete from associates;
ALTER TABLE associates ALTER COLUMN ID RESTART WITH 1;