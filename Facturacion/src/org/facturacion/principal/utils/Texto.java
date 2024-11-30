package org.facturacion.principal.utils;

import java.util.Locale;

public class Texto {
	
	public static String normalizar(String texto) throws Exception
	{
		 if (texto == null || texto.isEmpty()) {
			 throw new Exception("Existen campos de texto vacios");
		    }
		texto=texto.toLowerCase(Locale.ROOT);
		texto=texto.trim();
		return texto.substring(0, 1).toUpperCase() + texto.substring(1);
	}
	
	public static String validarEmail(String texto) throws Exception
	{
		texto=texto.trim();
		texto=texto.toLowerCase();
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
		dni=dni.trim();
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
	
	public static Long validarCuit(String cuit) throws Exception
	{
		if(cuit.length()!=11)
		{
			throw new Exception("El cuit debe tener 11 digitos");
		}
		cuit=cuit.trim();
		Long n_cuit;
		try
		{
			n_cuit=Long.parseLong(cuit);
		}
		catch(NumberFormatException ex)
		{
			throw new Exception("Formato invalido de cuit");
		}
		return n_cuit;
	}


}
