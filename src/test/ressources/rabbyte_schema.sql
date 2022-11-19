
CREATE SCHEMA rabbyte;


CREATE TABLE rabbyte.application (
                                     application_id integer NOT NULL,
                                     job_advertisement_id integer NOT NULL,
                                     user_id integer NOT NULL,
                                     datum date,
                                     inhalt text NOT NULL
);


CREATE SEQUENCE rabbyte."Bewerbung_bewerbung_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE rabbyte.user (
                                user_id integer NOT NULL,
                                email character varying(50) NOT NULL,
                                passwort character varying(128) NOT NULL,
                                plz numeric(5,0),
                                stadt character varying(25),
                                land character varying(25),
                                strasse character varying(256),
                                hausnummer character varying(5),
                                salt character varying(128) NOT NULL
);

CREATE SEQUENCE rabbyte."Nutzer_nutzer_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE rabbyte.job_advertisement (
                                           job_advertisement_id integer NOT NULL,
                                           user_id integer,
                                           title text NOT NULL,
                                           datum date,
                                           text text NOT NULL,
                                           type character varying
);

CREATE SEQUENCE rabbyte.seq_verification_id
    START WITH 60000000
    INCREMENT BY 1
    MINVALUE 60000000
    MAXVALUE 69999999
    CACHE 1;

CREATE TABLE rabbyte.verification_code (
                                           verification_id integer DEFAULT nextval('rabbyte.seq_verification_id'::regclass) NOT NULL,
                                           user_id integer NOT NULL,
                                           date date,
                                           token character varying(255) NOT NULL
);


CREATE SEQUENCE rabbyte."Stellenausschreibung_stellenausschreibung_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE rabbyte.student (
                                 user_id integer NOT NULL,
                                 vorname character varying(25) NOT NULL,
                                 nachname character varying(25) NOT NULL,
                                 fachbereich character varying(25)
);



CREATE SEQUENCE rabbyte."Student_nutzer_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE VIEW rabbyte."Studenten" AS
SELECT student.fachbereich,
       student.user_id,
       student.vorname,
       student.nachname
FROM rabbyte.student;



CREATE TABLE rabbyte.business (
                                  user_id integer NOT NULL,
                                  unternehmensname character varying(25) NOT NULL
);



CREATE SEQUENCE rabbyte.seq_bewerbung_id
    START WITH 10000000
    INCREMENT BY 1
    MINVALUE 10000000
    MAXVALUE 19999999
    CACHE 1;


CREATE SEQUENCE rabbyte.seq_nutzer_id
    START WITH 20000000
    INCREMENT BY 1
    MINVALUE 20000000
    MAXVALUE 29999999
    CACHE 1;




CREATE SEQUENCE rabbyte.seq_stellenausschreibung_id
    START WITH 30000000
    INCREMENT BY 1
    MINVALUE 30000000
    MAXVALUE 39999999
    CACHE 1;





ALTER TABLE ONLY rabbyte.application
    ADD CONSTRAINT "Bewerbung_pkey" PRIMARY KEY (application_id);


--
-- Name: user Nutzer_pkey; Type: CONSTRAINT; Schema: rabbyte; Owner: ihbib2s
--

ALTER TABLE ONLY rabbyte."user"
    ADD CONSTRAINT "Nutzer_pkey" PRIMARY KEY (user_id);


--
-- Name: job_advertisement Stellenausschreibung_pkey; Type: CONSTRAINT; Schema: rabbyte; Owner: ihbib2s
--

ALTER TABLE ONLY rabbyte.job_advertisement
    ADD CONSTRAINT "Stellenausschreibung_pkey" PRIMARY KEY (job_advertisement_id);


--
-- Name: student Student_pkey; Type: CONSTRAINT; Schema: rabbyte; Owner: ihbib2s
--

ALTER TABLE ONLY rabbyte.student
    ADD CONSTRAINT "Student_pkey" PRIMARY KEY (user_id);


--
-- Name: business Unternehmen_pkey; Type: CONSTRAINT; Schema: rabbyte; Owner: ihbib2s
--

ALTER TABLE ONLY rabbyte.business
    ADD CONSTRAINT "Unternehmen_pkey" PRIMARY KEY (user_id);


--
-- Name: application Bewerbung_nutzer_id_fkey; Type: FK CONSTRAINT; Schema: rabbyte; Owner: ihbib2s
--

ALTER TABLE ONLY rabbyte.application
    ADD CONSTRAINT "Bewerbung_nutzer_id_fkey" FOREIGN KEY (user_id) REFERENCES rabbyte.student(user_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: application Bewerbung_stellenausschreibung_id_fkey; Type: FK CONSTRAINT; Schema: rabbyte; Owner: ihbib2s
--

ALTER TABLE ONLY rabbyte.application
    ADD CONSTRAINT "Bewerbung_stellenausschreibung_id_fkey" FOREIGN KEY ("job_advertisement_id") REFERENCES rabbyte.job_advertisement(job_advertisement_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: job_advertisement Stellenausschreibung_nutzer_id_fkey; Type: FK CONSTRAINT; Schema: rabbyte; Owner: ihbib2s
--

ALTER TABLE ONLY rabbyte.job_advertisement
    ADD CONSTRAINT "Stellenausschreibung_nutzer_id_fkey" FOREIGN KEY (user_id) REFERENCES rabbyte.business(user_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: student Student_nutzer_id_fkey; Type: FK CONSTRAINT; Schema: rabbyte; Owner: ihbib2s
--

ALTER TABLE ONLY rabbyte.student
    ADD CONSTRAINT "Student_nutzer_id_fkey" FOREIGN KEY (user_id) REFERENCES rabbyte."user"(user_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: business Unternehmen_nutzer_id_fkey; Type: FK CONSTRAINT; Schema: rabbyte; Owner: ihbib2s
--

ALTER TABLE ONLY rabbyte.business
    ADD CONSTRAINT "Unternehmen_nutzer_id_fkey" FOREIGN KEY (user_id) REFERENCES rabbyte."user"(user_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY rabbyte.verification_code
    ADD CONSTRAINT verification_code_pkey PRIMARY KEY (verification_id);


--
-- Name: verification_code verification_code_user_id_fkey; Type: FK CONSTRAINT; Schema: rabbyte; Owner: ihbib2s
--

ALTER TABLE ONLY rabbyte.verification_code
    ADD CONSTRAINT verification_code_user_id_fkey FOREIGN KEY (user_id) REFERENCES rabbyte."user"(user_id);

--
-- PostgreSQL database dump complete
--
