--
-- PostgreSQL database dump
--

-- Started on 2008-04-03 20:51:12

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1501 (class 1259 OID 16533)
-- Dependencies: 1776 6
-- Name: rax_faq; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE rax_faq (
    id integer DEFAULT nextval('rax_faq_id_seq'::regclass) NOT NULL,
    question character varying(128) NOT NULL,
    answer text NOT NULL,
    pub_date timestamp without time zone NOT NULL,
    pub boolean NOT NULL,
    address character varying(128),
    summary text
);


--
-- TOC entry 1778 (class 2606 OID 16581)
-- Dependencies: 1501 1501
-- Name: rax_faq_primary; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY rax_faq
    ADD CONSTRAINT rax_faq_primary PRIMARY KEY (id);


-- Completed on 2008-04-03 20:51:12

--
-- PostgreSQL database dump complete
--

