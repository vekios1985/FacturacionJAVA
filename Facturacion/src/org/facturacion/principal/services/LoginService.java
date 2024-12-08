package org.facturacion.principal.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.facturacion.principal.dao.DaoRole;
import org.facturacion.principal.dao.DaoUsuario;
import org.facturacion.principal.excepciones.ExceptionLogin;
import org.facturacion.principal.models.Role;
import org.facturacion.principal.models.Usuario;
import org.facturacion.principal.utils.Conexion;

public class LoginService implements ILoginService{
	
	private DaoUsuario daousuario;
	private DaoRole daoroles;
	private Connection cnn;
	
	public LoginService()throws Exception
	{
		
			cnn=Conexion.getConnection();
		
		daousuario=new DaoUsuario(cnn);
		daoroles=new DaoRole(cnn);
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

	@Override
	public List<Usuario> findAllUser() throws Exception {
		return daousuario.findAll();
	}

	@Override
	public void saveUser(Usuario user) throws Exception {
		try
		{
			daousuario.save(user);
		}
		catch(SQLIntegrityConstraintViolationException ex)
		{
			throw new Exception("El nombre de usuario o dni ya existe");
		}
		
	}

	@Override
	public Usuario findUsuarioByDni(Integer dni) throws Exception {
		return findAllUser().stream().filter(u->u.getDni().equals(dni)).findFirst().orElse(null);
	}
	
	

}
