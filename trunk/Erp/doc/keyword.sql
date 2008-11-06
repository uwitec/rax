--
-- PostgreSQL database dump
--

-- Started on 2008-11-06 19:45:48

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1503 (class 1259 OID 16632)
-- Dependencies: 6
-- Name: keyword; Type: TABLE; Schema: erp; Owner: erp; Tablespace: 
--

CREATE TABLE keyword (
    token character varying(32) NOT NULL
);


ALTER TABLE erp.keyword OWNER TO erp;

--
-- TOC entry 1771 (class 2606 OID 16639)
-- Dependencies: 1503 1503
-- Name: idx_token; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY keyword
    ADD CONSTRAINT idx_token UNIQUE (token);


-- Completed on 2008-11-06 19:45:48

--
-- PostgreSQL database dump complete
--

