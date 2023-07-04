# ************************************************************
# Sequel Pro SQL dump
# Version 5446
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 8.0.31)
# Database: turni
# Generation Time: 2023-07-04 08:55:37 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table giorni_off
# ------------------------------------------------------------

DROP TABLE IF EXISTS `giorni_off`;

CREATE TABLE `giorni_off` (
  `StaffID` int DEFAULT NULL,
  `Giorno` int DEFAULT NULL,
  KEY `StaffID` (`StaffID`),
  CONSTRAINT `giorni_off_ibfk_1` FOREIGN KEY (`StaffID`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `giorni_off` WRITE;
/*!40000 ALTER TABLE `giorni_off` DISABLE KEYS */;

INSERT INTO `giorni_off` (`StaffID`, `Giorno`)
VALUES
	(1,18),
	(1,19),
	(2,24),
	(2,25),
	(3,22),
	(3,23),
	(4,4),
	(4,5),
	(5,23),
	(5,27),
	(6,11),
	(6,12),
	(7,16),
	(7,20),
	(8,9),
	(8,13),
	(9,17),
	(9,27),
	(10,14),
	(10,15),
	(11,15),
	(11,16),
	(12,11),
	(12,12),
	(13,2),
	(13,23),
	(14,16),
	(14,17),
	(15,0),
	(15,1),
	(16,4),
	(16,24),
	(17,11),
	(17,16),
	(18,26),
	(18,27);

/*!40000 ALTER TABLE `giorni_off` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table staff
# ------------------------------------------------------------

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `ID` int NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `MaxTotalMin` int DEFAULT NULL,
  `MinTotalMin` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;

INSERT INTO `staff` (`ID`, `Name`, `MaxTotalMin`, `MinTotalMin`)
VALUES
	(1,'Mario Rossi',8640,7680),
	(2,'Giovanni Verdi',8640,7680),
	(3,'Matteo Neri',8640,7680),
	(4,'Simona Bianchi',8640,7680),
	(5,'Valeria Violi',8640,7680),
	(6,'Matilde Ferrari',8640,7680),
	(7,'Chiara Russo',8640,7680),
	(8,'Michele Marchetti',8640,7680),
	(9,'Simone Ricci',8640,7680),
	(10,'Laura Fiore',8640,7680),
	(11,'Antonio Bruni',8640,7680),
	(12,'Luca Ferrara',8640,7680),
	(13,'Lorenzo Costa',8640,7680),
	(14,'Sofia Rinaldi',8640,7680),
	(15,'Martina D\'Amico',8640,7680),
	(16,'Francesca Rosi',4320,3360),
	(17,'Giulia Romano',4320,3360),
	(18,'Orazio Marino',4320,3360);

/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table turni
# ------------------------------------------------------------

DROP TABLE IF EXISTS `turni`;

CREATE TABLE `turni` (
  `ID` int NOT NULL,
  `Giorno` int DEFAULT NULL,
  `TipoTurno` varchar(1) DEFAULT NULL,
  `Richiesta` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `turni` WRITE;
/*!40000 ALTER TABLE `turni` DISABLE KEYS */;

INSERT INTO `turni` (`ID`, `Giorno`, `TipoTurno`, `Richiesta`)
VALUES
	(0,0,'M',3),
	(1,0,'P',4),
	(2,0,'S',4),
	(3,1,'M',4),
	(4,1,'P',4),
	(5,1,'S',2),
	(6,2,'M',4),
	(7,2,'P',4),
	(8,2,'S',5),
	(9,3,'M',4),
	(10,3,'P',3),
	(11,3,'S',4),
	(12,4,'M',4),
	(13,4,'P',6),
	(14,4,'S',5),
	(15,5,'M',3),
	(16,5,'P',3),
	(17,5,'S',2),
	(18,6,'M',3),
	(19,6,'P',3),
	(20,6,'S',4),
	(21,7,'M',4),
	(22,7,'P',3),
	(23,7,'S',3),
	(24,8,'M',3),
	(25,8,'P',4),
	(26,8,'S',2),
	(27,9,'M',4),
	(28,9,'P',2),
	(29,9,'S',6),
	(30,10,'M',3),
	(31,10,'P',2),
	(32,10,'S',3),
	(33,11,'M',5),
	(34,11,'P',3),
	(35,11,'S',2),
	(36,12,'M',4),
	(37,12,'P',3),
	(38,12,'S',6),
	(39,13,'M',2),
	(40,13,'P',4),
	(41,13,'S',4),
	(42,14,'M',4),
	(43,14,'P',4),
	(44,14,'S',4),
	(45,15,'M',2),
	(46,15,'P',3),
	(47,15,'S',4),
	(48,16,'M',5),
	(49,16,'P',4),
	(50,16,'S',6),
	(51,17,'M',2),
	(52,17,'P',2),
	(53,17,'S',2),
	(54,18,'M',5),
	(55,18,'P',3),
	(56,18,'S',5),
	(57,19,'M',3),
	(58,19,'P',3),
	(59,19,'S',3),
	(60,20,'M',2),
	(61,20,'P',2),
	(62,20,'S',3),
	(63,21,'M',4),
	(64,21,'P',4),
	(65,21,'S',3),
	(66,22,'M',6),
	(67,22,'P',3),
	(68,22,'S',3),
	(69,23,'M',4),
	(70,23,'P',5),
	(71,23,'S',2),
	(72,24,'M',4),
	(73,24,'P',3),
	(74,24,'S',4),
	(75,25,'M',2),
	(76,25,'P',2),
	(77,25,'S',2),
	(78,26,'M',5),
	(79,26,'P',4),
	(80,26,'S',4),
	(81,27,'M',5),
	(82,27,'P',5),
	(83,27,'S',4);

/*!40000 ALTER TABLE `turni` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table turni_off_richiesta
# ------------------------------------------------------------

DROP TABLE IF EXISTS `turni_off_richiesta`;

CREATE TABLE `turni_off_richiesta` (
  `StaffID` int DEFAULT NULL,
  `Giorno` int DEFAULT NULL,
  `TipoTurno` varchar(1) DEFAULT NULL,
  `Peso` int DEFAULT NULL,
  KEY `StaffID` (`StaffID`),
  CONSTRAINT `turni_off_richiesta_ibfk_1` FOREIGN KEY (`StaffID`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `turni_off_richiesta` WRITE;
/*!40000 ALTER TABLE `turni_off_richiesta` DISABLE KEYS */;

INSERT INTO `turni_off_richiesta` (`StaffID`, `Giorno`, `TipoTurno`, `Peso`)
VALUES
	(1,11,'P',3),
	(1,12,'P',3),
	(1,21,'M',2),
	(1,22,'M',2),
	(1,23,'M',2),
	(1,24,'M',2),
	(4,17,'M',1),
	(4,18,'M',1),
	(6,15,'M',2),
	(7,0,'M',1),
	(7,1,'M',1),
	(9,19,'P',2),
	(9,20,'P',2),
	(9,21,'P',2),
	(9,22,'P',2),
	(10,2,'M',3),
	(10,3,'M',3),
	(10,4,'M',3),
	(10,5,'M',3),
	(10,17,'M',2),
	(10,18,'M',2),
	(10,19,'M',2),
	(10,20,'M',2),
	(12,2,'M',3),
	(12,16,'P',3),
	(12,17,'P',3),
	(12,18,'P',3),
	(12,25,'S',2),
	(12,26,'S',2),
	(12,27,'S',2),
	(13,3,'S',3),
	(13,16,'M',3),
	(13,17,'M',3),
	(13,18,'M',3),
	(13,19,'M',3),
	(13,20,'M',3),
	(14,0,'P',2),
	(14,1,'P',2),
	(14,2,'P',2),
	(14,3,'P',2),
	(14,4,'P',2),
	(14,9,'S',3),
	(14,10,'S',3),
	(14,11,'S',3),
	(14,12,'S',3),
	(14,13,'S',3),
	(16,15,'P',3),
	(18,23,'S',2);

/*!40000 ALTER TABLE `turni_off_richiesta` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table turni_on_richiesta
# ------------------------------------------------------------

DROP TABLE IF EXISTS `turni_on_richiesta`;

CREATE TABLE `turni_on_richiesta` (
  `StaffID` int DEFAULT NULL,
  `Giorno` int DEFAULT NULL,
  `TipoTurno` varchar(1) DEFAULT NULL,
  `Peso` int DEFAULT NULL,
  KEY `StaffID` (`StaffID`),
  CONSTRAINT `turni_on_richiesta_ibfk_1` FOREIGN KEY (`StaffID`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `turni_on_richiesta` WRITE;
/*!40000 ALTER TABLE `turni_on_richiesta` DISABLE KEYS */;

INSERT INTO `turni_on_richiesta` (`StaffID`, `Giorno`, `TipoTurno`, `Peso`)
VALUES
	(1,0,'M',3),
	(1,1,'M',3),
	(1,2,'M',3),
	(1,3,'M',3),
	(1,4,'M',3),
	(2,0,'S',2),
	(2,1,'S',2),
	(2,2,'S',2),
	(2,10,'S',2),
	(2,18,'S',2),
	(2,19,'S',2),
	(2,20,'S',2),
	(2,21,'S',2),
	(2,22,'S',2),
	(3,6,'S',1),
	(3,7,'S',1),
	(3,8,'S',1),
	(3,9,'S',1),
	(3,17,'P',1),
	(3,18,'P',1),
	(3,19,'P',1),
	(3,20,'P',1),
	(3,27,'P',2),
	(4,12,'D',3),
	(4,13,'D',3),
	(5,1,'S',1),
	(5,8,'P',1),
	(5,9,'P',1),
	(5,10,'P',1),
	(5,11,'P',1),
	(5,12,'P',1),
	(6,2,'S',1),
	(6,3,'S',1),
	(6,4,'S',1),
	(7,8,'M',2),
	(7,9,'M',2),
	(7,10,'M',2),
	(7,11,'M',2),
	(7,12,'M',2),
	(7,21,'S',2),
	(7,22,'S',2),
	(7,23,'S',2),
	(8,2,'S',1),
	(8,15,'S',3),
	(8,16,'S',3),
	(8,17,'S',3),
	(8,18,'S',3),
	(8,22,'S',1),
	(8,23,'S',1),
	(8,24,'S',1),
	(8,25,'S',1),
	(9,3,'P',3),
	(10,11,'S',3),
	(10,12,'S',3),
	(10,27,'M',1),
	(11,3,'S',1),
	(11,4,'S',1),
	(11,5,'S',1),
	(11,6,'S',1),
	(11,7,'S',1),
	(12,9,'P',1),
	(12,10,'P',1),
	(13,8,'M',2),
	(13,9,'M',2),
	(13,10,'M',2),
	(13,11,'M',2),
	(13,12,'M',2),
	(14,19,'M',3),
	(14,20,'M',3),
	(15,3,'P',1),
	(15,4,'P',1),
	(15,13,'S',3),
	(15,19,'P',1),
	(15,20,'P',1),
	(15,21,'P',1),
	(15,22,'P',1),
	(15,23,'P',1),
	(16,8,'P',2),
	(16,9,'P',2),
	(17,6,'P',1),
	(17,7,'P',1),
	(17,13,'M',3),
	(17,14,'M',3),
	(17,15,'M',3),
	(17,25,'M',3),
	(18,3,'S',2),
	(18,11,'S',3);

/*!40000 ALTER TABLE `turni_on_richiesta` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
