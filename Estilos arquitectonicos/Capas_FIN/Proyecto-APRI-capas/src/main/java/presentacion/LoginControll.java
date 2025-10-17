/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package presentacion;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Administrador;
import modelo.Persona;
import modelo.Usuario;
import servicio.LoginServicio;

/**
 *
 * @author ACER-A315-59
 */
@WebServlet(name = "LoginControll", urlPatterns = {"/LoginControll"})
public class LoginControll extends HttpServlet {

    
    LoginServicio loginServicio;
    
    @Override
    public void init(){
        loginServicio = new LoginServicio();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          
        try {
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("contrasena");
            
            Persona u = loginServicio.iniciarSesion(correo,contrasena);
            if(u!=null){
                HttpSession miSesion = request.getSession();                
                miSesion.setAttribute("usuario",u);

                if(u instanceof Usuario){

                    response.sendRedirect("UsuarioControll?accion=dashboardUser");
                }
                
                if(u instanceof Administrador){
                   response.sendRedirect("UsuarioControll?accion=dashboardAdmin");

                }
                
            }else{
                request.setAttribute("mensajeF","Correo o contraseña incorrecta");
                request.getRequestDispatcher("InicioSesionUsuario.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginControll.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
