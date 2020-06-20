CREATE DATABASE  IF NOT EXISTS `teamweb2020` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `teamweb2020`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: teamweb2020
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `mem_id` int NOT NULL AUTO_INCREMENT,
  `mem_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `mem_mail` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `mem_pwd` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `mem_level` int NOT NULL DEFAULT '1' COMMENT '1:申請未確認\n2:已認證會員\n9:管理者',
  `mem_chkcode` varchar(6) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'Null',
  `mem_mphone` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手機號碼',
  `mem_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`mem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (19,'陳OO','cat@kitty.com','Aa123',2,'118028',NULL,NULL),(20,'林OO','109java01@gmail.com','Aa123',9,'214506',NULL,NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderlist`
--

DROP TABLE IF EXISTS `orderlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderlist` (
  `orderList_id` int NOT NULL COMMENT '訂單流水編號',
  `mem_id` int NOT NULL COMMENT '會員id',
  `orderList_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '訂單日期',
  `orderList_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '姓名',
  `orderList_mphone` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手機號碼',
  `orderList_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  `orderList_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '郵件信箱',
  PRIMARY KEY (`orderList_id`),
  KEY `mem_id_idx` (`mem_id`),
  CONSTRAINT `mem_id` FOREIGN KEY (`mem_id`) REFERENCES `member` (`mem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='訂單資料';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderlist`
--

LOCK TABLES `orderlist` WRITE;
/*!40000 ALTER TABLE `orderlist` DISABLE KEYS */;
INSERT INTO `orderlist` VALUES (1,20,'2020-05-28 15:00:46','小鴿子','0922345678','天涯海角','dove@bird.com'),(2,20,'2020-05-28 15:01:18','','','',''),(3,20,'2020-05-28 15:02:16','小鴿子','0922345678','天涯海角','dove@bird.com'),(4,20,'2020-06-02 13:14:33','','','',''),(5,20,'2020-06-02 13:14:42','','','',''),(6,20,'2020-06-02 13:18:58','','','',''),(7,20,'2020-06-02 16:08:29','小鴿子','0922345678','天涯海角','dove@bird.com'),(8,20,'2020-06-02 16:24:56','小鴿子','0922345678','天涯海角','dove@bird.com');
/*!40000 ALTER TABLE `orderlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderlistdetail`
--

DROP TABLE IF EXISTS `orderlistdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderlistdetail` (
  `orderList_id` int NOT NULL COMMENT '訂單流水編號',
  `prod_id` int NOT NULL COMMENT '商品ID',
  `quantity` int NOT NULL DEFAULT '1' COMMENT '購買數量',
  PRIMARY KEY (`orderList_id`,`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='訂單明細';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderlistdetail`
--

LOCK TABLES `orderlistdetail` WRITE;
/*!40000 ALTER TABLE `orderlistdetail` DISABLE KEYS */;
INSERT INTO `orderlistdetail` VALUES (1,19,5),(1,20,1),(1,21,1),(2,21,1),(3,13,4),(3,14,2),(3,18,2),(3,21,3),(4,20,4),(4,25,5),(5,25,1),(6,20,2),(7,18,3),(7,27,5),(8,17,3),(8,20,2);
/*!40000 ALTER TABLE `orderlistdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `prod_id` int NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `prod_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '商品名稱',
  `prod_price` int unsigned NOT NULL COMMENT '商品定價',
  `prod_introduction` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT '商品介紹',
  `prod_size_stock` int unsigned NOT NULL COMMENT '商品庫存',
  `prod_img` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `prod_view` int unsigned NOT NULL COMMENT '商品上架',
  PRIMARY KEY (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'小黑',500,'難用',1000,'C:MyGitHub	eamwebTeamWeb',0),(2,'小白',100,'好用',50,'C:MyGitHub	eamwebTeamWeb',0),(3,'小白',100,'好用',50,'C:MyGitHub	eamwebTeamWeb',0),(4,'小白',100,'好用',50,'C:MyGitHub	eamwebTeamWeb',0),(5,'小白',100,'好用',50,'C:MyGitHub	eamwebTeamWeb',0),(6,'小白',100,'好用',50,'C:MyGitHub	eamwebTeamWeb',0),(7,'小白',100,'好用',50,'C:MyGitHub	eamwebTeamWeb',0),(8,'小白',100,'好用',50,'C:MyGitHub	eamwebTeamWeb',0),(9,'小白',100,'好用',50,'C:MyGitHub	eamwebTeamWeb',0),(10,'小白',100,'好用',50,'C:MyGitHub	eamwebTeamWeb',0),(11,'小黑',10000,'難用',10,'C:MyGitHub	eamwebTeamWeb',0),(12,'小黑',100,'好用',50,'C:MyGitHub	eamwebTeamWeb',0),(13,'白色七分袖上衣',100,'這是敘述，講這個裙子多漂亮！多舒服！布料多好！講述設計者的靈感來源、設計理念以及吹無與倫比毛求疵的匠人精神。一切都是為了讓人穿起來有著什麼樣舒適的觸覺體驗，給別人看起來有什麼樣的心理感受以及視覺暗示。',96,'C:MyGitHub	eamwebTeamWeb',1),(14,'橘色毛大衣',100,'這是敘述，講這個裙子多漂亮！多舒服！布料多好！講述設計者的靈感來源、設計理念以及吹無與倫比毛求疵的匠人精神。一切都是為了讓人穿起來有著什麼樣舒適的觸覺體驗，給別人看起來有什麼樣的心理感受以及視覺暗示。',98,'C:MyGitHub	eamwebTeamWeb',1),(15,'紫色毛大衣',100,'這是敘述，講這個裙子多漂亮！多舒服！布料多好！講述設計者的靈感來源、設計理念以及吹無與倫比毛求疵的匠人精神。一切都是為了讓人穿起來有著什麼樣舒適的觸覺體驗，給別人看起來有什麼樣的心理感受以及視覺暗示。',100,'C:MyGitHub	eamwebTeamWeb',1),(16,'純白聖誕大衣',100,'這是敘述，講這個裙子多漂亮！多舒服！布料多好！講述設計者的靈感來源、設計理念以及吹無與倫比毛求疵的匠人精神。一切都是為了讓人穿起來有著什麼樣舒適的觸覺體驗，給別人看起來有什麼樣的心理感受以及視覺暗示。',100,'C:MyGitHub	eamwebTeamWeb',1),(17,'大紅聖誕大衣',100,'這是敘述，講這個裙子多漂亮！多舒服！布料多好！講述設計者的靈感來源、設計理念以及吹無與倫比毛求疵的匠人精神。一切都是為了讓人穿起來有著什麼樣舒適的觸覺體驗，給別人看起來有什麼樣的心理感受以及視覺暗示。',97,'C:MyGitHub	eamwebTeamWeb',1),(18,'粉紅格子洋裝',100,'這是敘述，講這個裙子多漂亮！多舒服！布料多好！講述設計者的靈感來源、設計理念以及吹無與倫比毛求疵的匠人精神。一切都是為了讓人穿起來有著什麼樣舒適的觸覺體驗，給別人看起來有什麼樣的心理感受以及視覺暗示。',95,'C:MyGitHub	eamwebTeamWeb',1),(19,'藍格子洋裝',100,'這是敘述，講這個裙子多漂亮！多舒服！布料多好！講述設計者的靈感來源、設計理念以及吹無與倫比毛求疵的匠人精神。一切都是為了讓人穿起來有著什麼樣舒適的觸覺體驗，給別人看起來有什麼樣的心理感受以及視覺暗示。',95,'C:MyGitHub	eamwebTeamWeb',1),(20,'海藍長裙',100,'「福爾摩沙」。神秘又美麗的海洋之島。沁心於深藍，令人意念徜徉於熱帶海洋般曠心。長版雙層設計於光照之下形成晴日海面般虛幻光影。沉穩色澤搭配外層質輕薄紗，隨風飄逸仿造真實鮮活的海洋波瀾。讓我們將移動的大海獻給來自福爾摩沙神秘且美麗的妳。',91,'C:MyGitHub	eamwebTeamWeb',1),(21,'粉紫長裙',1000,'採用台灣高海拔雪山蠶絲手工織打，整塊布料無接縫俐落剪裁。\r\n乾燥花般的淺紫淡粉，以色彩心理啟動清香甜美的意象味覺。\r\n唯有純粹質感才能呈現的簡約雅素，透過天然舒適的觸覺感受，讓人即使身處城市喧囂也不失原生俱來的仙靈氣動。',0,'C:MyGitHub	eamwebTeamWeb',1),(22,'小白',100,'好用',50,'C:MyGitHubMyTeamWebTeamWeb',0),(23,'小白',100,'好用',50,'C:MyGitHubMyTeamWebTeamWebWebContent',0),(24,'小白',100,'好用',50,'C:\\MyGitHub\\MyTeamWeb\\TeamWeb\\WebContent',0),(25,'小黑',10000,'蝦',494,'C:\\MyGitHub\\MyTeamWeb\\TeamWeb\\WebContent',0),(26,'小白',100,'好用s ',50,'C:\\MyGitHub\\MyTeamWeb\\TeamWeb\\WebContent',0),(27,'哥吉拉',10000,'帥翻天',0,'C:\\MyGitHub\\MyTeamWeb\\TeamWeb\\WebContent',0),(28,'vul3coi',100,'好用',50,'C:\\MyGitHub\\MyTeamWeb\\TeamWeb\\WebContent',0),(29,'小黑',1000,'好吃',500,'C:\\\\MyGitHub\\\\MyTeamWeb\\\\TeamWeb\\\\WebContent',0);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viewcount`
--

DROP TABLE IF EXISTS `viewcount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `viewcount` (
  `view_id` int NOT NULL,
  `view_time` datetime DEFAULT NULL,
  `view_ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viewcount`
--

LOCK TABLES `viewcount` WRITE;
/*!40000 ALTER TABLE `viewcount` DISABLE KEYS */;
INSERT INTO `viewcount` VALUES (20,'2020-05-06 23:01:39','0:0:0:0:0:0:0:1'),(20,'2020-05-09 10:48:45','0:0:0:0:0:0:0:1'),(20,'2020-05-09 10:55:01','0:0:0:0:0:0:0:1'),(32,'2020-05-09 10:56:51','0:0:0:0:0:0:0:1'),(32,'2020-05-09 10:57:11','0:0:0:0:0:0:0:1'),(20,'2020-05-09 13:54:21','0:0:0:0:0:0:0:1'),(32,'2020-05-09 13:54:30','0:0:0:0:0:0:0:1'),(32,'2020-05-09 14:15:16','0:0:0:0:0:0:0:1'),(20,'2020-05-10 11:11:58','0:0:0:0:0:0:0:1'),(32,'2020-05-10 11:12:11','0:0:0:0:0:0:0:1'),(20,'2020-05-10 11:29:53','0:0:0:0:0:0:0:1'),(20,'2020-05-10 11:31:43','0:0:0:0:0:0:0:1'),(20,'2020-05-10 11:42:28','0:0:0:0:0:0:0:1'),(20,'2020-05-10 11:44:58','0:0:0:0:0:0:0:1'),(20,'2020-05-10 11:49:18','0:0:0:0:0:0:0:1'),(20,'2020-05-10 11:51:10','0:0:0:0:0:0:0:1'),(20,'2020-05-15 16:35:18','0:0:0:0:0:0:0:1'),(20,'2020-05-15 16:37:18','0:0:0:0:0:0:0:1'),(32,'2020-05-18 21:28:19','0:0:0:0:0:0:0:1'),(32,'2020-05-18 21:50:23','0:0:0:0:0:0:0:1'),(20,'2020-05-18 21:50:46','0:0:0:0:0:0:0:1'),(32,'2020-05-18 22:04:19','0:0:0:0:0:0:0:1'),(32,'2020-05-18 22:05:25','0:0:0:0:0:0:0:1'),(20,'2020-05-18 22:05:46','0:0:0:0:0:0:0:1'),(32,'2020-05-18 22:06:22','0:0:0:0:0:0:0:1'),(32,'2020-05-18 22:19:45','0:0:0:0:0:0:0:1'),(32,'2020-05-19 09:13:42','0:0:0:0:0:0:0:1'),(32,'2020-05-19 10:16:04','0:0:0:0:0:0:0:1'),(32,'2020-05-19 10:27:58','0:0:0:0:0:0:0:1'),(32,'2020-05-19 19:17:41','0:0:0:0:0:0:0:1'),(32,'2020-05-19 19:40:27','0:0:0:0:0:0:0:1'),(32,'2020-05-19 19:42:03','0:0:0:0:0:0:0:1'),(32,'2020-05-19 19:47:01','0:0:0:0:0:0:0:1'),(32,'2020-05-19 19:47:09','0:0:0:0:0:0:0:1'),(32,'2020-05-20 18:39:31','0:0:0:0:0:0:0:1'),(32,'2020-05-20 18:39:47','0:0:0:0:0:0:0:1'),(32,'2020-05-20 18:39:59','0:0:0:0:0:0:0:1'),(32,'2020-05-20 18:48:58','0:0:0:0:0:0:0:1'),(32,'2020-05-20 18:49:10','0:0:0:0:0:0:0:1'),(32,'2020-05-20 18:51:40','0:0:0:0:0:0:0:1'),(32,'2020-05-20 18:51:45','0:0:0:0:0:0:0:1'),(32,'2020-05-20 18:51:52','0:0:0:0:0:0:0:1'),(32,'2020-05-20 18:52:48','0:0:0:0:0:0:0:1'),(32,'2020-05-20 18:53:08','0:0:0:0:0:0:0:1'),(32,'2020-05-21 08:32:58','0:0:0:0:0:0:0:1'),(32,'2020-05-21 08:58:23','0:0:0:0:0:0:0:1'),(32,'2020-05-21 09:09:45','0:0:0:0:0:0:0:1'),(32,'2020-05-21 09:09:54','0:0:0:0:0:0:0:1'),(32,'2020-05-21 09:10:51','0:0:0:0:0:0:0:1'),(32,'2020-05-21 09:28:54','0:0:0:0:0:0:0:1'),(32,'2020-05-21 09:33:04','0:0:0:0:0:0:0:1'),(32,'2020-05-21 09:35:36','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:12:22','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:13:08','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:13:20','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:16:34','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:17:09','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:17:11','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:17:12','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:23:41','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:23:55','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:24:17','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:24:36','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:25:06','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:26:03','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:34:50','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:34:56','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:47:32','0:0:0:0:0:0:0:1'),(32,'2020-05-21 10:48:36','0:0:0:0:0:0:0:1'),(32,'2020-05-21 15:51:05','0:0:0:0:0:0:0:1'),(32,'2020-05-21 15:51:16','0:0:0:0:0:0:0:1'),(32,'2020-05-21 15:51:22','0:0:0:0:0:0:0:1'),(32,'2020-05-21 15:51:25','0:0:0:0:0:0:0:1'),(32,'2020-05-21 21:45:17','0:0:0:0:0:0:0:1'),(32,'2020-05-21 21:45:28','0:0:0:0:0:0:0:1'),(32,'2020-05-21 21:45:59','0:0:0:0:0:0:0:1'),(32,'2020-05-21 23:32:11','0:0:0:0:0:0:0:1'),(32,'2020-05-22 09:56:27','0:0:0:0:0:0:0:1'),(32,'2020-05-22 09:56:39','0:0:0:0:0:0:0:1'),(32,'2020-05-22 10:18:28','0:0:0:0:0:0:0:1'),(32,'2020-05-22 10:24:05','0:0:0:0:0:0:0:1'),(32,'2020-05-22 10:24:20','0:0:0:0:0:0:0:1'),(32,'2020-05-22 10:24:22','0:0:0:0:0:0:0:1'),(32,'2020-05-22 10:24:33','0:0:0:0:0:0:0:1'),(32,'2020-05-22 10:27:50','0:0:0:0:0:0:0:1'),(32,'2020-05-22 10:37:31','0:0:0:0:0:0:0:1'),(32,'2020-05-22 10:39:03','0:0:0:0:0:0:0:1'),(32,'2020-05-22 10:40:01','0:0:0:0:0:0:0:1'),(32,'2020-05-22 10:45:17','0:0:0:0:0:0:0:1'),(32,'2020-05-22 10:46:11','0:0:0:0:0:0:0:1'),(32,'2020-05-22 10:46:19','0:0:0:0:0:0:0:1'),(32,'2020-05-22 11:05:03','0:0:0:0:0:0:0:1'),(32,'2020-05-22 11:19:13','0:0:0:0:0:0:0:1'),(32,'2020-05-25 18:20:37','0:0:0:0:0:0:0:1'),(32,'2020-05-25 21:03:50','0:0:0:0:0:0:0:1'),(32,'2020-05-25 21:13:19','0:0:0:0:0:0:0:1'),(32,'2020-05-25 21:21:03','0:0:0:0:0:0:0:1'),(32,'2020-05-25 21:21:07','0:0:0:0:0:0:0:1'),(32,'2020-05-25 21:21:10','0:0:0:0:0:0:0:1'),(32,'2020-05-25 21:21:12','0:0:0:0:0:0:0:1'),(32,'2020-05-25 21:28:46','0:0:0:0:0:0:0:1'),(32,'2020-05-25 21:55:45','0:0:0:0:0:0:0:1'),(32,'2020-05-25 22:02:25','0:0:0:0:0:0:0:1'),(32,'2020-05-25 22:11:30','0:0:0:0:0:0:0:1'),(32,'2020-05-25 22:33:17','0:0:0:0:0:0:0:1'),(32,'2020-05-26 08:36:01','0:0:0:0:0:0:0:1'),(32,'2020-05-26 11:02:38','0:0:0:0:0:0:0:1'),(32,'2020-05-26 11:06:59','0:0:0:0:0:0:0:1'),(32,'2020-05-26 20:31:39','0:0:0:0:0:0:0:1'),(32,'2020-05-27 18:58:29','0:0:0:0:0:0:0:1'),(32,'2020-05-27 19:17:09','0:0:0:0:0:0:0:1'),(20,'2020-05-27 21:28:04','0:0:0:0:0:0:0:1'),(20,'2020-05-27 21:28:19','0:0:0:0:0:0:0:1'),(20,'2020-05-27 22:41:04','0:0:0:0:0:0:0:1'),(20,'2020-05-27 23:01:26','0:0:0:0:0:0:0:1'),(20,'2020-05-27 23:09:04','0:0:0:0:0:0:0:1'),(20,'2020-05-27 23:18:35','0:0:0:0:0:0:0:1'),(20,'2020-05-28 19:05:08','0:0:0:0:0:0:0:1'),(20,'2020-05-28 21:41:46','0:0:0:0:0:0:0:1'),(20,'2020-05-28 21:41:55','0:0:0:0:0:0:0:1'),(20,'2020-05-28 22:03:19','0:0:0:0:0:0:0:1'),(20,'2020-05-28 22:03:26','0:0:0:0:0:0:0:1'),(20,'2020-05-28 22:03:45','0:0:0:0:0:0:0:1'),(20,'2020-05-28 22:05:40','0:0:0:0:0:0:0:1'),(20,'2020-05-28 22:09:52','0:0:0:0:0:0:0:1'),(20,'2020-05-28 22:13:31','0:0:0:0:0:0:0:1'),(20,'2020-05-28 22:15:46','0:0:0:0:0:0:0:1'),(20,'2020-05-28 22:17:36','0:0:0:0:0:0:0:1'),(20,'2020-05-28 22:41:17','0:0:0:0:0:0:0:1'),(20,'2020-05-28 23:00:47','0:0:0:0:0:0:0:1'),(20,'2020-06-01 23:22:15','0:0:0:0:0:0:0:1'),(20,'2020-06-01 23:23:29','0:0:0:0:0:0:0:1'),(20,'2020-06-02 10:00:46','0:0:0:0:0:0:0:1'),(20,'2020-06-02 10:02:56','0:0:0:0:0:0:0:1'),(20,'2020-06-02 20:12:44','0:0:0:0:0:0:0:1'),(20,'2020-06-02 20:40:29','0:0:0:0:0:0:0:1'),(20,'2020-06-02 21:18:20','0:0:0:0:0:0:0:1'),(20,'2020-06-03 00:06:48','0:0:0:0:0:0:0:1'),(20,'2020-06-03 00:09:56','0:0:0:0:0:0:0:1'),(20,'2020-06-03 00:15:28','0:0:0:0:0:0:0:1'),(20,'2020-06-03 00:18:37','0:0:0:0:0:0:0:1'),(20,'2020-06-03 00:24:56','0:0:0:0:0:0:0:1'),(20,'2020-06-03 00:33:33','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `viewcount` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-03  0:40:12
