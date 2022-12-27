<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="rules">
    <h1><em><b><u>Rules</u></b></em></h1>

    <h2><strong><br>Preparación:</strong></h2>
    <ul style="font-size: large;">
        <li>Elegir un territorio.</li>
        <li>Elegir al azar los 4 criterios para todos los jugadores (dos del sector a, dos del sector b).</li>
        <li>Objetivo: obtener la mayor puntuacion con los 4 criteios obtenidos antes.</li>
        <li>Se tienen tantos dados como jugadores haya mas 1.</li>
    </ul>

    <h2><strong><br>Rondas del juego:</strong></h2>
    <ul style="font-size: large;">
        <li>Comienza el jugador que ha creado la partida.</li>
        <li>En las siguientes rondas comenzara el segundo jugador en unirse a la partida.</li>
    </ul>
    
    <h2><strong><br>Turno de juego:</strong></h2>
    <ul style="font-size: large;">
        <li>Hay dos tipos de jugadores: jugador activo y jugador pasivo.</li>
        <li>El jugador activo: lanza los dados, decide que territorios va a dibujar y quita uno de los dados(este dado expresa el numero de territorios que va a dibujar).</li>
        <li>El jugador pasivo: debe escoger un numero entre los dados sobrantes.</li>
        <li>Una vez elegido. Todos dibujan el numero de territorios elegido por el jugador activo y el que ellos hayan escogido.</li>
        <li>Luego, pasa el turno al siguiente jugador unido a la partida.</li>
        <li>Se repite el proceso (con un dado menos).</li>
    </ul>

    <h2><strong><br>Fin de ronda:</strong></h2>
    <ul style="font-size: large;">
        <li>Cuando solo queda un dado, se termina la ronda.</li>
        <li>Se juega una nueva ronda con todos los dados.</li>
    </ul>

    <h2><strong><br>Reglas para dibujar:</strong></h2>
    <ul style="font-size: large;">
        <li>Tan solo puedes utilizar los territorios un numero determinado de veces.</li>
        <li>Cada territorio debe dibujarse concectado a otro territorio dibujado anteriormente.</li>
    </ul>

    <h2><strong><br>Poderes:</strong></h2>
    <h3 style="text-decoration-line: underline; text-decoration-style: double;text-indent: 2em;"><strong>+1 / -1</strong></h3>
    <ul style="font-size: large;">
        <li>Si dibujas cualquier territorio encima de un <strong>+1 / -1</strong>, debes tachar uno de los cuadros de uso de ese poder.</li>
        <li>Este poder sirve para cambiar el valor de un dado en <strong>+1 / -1</strong>.</li>
        <li>Se puede usar este poder mas de una vez en el mismo turno.</li>
    </ul>

    <h3 style="text-decoration-line: underline; text-decoration-style: double;text-indent: 2em;"><strong>?</strong></h3>
    <ul style="font-size: large;">
        <li>Este poder es obligatorio usarlos al final del turno en el que se dibuja en su casilla.</li>
        <li>Sirve para calbular la puntuacion de cualquiera de los 4 criterios del juego.</li>
    </ul>

    <h2><strong><br>Fin del juego:</strong></h2>
    <ul style="font-size: large;">
        <li>Finaliza cuando algun jugador no puede dibujar mas territorios requeridos en su tablero.</li>
        <li>Se contabilizan los puntos.</li>
        <li>El que tenga la mayor suma gana (se puede quedar empate).</li>
    </ul>

    <h2><strong><br>Modo solitario:</strong></h2>
    <ul style="font-size: large;">
        <li>Se lanzan 3 dados.</li>
        <li>Se decide el tipo de territorio a dibujar.</li>
        <li>Ahora juega con 2 dados.</li>
        <li>No puede usar el "+1/-1" al dado elegido para el tipo de territorio.</li>
        <li>Se toman los 3 dados de nuevo y comienza una nueva ronda.</li>
    </ul>

    <h2><strong><br>Video explicativo si tiene alguna duda:</strong></h2>
    <p style="text-align: center;"><br><iframe width="560" height="315" src="https://www.youtube.com/embed/EXVKaqZB4MQ" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></p>



</petclinic:layout>