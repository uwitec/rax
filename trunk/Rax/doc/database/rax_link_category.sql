--
-- PostgreSQL database dump
--

-- Started on 2008-04-03 20:51:40

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1505 (class 1259 OID 16549)
-- Dependencies: 1776 6
-- Name: rax_link_category; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE rax_link_category (
    id integer DEFAULT nextval('rax_link_category_id_seq'::regclass) NOT NULL,
    title character varying(64) NOT NULL,
    summary character varying(128) NOT NULL
);


--
-- TOC entry 1778 (class 2606 OID 16583)
-- Dependencies: 1505 1505
-- Name: rax_link_category_primary; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY rax_link_category
    ADD CONSTRAINT rax_link_category_primary PRIMARY KEY (id);


-- Completed on 2008-04-03 20:51:40

--
-- PostgreSQL database dump complete
--

