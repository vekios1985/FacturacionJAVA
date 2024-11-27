package org.facturacion.principal.utils;



public class Texto {
	
	public static String normalizar(String texto) throws Exception
	{
		 if (texto == null || texto.isEmpty()) {
			 throw new Exception("Existen campos de texto vacios");
		    }
		texto.toLowerCase();
		texto.trim();
		return texto.substring(0, 1).toUpperCase() + texto.substring(1);
	}
	
	public static String validarEmail(String texto) throws Exception
	{
		texto.trim();
		texto.toLowerCase();
		if(!texto.contains("@"))
		{
			throw new Exception("El email debe contener @");
		}
		return texto;
	}
	
	public static Integer validarDni(String dni) throws Exception
	{
		if(dni.length()!=8)
		{
			throw new Exception("El dni debe tener 8 digitos");
		}
		dni.trim();
		Integer n_dni;
		try
		{
			n_dni=Integer.parseInt(dni);
		}
		catch(NumberFormatException ex)
		{
			throw new Exception("Formato invalido de dni");
		}
		return n_dni;
	}

}
