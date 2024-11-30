package org.facturacion.principal.services;

import java.sql.SQLException;
import java.util.List;

import org.facturacion.principal.dao.DaoRole;
import org.facturacion.principal.dao.DaoUsuario;
import org.facturacion.principal.excepciones.ExceptionLogin;
import org.facturacion.principal.models.Role;
import org.facturacion.principal.models.Usuario;

public class LoginService implements ILoginService{
	
	private DaoUsuario daousuario;
	private DaoRole daoroles;
	
	public LoginService()
	{
		daousuario=new DaoUsuario();
		daoroles=new DaoRole();
	}
	
	@Override
	public Usuario validarUsuario(String username,String password) throws SQLException, Exception,ExceptionLogin
	{
		Usuario usuario=null;
		
		usuario=daousuario.findByString(username);
		if(usuario!=null)
		{
			if(usuario.getPassword().equals(password))
			{
				return usuario;
			}
		}
		
		throw new ExceptionLogin();
	}

	@Override
	public List<Role> listarRoles() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return daoroles.findAll();
	}

	@Override
	public Role obtenerRole(Usuario usuario) throws SQLException, Exception {
		
			for(Role r:daoroles.findAll())
			{
				if(usuario.getRole().getNombre().equals(r.getNombre()))
					return r;
			}
		
		
			
		return null;
	}
	
	

}
