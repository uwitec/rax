--
-- PostgreSQL database dump
--

-- Started on 2008-10-28 19:20:21

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1490 (class 1259 OID 16530)
-- Dependencies: 1766 6
-- Name: sell_item; Type: TABLE; Schema: erp; Owner: erp; Tablespace: 
--

CREATE TABLE sell_item (
    id integer NOT NULL,
    sell_id integer NOT NULL,
    ware_id integer NOT NULL,
    number integer NOT NULL,
    price real DEFAULT 0 NOT NULL
);


ALTER TABLE erp.sell_item OWNER TO erp;

--
-- TOC entry 1497 (class 1259 OID 16560)
-- Dependencies: 1490 6
-- Name: sell_item_id_seq; Type: SEQUENCE; Schema: erp; Owner: erp
--

CREATE SEQUENCE sell_item_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE erp.sell_item_id_seq OWNER TO erp;

--
-- TOC entry 1773 (class 0 OID 0)
-- Dependencies: 1497
-- Name: sell_item_id_seq; Type: SEQUENCE OWNED BY; Schema: erp; Owner: erp
--

ALTER SEQUENCE sell_item_id_seq OWNED BY sell_item.id;


--
-- TOC entry 1767 (class 2604 OID 16569)
-- Dependencies: 1497 1490
-- Name: id; Type: DEFAULT; Schema: erp; Owner: erp
--

ALTER TABLE sell_item ALTER COLUMN id SET DEFAULT nextval('sell_item_id_seq'::regclass);


--
-- TOC entry 1770 (class 2606 OID 16577)
-- Dependencies: 1490 1490
-- Name: sell_lst_p_code; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY sell_item
    ADD CONSTRAINT sell_lst_p_code PRIMARY KEY (id);


--
-- TOC entry 1768 (class 1259 OID 16590)
-- Dependencies: 1490
-- Name: sell_item_sellId; Type: INDEX; Schema: erp; Owner: erp; Tablespace: 
--

CREATE INDEX "sell_item_sellId" ON sell_item USING btree (sell_id);


-- Completed on 2008-10-28 19:20:21

--
-- PostgreSQL database dump complete
--

