 <%@ attribute name="accion" required="true" rtexprvalue="true" type="org.springframework.samples.petclinic.accion.Accion"
 description="Accion to be rendered" %>

 image = document.getElementById('BOSQUE');
 ctx.drawImage(image,(${accion.idCasilla.columna})*100,${accion.idCasilla.fila*180},50,50);