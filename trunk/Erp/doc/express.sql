--
-- PostgreSQL database dump
--

-- Started on 2009-02-15 00:11:55

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = erp, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1504 (class 1259 OID 17117)
-- Dependencies: 1772 1773 6
-- Name: express; Type: TABLE; Schema: erp; Owner: erp; Tablespace: 
--

CREATE TABLE express (
    id integer NOT NULL,
    ex_name character varying(256) NOT NULL,
    phone character varying(64) NOT NULL,
    settle_date date NOT NULL,
    tpl_size_x integer NOT NULL,
    tpl_size_y integer NOT NULL,
    tpl_date_x integer NOT NULL,
    tpl_date_y integer NOT NULL,
    tpl_date_fmt character varying(32) NOT NULL,
    tpl_src_name_x integer NOT NULL,
    tpl_src_name_y integer NOT NULL,
    tpl_src_phone_x integer NOT NULL,
    tpl_src_phone_y integer NOT NULL,
    tpl_src_zip_x integer NOT NULL,
    tpl_src_zip_y integer NOT NULL,
    tpl_src_address_lb_x integer NOT NULL,
    tpl_src_address_lb_y integer NOT NULL,
    tpl_src_address_rt_x integer NOT NULL,
    tpl_src_address_rt_y integer NOT NULL,
    tpl_dst_name_x integer NOT NULL,
    tpl_dst_name_y integer NOT NULL,
    tpl_dst_phone1_x integer NOT NULL,
    tpl_dst_phone1_y integer NOT NULL,
    tpl_dst_phone2_x integer NOT NULL,
    tpl_dst_phone2_y integer NOT NULL,
    tpl_dst_zip_x integer NOT NULL,
    tpl_dst_zip_y integer NOT NULL,
    tpl_dst_address_lb_x integer NOT NULL,
    tpl_dst_address_lb_y integer NOT NULL,
    tpl_dst_address_rt_x integer NOT NULL,
    tpl_dst_address_rt_y integer NOT NULL,
    tpl_src_address_indent integer DEFAULT 0 NOT NULL,
    tpl_dst_address_indent integer DEFAULT 0 NOT NULL
);


ALTER TABLE erp.express OWNER TO erp;

--
-- TOC entry 1503 (class 1259 OID 17115)
-- Dependencies: 6 1504
-- Name: express_id_seq; Type: SEQUENCE; Schema: erp; Owner: erp
--

CREATE SEQUENCE express_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE erp.express_id_seq OWNER TO erp;

--
-- TOC entry 1779 (class 0 OID 0)
-- Dependencies: 1503
-- Name: express_id_seq; Type: SEQUENCE OWNED BY; Schema: erp; Owner: erp
--

ALTER SEQUENCE express_id_seq OWNED BY express.id;


--
-- TOC entry 1780 (class 0 OID 0)
-- Dependencies: 1503
-- Name: express_id_seq; Type: SEQUENCE SET; Schema: erp; Owner: erp
--

SELECT pg_catalog.setval('express_id_seq', 1, false);


--
-- TOC entry 1771 (class 2604 OID 17120)
-- Dependencies: 1504 1503 1504
-- Name: id; Type: DEFAULT; Schema: erp; Owner: erp
--

ALTER TABLE express ALTER COLUMN id SET DEFAULT nextval('express_id_seq'::regclass);


--
-- TOC entry 1776 (class 0 OID 17117)
-- Dependencies: 1504
-- Data for Name: express; Type: TABLE DATA; Schema: erp; Owner: erp
--

COPY express (id, ex_name, phone, settle_date, tpl_size_x, tpl_size_y, tpl_date_x, tpl_date_y, tpl_date_fmt, tpl_src_name_x, tpl_src_name_y, tpl_src_phone_x, tpl_src_phone_y, tpl_src_zip_x, tpl_src_zip_y, tpl_src_address_lb_x, tpl_src_address_lb_y, tpl_src_address_rt_x, tpl_src_address_rt_y, tpl_dst_name_x, tpl_dst_name_y, tpl_dst_phone1_x, tpl_dst_phone1_y, tpl_dst_phone2_x, tpl_dst_phone2_y, tpl_dst_zip_x, tpl_dst_zip_y, tpl_dst_address_lb_x, tpl_dst_address_lb_y, tpl_dst_address_rt_x, tpl_dst_address_rt_y, tpl_src_address_indent, tpl_dst_address_indent) FROM stdin;
0	韵达	联系电话	2009-02-01	232	127	36	92	yy    MM    dd	65	85	30	56	85	56	20	60	108	85	165	86	125	56	125	61	175	56	115	60	201	85	0	0
1	申通	联系电话	1970-01-01	232	127	125	45	yy MM dd	37	63	38	55	72	55	34	71	94	90	117	63	117	55	117	50	157	55	115	71	174	90	0	0
2	圆通	联系电话	1970-01-01	232	127	24	18	yy     MM     dd	34	98	40	63	83	63	30	70	107	97	128	93	175	93	175	87	185	63	112	63	205	76	0	0
3	中通	联系电话	2008-12-31	232	127	39	20	MM    dd	42	90	42	56	85	56	40	66	109	89	132	90	132	56	132	61	175	56	129	66	200	89	0	0
4	天天	联系电话	2009-02-01	232	127	32	93	yyyy-MM-dd	50	53	32	66	82	73	20	72	110	91	132	53	179	66	127	66	179	73	114	72	201	91	5	5
5	顺丰	联系电话	1970-01-01	232	127	163	40	MM    dd	80	102	60	78	0	0	25	85	97	100	80	70	60	37	15	37	0	0	25	43	97	66	0	0
6	CCES	联系电话	1970-01-01	232	127	0	0		41	62	38	51	0	0	35	68	97	81	123	62	110	51	110	56	0	0	115	68	178	87	0	0
7	彪记	联系电话	1970-01-01	243	102	150	68	dd-MM-yyyy	51	20	22	25	0	0	15	30	93	61	113	20	100	27	100	22	0	0	94	38	171	61	0	0
8	宅急送	联系电话	1970-01-01	232	140	30	15	MM-dd	42	100	30	70	0	0	32	75	110	98	140	100	125	70	180	70	0	0	127	75	203	98	0	0
9	EMS	联系电话	1970-01-01	231	127	75	95	yyyy-MM-dd	36	89	80	91	85	58	20	62	108	77	130	89	175	91	175	86	178	58	113	62	208	77	0	0
-1	无		1970-01-01	232	127	0	0	yyyy-MM-dd	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
99	其他		1970-01-01	232	127	0	0	yyyy-MM-dd	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
\.


--
-- TOC entry 1775 (class 2606 OID 17122)
-- Dependencies: 1504 1504
-- Name: express_p_key; Type: CONSTRAINT; Schema: erp; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY express
    ADD CONSTRAINT express_p_key PRIMARY KEY (id);


-- Completed on 2009-02-15 00:11:55

--
-- PostgreSQL database dump complete
--

