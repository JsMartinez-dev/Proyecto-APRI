/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ACER-A315-59
 */
@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

  
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
        
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        
        try {
            Usuario user = validarCorreo(correo);
            if(user!=null){
                
                if(BCrypt.checkpw(contrasena,user.getContraseña())){
                    
                    HttpSession miSesion = request.getSession();
                    miSesion.setAttribute("usuario", user);     
                    
                    if(user.getTipo().equals("usuario")){
                        response.sendRedirect("DashboardUser.jsp");
                    }else{
                        System.out.println("FALTA HACER EL DASHBOARD DEL ADMIN XD");
                    }
                    
                }else{
                    request.setAttribute("mensajeF","Contraseña incorrecta");
                    request.getRequestDispatcher("InicioSesionUsuario.jsp").forward(request, response);
                   
                }
                
                
            }else{
                    request.setAttribute("mensajeF","Correo inexistente");
                    request.getRequestDispatcher("InicioSesionUsuario.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR en el SQL, ERROR: "+ex.getMessage());
        }
        
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Usuario validarCorreo(String correo) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";
        System.out.println("ANTES DEL QUERY");

        try(Connection con = ConexionBD.getConexion(); PreparedStatement stmt = con.prepareStatement(sql)){
            System.out.println("ANTES DEL execute");
            
            stmt.setString(1,correo);
            try(ResultSet rs = stmt.executeQuery()){
                
               if(rs.next()){ //Significa que encontro la fila con el correo
                       int id= rs.getInt(1);
                       String primer_nombre = rs.getString(2);
                       String primer_apellido = rs.getString(3);
                       String correito = rs.getString(4);
                       String institucion = rs.getString(5);
                       Date fecha_nacimientoDate = rs.getDate(6);
                       String fecha_nacimiento = String.valueOf(fecha_nacimientoDate);
                       Date fecha_registroDate = rs.getDate(7);
                       String fecha_registro = String.valueOf(fecha_registroDate);
                       String contraseña_hasheada = rs.getString(8);
                       boolean estado = rs.getBoolean(9);
                       String tipo = rs.getString(10);

                       Usuario u = new Usuario(id, primer_nombre, primer_apellido, fecha_nacimiento, fecha_registro, correito, institucion,contraseña_hasheada,estado,tipo);
                       return u;        
               }               
            }
            
            
        }
        return null;
    }

}
