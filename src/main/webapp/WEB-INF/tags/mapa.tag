
<canvas id="canvas" width="1000" height="1000"></canvas>
<img id="source" src="resources/images/tablero.png" style="display:none">
<img id="BOSQUE" src="resources/images/favicon.png" style="display:none">

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