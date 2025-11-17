--
-- Base de datos: MICROSERVICIO MATERIALES EDUCATIVOS
-- Contiene: materiales_educativos, articulos, cursos, libros, modulos, lecciones, 
--           descargas, resenas, progreso_leccion, registro_inscripciones
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';
SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 18108)
-- Name: materiales_educativos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.materiales_educativos (
    id_material_educativo integer NOT NULL,
    categoria character varying NOT NULL,
    descripcion character varying,
    nombre character varying NOT NULL,
    anio_publicacion date NOT NULL,
    estado boolean NOT NULL,
    tipo character varying NOT NULL,
    id_usuario integer
);

ALTER TABLE public.materiales_educativos OWNER TO postgres;

--
-- Name: materiales_educativos_id_material_educativo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.materiales_educativos_id_material_educativo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.materiales_educativos_id_material_educativo_seq OWNER TO postgres;
ALTER SEQUENCE public.materiales_educativos_id_material_educativo_seq OWNED BY public.materiales_educativos.id_material_educativo;

--
-- TOC entry 224 (class 1259 OID 18122)
-- Name: articulos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.articulos (
    volumen integer NOT NULL,
    cantidad_paginas integer NOT NULL,
    archivopdf bytea
)
INHERITS (public.materiales_educativos);

ALTER TABLE public.articulos OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 18116)
-- Name: cursos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cursos (
    duracion integer NOT NULL,
    nivel character varying(50)
)
INHERITS (public.materiales_educativos);

ALTER TABLE public.cursos OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 18128)
-- Name: libros; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.libros (
    edicion integer NOT NULL,
    editorial character varying NOT NULL,
    cantidad_paginas integer NOT NULL,
    archivopdf bytea
)
INHERITS (public.materiales_educativos);

ALTER TABLE public.libros OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 18135)
-- Name: modulos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.modulos (
    id_modulo integer NOT NULL,
    titulo character varying NOT NULL,
    id_curso integer
);

ALTER TABLE public.modulos OWNER TO postgres;

--
-- Name: modulos_id_modulo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.modulos_id_modulo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.modulos_id_modulo_seq OWNER TO postgres;
ALTER SEQUENCE public.modulos_id_modulo_seq OWNED BY public.modulos.id_modulo;

--
-- TOC entry 229 (class 1259 OID 18149)
-- Name: lecciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lecciones (
    id_leccion integer NOT NULL,
    nombre character varying NOT NULL,
    url_video character varying NOT NULL,
    descripcion character varying,
    id_modulo integer
);

ALTER TABLE public.lecciones OWNER TO postgres;

--
-- Name: lecciones_id_leccion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lecciones_id_leccion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.lecciones_id_leccion_seq OWNER TO postgres;
ALTER SEQUENCE public.lecciones_id_leccion_seq OWNED BY public.lecciones.id_leccion;

--
-- TOC entry 239 (class 1259 OID 18255)
-- Name: descargas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.descargas (
    id_usuario integer NOT NULL,
    id_material_educativo integer NOT NULL,
    fecha_descarga date NOT NULL
);

ALTER TABLE public.descargas OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 18201)
-- Name: resenas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.resenas (
    id_resenas integer NOT NULL,
    comentario character varying,
    cantidad_estrellas integer NOT NULL,
    id_usuario integer,
    id_material_educativo integer
);

ALTER TABLE public.resenas OWNER TO postgres;

--
-- Name: resenas_id_resenas_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.resenas_id_resenas_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.resenas_id_resenas_seq OWNER TO postgres;
ALTER SEQUENCE public.resenas_id_resenas_seq OWNED BY public.resenas.id_resenas;

--
-- TOC entry 240 (class 1259 OID 18270)
-- Name: progreso_leccion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.progreso_leccion (
    id_usuario integer NOT NULL,
    id_leccion integer NOT NULL,
    completado boolean NOT NULL,
    fecha_completado timestamp without time zone
);

ALTER TABLE public.progreso_leccion OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 18285)
-- Name: registro_inscripciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.registro_inscripciones (
    id_usuario integer NOT NULL,
    id_curso integer NOT NULL,
    completado boolean NOT NULL,
    fecha_inscripcion timestamp without time zone NOT NULL
);

ALTER TABLE public.registro_inscripciones OWNER TO postgres;

--
-- Alter sequences
--

ALTER TABLE ONLY public.materiales_educativos ALTER COLUMN id_material_educativo SET DEFAULT nextval('public.materiales_educativos_id_material_educativo_seq'::regclass);
ALTER TABLE ONLY public.articulos ALTER COLUMN id_material_educativo SET DEFAULT nextval('public.materiales_educativos_id_material_educativo_seq'::regclass);
ALTER TABLE ONLY public.cursos ALTER COLUMN id_material_educativo SET DEFAULT nextval('public.materiales_educativos_id_material_educativo_seq'::regclass);
ALTER TABLE ONLY public.libros ALTER COLUMN id_material_educativo SET DEFAULT nextval('public.materiales_educativos_id_material_educativo_seq'::regclass);
ALTER TABLE ONLY public.modulos ALTER COLUMN id_modulo SET DEFAULT nextval('public.modulos_id_modulo_seq'::regclass);
ALTER TABLE ONLY public.lecciones ALTER COLUMN id_leccion SET DEFAULT nextval('public.lecciones_id_leccion_seq'::regclass);
ALTER TABLE ONLY public.resenas ALTER COLUMN id_resenas SET DEFAULT nextval('public.resenas_id_resenas_seq'::regclass);

--
-- Constraints
--

ALTER TABLE ONLY public.materiales_educativos
    ADD CONSTRAINT materiales_educativos_pkey PRIMARY KEY (id_material_educativo);

ALTER TABLE ONLY public.modulos
    ADD CONSTRAINT modulos_pkey PRIMARY KEY (id_modulo);

ALTER TABLE ONLY public.lecciones
    ADD CONSTRAINT lecciones_pkey PRIMARY KEY (id_leccion);

ALTER TABLE ONLY public.descargas
    ADD CONSTRAINT descargas_pkey PRIMARY KEY (id_usuario, id_material_educativo);

ALTER TABLE ONLY public.resenas
    ADD CONSTRAINT resenas_pkey PRIMARY KEY (id_resenas);

ALTER TABLE ONLY public.progreso_leccion
    ADD CONSTRAINT progreso_leccion_pkey PRIMARY KEY (id_usuario, id_leccion);

ALTER TABLE ONLY public.registro_inscripciones
    ADD CONSTRAINT registro_inscripciones_pkey PRIMARY KEY (id_usuario, id_curso);

--
-- Foreign Keys (mantienen referencia interna dentro del microservicio)
--

ALTER TABLE ONLY public.lecciones
    ADD CONSTRAINT lecciones_id_modulo_fkey FOREIGN KEY (id_modulo) REFERENCES public.modulos(id_modulo);

ALTER TABLE ONLY public.descargas
    ADD CONSTRAINT descargas_id_material_educativo_fkey FOREIGN KEY (id_material_educativo) REFERENCES public.materiales_educativos(id_material_educativo);

ALTER TABLE ONLY public.resenas
    ADD CONSTRAINT resenas_id_material_educativo_fkey FOREIGN KEY (id_material_educativo) REFERENCES public.materiales_educativos(id_material_educativo);

ALTER TABLE ONLY public.progreso_leccion
    ADD CONSTRAINT progreso_leccion_id_leccion_fkey FOREIGN KEY (id_leccion) REFERENCES public.lecciones(id_leccion);

--
-- Nota: Los siguientes campos son INTEGER sin FK para comunicación entre microservicios:
-- - id_usuario en: materiales_educativos, descargas, resenas, progreso_leccion, registro_inscripciones
-- - id_curso en: registro_inscripciones
--


SELECT * FROM public.libros