-- MySQL dump 10.13  Distrib 5.5.28, for Win32 (x86)
--
-- Host: localhost    Database: proyecto
-- ------------------------------------------------------
-- Server version	5.5.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `eventos`
--

DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventos` (
  `nombre` varchar(30) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `ubicacion` varchar(35) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos`
--

LOCK TABLES `eventos` WRITE;
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
INSERT INTO `eventos` VALUES ('CodeCampSDQ','2013-04-03','INTEC',26),('Proyecto Final','2013-04-02','ITLA',27),('Party','2013-04-02','Santo Domingo',28),('Otlkkljlk','2013-04-02','Mi casa',29),('ITLA hoy','2013-04-03','Boca Chica',30),('jajaja','2013-04-02','LOL',31),('Ulloa','2013-04-02','papkote',32),('asdgasd','2013-04-02','jkljlk',33),('kdfjdksfl','2013-02-03','sdfghjkl.,mnv',34),('asdfghjkl','2016-01-05','azsdfghjkl',35);
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitaciones`
--

DROP TABLE IF EXISTS `invitaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `invitado` varchar(30) DEFAULT NULL,
  `evento` varchar(30) DEFAULT NULL,
  `asistencia` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitaciones`
--

LOCK TABLES `invitaciones` WRITE;
/*!40000 ALTER TABLE `invitaciones` DISABLE KEYS */;
INSERT INTO `invitaciones` VALUES (16,'Raydelto Hernández','CodeCampSDQ','Presente'),(17,'Pedro T','sdfgdasd','Presente'),(18,'Pedro Toribio','CodeCampSDQ','Presente'),(20,'Eduardo Canario','partyjorgedsd','Presente'),(21,'Abigail Torres','CodeCampSDQ','Presente'),(22,'maria lopez','Partya',NULL),(23,'Raydelto Hernández','Proyecto Finalsda',NULL);
/*!40000 ALTER TABLE `invitaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitados`
--

DROP TABLE IF EXISTS `invitados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `apellido` varchar(30) DEFAULT NULL,
  `telefono` char(12) DEFAULT NULL,
  `direccion` varchar(30) DEFAULT NULL,
  `sexo` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitados`
--

LOCK TABLES `invitados` WRITE;
/*!40000 ALTER TABLE `invitados` DISABLE KEYS */;
INSERT INTO `invitados` VALUES (23,'Pedro','Toribio','jkljkl','jkl','Hombre'),(24,'Raydelto','Hernández','809-564-9887','Esche Quisqueya','Hombre'),(25,'samuelito','asdgjlk','kljlk','j','Hombre'),(26,'Nuevo','kljkl','jkl','jkl','Hombre'),(27,'Eduardo','Canario','809-594-6058','jcasklnnp+olwckmds, -x.','Hombre'),(29,'jole','lopes','67890\'','sdfghjkl,mn','Mujer'),(30,'maria','lopez','34567890\'','fvghjkl.,mnb','Mujer'),(31,'ÁSDFGHJKL','SDFGHJK','234567890','EFGHJK,MNB','Hombre');
/*!40000 ALTER TABLE `invitados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfiles`
--

DROP TABLE IF EXISTS `perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfiles` (
  `Perfil_ID` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Perfil_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfiles`
--

LOCK TABLES `perfiles` WRITE;
/*!40000 ALTER TABLE `perfiles` DISABLE KEYS */;
INSERT INTO `perfiles` VALUES (1,'Administrador'),(2,'Portero');
/*!40000 ALTER TABLE `perfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro`
--

DROP TABLE IF EXISTS `registro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registro` (
  `nombre` varchar(30) DEFAULT NULL,
  `apellido` varchar(30) DEFAULT NULL,
  `usuario` varchar(30) DEFAULT NULL,
  `motivo` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro`
--

LOCK TABLES `registro` WRITE;
/*!40000 ALTER TABLE `registro` DISABLE KEYS */;
INSERT INTO `registro` VALUES ('Ulloa','Manuel','Samueito','Hola que haces'),('Oneal','Mercedes','Jose','No pudo hacer la transccion a tiempo'),('Nuevo','Toribio','Efrain','el esta....');
/*!40000 ALTER TABLE `registro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `apellido` varchar(30) DEFAULT NULL,
  `usuario` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `perfil` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `perfil` (`perfil`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`perfil`) REFERENCES `perfiles` (`Perfil_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (5,'Efrain','Toribio','Efrain','losreyes',1),(6,'Jose','Manuel','Jose','papakote',2),(7,'Samueito','Ortiz','samueito','223',2),(8,'imiin','k','imiin','jkl',1),(9,'maria','lopez','maria','1234',2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-04-04  3:43:47
