INSERT INTO user (id, username, password) VALUES (1, 'almafa', 'almafa');

INSERT INTO mechanics (name, address, phonenumber, id, password) VALUES ('Jozsi', 'xy utca 1', '06505337655', 1, 'jozsi83');
INSERT INTO mechanics (name, address, phonenumber, id, password) VALUES ('Bela', 'zx utca 87', '06506673234', 2, '12345');
INSERT INTO mechanics (name, address, phonenumber, id, password) VALUES ('Tihamer', 'wv út 4', '06508656433', 3, 'jelszo');

INSERT INTO partners (name, id, password) VALUES ('Janos', 1, 'tizenketmajom');
INSERT INTO partners (name, id, password) VALUES ('Gabor', 2, 'reparetek');
INSERT INTO partners (name, id, password) VALUES ('Gandalf', 3, 'youshallnotpassword');

INSERT INTO bookings (id, partnerid, date, mechanicid, type, comment) VALUES (1, 3, '2017.10.20.', 2, 'kötelező szerviz', 'kotelezo');
INSERT INTO bookings (id, partnerid, date, mechanicid, type, comment) VALUES (2, 1, '2017.05.30.', 3, 'műszaki vizsga', 'muszaki');
INSERT INTO bookings (id, partnerid, date, mechanicid, type, comment) VALUES (3, 2, '2017.07.02', 1, 'meghibásodás', 'hibás');

INSERT INTO worksheets (id, partnerid, mechanicid, materialid, partid) VALUES (1, 1, 1, 1, 1);
INSERT INTO worksheets (id, partnerid, mechanicid, materialid, partid) VALUES (2, 2, 2, 2, 2);
INSERT INTO worksheets (id, partnerid, mechanicid, materialid, partid) VALUES (3, 3, 3, 3, 3);

INSERT INTO materials (id, name, price) VALUES (1, 'vas', 10);
INSERT INTO materials (id, name, price) VALUES (2, 'fa', 20);
INSERT INTO materials (id, name, price) VALUES (3, 'aluminium', 30);

INSERT INTO parts (id, name, price) VALUES (1, 'csavar', 40);
INSERT INTO parts (id, name, price) VALUES (2, 'kar', 70);
INSERT INTO parts (id, name, price) VALUES (3, 'doboz', 100);

INSERT INTO rates (id, name, price) VALUES (1, 'ezmi', 1000);
INSERT INTO rates (id, name, price) VALUES (2, 'ezismi', 1200);
INSERT INTO rates (id, name, price) VALUES (3, 'megezismi', 2500);

CREATE VIEW booked AS
select A.id "ID", C.name as "Partner Name", B.name as "Mechanic Name", B.phonenumber as "Mechanic Phone Number", A.date as "Date", A.type as "Type", A.comment as "Comment" from
bookings A
INNER JOIN mechanics B ON A.mechanicid = B.id
INNER JOIN partners C ON A.partnerid = C.id;









