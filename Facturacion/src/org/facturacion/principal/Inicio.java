package org.facturacion.principal;

import org.facturacion.principal.controllers.PrincipalController;
import org.facturacion.principal.vista.FormPrincipal;

public class Inicio {
	
	public static void main(String[] args) {
		FormPrincipal form=new FormPrincipal();
		
		new PrincipalController(form);
	}

}
