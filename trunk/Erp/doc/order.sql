--
-- PostgreSQL database dump
--

-- Started on 2008-10-28 19:20:48

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1487 (class 1259 OID 16517)
-- Dependencies: 6
-- Name: order; Type: TABLE; Schema: erp; Owner: erp; Tablespace: 
--

CREATE TABLE "order" (
    id integer NOT NULL,
    create_date timestamp without time zone NOT NULL,
    status integer NOT NULL,
    fee real NOT NULL,
    comment character varying(64) NOT NULL
);


ALTER TABLE erp."order" OWNER TO erp;

--
-- TOC entry 1494 (class 1259 OID 16554)
-- Dependencies: 1487 6
-- Name: order_id_seq; Type: SEQUENCE; Schema: erp; Owner: erp
--

CREATE SEQUENCE order_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE erp.order_id_seq OWNER TO erp;

--
-- TOC entry 1773 (class 0 OID 0)
-- Dependencies: 1494
-- Name: order_id_seq; Type: SEQUENCE OWNED BY; Schema: erp; Owner: erp
--

ALTER SEQUENCE order_id_seq OWNED BY "order".id;


--
-- TOC entry 1766 (class 2604 OID 16566)
-- Dependencies: 1494 1487
-- Name: id; Type: DEFAULT; Schema: erp; Owner: erp
--

ALTER TABLE "order" ALTER COLUMN id SET DEFAULT nextval('order_id_seq'::regclass);


--
-- TOC entry 1769 (class 2606 OID 16575)
-- Dependencies: 1487 1487
-- Name: order_p_key; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_p_key PRIMARY KEY (id);


--
-- TOC entry 1767 (class 1259 OID 16586)
-- Dependencies: 1487
-- Name: order_crdate_index; Type: INDEX; Schema: erp; Owner: erp; Tablespace: 
--

CREATE INDEX order_crdate_index ON "order" USING btree (create_date);


--
-- TOC entry 1770 (class 1259 OID 16587)
-- Dependencies: 1487
-- Name: order_status_index; Type: INDEX; Schema: erp; Owner: erp; Tablespace: 
--

CREATE INDEX order_status_index ON "order" USING btree (status);


-- Completed on 2008-10-28 19:20:49

--
-- PostgreSQL database dump complete
--

