package org.facturacion.principal.services;

import java.sql.SQLException;
import java.util.List;

import org.facturacion.principal.excepciones.ExceptionLogin;
import org.facturacion.principal.models.Role;
import org.facturacion.principal.models.Usuario;

public interface ILoginService {
	
	public Usuario validarUsuario(String username,String password)throws SQLException, Exception,ExceptionLogin;
	public Usuario findUsuarioByDni(Integer dni)throws Exception;
	public List<Usuario> findAllUser()throws Exception;
	public void saveUser(Usuario user)throws Exception;
	public List<Role> listarRoles()throws SQLException, Exception;
	public Role obtenerRole(Usuario usuario)throws SQLException, Exception;

}
