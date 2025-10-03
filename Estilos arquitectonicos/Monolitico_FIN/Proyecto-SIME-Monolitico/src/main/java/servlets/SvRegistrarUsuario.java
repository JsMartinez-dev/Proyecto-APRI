/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author ACER-A315-59
 */
@WebServlet(name = "SvRegistrarUsuario", urlPatterns = {"/SvRegistrarUsuario"})
public class SvRegistrarUsuario extends HttpServlet {


    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /*
            primer nombre
            primer apellid
            correo
            institucion
            fecha de nacimiento
            contraseña
        */
        
        String primer_nombre = request.getParameter("primerNombre");
        String primer_apellido = request.getParameter("primerApellido");
        String correo = request.getParameter("correo");
        String institucion = request.getParameter("institucion");
        
        String fecha_nacimiento = request.getParameter("fechaNacimiento");
        LocalDate fecha_original = LocalDate.parse(fecha_nacimiento); //Parseamos la fecha de nacimiento
        
        LocalDate fecha_registro = LocalDate.now();
       
        String contraseña_plana = request.getParameter("contrasena");
        String contra_hasheada = BCrypt.hashpw(contraseña_plana,BCrypt.gensalt());
        System.out.println("Hash: "+contra_hasheada+"\n");
        
        String sql = "INSERT INTO usuarios(primer_nombre, primer_apellido, correo, institucion, fecha_nacimiento, fecha_registro, contrasena, estado, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
       
        //Vamos a guardarlo en la BD
        try(Connection conn = ConexionBD.getConexion();
                PreparedStatement stmt= conn.prepareStatement(sql)) {
              
          if(validarCorreo(correo)){
            stmt.setString(1,primer_nombre);
            stmt.setString(2,primer_apellido);
            stmt.setString(3,correo);
            stmt.setString(4,institucion);
            stmt.setObject(5,fecha_original);
            stmt.setObject(6,fecha_registro);
            stmt.setString(7,contra_hasheada);
            stmt.setBoolean(8, true);
            stmt.setString(9,"usuario");
            
            if(stmt.executeUpdate()>0){
                response.sendRedirect("InicioSesionUsuario.jsp?registro=exitoso");
            }else{
                request.setAttribute("mensajeF","INGRESO FALIDO");
                request.getRequestDispatcher("RegistrarUsuario.jsp").forward(request, response);

            }             
          }else{
                request.setAttribute("mensajeF","Correo ya existente");
                request.getRequestDispatcher("RegistrarUsuario.jsp").forward(request, response);

          }
 
            
                  
        }catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("mensajeF", "ERROR AL GUARDAR EL USUARIO");
            request.getRequestDispatcher("RegistrarUsuario.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
            e.printStackTrace();
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean validarCorreo(String correo) throws SQLException {

        String sql = "SELECT * FROM usuarios WHERE correo = ?";
        try(Connection conn = ConexionBD.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1,correo);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                return false;
            }
            
        }
        return true;
    }

}
