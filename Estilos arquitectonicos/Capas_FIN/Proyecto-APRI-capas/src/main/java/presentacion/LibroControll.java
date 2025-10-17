/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package presentacion;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.time.LocalDate;
import modelo.Libro;
import modelo.Usuario;
import servicio.LibroServicio;

/**
 *
 * @author ACER-A315-59
 */
@WebServlet(name = "SubirLibroControll", urlPatterns = {"/SubirLibroControll"})
@MultipartConfig(maxFileSize = 16177215) //Decimos que el maximo de tamaño de un archivo es de 16 mb
public class LibroControll extends HttpServlet {
    
    LibroServicio subirMatServicio;
    
    @Override
    public void init(){
        subirMatServicio = new LibroServicio();
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
        HttpSession sesion = request.getSession(false);
        System.out.println("LLEGO SERVLET");
        
      
        String nombreLibro = request.getParameter("nombreLibro");
        String descripcion = request.getParameter("Descripcion");
        String categoria = request.getParameter("Categoria");
        int edicion = Integer.parseInt(request.getParameter("edicion"));
        String año_publi =  request.getParameter("AñoPublicacion");
        LocalDate año_publicacion = LocalDate.parse(año_publi);
        String editorial =  request.getParameter("Editorial");
        int cantPaginas = Integer.parseInt(request.getParameter("cantPaginas"));
   
    
        InputStream inputStream = null;
        Part filePart = request.getPart("archivoPDF");
        if(filePart!=null){
            inputStream = filePart.getInputStream();
            System.out.println("NO ESTA VACIO ");
        }else{
            System.out.println("ESTA VACIO ");
            
        }
   
        Usuario user =  (Usuario) sesion.getAttribute("usuario");
        Libro librito  = new Libro(edicion, editorial, cantPaginas, 1, categoria, nombreLibro, año_publicacion,"libro", descripcion, true, user);
     
        try {
            if(subirMatServicio.subirLibro(librito,user,inputStream)){
                request.setAttribute("mensaje","Ingreso exitoso");
                request.getRequestDispatcher("SubirLibro.jsp").forward(request, response);
            }else{
                request.setAttribute("error","No se pudo agregar libro");
                request.getRequestDispatcher("SubirLibro.jsp").forward(request, response);                
            }
        }catch (NumberFormatException ex) {
        request.setAttribute("error", "Error en formato de números: " + ex.getMessage());
        request.getRequestDispatcher("SubirLibro.jsp").forward(request, response);
        } catch (Exception ex) {
        request.setAttribute("error", "Error al procesar: " + ex.getMessage());
        request.getRequestDispatcher("SubirLibro.jsp").forward(request, response);
        ex.printStackTrace(); // Para ver el error completo en consola
        }
    
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
