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
import java.time.LocalDate;
import modelo.Usuario;
import servicio.RegistroServicio;

@WebServlet(name = "RegistroControll", urlPatterns = {"/RegistroControll"})
public class RegistroControll extends HttpServlet {


    private RegistroServicio registroServicio;
    
    
    @Override
    public void init(){
        registroServicio = new RegistroServicio();
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
        
        try{
            String primer_nombre = request.getParameter("primerNombre");
            String primer_apellido = request.getParameter("primerApellido");
            String correo = request.getParameter("correo");
            String institucion = request.getParameter("institucion");

            String fecha_nacimiento = request.getParameter("fechaNacimiento");
            LocalDate fecha_naci = LocalDate.parse(fecha_nacimiento);
            
            LocalDate fecha_registro = LocalDate.now();

            String contraseña_plana = request.getParameter("contrasena");        
            Usuario u = new Usuario(1, primer_nombre, primer_apellido, fecha_naci, fecha_registro, correo, institucion, contraseña_plana, false,"usuario");            
            if(registroServicio.registrarUsuario(u)){
                response.sendRedirect("InicioSesionUsuario.jsp?registro=exitoso");
            }else{
                request.setAttribute("mensajeF","INGRESO FALIDO");
                request.getRequestDispatcher("RegistrarUsuario.jsp").forward(request, response);                
            }
            
            
        }catch(Exception e){
            System.out.println("Error al DoPost Registro: "+e.getMessage());
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
