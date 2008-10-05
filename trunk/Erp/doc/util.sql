--
-- PostgreSQL database dump
--

-- Started on 2008-10-05 18:16:00

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1499 (class 1259 OID 16510)
-- Dependencies: 7
-- Name: util; Type: TABLE; Schema: erp; Owner: erp; Tablespace: 
--

CREATE TABLE util (
    key character varying(64) NOT NULL,
    value character varying(256) NOT NULL
);


ALTER TABLE erp.util OWNER TO erp;

--
-- TOC entry 1768 (class 0 OID 16510)
-- Dependencies: 1499
-- Data for Name: util; Type: TABLE DATA; Schema: erp; Owner: erp
--

COPY util (key, value) FROM stdin;
\.


--
-- TOC entry 1767 (class 2606 OID 16519)
-- Dependencies: 1499 1499
-- Name: util_p_key; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY util
    ADD CONSTRAINT util_p_key PRIMARY KEY (key);


-- Completed on 2008-10-05 18:16:01

--
-- PostgreSQL database dump complete
--

