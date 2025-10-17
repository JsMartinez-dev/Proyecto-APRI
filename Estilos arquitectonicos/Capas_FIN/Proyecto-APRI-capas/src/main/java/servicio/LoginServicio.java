
package servicio;

import modelo.Administrador;
import modelo.Persona;
import modelo.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import persistencia.DaoUsuario;
import persistencia.DaoUsuarioImp;


public class LoginServicio {
   
    
    DaoUsuario daoUser;

    public LoginServicio() {
        daoUser = new DaoUsuarioImp();
    }
    
    public Persona iniciarSesion(String correo, String contraseña) throws Exception{
        Persona u = daoUser.correoExistente(correo);
        
        if(u!=null){
            
            if(u instanceof Usuario){
                if(validarContraseñaUsuario(contraseña,u.getContraseña())){
                   return u;
                }
            }

            if(u instanceof Administrador){
                
                if(contraseña.equals(u.getContraseña())){
                   return u;
                }
            }
            
        }
        
        return null;
    }

    private boolean validarContraseñaUsuario(String contraseña, String contraseñaHash) {
        
        return BCrypt.checkpw(contraseña, contraseñaHash);
    }

   
    public boolean cerrarSesion(Usuario u) throws Exception{
        
        return daoUser.cambiarEstadoF(u.getId_persona());
    }
    
    
}
