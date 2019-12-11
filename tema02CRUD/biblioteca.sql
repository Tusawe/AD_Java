-- Adminer 4.7.5 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `libro`;
CREATE TABLE `libro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isbn10` varchar(10) DEFAULT NULL,
  `isbn13` varchar(13) DEFAULT NULL,
  `titulo` varchar(50) NOT NULL,
  `editorial` varchar(35) NOT NULL,
  `fechaPublicacion` varchar(4) NOT NULL,
  `nPaginas` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `libro` (`id`, `isbn10`, `isbn13`, `titulo`, `editorial`, `fechaPublicacion`, `nPaginas`) VALUES
(5,	'13246579AB',	NULL,	'Introduccion a Android',	'Edelvives',	'2000',	120);

DELIMITER ;;

CREATE TRIGGER `comprobar_isbn` BEFORE INSERT ON `libro` FOR EACH ROW
IF ((LENGTH(NEW.`isbn10`) < 10) OR (LENGTH(NEW.`isbn13`) < 13))
    THEN
        SIGNAL sqlstate '45010'
        SET message_text = 'El ISBN no tiene la longitud adecuada.';
END IF;;

CREATE TRIGGER `comprobar_isbn_update` BEFORE UPDATE ON `libro` FOR EACH ROW
IF ((LENGTH(NEW.`isbn10`) < 10) OR (LENGTH(NEW.`isbn13`) < 13))
    THEN
        SIGNAL sqlstate '45011'
        SET message_text = 'El ISBN no tiene la longitud adecuada.';
END IF;;

DELIMITER ;

DROP TABLE IF EXISTS `prestamo`;
CREATE TABLE `prestamo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_libro` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaEntrega` date DEFAULT NULL,
  `prorroga` int(11) NOT NULL,
  `estado` enum('Perfecto','Bueno','Regular','Malo','Dañado') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_libro` (`id_libro`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `prestamo_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id`),
  CONSTRAINT `prestamo_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `prestamo` (`id`, `id_libro`, `id_usuario`, `fechaInicio`, `fechaEntrega`, `prorroga`, `estado`) VALUES
(6,	5,	4,	'2019-12-03',	'2019-12-18',	2,	'Bueno');

DELIMITER ;;

CREATE TRIGGER `prestamo_limite_alumno` BEFORE INSERT ON `prestamo` FOR EACH ROW
IF (SELECT COUNT(*) FROM `prestamo` 
    WHERE `prestamo`.`fechaEntrega` IS NULL 
    AND `prestamo`.`id_usuario`= NEW.`id_usuario`
    AND (SELECT `tipo` FROM `usuario` WHERE `id` = `prestamo`.`id_usuario`) = 'alumno') > 1
    THEN
        SIGNAL sqlstate '45001'
        SET message_text = 'Sólo se permite 2 prestamos por alumno sin devolver.';
END IF;;

CREATE TRIGGER `prestamo_limite_profesor` BEFORE INSERT ON `prestamo` FOR EACH ROW
IF (SELECT COUNT(*) FROM `prestamo` 
    WHERE `prestamo`.`fechaEntrega` IS NULL 
    AND `prestamo`.`id_usuario`= NEW.`id_usuario`
    AND (SELECT `tipo` FROM `usuario` WHERE `id` = `prestamo`.`id_usuario`) = 'profesor'
    OR (SELECT `tipo` FROM `usuario` WHERE `id` = `prestamo`.`id_usuario`) = 'proAdmin') > 2
    THEN
        SIGNAL sqlstate '45001'
        SET message_text = 'Sólo se permite 3 prestamos por profesor sin devolver.';
END IF;;

CREATE TRIGGER `comprobar_fecha` BEFORE UPDATE ON `prestamo` FOR EACH ROW
IF ((OLD.`fechaEntrega` IS NOT NULL) AND (NEW.`fechaEntrega` IS NULL))
    THEN
        SIGNAL sqlstate '45005'
        SET message_text = 'La fecha de entrega no puede ser NULL.';
END IF;;

DELIMITER ;

DROP TABLE IF EXISTS `resenia`;
CREATE TABLE `resenia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_prestamo` int(11) NOT NULL,
  `comentario` varchar(200) NOT NULL,
  `fecha` date NOT NULL,
  `estrellas` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_prestamo` (`id_prestamo`),
  CONSTRAINT `resenia_ibfk_1` FOREIGN KEY (`id_prestamo`) REFERENCES `prestamo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `resenia` (`id`, `id_prestamo`, `comentario`, `fecha`, `estrellas`) VALUES
(3,	6,	'Muy chulo',	'2019-12-26',	4);

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `tipo` enum('alumno','profesor','admin','proAdmin') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `usuario` (`id`, `username`, `password`, `dni`, `nombre`, `apellido`, `email`, `telefono`, `tipo`) VALUES
(1,	'pepe',	'pepe123',	'11111111H',	'Jose',	'Rubio',	'peperubio@gmail.com',	'666552233',	'profesor'),
(2,	'admin',	'admin',	NULL,	NULL,	NULL,	NULL,	NULL,	'admin'),
(3,	'paco',	'paco123',	NULL,	'Francisco',	'Montes',	'pacomontes@gmail.com',	NULL,	'alumno'),
(4,	'juan',	'juan123',	'11111111H',	'Juan',	'Aranda',	'juanaranda@yahoo.es',	'666997744',	'proAdmin'),
(8,	'Lorenzo',	'fran123',	'77373616Y',	'Francisco',	'Franco',	'lorenzo@gmail.com',	'953221155',	'profesor');

DELIMITER ;;

CREATE TRIGGER `comprobar_dni` BEFORE INSERT ON `usuario` FOR EACH ROW
BEGIN

    DECLARE v_dni VARCHAR(9);
    DECLARE v_letra CHAR;
    DECLARE v_dninum INT(8);
    DECLARE resto INT(2);
    DECLARE letra CHAR;

    SET v_dni:= NEW.`dni`;
    SET v_letra := CAST(substr(v_dni,9,1) AS CHAR);
    SET v_dninum := 0+substr(v_dni,1,8);

    SET resto:=mod(v_dninum,23);

    IF resto=0 THEN
    SET letra:= 'T';
    ELSEIF resto=1 THEN
    SET letra:= 'R';
    ELSEIF resto=2 THEN
    SET letra:= 'W';
    ELSEIF resto=3 THEN
    SET letra:= 'A';
    ELSEIF resto=4 THEN
    SET letra:= 'G';
    ELSEIF resto=5 THEN
    SET letra:= 'M';
    ELSEIF resto=6 THEN
    SET letra:= 'Y';
    ELSEIF resto=7 THEN
    SET letra:= 'F';
    ELSEIF resto=8 THEN
    SET letra:= 'P';
    ELSEIF resto=9 THEN
    SET letra:= 'D';
    ELSEIF resto=10 THEN
    SET letra:= 'X';
    ELSEIF resto=11 THEN
    SET letra:= 'B';
    ELSEIF resto=12 THEN
    SET letra:= 'N';
    ELSEIF resto=13 THEN
    SET letra:= 'J';
    ELSEIF resto=14 THEN
    SET letra:= 'Z';
    ELSEIF resto=15 THEN
    SET letra:= 'S';
    ELSEIF resto=16 THEN
    SET letra:= 'Q';
    ELSEIF resto=17 THEN
    SET letra:= 'V';
    ELSEIF resto=18 THEN
    SET letra:= 'H';
    ELSEIF resto=19 THEN
    SET letra:= 'L';
    ELSEIF resto=20 THEN
    SET letra:= 'C';
    ELSEIF resto=21 THEN
    SET letra:= 'K';
    ELSEIF resto=22 THEN
    SET letra:= 'E';
    END IF;

    IF (letra <> v_letra) THEN
        SIGNAL SQLSTATE '45050'
        SET message_text = 'Letra de DNI incorrecta.';
    END IF;

END;;

CREATE TRIGGER `comprobar_dni_update` BEFORE UPDATE ON `usuario` FOR EACH ROW
begin

    declare v_dni varchar(9);
    declare v_letra char;
    declare v_dninum int(8);
    declare resto int(2);
    declare letra char;

    set v_dni:= NEW.`dni`;
    set v_letra := CAST(substr(v_dni,9,1) AS CHAR);
    set v_dninum := 0+substr(v_dni,1,8);

    set resto:=mod(v_dninum,23);

    IF resto=0 THEN
    set letra:= 'T';
    ELSEIF resto=1 THEN
    set letra:= 'R';
    ELSEIF resto=2 THEN
    set letra:= 'W';
    ELSEIF resto=3 THEN
    set letra:= 'A';
    ELSEIF resto=4 THEN
    set letra:= 'G';
    ELSEIF resto=5 THEN
    set letra:= 'M';
    ELSEIF resto=6 THEN
    set letra:= 'Y';
    ELSEIF resto=7 THEN
    set letra:= 'F';
    ELSEIF resto=8 THEN
    set letra:= 'P';
    ELSEIF resto=9 THEN
    set letra:= 'D';
    ELSEIF resto=10 THEN
    set letra:= 'X';
    ELSEIF resto=11 THEN
    set letra:= 'B';
    ELSEIF resto=12 THEN
    set letra:= 'N';
    ELSEIF resto=13 THEN
    set letra:= 'J';
    ELSEIF resto=14 THEN
    set letra:= 'Z';
    ELSEIF resto=15 THEN
    set letra:= 'S';
    ELSEIF resto=16 THEN
    set letra:= 'Q';
    ELSEIF resto=17 THEN
    set letra:= 'V';
    ELSEIF resto=18 THEN
    set letra:= 'H';
    ELSEIF resto=19 THEN
    set letra:= 'L';
    ELSEIF resto=20 THEN
    set letra:= 'C';
    ELSEIF resto=21 THEN
    set letra:= 'K';
    ELSEIF resto=22 THEN
    set letra:= 'E';
    END IF;

    if (letra <> v_letra) then
        SIGNAL sqlstate '45050'
        SET message_text = 'Letra de DNI incorrecta.';
    end if;

end;;

DELIMITER ;

-- 2019-12-11 19:26:15
