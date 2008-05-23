--
-- PostgreSQL database dump
--

-- Started on 2008-05-23 21:10:57

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1489 (class 1259 OID 16424)
-- Dependencies: 7
-- Name: ware_category; Type: TABLE; Schema: erp; Owner: erp; Tablespace: 
--

CREATE TABLE ware_category (
    id integer NOT NULL,
    name character varying(64) NOT NULL
);


ALTER TABLE erp.ware_category OWNER TO erp;

--
-- TOC entry 1495 (class 1259 OID 16438)
-- Dependencies: 7 1489
-- Name: ware_category_id_seq; Type: SEQUENCE; Schema: erp; Owner: erp
--

CREATE SEQUENCE ware_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE erp.ware_category_id_seq OWNER TO erp;

--
-- TOC entry 1769 (class 0 OID 0)
-- Dependencies: 1495
-- Name: ware_category_id_seq; Type: SEQUENCE OWNED BY; Schema: erp; Owner: erp
--

ALTER SEQUENCE ware_category_id_seq OWNED BY ware_category.id;


--
-- TOC entry 1764 (class 2604 OID 16470)
-- Dependencies: 1495 1489
-- Name: id; Type: DEFAULT; Schema: erp; Owner: erp
--

ALTER TABLE ware_category ALTER COLUMN id SET DEFAULT nextval('ware_category_id_seq'::regclass);


--
-- TOC entry 1766 (class 2606 OID 16460)
-- Dependencies: 1489 1489
-- Name: ware_category_p_key; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY ware_category
    ADD CONSTRAINT ware_category_p_key PRIMARY KEY (id);


-- Completed on 2008-05-23 21:10:57

--
-- PostgreSQL database dump complete
--

