package org.springframework.samples.petclinic.accion;

import java.util.List;

public class AccionForm {
    private List<Accion> acciones;

    public List<Accion> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<Accion> acciones) {
		this.acciones = acciones;
	}


	@Override
	public String toString() {
		return "{" +
			" acciones='" + getAcciones() + "'" +
			"}";
	}

}
