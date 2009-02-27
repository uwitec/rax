--
-- PostgreSQL database dump
--

-- Started on 2009-02-27 20:23:21

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1494 (class 1259 OID 16537)
-- Dependencies: 1771 1772 1773 1774 1776 1777 6
-- Name: ware; Type: TABLE; Schema: erp; Owner: erp; Tablespace: 
--

CREATE TABLE ware (
    id integer NOT NULL,
    name character varying(128) NOT NULL,
    cost real DEFAULT 0 NOT NULL,
    price real DEFAULT 0 NOT NULL,
    number integer NOT NULL,
    barcode character varying(16) NOT NULL,
    status integer DEFAULT 0 NOT NULL,
    idx_name tsvector,
    category_id integer DEFAULT 0 NOT NULL,
    lowest_cost real NOT NULL,
    number_alarm integer DEFAULT (-1) NOT NULL,
    number_alarm_enable integer DEFAULT 1 NOT NULL
);


ALTER TABLE erp.ware OWNER TO erp;

--
-- TOC entry 1501 (class 1259 OID 16564)
-- Dependencies: 6 1494
-- Name: ware_id_seq; Type: SEQUENCE; Schema: erp; Owner: erp
--

CREATE SEQUENCE ware_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE erp.ware_id_seq OWNER TO erp;

--
-- TOC entry 1786 (class 0 OID 0)
-- Dependencies: 1501
-- Name: ware_id_seq; Type: SEQUENCE OWNED BY; Schema: erp; Owner: erp
--

ALTER SEQUENCE ware_id_seq OWNED BY ware.id;


--
-- TOC entry 1775 (class 2604 OID 16570)
-- Dependencies: 1501 1494
-- Name: id; Type: DEFAULT; Schema: erp; Owner: erp
--

ALTER TABLE ware ALTER COLUMN id SET DEFAULT nextval('ware_id_seq'::regclass);


--
-- TOC entry 1782 (class 2606 OID 16585)
-- Dependencies: 1494 1494
-- Name: ware_p_key; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY ware
    ADD CONSTRAINT ware_p_key PRIMARY KEY (id);


--
-- TOC entry 1778 (class 1259 OID 16592)
-- Dependencies: 1494
-- Name: ware_barcode_index; Type: INDEX; Schema: erp; Owner: erp; Tablespace: 
--

CREATE INDEX ware_barcode_index ON ware USING btree (barcode);


--
-- TOC entry 1779 (class 1259 OID 16593)
-- Dependencies: 1494
-- Name: ware_category_index; Type: INDEX; Schema: erp; Owner: erp; Tablespace: 
--

CREATE INDEX ware_category_index ON ware USING btree (category_id);


--
-- TOC entry 1780 (class 1259 OID 16668)
-- Dependencies: 1494
-- Name: ware_idxname_fti; Type: INDEX; Schema: erp; Owner: erp; Tablespace: 
--

CREATE INDEX ware_idxname_fti ON ware USING gist (idx_name);


--
-- TOC entry 1783 (class 1259 OID 16596)
-- Dependencies: 1494
-- Name: ware_status_index; Type: INDEX; Schema: erp; Owner: erp; Tablespace: 
--

CREATE INDEX ware_status_index ON ware USING btree (status);


-- Completed on 2009-02-27 20:23:22

--
-- PostgreSQL database dump complete
--

