-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(name, last_name, username, password, birth_date, email, phone, games_played, games_win, total_points, max_points, times_used_power_question, times_used_power_one, enabled) 
VALUES ('ad', 'min', 'admin1', '4dm1n', '2010-09-07', 'admin@gmail.com', '954734895', 5, 3, 550, 130, 5, 7, true);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(name, last_name, username, password, birth_date, email, phone, games_played, games_win, total_points, max_points, times_used_power_question, times_used_power_one, enabled) 
VALUES ('ow', 'ner', 'owner1', '0wn3r', '2010-09-07', 'owner@gmail.com', '954734895', 5, 3, 550, 130, 5, 7, true);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','player');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(name, last_name, username, password, birth_date, email, phone, games_played, games_win, total_points, max_points, times_used_power_question, times_used_power_one, enabled) 
VALUES ('vete', 'rinary', 'vet1', 'v3t', '2010-09-07', 'vet@gmail.com', '954734895', 5, 3, 550, 130, 5, 7, true);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','player');
-- Owner user
INSERT INTO users(name, last_name, username, password, birth_date, email, phone, games_played, games_win, total_points, max_points, times_used_power_question, times_used_power_one, enabled) 
VALUES ('Francis', 'Villalobos', 'fravilpae', 'password', '2010-09-07', 'fravilpae@alum.us.es', '954734895', 5, 3, 550, 130, 5, 7, true);
INSERT INTO authorities(id,username,authority) VALUES (4,'fravilpae','player');

INSERT INTO users(name, last_name, username, password, birth_date, email, phone, games_played, games_win, total_points, max_points, times_used_power_question, times_used_power_one, enabled) 
VALUES ('Jesus', 'Zambrana', 'jeszamgue', 'password', '2010-09-07', 'jeszamgue@alum.us.es', '954734895', 5, 3, 550, 130, 5, 7, true);
INSERT INTO authorities(id,username,authority) VALUES (5,'jeszamgue','player');

INSERT INTO users(name, last_name, username, password, birth_date, email, phone, games_played, games_win, total_points, max_points, times_used_power_question, times_used_power_one, enabled)
VALUES ('Aitor', 'Rodriguez', 'aitroddue', 'password', '2010-09-07', 'aitroddue@alum.us.es', '954734895', 5, 3, 550, 130, 5, 7, true);
INSERT INTO authorities(id,username,authority) VALUES (6,'aitroddue','player');


INSERT INTO tablero(id, idpartida, idpoderes, puntos) VALUES(1,1,1,1);

INSERT INTO tablero(id, idpartida, idpoderes, puntos) VALUES(2,1,1,1);

/*
INSERT INTO jugador(id,activo,nombrereino,usosterritorio1,usosterritorio2,
usosterritorio3,usosterritorio4,usosterritorio5,usosterritorio6,anfitrión,usuario_id,tablero_id)
VALUES (1,False,'x',2,2,2,2,3,0,TRUE,'jeszamgue',1);

INSERT INTO jugador(id,activo,nombrereino,usosterritorio1,usosterritorio2,
usosterritorio3,usosterritorio4,usosterritorio5,usosterritorio6,anfitrión,usuario_id,tablero_id)
VALUES (2,TRUE,'xa',2,0,2,2,1,2,TRUE,'jeszamgue',2);
*/
