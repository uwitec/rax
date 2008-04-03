--
-- PostgreSQL database dump
--

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: rax_article_category; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE rax_article_category (
    id integer NOT NULL,
    parent_id integer NOT NULL,
    name character varying(32) NOT NULL,
    summary character varying(128) NOT NULL,
    l_thread integer NOT NULL,
    r_thread integer NOT NULL,
    create_date timestamp without time zone NOT NULL
);


--
-- Name: rax_article_category_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE rax_article_category_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: rax_article_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE rax_article_category_id_seq OWNED BY rax_article_category.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE rax_article_category ALTER COLUMN id SET DEFAULT nextval('rax_article_category_id_seq'::regclass);


--
-- Name: rax_article_category_primary; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY rax_article_category
    ADD CONSTRAINT rax_article_category_primary PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

