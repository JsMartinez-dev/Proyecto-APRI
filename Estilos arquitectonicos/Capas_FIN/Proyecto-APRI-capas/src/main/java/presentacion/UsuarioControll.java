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
import java.util.List;
import modelo.MaterialEducativo;
import modelo.Usuario;
import servicio.LibroServicio;
import servicio.UsuarioServicio;

/**
 *
 * @author ACER-A315-59
 */
@WebServlet(name = "UsuarioControll", urlPatterns = {"/UsuarioControll"})
public class UsuarioControll extends HttpServlet {

     UsuarioServicio listUServicio;
     LibroServicio buscarMatServicio;

     
     @Override
    public void init(){
        listUServicio=new UsuarioServicio();
        buscarMatServicio =new LibroServicio();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String accion = request.getParameter("accion");
        System.out.println("GUARDAMOS LA ACCION");
        
        if(accion==null){
            response.sendRedirect("InicioSesionUsuario.jsp");
            return;
        }
        
        switch (accion) {
            case "dashboardUser" -> dashboardUser(request,response);
            case "dashboardAdmin" -> dashboardAdmin(request,response);
            case "GestionUsuario" -> gestionUsuario(request,response);
            default -> throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void dashboardUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession miSesion = request.getSession(false);
        
        Usuario usuario =(Usuario) miSesion.getAttribute("usuario");
        
        List<MaterialEducativo> listMat = buscarMatServicio.buscarMaterial(usuario);       
        if(listMat!=null){
            System.out.println("NOT NULLL");
            request.setAttribute("listaMar",listMat);
            request.getRequestDispatcher("DashboardUser.jsp").forward(request, response);

        }else{
            System.out.println("NULLL");
            request.getRequestDispatcher("InicioSesionUsuario.jsp?dashUser=fallo");
        }
    }

    private void dashboardAdmin(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            List<Usuario> listaU = listUServicio.listUser();
            
            if(listaU !=null){
                request.setAttribute("listU",listaU);
                request.getRequestDispatcher("DashboardAdmin.jsp").forward(request, response);
            }else{
                response.getWriter().print("ERROR EN EL GESTION USUARIOS");
            }
        } catch (Exception ex) {
            System.out.println("ERROR EN EL DoGett, Error: "+ex.getMessage());
        }
    }

    private void gestionUsuario(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            List<Usuario> listaU = listUServicio.listUser();
            
            if(listaU !=null){
                request.setAttribute("listU",listaU);
                request.getRequestDispatcher("DashboardAdmin_GU.jsp").forward(request, response);
            }else{
                response.getWriter().print("ERROR EN EL GESTION USUARIOS");
            }
        } catch (Exception ex) {
            System.out.println("ERROR EN EL DoGett, Error: "+ex.getMessage());
        }
    }

}
