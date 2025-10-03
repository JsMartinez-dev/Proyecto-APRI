
package modelo;


public class Libro extends MaterialEducativo {
    
    private String edicion;
    private String editorial;
    private int cantidad_paginas;

    public Libro(String edicion, String editorial, int cantidad_paginas) {
        this.edicion = edicion;
        this.editorial = editorial;
        this.cantidad_paginas = cantidad_paginas;
    }

    public Libro(String edicion, String editorial, int cantidad_paginas, int id_materialEducativo, String categoria, String nombre, String año_publicacion, String tipo, String descripcion, boolean estado, Usuario usuario) {
        super(id_materialEducativo, categoria, nombre, año_publicacion, tipo, descripcion, estado, usuario);
        this.edicion = edicion;
        this.editorial = editorial;
        this.cantidad_paginas = cantidad_paginas;
    }

    @Override
    public String toString() {
        return "Libro{" +super.toString()+ ", edicion=" + edicion + ", editorial=" + editorial + ", cantidad_paginas=" + cantidad_paginas + '}';
    }
    
    

    /**
     * @return the edicion
     */
    public String getEdicion() {
        return edicion;
    }

    /**
     * @param edicion the edicion to set
     */
    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    /**
     * @return the editorial
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * @param editorial the editorial to set
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * @return the cantidad_paginas
     */
    public int getCantidad_paginas() {
        return cantidad_paginas;
    }

    /**
     * @param cantidad_paginas the cantidad_paginas to set
     */
    public void setCantidad_paginas(int cantidad_paginas) {
        this.cantidad_paginas = cantidad_paginas;
    }
    
    
}
