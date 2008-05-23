--
-- PostgreSQL database dump
--

-- Started on 2008-05-23 21:10:45

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1488 (class 1259 OID 16419)
-- Dependencies: 1764 1765 1767 7
-- Name: ware; Type: TABLE; Schema: erp; Owner: erp; Tablespace: 
--

CREATE TABLE ware (
    id integer NOT NULL,
    name character varying(128) NOT NULL,
    cost real DEFAULT 0 NOT NULL,
    price real DEFAULT 0 NOT NULL,
    number integer NOT NULL,
    barcode character varying(16),
    status integer DEFAULT 0 NOT NULL,
    last_cost real
);


ALTER TABLE erp.ware OWNER TO erp;

--
-- TOC entry 1497 (class 1259 OID 16442)
-- Dependencies: 7 1488
-- Name: ware_id_seq; Type: SEQUENCE; Schema: erp; Owner: erp
--

CREATE SEQUENCE ware_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE erp.ware_id_seq OWNER TO erp;

--
-- TOC entry 1772 (class 0 OID 0)
-- Dependencies: 1497
-- Name: ware_id_seq; Type: SEQUENCE OWNED BY; Schema: erp; Owner: erp
--

ALTER SEQUENCE ware_id_seq OWNED BY ware.id;


--
-- TOC entry 1766 (class 2604 OID 16469)
-- Dependencies: 1497 1488
-- Name: id; Type: DEFAULT; Schema: erp; Owner: erp
--

ALTER TABLE ware ALTER COLUMN id SET DEFAULT nextval('ware_id_seq'::regclass);


--
-- TOC entry 1769 (class 2606 OID 16464)
-- Dependencies: 1488 1488
-- Name: ware_p_key; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY ware
    ADD CONSTRAINT ware_p_key PRIMARY KEY (id);


-- Completed on 2008-05-23 21:10:45

--
-- PostgreSQL database dump complete
--

