INSERT INTO user (id, username, password) VALUES (1, 'almafa', 'almafa');

INSERT INTO partner (name, address, phonenumber, id, password) VALUES ('Jozsi', 'xy utca 1', '06505337655', 1, 'jozsi83');
INSERT INTO partner (name, address, phonenumber, id, password) VALUES ('Bela', 'zx utca 87', '06506673234', 2, '12345');
INSERT INTO partner (name, address, phonenumber, id, password) VALUES ('Tihamer', 'wv út 4', '06508656433', 3, 'jelszo');

INSERT INTO mechanic (name, id, password) VALUES ('Janos', 1, 'tizenketmajom');
INSERT INTO mechanic (name, id, password) VALUES ('Gabor', 2, 'reparetek');
INSERT INTO mechanic (name, id, password) VALUES ('Gandalf', 3, 'youshallnotpassword');

INSERT INTO material (id, name, price) VALUES (1, 'vas', 10);
INSERT INTO material (id, name, price) VALUES (2, 'fa', 20);
INSERT INTO material (id, name, price) VALUES (3, 'aluminium', 30);

INSERT INTO part (id, name, price) VALUES (1, 'csavar', 40);
INSERT INTO part (id, name, price) VALUES (2, 'kar', 70);
INSERT INTO part (id, name, price) VALUES (3, 'doboz', 100);

INSERT INTO rate (id, name, price) VALUES (1, 'ezmi', 1000);
INSERT INTO rate (id, name, price) VALUES (2, 'ezismi', 1200);
INSERT INTO rate (id, name, price) VALUES (3, 'megezismi', 2500);

INSERT INTO booking (id, partner_id, date, mechanic_id, type, comment) VALUES (0, 3, '2017.10.20.', 2, 'COMPULSORY_SERVICE', 'kotelezo');
INSERT INTO booking (id, partner_id, date, mechanic_id, type, comment) VALUES (1, 1, '2017.05.30.', 3, 'TECHNICAL_EXAMINATION', 'muszaki');
INSERT INTO booking (id, partner_id, date, mechanic_id, type, comment) VALUES (2, 2, '2017.07.02', 1, 'MALFUNCTION', 'hibás');

INSERT INTO worksheet (id, partner_id, mechanic_id, material_id, part_id) VALUES (1, 1, 1, 1, 1);
INSERT INTO worksheet (id, partner_id, mechanic_id, material_id, part_id) VALUES (2, 2, 2, 2, 2);
INSERT INTO worksheet (id, partner_id, mechanic_id, material_id, part_id) VALUES (3, 3, 3, 3, 3);

/*CREATE VIEW booked AS
select A.id "ID", C.name as "Partner Name", B.name as "Mechanic Name", B.phonenumber as "Mechanic Phone Number", A.date as "Date", A.type as "Type", A.comment as "Comment" from
booking A
INNER JOIN partner B ON A.mechanicid = B.id
INNER JOIN mechanic C ON A.partnerid = C.id;*/









