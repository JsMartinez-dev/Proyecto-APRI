--
-- Base de datos: MICROSERVICIO PQRS
-- Contiene: pqrs, comentarios_pqrs
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
-- TOC entry 231 (class 1259 OID 18163)
-- Name: pqrs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pqrs (
    id_pqrs integer NOT NULL,
    fecha_creacion date NOT NULL,
    asunto character varying NOT NULL,
    tipo character varying NOT NULL,
    estado boolean NOT NULL,
    id_usuario integer,
    id_leccion integer
);

ALTER TABLE public.pqrs OWNER TO postgres;

--
-- Name: pqrs_id_pqrs_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pqrs_id_pqrs_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.pqrs_id_pqrs_seq OWNER TO postgres;
ALTER SEQUENCE public.pqrs_id_pqrs_seq OWNED BY public.pqrs.id_pqrs;

--
-- TOC entry 233 (class 1259 OID 18182)
-- Name: comentarios_pqrs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comentarios_pqrs (
    id_comentario integer NOT NULL,
    contenido character varying NOT NULL,
    id_pqrs integer,
    id_usuario integer
);

ALTER TABLE public.comentarios_pqrs OWNER TO postgres;

--
-- Name: comentarios_pqrs_id_comentario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comentarios_pqrs_id_comentario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.comentarios_pqrs_id_comentario_seq OWNER TO postgres;
ALTER SEQUENCE public.comentarios_pqrs_id_comentario_seq OWNED BY public.comentarios_pqrs.id_comentario;

--
-- Alter sequences
--

ALTER TABLE ONLY public.pqrs ALTER COLUMN id_pqrs SET DEFAULT nextval('public.pqrs_id_pqrs_seq'::regclass);
ALTER TABLE ONLY public.comentarios_pqrs ALTER COLUMN id_comentario SET DEFAULT nextval('public.comentarios_pqrs_id_comentario_seq'::regclass);

--
-- Constraints
--

ALTER TABLE ONLY public.pqrs
    ADD CONSTRAINT pqrs_pkey PRIMARY KEY (id_pqrs);

ALTER TABLE ONLY public.comentarios_pqrs
    ADD CONSTRAINT comentarios_pqrs_pkey PRIMARY KEY (id_comentario);

--
-- Foreign Keys (mantienen referencia interna dentro del microservicio)
--

ALTER TABLE ONLY public.comentarios_pqrs
    ADD CONSTRAINT comentarios_pqrs_id_pqrs_fkey FOREIGN KEY (id_pqrs) REFERENCES public.pqrs(id_pqrs);

--
-- Nota: Los siguientes campos son INTEGER sin FK para comunicación entre microservicios:
-- - id_usuario en: pqrs, comentarios_pqrs
-- - id_leccion en: pqrs
--