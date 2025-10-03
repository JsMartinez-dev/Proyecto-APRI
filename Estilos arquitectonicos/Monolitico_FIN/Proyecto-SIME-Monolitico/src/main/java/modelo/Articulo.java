
package modelo;



public class Articulo extends MaterialEducativo {
    
    private int volumen;
    private int cantidad_paginas;

    public Articulo(int volumen, int cantidad_paginas) {
        this.volumen = volumen;
        this.cantidad_paginas = cantidad_paginas;
    }

    public Articulo(int volumen, int cantidad_paginas, int id_materialEducativo, String categoria, String nombre, String año_publicacion, String tipo, String descripcion, boolean estado, Usuario usuario) {
        super(id_materialEducativo, categoria, nombre, año_publicacion, tipo, descripcion, estado, usuario);
        this.volumen = volumen;
        this.cantidad_paginas = cantidad_paginas;
    }

    @Override
    public String toString() {
        return "Articulo{" + "volumen=" + volumen + ", cantidad_paginas=" + cantidad_paginas + '}';
    }
    
    

    /**
     * @return the volumen
     */
    public int getVolumen() {
        return volumen;
    }

    /**
     * @param volumen the volumen to set
     */
    public void setVolumen(int volumen) {
        this.volumen = volumen;
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
