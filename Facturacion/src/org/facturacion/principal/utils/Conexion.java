package org.facturacion.principal.utils;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

	//private static String url="jdbc:mariadb://localhost:3306/java_curso";
	//private static String usuario="root";
	//private static String clave="";
	private static Connection cnn=null;
	private static int cantidad=0;
	
	public static Connection getConnection()throws Exception
	{
		if(cnn==null||cnn.isClosed())
		{
			
				Properties config = new Properties();
				InputStream configInput = null;
				//configInput = new FileInputStream("cnf.properties");
				configInput=Conexion.class.getClassLoader().getResourceAsStream("configuracion/cnf.properties");
			    config.load(configInput);	
				cnn=DriverManager.getConnection(config.getProperty("url"), config.getProperty("user"),config.getProperty("pass"));
				cantidad++;
			
			
		}
		return cnn;
	}
	
	public static void closeConnection() throws SQLException
	{
		if(cantidad==1)
		{
			
			cnn.close();
			cantidad--;
		}
		else
		{
			cantidad--;
		}
	}
	
}
