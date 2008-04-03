--
-- PostgreSQL database dump
--

-- Started on 2008-04-03 20:52:05

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1506 (class 1259 OID 16553)
-- Dependencies: 6
-- Name: rax_picture; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE rax_picture (
    id integer NOT NULL,
    article_id integer NOT NULL,
    title character varying(32) NOT NULL,
    file_path character varying(128) NOT NULL,
    size integer NOT NULL,
    upload_date timestamp without time zone NOT NULL,
    mime_type character varying(32) NOT NULL,
    summary character varying(128) NOT NULL
);


--
-- TOC entry 1507 (class 1259 OID 16556)
-- Dependencies: 6 1506
-- Name: rax_picture_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE rax_picture_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- TOC entry 1781 (class 0 OID 0)
-- Dependencies: 1507
-- Name: rax_picture_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE rax_picture_id_seq OWNED BY rax_picture.id;


--
-- TOC entry 1776 (class 2604 OID 16573)
-- Dependencies: 1507 1506
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE rax_picture ALTER COLUMN id SET DEFAULT nextval('rax_picture_id_seq'::regclass);


--
-- TOC entry 1778 (class 2606 OID 16587)
-- Dependencies: 1506 1506
-- Name: rax_picture_primary; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY rax_picture
    ADD CONSTRAINT rax_picture_primary PRIMARY KEY (id);


-- Completed on 2008-04-03 20:52:05

--
-- PostgreSQL database dump complete
--

