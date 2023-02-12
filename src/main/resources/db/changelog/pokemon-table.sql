--liquibase formatted sql

--changeset visrut:power-table
CREATE TABLE `power` (
  `id` int(11) AUTO_INCREMENT NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gitsfk2012ttl9ktw2rde4u9i` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--changeset visrut:pokemon-table
CREATE TABLE `pokemon` (
  `id` int(11) AUTO_INCREMENT NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `power` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1l2d8t2w3h2rw3tm4h5fc3fyw` (`name`),
  KEY `FKcxhpy49pkxxvggjrhebni40ix` (`power`),
  CONSTRAINT `FKcxhpy49pkxxvggjrhebni40ix` FOREIGN KEY (`power`) REFERENCES `power` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--changeset visrut:master-data-power-inserted
INSERT INTO power (name)
	VALUES ('Grass'),('Fire'),('Water'),('Poison');