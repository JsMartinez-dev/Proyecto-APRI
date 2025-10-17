
package persistencia;

import edu.apri.collections.CircularList;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Libro;
import modelo.MaterialEducativo;
import modelo.Usuario;


public class DaoLibroImp implements DaoLibro{

    @Override
    public boolean registrar(Libro libro, Usuario usuario, InputStream inputStream) throws ApriException{

        String sql = "INSERT INTO libros( categoria, descripcion, nombre, anio_publicacion, estado, tipo, edicion, editorial, cantidad_paginas, id_usuario, archivopdf ) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try(Connection conn = ConexionBD.getConexion();
                PreparedStatement stmt = conn.prepareCall(sql)){
         System.out.println("Iniciando registro de libro: " + libro.getNombre());
           
            stmt.setString(1, libro.getCategoria());
            stmt.setString(2, libro.getDescripcion());
            stmt.setString(3,libro.getNombre());
            stmt.setDate(4, Date.valueOf(libro.getAño_publicacion()));
            stmt.setBoolean(5,libro.isEstado());
            stmt.setString(6, libro.getTipo());
            stmt.setInt(7,libro.getEdicion());
            stmt.setString(8,libro.getEditorial());
            stmt.setInt(9,libro.getCantidad_paginas());
            stmt.setInt(10,usuario.getId_persona());
            stmt.setBinaryStream(11, inputStream);
            
            if(stmt.executeUpdate()>0){
                return true;
            }
        }catch (SQLException ex) {
        throw new ApriException("Error al insertar el libro a la BD: " + ex.getMessage());
    }
        
        return false;
    }
    
    @Override
    public List<MaterialEducativo> buscarListUser(Usuario user) throws Exception {
        
        List<MaterialEducativo> listaM = new CircularList<>();
        
        String sql = "SELECT * FROM materiales_educativos WHERE id_usuario = ?";
        
        try(Connection conn = ConexionBD.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,user.getId_persona());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
               int idMaterialEducativo = rs.getInt("id_material_educativo");
               String categoria = rs.getString("categoria");
               String descripcion = rs.getString("descripcion");
               String nombre = rs.getString("nombre");
               LocalDate anioPublicacion = rs.getDate("anio_publicacion").toLocalDate();
               boolean estado = rs.getBoolean("estado");
               String tipo = rs.getString("tipo");               
               listaM.add(new MaterialEducativo(idMaterialEducativo, categoria, nombre, anioPublicacion, tipo, descripcion, estado, user));
               
                
            }
            
        } catch (SQLException e) {
            System.err.println("Error SQL al consultar materiales educativos: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Error: algún valor obtenido del ResultSet es nulo. " + e.getMessage());
        }catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
        return listaM;
    }
    
    @Override
    public boolean actualizar(Libro libro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(Libro libro) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Libro> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean cambiarEstadoF(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean cambiarEstadoT(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }




    
}
