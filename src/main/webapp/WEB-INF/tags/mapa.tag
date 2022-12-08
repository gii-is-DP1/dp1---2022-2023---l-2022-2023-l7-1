
<canvas id="canvas" width="1000" height="1000"></canvas>
<img id="source" src="/resources/images/tablero.png" style="display:none">
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
    ctx.drawImage(image, 0, 0, 1170, 1004);     
    <jsp:doBody/>
}
window.onload =drawBoard();
</script>