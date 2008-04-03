--
-- PostgreSQL database dump
--

-- Started on 2008-04-03 20:50:40

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1496 (class 1259 OID 16518)
-- Dependencies: 6
-- Name: rax_article_content; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE rax_article_content (
    article_id integer NOT NULL,
    page_id integer NOT NULL,
    content text NOT NULL
);


-- Completed on 2008-04-03 20:50:40

--
-- PostgreSQL database dump complete
--

