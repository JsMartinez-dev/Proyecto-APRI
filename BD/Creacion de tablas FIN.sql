-- ===== Personas =====
CREATE TABLE personas (
  id_persona      serial PRIMARY KEY,
  primer_nombre   varchar NOT NULL,
  primer_apellido varchar NOT NULL,
  correo          varchar NOT NULL,
  institucion     varchar NOT NULL,
  fecha_nacimiento date NOT NULL,
  fecha_registro   date NOT NULL,
  contrasena      varchar NOT NULL,
  estado          boolean NOT NULL,
  tipo            varchar NOT NULL
);

-- ===== Usuarios y Administradores (heredan de Personas) =====
CREATE TABLE usuarios (
  CHECK (tipo = 'usuario')
) INHERITS (personas);

CREATE TABLE administradores (
  rol varchar NOT NULL,
  CHECK (tipo = 'administrador')
) INHERITS (personas);

-- ===== Materiales educativos =====
CREATE TABLE materiales_educativos (
  id_material_educativo serial PRIMARY KEY,
  categoria varchar NOT NULL,
  descripcion varchar,
  nombre varchar NOT NULL,
  anio_publicacion date NOT NULL,
  estado boolean NOT NULL,
  tipo varchar NOT NULL,
  id_usuario integer REFERENCES personas(id_persona)
);

-- Subtipos de Material Educativo
CREATE TABLE cursos (
  duracion time NOT NULL
) INHERITS (materiales_educativos);

CREATE TABLE articulos (
  volumen integer NOT NULL,
  cantidad_paginas integer NOT NULL
) INHERITS (materiales_educativos);

CREATE TABLE libros (
  edicion integer NOT NULL,
  editorial varchar NOT NULL,
  cantidad_paginas integer NOT NULL
) INHERITS (materiales_educativos);

-- ===== Modulos y Lecciones =====
CREATE TABLE modulos (
  id_modulo serial PRIMARY KEY,
  titulo varchar NOT NULL,
  id_curso integer REFERENCES materiales_educativos(id_material_educativo)
);

CREATE TABLE lecciones (
  id_leccion serial PRIMARY KEY,
  nombre varchar NOT NULL,
  url_video varchar NOT NULL,
  descripcion varchar,
  id_modulo integer REFERENCES modulos(id_modulo)
);

-- ===== PQRs y Comentarios =====
CREATE TABLE pqrs (
  id_pqrs serial PRIMARY KEY,
  fecha_creacion date NOT NULL,
  asunto varchar NOT NULL,
  tipo varchar NOT NULL,
  estado boolean NOT NULL,
  id_usuario integer REFERENCES personas(id_persona),
  id_leccion integer REFERENCES lecciones(id_leccion)
);

CREATE TABLE comentarios_pqrs (
  id_comentario serial PRIMARY KEY,
  contenido varchar NOT NULL,
  id_pqrs integer REFERENCES pqrs(id_pqrs),
  id_usuario integer REFERENCES personas(id_persona)
);

-- ===== Reseñas, Reportes y Respuestas =====
CREATE TABLE resenas (
  id_resenas serial PRIMARY KEY,
  comentario varchar,
  cantidad_estrellas integer NOT NULL,
  id_usuario integer REFERENCES personas(id_persona),
  id_material_educativo integer REFERENCES materiales_educativos(id_material_educativo)
);

CREATE TABLE reportes (
  id_reporte serial PRIMARY KEY,
  motivo varchar NOT NULL,
  fecha_reporte date NOT NULL,
  id_usuario integer REFERENCES personas(id_persona),
  id_resena integer REFERENCES resenas(id_resenas)
);

CREATE TABLE respuestas_reporte (
  id_reporte integer REFERENCES reportes(id_reporte),
  id_administrador integer REFERENCES personas(id_persona),
  accion varchar NOT NULL,
  respuesta varchar,
  fecha_solucion date NOT NULL,
  PRIMARY KEY (id_reporte, id_administrador)
);

-- ===== Descargas, Progreso, Inscripciones =====
CREATE TABLE descargas (
  id_usuario integer REFERENCES personas(id_persona),
  id_material_educativo integer REFERENCES materiales_educativos(id_material_educativo),
  fecha_descarga date NOT NULL,
  PRIMARY KEY (id_usuario, id_material_educativo)
);

CREATE TABLE progreso_leccion (
  id_usuario integer REFERENCES personas(id_persona),
  id_leccion integer REFERENCES lecciones(id_leccion),
  completado boolean NOT NULL,
  fecha_completado timestamp,
  PRIMARY KEY (id_usuario, id_leccion)
);

CREATE TABLE registro_inscripciones (
  id_usuario integer REFERENCES personas(id_persona),
  id_curso integer REFERENCES materiales_educativos(id_material_educativo),
  completado boolean NOT NULL,
  fecha_inscripcion timestamp NOT NULL,
  PRIMARY KEY (id_usuario, id_curso)
);
