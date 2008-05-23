--
-- PostgreSQL database dump
--

-- Started on 2008-05-23 21:11:11

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1490 (class 1259 OID 16427)
-- Dependencies: 7
-- Name: ware_grouping; Type: TABLE; Schema: erp; Owner: erp; Tablespace: 
--

CREATE TABLE ware_grouping (
    id integer NOT NULL,
    ware_id integer NOT NULL,
    ware_category_id integer NOT NULL
);


ALTER TABLE erp.ware_grouping OWNER TO erp;

--
-- TOC entry 1496 (class 1259 OID 16440)
-- Dependencies: 7 1490
-- Name: ware_grouping_id_seq; Type: SEQUENCE; Schema: erp; Owner: erp
--

CREATE SEQUENCE ware_grouping_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE erp.ware_grouping_id_seq OWNER TO erp;

--
-- TOC entry 1769 (class 0 OID 0)
-- Dependencies: 1496
-- Name: ware_grouping_id_seq; Type: SEQUENCE OWNED BY; Schema: erp; Owner: erp
--

ALTER SEQUENCE ware_grouping_id_seq OWNED BY ware_grouping.id;


--
-- TOC entry 1764 (class 2604 OID 16471)
-- Dependencies: 1496 1490
-- Name: id; Type: DEFAULT; Schema: erp; Owner: erp
--

ALTER TABLE ware_grouping ALTER COLUMN id SET DEFAULT nextval('ware_grouping_id_seq'::regclass);


--
-- TOC entry 1766 (class 2606 OID 16462)
-- Dependencies: 1490 1490
-- Name: ware_grouping_p_key; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY ware_grouping
    ADD CONSTRAINT ware_grouping_p_key PRIMARY KEY (id);


-- Completed on 2008-05-23 21:11:11

--
-- PostgreSQL database dump complete
--

