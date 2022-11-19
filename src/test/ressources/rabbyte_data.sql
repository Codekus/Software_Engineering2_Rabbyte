--
-- Data for Name: user; Type: TABLE DATA; Schema: rabbyte; Owner: ihbib2s
--

INSERT INTO rabbyte.user (user_id, email, passwort, plz, stadt, land, strasse, hausnummer, salt)  VALUES (20000050 , 'max@gmx.de' , '8e0ac6815b51b73afd18c0ffd5d1eb0cdd3fb8ece16ad685f9199b1c4b55435bfde9ba85616ceee5072418c76d0de5086a0ae002f0be1665cd33a5af33b2f24f' , 12345 , 'Musterstadt' , 'DE' , 'Musterstadt' , '1' , '0a7031361558cd761bb1fda3e4b7367d7bf4b04930c8bad4da16b1b46648428366f1376f326c8a8cedc6a73d7c55e442e4c3ffa0a515d9190c770cffb2c56d73');
INSERT INTO rabbyte.user (user_id, email, passwort, plz, stadt, land, strasse, hausnummer, salt)  VALUES (20000090 , 'money@gmx.de' , 'f0573aff087a02407082db3e9c7bd95268c789727aeaa2f07c29b19a19f5360d31e3f4c44d7b158c766d8f541685bdd953fbe0a78b1bb840a3a54bc4a57fce56' , 54321 , 'Musterstadt' , 'DE' , 'Musterstadt' , '5' , '19049ccf217f2938f9370efcec79df9ba0b3cd5f3503ee01e1cbe084dddcfd151fea1a93e7b9edc869e9d276f9b6be803647c424cb4919b14b2079ba68d59e46');
INSERT INTO rabbyte.student (user_id, vorname, nachname, fachbereich) VALUES (20000050 , 'Max' , 'Mustermann' , 'Informatik');
INSERT INTO rabbyte.business(user_id, unternehmensname) VALUES (20000090 , 'MoneyInc');
INSERT INTO rabbyte.job_advertisement(job_advertisement_id, user_id, title, datum, text, type) VALUES (30000087 , 20000090 , 'Advertisement Title' , NULL , 'Advertisement Text' , 'Arbeitsstelle' );
INSERT INTO rabbyte.application(application_id, "job_advertisement_id", user_id, datum, inhalt) VALUES (10000001 ,  30000087 , 20000050 , NULL , 'Application Text');
INSERT INTO rabbyte.verification_code(verification_id, user_id, date, token) VALUES (60000017, 20000050, '2022-11-18', '606728a3-f4dd-4a12-a75d-1411773e25b7');