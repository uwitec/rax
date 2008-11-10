--
-- PostgreSQL database dump
--

-- Started on 2008-11-10 23:56:39

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1491 (class 1259 OID 16537)
-- Dependencies: 1766 1767 1768 1769 6
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
    idx_name tsvector,
    category_id integer DEFAULT 0 NOT NULL,
    lowest_cost real NOT NULL
);


ALTER TABLE erp.ware OWNER TO erp;

--
-- TOC entry 1498 (class 1259 OID 16564)
-- Dependencies: 6 1491
-- Name: ware_id_seq; Type: SEQUENCE; Schema: erp; Owner: erp
--

CREATE SEQUENCE ware_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE erp.ware_id_seq OWNER TO erp;

--
-- TOC entry 1780 (class 0 OID 0)
-- Dependencies: 1498
-- Name: ware_id_seq; Type: SEQUENCE OWNED BY; Schema: erp; Owner: erp
--

ALTER SEQUENCE ware_id_seq OWNED BY ware.id;


--
-- TOC entry 1770 (class 2604 OID 16570)
-- Dependencies: 1498 1491
-- Name: id; Type: DEFAULT; Schema: erp; Owner: erp
--

ALTER TABLE ware ALTER COLUMN id SET DEFAULT nextval('ware_id_seq'::regclass);


--
-- TOC entry 1776 (class 2606 OID 16585)
-- Dependencies: 1491 1491
-- Name: ware_p_key; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY ware
    ADD CONSTRAINT ware_p_key PRIMARY KEY (id);


--
-- TOC entry 1771 (class 1259 OID 16592)
-- Dependencies: 1491
-- Name: ware_barcode_index; Type: INDEX; Schema: erp; Owner: erp; Tablespace: 
--

CREATE INDEX ware_barcode_index ON ware USING btree (barcode);


--
-- TOC entry 1772 (class 1259 OID 16593)
-- Dependencies: 1491
-- Name: ware_category_index; Type: INDEX; Schema: erp; Owner: erp; Tablespace: 
--

CREATE INDEX ware_category_index ON ware USING btree (category_id);


--
-- TOC entry 1773 (class 1259 OID 16668)
-- Dependencies: 1491
-- Name: ware_idxname_fti; Type: INDEX; Schema: erp; Owner: erp; Tablespace: 
--

CREATE INDEX ware_idxname_fti ON ware USING gist (idx_name);


--
-- TOC entry 1774 (class 1259 OID 16595)
-- Dependencies: 1491
-- Name: ware_number_index; Type: INDEX; Schema: erp; Owner: erp; Tablespace: 
--

CREATE INDEX ware_number_index ON ware USING btree (number);


--
-- TOC entry 1777 (class 1259 OID 16596)
-- Dependencies: 1491
-- Name: ware_status_index; Type: INDEX; Schema: erp; Owner: erp; Tablespace: 
--

CREATE INDEX ware_status_index ON ware USING btree (status);


-- Completed on 2008-11-10 23:56:39

--
-- PostgreSQL database dump complete
--

