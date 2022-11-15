--FILA 1
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(1,true,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(2,true,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(3,true,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(4,true,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(5,true,false,false);
--FILA 2
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(6,true,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(7,false,true,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(8,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(9,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(10,false,true,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(11,true,false,false);
--FILA 3
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(12,true,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(13,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(14,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(15,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(16,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(17,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(18,true,false,false);
--FILA 4
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(19,true,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(20,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(21,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(22,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(23,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(24,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(25,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(26,true,false,false);
--FILA 5
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(27,true,true,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(28,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(29,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(30,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(31,false,false,true);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(32,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(33,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(34,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(35,true,true,false);
--FILA 6
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(36,true,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(37,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(38,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(39,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(40,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(41,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(42,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(43,true,false,false);
--FILA 7
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(44,true,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(45,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(46,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(47,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(48,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(49,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(50,true,false,false);
--FILA 8
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(51,true,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(52,false,true,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(53,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(54,false,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(55,false,true,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(56,true,false,false);
--FILA 9
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(57,true,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(58,true,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(59,true,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(60,true,false,false);
INSERT INTO CASILLAS(id, borde,poder1,poder2) VALUES(61,true,false,false);

-- Fila es un INTEGER, columna un FLOAT
-- Casillas adyacencia
-------------------------------FILA 1---------------------------------
--CASILLA 1
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(1, 1, 3.0, 2);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(1, 1, 3.0, 6);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(1, 1, 3.0, 7);
--CASILLA 2
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(2, 1, 4.0, 1);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(2, 1, 4.0, 3);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(2, 1, 4.0, 7);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(2, 1, 4.0, 8);
--CASILLA 3
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(3, 1, 5.0, 2);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(3, 1, 5.0, 4);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(3, 1, 5.0, 8);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(3, 1, 5.0, 9);
--CASILLA 4
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(4, 1, 6.0, 3);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(4, 1, 6.0, 5);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(4, 1, 6.0, 9);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(4, 1, 6.0, 10);
--CASILLA 5
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(5, 1, 7.0, 4);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(5, 1, 7.0, 10);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(5, 1, 7.0, 11);

-------------------------------FILA 2---------------------------------
--CASILLA 6
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(6, 2, 2.5, 1);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(6, 2, 2.5, 7);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(6, 2, 2.5, 12);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(6, 2, 2.5, 13);
--CASILLA 7
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(7, 2, 3.5, 1);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(7, 2, 3.5, 2);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(7, 2, 3.5, 6);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(7, 2, 3.5, 8);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(7, 2, 3.5, 13);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(7, 2, 3.5, 14);
--CASILLA 8
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(8, 2, 4.5, 2);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(8, 2, 4.5, 3);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(8, 2, 4.5, 7);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(8, 2, 4.5, 9);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(8, 2, 4.5, 14);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(8, 2, 4.5, 15);
--CASILLA 9
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(9, 2, 5.5, 3);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(9, 2, 5.5, 4);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(9, 2, 5.5, 8);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(9, 2, 5.5, 10);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(9, 2, 5.5, 15);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(9, 2, 5.5, 16);
--CASILLA 10
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(10, 2, 6.5, 4);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(10, 2, 6.5, 5);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(10, 2, 6.5, 9);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(10, 2, 6.5, 11);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(10, 2, 6.5, 16);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(10, 2, 6.5, 17);
--CASILLA 11
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(11, 2, 7.5, 5);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(11, 2, 7.5, 10);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(11, 2, 7.5, 17);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(11, 2, 7.5, 18);
-------------------------------FILA 3---------------------------------
--CASILLA 12
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(12, 3, 2.0, 6);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(12, 3, 2.0, 13);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(12, 3, 2.0, 19);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(12, 3, 2.0, 20);
--CASILLA 13
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(13, 3, 3.0, 6);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(13, 3, 3.0, 7);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(13, 3, 3.0, 12);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(13, 3, 3.0, 14);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(13, 3, 3.0, 20);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(13, 3, 3.0, 21);
--CASILLA 14
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(14, 3, 4.0, 7);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(14, 3, 4.0, 8);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(14, 3, 4.0, 13);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(14, 3, 4.0, 15);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(14, 3, 4.0, 21);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(14, 3, 4.0, 22);
--CASILLA 15
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(15, 3, 5.0, 8);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(15, 3, 5.0, 9);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(15, 3, 5.0, 14);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(15, 3, 5.0, 16);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(15, 3, 5.0, 22);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(15, 3, 5.0, 23);
--CASILLA 16
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(16, 3, 6.0, 9);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(16, 3, 6.0, 10);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(16, 3, 6.0, 15);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(16, 3, 6.0, 17);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(16, 3, 6.0, 23);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(16, 3, 6.0, 24);
--CASILLA 17
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(17, 3, 7.0, 10);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(17, 3, 7.0, 11);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(17, 3, 7.0, 16);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(17, 3, 7.0, 18);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(17, 3, 7.0, 24);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(17, 3, 7.0, 25);
--CASILLA 18
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(18, 3, 8.0, 11);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(18, 3, 8.0, 17);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(18, 3, 8.0, 25);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(18, 3, 8.0, 26);
-------------------------------FILA 4---------------------------------
--CASILLA 19
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(19, 4, 1.5, 12);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(19, 4, 1.5, 20);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(19, 4, 1.5, 27);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(19, 4, 1.5, 28);
--CASILLA 20
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(20, 4, 2.5, 12);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(20, 4, 2.5, 13);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(20, 4, 2.5, 19);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(20, 4, 2.5, 21);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(20, 4, 2.5, 28);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(20, 4, 2.5, 29);
--CASILLA 21
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(21, 4, 3.5, 13);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(21, 4, 3.5, 14);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(21, 4, 3.5, 20);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(21, 4, 3.5, 22);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(21, 4, 3.5, 29);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(21, 4, 3.5, 30);
--CASILLA 22
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(22, 4, 4.5, 14);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(22, 4, 4.5, 15);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(22, 4, 4.5, 21);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(22, 4, 4.5, 23);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(22, 4, 4.5, 30);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(22, 4, 4.5, 31);
--CASILLA 23
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(23, 4, 5.5, 15);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(23, 4, 5.5, 16);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(23, 4, 5.5, 22);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(23, 4, 5.5, 24);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(23, 4, 5.5, 31);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(23, 4, 5.5, 32);
--CASILLA 24
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(24, 4, 6.5, 16);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(24, 4, 6.5, 17);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(24, 4, 6.5, 23);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(24, 4, 6.5, 25);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(24, 4, 6.5, 32);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(24, 4, 6.5, 33);
--CASILLA 25
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(25, 4, 7.5, 17);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(25, 4, 7.5, 18);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(25, 4, 7.5, 24);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(25, 4, 7.5, 26);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(25, 4, 7.5, 33);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(25, 4, 7.5, 34);
--CASILLA 26
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(26, 4, 8.5, 18);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(26, 4, 8.5, 25);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(26, 4, 8.5, 34);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(26, 4, 8.5, 35);
-------------------------------FILA 5---------------------------------
--CASILLA 27
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(26, 5, 1.0, 35);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(27, 5, 1.0, 19);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(27, 5, 1.0, 28);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(27, 5, 1.0, 36);
--CASILLA 28
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(28, 5, 2.0, 19);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(28, 5, 2.0, 20);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(28, 5, 2.0, 27);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(28, 5, 2.0, 29);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(28, 5, 2.0, 36);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(28, 5, 2.0, 37);
--CASILLA 29
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(29, 5, 3.0, 20);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(29, 5, 3.0, 21);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(29, 5, 3.0, 28);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(29, 5, 3.0, 30);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(29, 5, 3.0, 37);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(29, 5, 3.0, 38);
--CASILLA 30
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(30, 5, 4.0, 21);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(30, 5, 4.0, 22);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(30, 5, 4.0, 29);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(30, 5, 4.0, 31);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(30, 5, 4.0, 38);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(30, 5, 4.0, 39);
--CASILLA 31
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(31, 5, 5.0, 22);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(31, 5, 5.0, 23);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(31, 5, 5.0, 30);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(31, 5, 5.0, 32);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(31, 5, 5.0, 39);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(31, 5, 5.0, 40);
--CASILLA 32
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(32, 5, 6.0, 23);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(32, 5, 6.0, 24);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(32, 5, 6.0, 31);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(32, 5, 6.0, 33);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(32, 5, 6.0, 40);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(32, 5, 6.0, 41);
--CASILLA 33
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(33, 5, 7.0, 24);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(33, 5, 7.0, 25);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(33, 5, 7.0, 32);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(33, 5, 7.0, 34);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(33, 5, 7.0, 41);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(33, 5, 7.0, 42);
--CASILLA 34
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(34, 5, 8.0, 25);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(34, 5, 8.0, 26);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(34, 5, 8.0, 33);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(34, 5, 8.0, 35);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(34, 5, 8.0, 42);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(34, 5, 8.0, 43);
--CASILLA 35
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(35, 5, 9.0, 26);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(35, 5, 9.0, 34);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(35, 5, 9.0, 43);
-------------------------------FILA 6---------------------------------
--CASILLA 36
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(36, 6, 1.5, 27);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(36, 6, 1.5, 28);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(36, 6, 1.5, 37);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(36, 6, 1.5, 44);
--CASILLA 37
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(37, 6, 2.5, 28);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(37, 6, 2.5, 29);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(37, 6, 2.5, 36);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(37, 6, 2.5, 38);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(37, 6, 2.5, 44);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(37, 6, 2.5, 45);
--CASILLA 38
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(38, 6, 3.5, 29);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(38, 6, 3.5, 30);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(38, 6, 3.5, 37);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(38, 6, 3.5, 39);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(38, 6, 3.5, 45);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(38, 6, 3.5, 46);
--CASILLA 39
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(39, 6, 4.5, 30);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(39, 6, 4.5, 31);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(39, 6, 4.5, 38);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(39, 6, 4.5, 40);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(39, 6, 4.5, 46);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(39, 6, 4.5, 47);
--CASILLA 40
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(40, 6, 5.5, 31);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(40, 6, 5.5, 32);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(40, 6, 5.5, 39);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(40, 6, 5.5, 41);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(40, 6, 5.5, 47);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(40, 6, 5.5, 48);
--CASILLA 41
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(41, 6, 6.5, 32);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(41, 6, 6.5, 33);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(41, 6, 6.5, 40);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(41, 6, 6.5, 42);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(41, 6, 6.5, 48);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(41, 6, 6.5, 49);
--CASILLA 42
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(42, 6, 7.5, 33);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(42, 6, 7.5, 34);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(42, 6, 7.5, 41);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(42, 6, 7.5, 43);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(42, 6, 7.5, 49);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(42, 6, 7.5, 50);
--CASILLA 43
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(43, 6, 8.5, 34);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(43, 6, 8.5, 35);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(43, 6, 8.5, 42);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(43, 6, 8.5, 50);
-------------------------------FILA 7---------------------------------
--CASILLA 44
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(44, 7, 2.0, 36);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(44, 7, 2.0, 37);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(44, 7, 2.0, 45);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(44, 7, 2.0, 51);
--CASILLA 45
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(45, 7, 3.0, 37);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(45, 7, 3.0, 38);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(45, 7, 3.0, 44);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(45, 7, 3.0, 46);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(45, 7, 3.0, 51);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(45, 7, 3.0, 52);
--CASILLA 46
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(46, 7, 4.0, 38);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(46, 7, 4.0, 39);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(46, 7, 4.0, 45);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(46, 7, 4.0, 47);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(46, 7, 4.0, 52);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(46, 7, 4.0, 53);
--CASILLA 47
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(47, 7, 5.0, 39);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(47, 7, 5.0, 40);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(47, 7, 5.0, 46);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(47, 7, 5.0, 48);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(47, 7, 5.0, 53);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(47, 7, 5.0, 54);
--CASILLA 48
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(48, 7, 6.0, 40);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(48, 7, 6.0, 41);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(48, 7, 6.0, 47);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(48, 7, 6.0, 49);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(48, 7, 6.0, 54);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(48, 7, 6.0, 55);
--CASILLA 49
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(49, 7, 7.0, 41);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(49, 7, 7.0, 42);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(49, 7, 7.0, 48);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(49, 7, 7.0, 50);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(49, 7, 7.0, 55);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(49, 7, 7.0, 56);
--CASILLA 50
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(50, 7, 8.0, 42);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(50, 7, 8.0, 43);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(50, 7, 8.0, 49);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(50, 7, 8.0, 56);
-------------------------------FILA 8---------------------------------
--CASILLA 51
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(51, 8, 2.5, 44);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(51, 8, 2.5, 45);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(51, 8, 2.5, 52);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(51, 8, 2.5, 57);
--CASILLA 52
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(52, 8, 3.5, 45);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(52, 8, 3.5, 46);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(52, 8, 3.5, 51);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(52, 8, 3.5, 53);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(52, 8, 3.5, 57);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(52, 8, 3.5, 58);
--CASILLA 53
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(53, 8, 4.5, 46);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(53, 8, 4.5, 47);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(53, 8, 4.5, 52);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(53, 8, 4.5, 54);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(53, 8, 4.5, 58);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(53, 8, 4.5, 59);
--CASILLA 54
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(54, 8, 5.5, 47);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(54, 8, 5.5, 48);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(54, 8, 5.5, 53);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(54, 8, 5.5, 55);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(54, 8, 5.5, 59);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(54, 8, 5.5, 60);
--CASILLA 55
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(55, 8, 6.5, 48);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(55, 8, 6.5, 49);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(55, 8, 6.5, 54);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(55, 8, 6.5, 56);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(55, 8, 6.5, 60);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(55, 8, 6.5, 61);
--CASILLA 56
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(56, 8, 7.5, 49);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(56, 8, 7.5, 50);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(56, 8, 7.5, 55);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(56, 8, 7.5, 61);
-------------------------------FILA 9---------------------------------
--CASILLA 57
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(57, 9, 3.0, 51);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(57, 9, 3.0, 52);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(57, 9, 3.0, 58);
--CASILLA 58
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(58, 9, 4.0, 52);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(58, 9, 4.0, 53);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(58, 9, 4.0, 57);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(58, 9, 4.0, 59);
--CASILLA 59
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(59, 9, 5.0, 53);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(59, 9, 5.0, 54);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(59, 9, 5.0, 58);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(59, 9, 5.0, 60);
--CASILLA 60
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(60, 9, 6.0, 54);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(60, 9, 6.0, 55);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(60, 9, 6.0, 59);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(60, 9, 6.0, 61);
--CASILLA 61
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(61, 9, 7.0, 55);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(61, 9, 7.0, 56);
INSERT INTO CASILLAS_ADYACENCIA(casilla_id, fila, columna, adyacencia_id) VALUES(61, 9, 7.0, 60);

INSERT INTO ACCIONES (id, id_turno, id_jugador, id_casilla_id) VALUES(1,1,1,1);
INSERT INTO ACCIONES (id, id_turno, id_jugador, id_casilla_id) VALUES(2,1,1,2);
INSERT INTO ACCIONES (id, id_turno, id_jugador, id_casilla_id) VALUES(3,1,1,3);
INSERT INTO ACCIONES (id, id_turno, id_jugador, id_casilla_id) VALUES(4,1,1,4);
INSERT INTO ACCIONES (id, id_turno, id_jugador, id_casilla_id) VALUES(5,2,1,5);
INSERT INTO ACCIONES (id, id_turno, id_jugador, id_casilla_id) VALUES(6,2,1,6);

INSERT INTO TURNOS(id, id_jugador, id_partida,num_territoriosj1,num_territoriosj2,num_territoriosj3,num_territoriosj4,territorio)
 VALUES(1,1,1,4,0,0,0,NULL);

INSERT INTO PARTIDAS(id,puntos_tablero1,puntos_tablero2,puntos_tablero3,puntos_tablero4, fecha  ,id_criterioa1,id_criterioa2,id_criteriob1,id_criteriob2,id_tablero1,id_tablero2,id_tablero3,id_tablero4 )
VALUES(1,0,0,0,0,NULL, 0,0,0,0,1,1,1,1);

INSERT INTO PARTIDAS(id,puntos_tablero1,puntos_tablero2,puntos_tablero3,puntos_tablero4, fecha,id_criterioa1,id_criterioa2,id_criteriob1,id_criteriob2,id_tablero1,id_tablero2,id_tablero3,id_tablero4 )
VALUES(2,0,0,0,0,NULL, 0,0,0,0,1,1,1,1);


-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');
-- Owner user
INSERT INTO users(username,password,enabled) VALUES ('fravilpae','password',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'fravilpae','owner');

INSERT INTO users(username,password,enabled) VALUES ('jeszamgue','password',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'jeszamgue','owner');

INSERT INTO users(username,password,enabled) VALUES ('aitroddue','password',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'aitroddue','owner');

INSERT INTO vets(id, first_name,last_name) VALUES (1, 'James', 'Carter');
INSERT INTO vets(id, first_name,last_name) VALUES (2, 'Helen', 'Leary');
INSERT INTO vets(id, first_name,last_name) VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets(id, first_name,last_name) VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets(id, first_name,last_name) VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets(id, first_name,last_name) VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');
INSERT INTO types VALUES (7, 'turtle');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO owners VALUES (11, 'Francis', 'Villalobos', '112 Smith Ave.', 'Detroit', '622316650', 'fravilpae');
INSERT INTO owners VALUES (12, 'Jesus', 'Zambrana', '9 Spain Ave.', 'Seville', '722870612', 'jeszamgue');
INSERT INTO owners VALUES (13, 'Aitor', 'Rodriguez', '18 Diego St.', 'Seville', '654743409', 'aitroddue');


INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Perry', '2022-06-08', 2, 11);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'kamuy', '2021-07-08', 1, 12);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'Steven', '2020-10-10', 5, 13);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

