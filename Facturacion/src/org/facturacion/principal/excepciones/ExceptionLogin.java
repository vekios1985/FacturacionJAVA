package org.facturacion.principal.excepciones;

public class ExceptionLogin extends Exception{
	
	
	private static final long serialVersionUID = 5395804667033537211L;

	public ExceptionLogin()
	{
		super("Credenciales Invalidas");
	}

}
