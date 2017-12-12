INSERT INTO partner (name, address, phonenumber, id, password) VALUES ('Jozsi', 'xy utca 1', '06505337655', 1, 'jozsi83');
INSERT INTO partner (name, address, phonenumber, id, password) VALUES ('Bela', 'zx utca 87', '06506673234', 2, '12345');
INSERT INTO partner (name, address, phonenumber, id, password) VALUES ('Tihamer', 'wv út 4', '06508656433', 3, 'jelszo');

INSERT INTO mechanic (name, id, password) VALUES ('Janos', 1, 'tizenketmajom');
INSERT INTO mechanic (name, id, password) VALUES ('Gabor', 2, 'reparetek');
INSERT INTO mechanic (name, id, password) VALUES ('Gandalf', 3, 'youshallnotpassword');

INSERT INTO material (id, name, price) VALUES (1, 'vas', 10);
INSERT INTO material (id, name, price) VALUES (2, 'fa', 20);
INSERT INTO material (id, name, price) VALUES (3, 'aluminium', 30);

INSERT INTO booking (id, partner_id, date, mechanic_id, type, comment) VALUES (0, 3, '2017-10-20 09:00:00', 2, 'COMPULSORY_SERVICE', 'kotelezo');
INSERT INTO booking (id, partner_id, date, mechanic_id, type, comment) VALUES (1, 1, '2017-05-30 13:00:00', 3, 'TECHNICAL_EXAMINATION', 'muszaki');
INSERT INTO booking (id, partner_id, date, mechanic_id, type, comment) VALUES (2, 2, '2017-07-02 16:00:00', 1, 'MALFUNCTION', 'hibas');

INSERT INTO worksheet (id, partner_id, date, mechanic_id, material_id/*, part_id*/) VALUES (0, 3, '2017-10-20 09:00:00', 2, 1);
INSERT INTO worksheet (id, partner_id, date, mechanic_id, material_id) VALUES (1, 1, '2017-05-30 13:00:00', 3, 2);
INSERT INTO worksheet (id, partner_id, date, mechanic_id, material_id) VALUES (2, 2, '2017-07-02 16:00:00', 1, 3);





