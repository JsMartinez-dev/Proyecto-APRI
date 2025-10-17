/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package presentacion;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;
import servicio.LoginServicio;

/**
 *
 * @author ACER-A315-59
 */
@WebServlet(name = "CerrarSesion", urlPatterns = {"/CerrarSesion"})
public class CerrarSesionControll extends HttpServlet {
    
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

        HttpSession Misesion = request.getSession(false);

        try {
            if(Misesion != null && Misesion.getAttribute("usuario") != null){
                Usuario u = (Usuario) Misesion.getAttribute("usuario");

                // Intentar cerrar sesión en la base de datos
                try {
                    loginServicio.cerrarSesion(u);
                } catch (Exception ex) {
                    System.out.println("Error al actualizar estado en BD: " + ex.getMessage());
                }

                Misesion.invalidate();
            }

            response.sendRedirect("InicioSesionUsuario.jsp");

        } catch (IllegalStateException ex) {
            System.out.println("Sesión ya invalidada: " + ex.getMessage());
            response.sendRedirect("InicioSesionUsuario.jsp");
        } catch (Exception ex) {
            System.out.println("Error al cerrar sesión: " + ex.getMessage());
            response.sendRedirect("InicioSesionUsuario.jsp");
        }
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
