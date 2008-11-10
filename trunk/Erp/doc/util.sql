--
-- PostgreSQL database dump
--

-- Started on 2008-11-10 23:56:47

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1490 (class 1259 OID 16534)
-- Dependencies: 6
-- Name: util; Type: TABLE; Schema: erp; Owner: erp; Tablespace: 
--

CREATE TABLE util (
    key character varying(64) NOT NULL,
    value character varying(256) NOT NULL
);


ALTER TABLE erp.util OWNER TO erp;

--
-- TOC entry 1768 (class 2606 OID 16581)
-- Dependencies: 1490 1490
-- Name: util_p_key; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY util
    ADD CONSTRAINT util_p_key PRIMARY KEY (key);


--
-- TOC entry 1766 (class 1259 OID 16667)
-- Dependencies: 1490
-- Name: util_key; Type: INDEX; Schema: erp; Owner: erp; Tablespace: 
--

CREATE INDEX util_key ON util USING btree (key);


-- Completed on 2008-11-10 23:56:47

--
-- PostgreSQL database dump complete
--

