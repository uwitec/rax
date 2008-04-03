--
-- PostgreSQL database dump
--

-- Started on 2008-04-03 20:51:51

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1503 (class 1259 OID 16542)
-- Dependencies: 1776 1777 6
-- Name: rax_link; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE rax_link (
    id integer DEFAULT nextval('rax_link_id_seq'::regclass) NOT NULL,
    category_id integer DEFAULT 0 NOT NULL,
    title character varying(64) NOT NULL,
    address character varying(128) NOT NULL,
    summary character varying(128) NOT NULL,
    pub boolean NOT NULL
);


--
-- TOC entry 1779 (class 2606 OID 16585)
-- Dependencies: 1503 1503
-- Name: rax_link_primary; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY rax_link
    ADD CONSTRAINT rax_link_primary PRIMARY KEY (id);


-- Completed on 2008-04-03 20:51:51

--
-- PostgreSQL database dump complete
--

