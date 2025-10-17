
package servicio;

import modelo.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import persistencia.DaoUsuario;
import persistencia.DaoUsuarioImp;


public class RegistroServicio {
    
    
    private final DaoUsuario daoUser;

    public RegistroServicio() {
        this.daoUser = new DaoUsuarioImp();
    }
    
    public String hashearPassword(String password){
    
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }
    
    public boolean registrarUsuario(Usuario u) throws Exception{
        
        if(u!=null){       
                if(daoUser.validarCorreo(u.getCorreo())){
                    if(u.getFecha_nacimiento() !=null ){
                        u.setContraseña(hashearPassword(u.getContraseña()));
                        return daoUser.registrar(u);
                    }
                }
            
        }
        
        return false;
    }
    
    
    
}
