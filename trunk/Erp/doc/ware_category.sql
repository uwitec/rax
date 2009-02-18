--
-- PostgreSQL database dump
--

-- Started on 2008-10-28 19:19:58

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1493 (class 1259 OID 16547)
-- Dependencies: 1766 6
-- Name: ware_category; Type: TABLE; Schema: erp; Owner: erp; Tablespace: 
--

CREATE TABLE ware_category (
    id integer NOT NULL,
    name character varying(64) NOT NULL
);


ALTER TABLE erp.ware_category OWNER TO erp;

--
-- TOC entry 1498 (class 1259 OID 16562)
-- Dependencies: 1493 6
-- Name: ware_category_id_seq; Type: SEQUENCE; Schema: erp; Owner: erp
--

CREATE SEQUENCE ware_category_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE erp.ware_category_id_seq OWNER TO erp;

--
-- TOC entry 1772 (class 0 OID 0)
-- Dependencies: 1498
-- Name: ware_category_id_seq; Type: SEQUENCE OWNED BY; Schema: erp; Owner: erp
--

ALTER SEQUENCE ware_category_id_seq OWNED BY ware_category.id;


--
-- TOC entry 1767 (class 2604 OID 16571)
-- Dependencies: 1498 1493
-- Name: id; Type: DEFAULT; Schema: erp; Owner: erp
--

ALTER TABLE ware_category ALTER COLUMN id SET DEFAULT nextval('ware_category_id_seq'::regclass);


--
-- TOC entry 1769 (class 2606 OID 16583)
-- Dependencies: 1493 1493
-- Name: ware_category_p_key; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY ware_category
    ADD CONSTRAINT ware_category_p_key PRIMARY KEY (id);


-- Completed on 2008-10-28 19:19:59

--
-- PostgreSQL database dump complete
--

