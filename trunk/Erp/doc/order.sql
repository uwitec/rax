--
-- PostgreSQL database dump
--

-- Started on 2008-05-23 21:09:54

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1484 (class 1259 OID 16406)
-- Dependencies: 7
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
-- TOC entry 1491 (class 1259 OID 16430)
-- Dependencies: 1484 7
-- Name: order_id_seq; Type: SEQUENCE; Schema: erp; Owner: erp
--

CREATE SEQUENCE order_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE erp.order_id_seq OWNER TO erp;

--
-- TOC entry 1769 (class 0 OID 0)
-- Dependencies: 1491
-- Name: order_id_seq; Type: SEQUENCE OWNED BY; Schema: erp; Owner: erp
--

ALTER SEQUENCE order_id_seq OWNED BY "order".id;


--
-- TOC entry 1764 (class 2604 OID 16465)
-- Dependencies: 1491 1484
-- Name: id; Type: DEFAULT; Schema: erp; Owner: erp
--

ALTER TABLE "order" ALTER COLUMN id SET DEFAULT nextval('order_id_seq'::regclass);


--
-- TOC entry 1766 (class 2606 OID 16454)
-- Dependencies: 1484 1484
-- Name: order_p_key; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY "order"
    ADD CONSTRAINT order_p_key PRIMARY KEY (id);


-- Completed on 2008-05-23 21:09:55

--
-- PostgreSQL database dump complete
--

