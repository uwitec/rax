--
-- PostgreSQL database dump
--

-- Started on 2009-02-22 15:24:10

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1495 (class 1259 OID 16547)
-- Dependencies: 6
-- Name: ware_category; Type: TABLE; Schema: erp; Owner: erp; Tablespace: 
--

CREATE TABLE ware_category (
    id integer NOT NULL,
    name character varying(64) NOT NULL
);


ALTER TABLE erp.ware_category OWNER TO erp;

--
-- TOC entry 1500 (class 1259 OID 16562)
-- Dependencies: 6 1495
-- Name: ware_category_id_seq; Type: SEQUENCE; Schema: erp; Owner: erp
--

CREATE SEQUENCE ware_category_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE erp.ware_category_id_seq OWNER TO erp;

--
-- TOC entry 1776 (class 0 OID 0)
-- Dependencies: 1500
-- Name: ware_category_id_seq; Type: SEQUENCE OWNED BY; Schema: erp; Owner: erp
--

ALTER SEQUENCE ware_category_id_seq OWNED BY ware_category.id;


--
-- TOC entry 1771 (class 2604 OID 16571)
-- Dependencies: 1500 1495
-- Name: id; Type: DEFAULT; Schema: erp; Owner: erp
--

ALTER TABLE ware_category ALTER COLUMN id SET DEFAULT nextval('ware_category_id_seq'::regclass);


--
-- TOC entry 1773 (class 2606 OID 16583)
-- Dependencies: 1495 1495
-- Name: ware_category_p_key; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY ware_category
    ADD CONSTRAINT ware_category_p_key PRIMARY KEY (id);


-- Completed on 2009-02-22 15:24:10

--
-- PostgreSQL database dump complete
--

