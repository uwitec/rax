--
-- PostgreSQL database dump
--

-- Started on 2008-04-03 20:50:55

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1498 (class 1259 OID 16526)
-- Dependencies: 6
-- Name: rax_attachment; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE rax_attachment (
    id integer NOT NULL,
    title character varying(32) NOT NULL,
    upload_name character varying(128) NOT NULL,
    upload_date timestamp without time zone NOT NULL,
    file_path character varying(128) NOT NULL,
    downloads integer NOT NULL,
    size integer NOT NULL,
    article_id integer NOT NULL
);


--
-- TOC entry 1499 (class 1259 OID 16529)
-- Dependencies: 1498 6
-- Name: rax_attachment_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE rax_attachment_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- TOC entry 1781 (class 0 OID 0)
-- Dependencies: 1499
-- Name: rax_attachment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE rax_attachment_id_seq OWNED BY rax_attachment.id;


--
-- TOC entry 1776 (class 2604 OID 16572)
-- Dependencies: 1499 1498
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE rax_attachment ALTER COLUMN id SET DEFAULT nextval('rax_attachment_id_seq'::regclass);


--
-- TOC entry 1778 (class 2606 OID 16579)
-- Dependencies: 1498 1498
-- Name: rax_attachment_primary; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY rax_attachment
    ADD CONSTRAINT rax_attachment_primary PRIMARY KEY (id);


-- Completed on 2008-04-03 20:50:56

--
-- PostgreSQL database dump complete
--

