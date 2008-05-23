--
-- PostgreSQL database dump
--

-- Started on 2008-05-23 21:10:07

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1485 (class 1259 OID 16409)
-- Dependencies: 7
-- Name: order_item; Type: TABLE; Schema: erp; Owner: erp; Tablespace: 
--

CREATE TABLE order_item (
    id integer NOT NULL,
    order_id integer NOT NULL,
    ware_id integer NOT NULL,
    cost real NOT NULL,
    number integer NOT NULL
);


ALTER TABLE erp.order_item OWNER TO erp;

--
-- TOC entry 1492 (class 1259 OID 16432)
-- Dependencies: 1485 7
-- Name: order_item_id_seq; Type: SEQUENCE; Schema: erp; Owner: erp
--

CREATE SEQUENCE order_item_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE erp.order_item_id_seq OWNER TO erp;

--
-- TOC entry 1769 (class 0 OID 0)
-- Dependencies: 1492
-- Name: order_item_id_seq; Type: SEQUENCE OWNED BY; Schema: erp; Owner: erp
--

ALTER SEQUENCE order_item_id_seq OWNED BY order_item.id;


--
-- TOC entry 1764 (class 2604 OID 16466)
-- Dependencies: 1492 1485
-- Name: id; Type: DEFAULT; Schema: erp; Owner: erp
--

ALTER TABLE order_item ALTER COLUMN id SET DEFAULT nextval('order_item_id_seq'::regclass);


--
-- TOC entry 1766 (class 2606 OID 16452)
-- Dependencies: 1485 1485
-- Name: order_lst_p_key; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY order_item
    ADD CONSTRAINT order_lst_p_key PRIMARY KEY (id);


-- Completed on 2008-05-23 21:10:08

--
-- PostgreSQL database dump complete
--

