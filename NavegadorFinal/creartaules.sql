CREATE TABLE `idioma` (
  `idi_id` int(11) NOT NULL AUTO_INCREMENT,
  `idi_cod` varchar(3) NOT NULL,
  PRIMARY KEY (`idi_id`),
  UNIQUE KEY `idi_cod` (`idi_cod`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `literal` (
  `lit_id` int(11) NOT NULL AUTO_INCREMENT,
  `idi_cod` varchar(3) NOT NULL,
  `lit_clau` varchar(30) NOT NULL,
  `lit_text` varchar(300) NOT NULL,
  PRIMARY KEY (`lit_id`),
  UNIQUE KEY `lit_clau` (`lit_clau`,`idi_cod`),
  KEY `idi_cod` (`idi_cod`),
  CONSTRAINT `literal_ibfk_1` FOREIGN KEY (`idi_cod`) REFERENCES `idioma` (`idi_cod`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `log_texte` varchar(200) NOT NULL,
  `log_data` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
