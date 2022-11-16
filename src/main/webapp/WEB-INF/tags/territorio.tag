 <%@ attribute name="accion" required="true" rtexprvalue="true" type="org.springframework.samples.petclinic.accion.Accion"
 description="Accion to be rendered" %>


 image = document.getElementById('${accion.turno.territorio}');
 ctx.drawImage(image,'${accion.casilla.fila}'*10,'${accion.casilla.columna}'*10,100,100);