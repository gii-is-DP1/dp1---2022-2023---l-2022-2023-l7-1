 <%@ attribute name="accion" required="true" rtexprvalue="true" type="org.springframework.samples.petclinic.accion.Accion"
 description="Accion to be rendered" %>


 image = document.getElementById('${accion.turno.territorio}');
 ctx.drawImage(image,(${accion.casilla.columna})*100,${accion.casilla.fila*180},112,112);