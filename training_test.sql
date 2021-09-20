USE training;
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
LOCK TABLES `test` WRITE;
INSERT INTO `test` VALUES (1,'taro'),(2,'jiro'),(3,'saburo');
UNLOCK TABLES;