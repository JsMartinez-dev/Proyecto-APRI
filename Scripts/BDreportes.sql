--
-- Base de datos: MICROSERVICIO REPORTES
-- Contiene: reportes, respuestas_reporte
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
-- TOC entry 237 (class 1259 OID 18220)
-- Name: reportes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reportes (
    id_reporte integer NOT NULL,
    motivo character varying NOT NULL,
    fecha_reporte date NOT NULL,
    id_usuario integer,
    id_resena integer
);

ALTER TABLE public.reportes OWNER TO postgres;

--
-- Name: reportes_id_reporte_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reportes_id_reporte_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.reportes_id_reporte_seq OWNER TO postgres;
ALTER SEQUENCE public.reportes_id_reporte_seq OWNED BY public.reportes.id_reporte;

--
-- TOC entry 238 (class 1259 OID 18238)
-- Name: respuestas_reporte; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.respuestas_reporte (
    id_reporte integer NOT NULL,
    id_administrador integer NOT NULL,
    accion character varying NOT NULL,
    respuesta character varying,
    fecha_solucion date NOT NULL
);

ALTER TABLE public.respuestas_reporte OWNER TO postgres;

--
-- Alter sequences
--

ALTER TABLE ONLY public.reportes ALTER COLUMN id_reporte SET DEFAULT nextval('public.reportes_id_reporte_seq'::regclass);

--
-- Constraints
--

ALTER TABLE ONLY public.reportes
    ADD CONSTRAINT reportes_pkey PRIMARY KEY (id_reporte);

ALTER TABLE ONLY public.respuestas_reporte
    ADD CONSTRAINT respuestas_reporte_pkey PRIMARY KEY (id_reporte, id_administrador);

--
-- Foreign Keys (mantienen referencia interna dentro del microservicio)
--

ALTER TABLE ONLY public.respuestas_reporte
    ADD CONSTRAINT respuestas_reporte_id_reporte_fkey FOREIGN KEY (id_reporte) REFERENCES public.reportes(id_reporte);

--
-- Nota: Los siguientes campos son INTEGER sin FK para comunicación entre microservicios:
-- - id_usuario en: reportes
-- - id_resena en: reportes
-- - id_administrador en: respuestas_reporte
--