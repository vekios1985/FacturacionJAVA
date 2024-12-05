package org.facturacion.principal.utils;



public class Texto {
	
	public static String normalizar(String texto) throws Exception
	{
				
		 if (texto == null || texto.isEmpty()) {
			 throw new Exception("Existen campos de texto vacios");}
		 texto=texto.trim();
	        // Dividir el texto en palabras
	        String[] palabras = texto.toLowerCase().split("\\s+"); // Divide por espacios
	        
	        StringBuilder textoNormalizado = new StringBuilder();
	        
	        for (String palabra : palabras) {
	            // Convertir la primera letra en mayúscula y el resto en minúscula
	            if (!palabra.isEmpty()) {
	                textoNormalizado.append(Character.toUpperCase(palabra.charAt(0)))
	                                .append(palabra.substring(1))
	                                .append(" ");
	            }
	        }
	        
	        // Retornar el texto normalizado eliminando el espacio final
	        return textoNormalizado.toString().trim();
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
