-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: artrend
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `art_trend`
--

DROP TABLE IF EXISTS `art_trend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `art_trend` (
  `art_trend_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(5000) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`art_trend_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist` (
  `artist_id` bigint NOT NULL AUTO_INCREMENT,
  `birth` varchar(255) DEFAULT NULL,
  `death` varchar(255) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `korean_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`artist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `changed_painting`
--

DROP TABLE IF EXISTS `changed_painting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `changed_painting` (
  `changed_painting_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `member_id` bigint DEFAULT NULL,
  `url` longtext,
  `painting_id` bigint DEFAULT NULL,
  PRIMARY KEY (`changed_painting_id`),
  KEY `FKloruxxoxfx6tnshm4bdchon39` (`painting_id`),
  CONSTRAINT `FKloruxxoxfx6tnshm4bdchon39` FOREIGN KEY (`painting_id`) REFERENCES `painting` (`painting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detail_recommended_painting`
--

DROP TABLE IF EXISTS `detail_recommended_painting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detail_recommended_painting` (
  `detail_recommended_painting_id` bigint NOT NULL AUTO_INCREMENT,
  `painting_id` bigint DEFAULT NULL,
  `recommended_id` bigint DEFAULT NULL,
  PRIMARY KEY (`detail_recommended_painting_id`),
  KEY `FK1qpwiw3u00gmmphip62ggrrq1` (`painting_id`),
  KEY `FKin07rbe4ugv0sorssiksmw95h` (`recommended_id`),
  CONSTRAINT `FK1qpwiw3u00gmmphip62ggrrq1` FOREIGN KEY (`painting_id`) REFERENCES `painting` (`painting_id`),
  CONSTRAINT `FKin07rbe4ugv0sorssiksmw95h` FOREIGN KEY (`recommended_id`) REFERENCES `painting` (`painting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3804 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `favorite_style`
--

DROP TABLE IF EXISTS `favorite_style`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite_style` (
  `painting_id` bigint NOT NULL,
  PRIMARY KEY (`painting_id`),
  CONSTRAINT `FK7k2rgam7054qbk9xujl1exeai` FOREIGN KEY (`painting_id`) REFERENCES `painting` (`painting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `genre_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(5000) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `liked_painting`
--

DROP TABLE IF EXISTS `liked_painting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `liked_painting` (
  `liked_painting_id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint DEFAULT NULL,
  `painting_id` bigint DEFAULT NULL,
  PRIMARY KEY (`liked_painting_id`),
  KEY `FKi0ei92iujg2sfedorqr7dtci9` (`painting_id`),
  CONSTRAINT `FKi0ei92iujg2sfedorqr7dtci9` FOREIGN KEY (`painting_id`) REFERENCES `painting` (`painting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `di` varchar(255) DEFAULT NULL,
  `google_provider` varchar(255) DEFAULT NULL,
  `google_provider_id` varchar(255) DEFAULT NULL,
  `kakao_provider` varchar(255) DEFAULT NULL,
  `kakao_provider_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `member_log`
--

DROP TABLE IF EXISTS `member_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_log` (
  `log_id` bigint NOT NULL AUTO_INCREMENT,
  `change_count` bigint DEFAULT NULL,
  `click_count` bigint DEFAULT NULL,
  `in_time` varchar(255) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  `out_time` varchar(255) DEFAULT NULL,
  `painting_id` bigint DEFAULT NULL,
  `zoom_count` bigint DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `painting`
--

DROP TABLE IF EXISTS `painting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `painting` (
  `painting_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `art_trend` varchar(255) DEFAULT NULL,
  `artist` varchar(255) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `dimensions` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `hits` int DEFAULT NULL,
  `korean_title` varchar(255) DEFAULT NULL,
  `medium` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `total_change_count` int DEFAULT NULL,
  `total_like_count` int DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`painting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recommended_painting`
--

DROP TABLE IF EXISTS `recommended_painting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recommended_painting` (
  `recommended_painting_id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint DEFAULT NULL,
  `painting_id` bigint DEFAULT NULL,
  PRIMARY KEY (`recommended_painting_id`),
  KEY `FKc81lkux64j5rsukmuga9kfhe7` (`painting_id`),
  CONSTRAINT `FKc81lkux64j5rsukmuga9kfhe7` FOREIGN KEY (`painting_id`) REFERENCES `painting` (`painting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `selected_painting`
--

DROP TABLE IF EXISTS `selected_painting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `selected_painting` (
  `selected_painting_id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint DEFAULT NULL,
  `favorite_style_painting_id` bigint DEFAULT NULL,
  PRIMARY KEY (`selected_painting_id`),
  KEY `FKhow8r7lq7obhgmdk09yinhek8` (`favorite_style_painting_id`),
  CONSTRAINT `FKhow8r7lq7obhgmdk09yinhek8` FOREIGN KEY (`favorite_style_painting_id`) REFERENCES `favorite_style` (`painting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-02  6:02:56
