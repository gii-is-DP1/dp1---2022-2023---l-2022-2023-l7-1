<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<style>
    #p0 { text-indent: 2em; }
</style>
<petclinic:layout pageName="rules">
    <h2>REGLAS</h2>
    <p><strong>Preparacion:</strong></p>
    <h4>
        <p id="p0">-Elegir un territorio.<br></p>
	    <p id="p0">-Elegir al azar los 4 criterios para todos los jugadores (dos del sector a, dos del sector b).<br></p>
	    <p id="p0">-Objetivo: obtener la mayor puntuacion con los 4 criteios obtenidos antes.<br></p>
	    <p id="p0">-Se tienen tantos dados como jugadores haya mas 1.<br></p>
    </h4>
    <p><strong>Rondas del juego:</strong></p>
    <h4>
        <p id="p0">-Comienza el jugador que ha creado la partida.<br></p>
	    <p id="p0">-En las siguientes rondas comenzara el segundo jugador en unirse a la partida.<br></p>
    </h4>
    <p><strong>Turno de juego:</strong></p>
    <h4>
        <p id="p0">-Hay dos tipos de jugadores: jugador activo y jugador pasivo.<br></p>
	    <p id="p0">-El jugador activo: lanza los dados, decide que territorios va a dibujar y quita uno de los dados(este dado expresa el numero de territorios que va a dibujar).<br></p>
	    <p id="p0">-El jugador pasivo: debe escoger un numero entre los dados sobrantes.<br></p>
	    <p id="p0">-Una vez elegido. Todos dibujan el numero de territorios elegido por el jugador activo y el que ellos hayan escogido.<br></p>
        <p id="p0">-Luego, pasa el turno al siguiente jugador unido a la partida.</p>
        <p id="p0">-Se repite el proceso (con un dado menos).</p>    
    </h4>
    <p><strong>Fin de ronda:</strong></p>
    <h4>
        <p id="p0">-Cuando solo queda un dado, se termina la ronda.<br></p>
	    <p id="p0">-Se juega una nueva ronda con todos los dados.</p>    
    </h4>
    <p><strong>Reglas para dibujar:</strong></p>
    <h4>
        <p id="p0">-Tan solo puedes utilizar los territorios un numero determinado de veces.<br></p>
	    <p id="p0">-Cada territorio debe dibujarse concectado a otro territorio dibujado anteriormente.</p>    
    </h4>
    <p><strong>Poderes:</strong></p>
    <h3><p id="p0">---"+1/-1"---</p></h3>
    <h4>
        <p id="p0">-Si dibujas cualquier territorio encima de un "+1/-1", debes tachar uno de los cuadros de uso de ese poder.<br></p>
	    <p id="p0">-Este poder sirve para cambiar el valor de un dado en +1/-1</p>    
        <p id="p0">-Se puede usar este poder mas de una vez en el mismo turno.</p>    
    </h4>
    <h3><p id="p0">---"?"---</p></h3>
    <h4>
        <p id="p0">-Este poder es obligatorio usarlos al final del turno en el que se dibuja en su casilla.<br></p>
	    <p id="p0">-Sirve para calbular la puntuacion de cualquiera de los 4 criterios del juego.</p>    
    </h4>
    <p><strong>Fin del juego:</strong></p>
    <h4>
        <p id="p0">-Finaliza cuando algun jugador no puede dibujar mas territorios requeridos en su tablero.<br></p>
	    <p id="p0">-Se contabilizan los puntos.</p> 
        <p id="p0">-El que tenga la mayor suma gana (se puede quedar empate).</p>     
    </h4>
    <p><strong>Modo solitario:</strong></p>
    <h4>
        <p id="p0">-Se lanzan 3 dados.<br></p>
	    <p id="p0">-Se decide el tipo de territorio a dibujar.</p> 
        <p id="p0">-Ahora juega con 2 dados.</p>   
        <p id="p0">-No puede usar el "+1/-1" al dado elegido para el tipo de territorio.</p>
        <p id="p0">-Se toman los 3 dados de nuevo y comienza una nueva ronda.</p>  
    </h4>
    <p><strong>Video explicativo si tiene alguna duda:</strong></p>
    <iframe width="560" height="315" src="https://www.youtube.com/embed/EXVKaqZB4MQ" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>



</petclinic:layout>