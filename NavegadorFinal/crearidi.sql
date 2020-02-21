CREATE TABLE `literal` (
  `lit_id` int(11) NOT NULL AUTO_INCREMENT,
  `idi_cod` varchar(3) NOT NULL,
  `lit_clau` varchar(30) NOT NULL,
  `lit_text` varchar(300) NOT NULL,
  PRIMARY KEY (`lit_id`),
  UNIQUE KEY `lit_clau` (`lit_clau`,`idi_cod`),
  KEY `idi_cod` (`idi_cod`),
  CONSTRAINT `literal_ibfk_1` FOREIGN KEY (`idi_cod`) REFERENCES `idioma` (`idi_cod`) ON DELETE CASCADE ON UPDATE RESTRICT
);

INSERT INTO IDIOMA values(1,'CAT');
INSERT INTO IDIOMA values(2,'ENG');
INSERT INTO IDIOMA values(3,'ES');


