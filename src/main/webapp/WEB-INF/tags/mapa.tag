
<canvas id="canvas" width="1500" height="1000"></canvas>
<img id="source" src="/resources/images/tablero2.png" style="display:none">
<img id="criterios" src="/resources/images/criteriosSinFondo.png" style="display:none">
<img id="BOSQUE" src="/resources/images/territorios/BOSQUE.png" style="display:none">
<img id="CASTILLO" src="/resources/images/territorios/CASTILLO.png" style="display:none">
<img id="MONTANA" src="/resources/images/territorios/MONTANA.png" style="display:none">
<img id="POBLADO" src="/resources/images/territorios/POBLADO.png" style="display:none">
<img id="PRADERA" src="/resources/images/territorios/PRADERA.png" style="display:none">
<img id="RIO" src="/resources/images/territorios/RIO.png" style="display:none">

<script>
function drawBoard(){ 
    var canvas = document.getElementById("canvas");
    var ctx = canvas.getContext("2d");
    var image = document.getElementById('source');
    var criterios = document.getElementById('criterios');
    ctx.drawImage(image, -150, -135, 1170, 1004);
    ctx.drawImage(criterios,696,98,597,502);
    <jsp:doBody/>
}
window.onload =drawBoard();
</script>