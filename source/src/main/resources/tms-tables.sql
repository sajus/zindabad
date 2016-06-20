-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: taskmanagement
-- ------------------------------------------------------
-- Server version	5.5.25a

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
-- Table structure for table `tms_code_review`
--

DROP TABLE IF EXISTS `tms_code_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tms_code_review` (
  `REVIEW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `COMMENT_TYPE` varchar(45) DEFAULT NULL,
  `COMMENTS` varchar(45) DEFAULT NULL,
  `COMMENTS_FIXED_DATE` date NOT NULL,
  `FILE_NAME` varchar(45) DEFAULT NULL,
  `PULL_REQUEST` varchar(45) DEFAULT NULL,
  `REVIEW_DATE` date NOT NULL,
  `REVIEWER` varchar(45) NOT NULL,
  `REVIEWER_TYPE` varchar(45) NOT NULL,
  `TASK_ID` bigint(20) NOT NULL,
  `DEVELOPER` bigint(20) NOT NULL,
  `FIXED_BY` bigint(20) NOT NULL,
  `ID` bigint(20) NOT NULL,
  PRIMARY KEY (`REVIEW_ID`),
  KEY `FK_krht12jfxj0wciqnuwbxonyiu` (`TASK_ID`),
  KEY `FK_d0ey9b8s4h6wgyc98ido0nv3d` (`DEVELOPER`),
  KEY `FK_6argn3b67xjkmxxri9yuo8emi` (`FIXED_BY`),
  CONSTRAINT `FK_6argn3b67xjkmxxri9yuo8emi` FOREIGN KEY (`FIXED_BY`) REFERENCES `tms_users` (`ID`),
  CONSTRAINT `FK_d0ey9b8s4h6wgyc98ido0nv3d` FOREIGN KEY (`DEVELOPER`) REFERENCES `tms_users` (`ID`),
  CONSTRAINT `FK_krht12jfxj0wciqnuwbxonyiu` FOREIGN KEY (`TASK_ID`) REFERENCES `tms_subtask` (`SUBTASK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tms_efforts`
--

DROP TABLE IF EXISTS `tms_efforts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tms_efforts` (
  `EFFORT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOGGED_DATE` date NOT NULL,
  `LOGGED_HOURS` double NOT NULL,
  `SPRINT_ID` bigint(20) NOT NULL,
  `SUBTASK_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`EFFORT_ID`),
  KEY `FK_qmk6u2p4g2pplud7wpgvgkytt` (`SPRINT_ID`),
  KEY `FK_ne1t65qxnm1ii7m4l30npiwnq` (`SUBTASK_ID`),
  CONSTRAINT `FK_ne1t65qxnm1ii7m4l30npiwnq` FOREIGN KEY (`SUBTASK_ID`) REFERENCES `tms_subtask` (`SUBTASK_ID`),
  CONSTRAINT `FK_qmk6u2p4g2pplud7wpgvgkytt` FOREIGN KEY (`SPRINT_ID`) REFERENCES `tms_sprint_mst` (`SPRINT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tms_leave_mst`
--

DROP TABLE IF EXISTS `tms_leave_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tms_leave_mst` (
  `LEAVE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SPRINTID` bigint(20) NOT NULL,
  `USERID` bigint(20) NOT NULL,
  `DURATION` int(11) NOT NULL,
  `ENDDATE` date NOT NULL,
  `REASON` varchar(50) NOT NULL,
  `STARTDATE` date NOT NULL,
  PRIMARY KEY (`LEAVE_ID`),
  KEY `FK_ct7i20aljt7lgsb4dinnibhai` (`SPRINTID`),
  KEY `FK_pouvsp9amppneogy7adwbv7fd` (`USERID`),
  CONSTRAINT `FK_ct7i20aljt7lgsb4dinnibhai` FOREIGN KEY (`SPRINTID`) REFERENCES `tms_sprint_mst` (`SPRINT_ID`),
  CONSTRAINT `FK_pouvsp9amppneogy7adwbv7fd` FOREIGN KEY (`USERID`) REFERENCES `tms_users` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tms_module`
--

DROP TABLE IF EXISTS `tms_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tms_module` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MODULE_DESCRIPTION` varchar(100) DEFAULT NULL,
  `MODULE_NAME` varchar(45) DEFAULT NULL,
  `PROJECT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_1gyin4l54mymrdm29g9ja50uh` (`PROJECT_ID`),
  CONSTRAINT `FK_1gyin4l54mymrdm29g9ja50uh` FOREIGN KEY (`PROJECT_ID`) REFERENCES `tms_project` (`PID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tms_org_leaves`
--

DROP TABLE IF EXISTS `tms_org_leaves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tms_org_leaves` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE` date DEFAULT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_ny9fn5knmld6nl2ekfghhd3g4` (`DATE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tms_project`
--

DROP TABLE IF EXISTS `tms_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tms_project` (
  `PID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) DEFAULT NULL,
  `START_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `PROJECT_DESC` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tms_sprint_mst`
--

DROP TABLE IF EXISTS `tms_sprint_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tms_sprint_mst` (
  `SPRINT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SPRINT_END_DATE` date NOT NULL,
  `SPRINT_HOURS` int(11) NOT NULL,
  `SPRINT_NAME` varchar(45) NOT NULL,
  `SPRINT_START_DATE` date NOT NULL,
  `SPRINT_STATUS` varchar(6) NOT NULL,
  `SPRINT_VELOCITY` int(11) NOT NULL,
  `PROJECT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SPRINT_ID`),
  KEY `fk_project_id_idx` (`PROJECT_ID`),
  CONSTRAINT `fk_project_id` FOREIGN KEY (`PROJECT_ID`) REFERENCES `tms_project` (`PID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tms_status_mst`
--

DROP TABLE IF EXISTS `tms_status_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tms_status_mst` (
  `STATUS_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `STATUS` varchar(45) NOT NULL,
  PRIMARY KEY (`STATUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tms_story_mst`
--

DROP TABLE IF EXISTS `tms_story_mst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tms_story_mst` (
  `STORY_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `JIRA_ID` varchar(45) NOT NULL,
  `STORY_POINT` int(11) NOT NULL,
  `MODULE_ID` bigint(20) DEFAULT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  PRIMARY KEY (`STORY_ID`),
  KEY `FK_9sgvh9f7on64xo5dqhde8ds3f` (`MODULE_ID`),
  CONSTRAINT `FK_9sgvh9f7on64xo5dqhde8ds3f` FOREIGN KEY (`MODULE_ID`) REFERENCES `tms_module` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tms_subtask`
--

DROP TABLE IF EXISTS `tms_subtask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tms_subtask` (
  `SUBTASK_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EFFORTS` int(11) NOT NULL DEFAULT '0',
  `JIRA_ID` varchar(45) NOT NULL,
  `SCOPE` varchar(10) NOT NULL,
  `TYPE` varchar(8) NOT NULL,
  `STORY_ID` bigint(20) NOT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  PRIMARY KEY (`SUBTASK_ID`),
  KEY `FK_2kc52spu2h2a7ywk70fuae5eu` (`STORY_ID`),
  CONSTRAINT `FK_2kc52spu2h2a7ywk70fuae5eu` FOREIGN KEY (`STORY_ID`) REFERENCES `tms_story_mst` (`STORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tms_users`
--

DROP TABLE IF EXISTS `tms_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tms_users` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(45) NOT NULL,
  `IS_ACTIVE` varchar(9) NOT NULL,
  `PASSWORD` varchar(80) NOT NULL,
  `USER_NAME` varchar(45) NOT NULL,
  `USER_ROLE` varchar(9) NOT NULL,
  `PROJECT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_h8ptb9amqqrtm7chh7osmjiqu` (`USER_NAME`),
  UNIQUE KEY `UK_og2upg95dy3y38v0prhtmxwfn` (`EMAIL`),
  KEY `FK_iq4y828t2oeftb05otpoa89j8` (`PROJECT_ID`),
  CONSTRAINT `FK_iq4y828t2oeftb05otpoa89j8` FOREIGN KEY (`PROJECT_ID`) REFERENCES `tms_project` (`PID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_story_staus`
--

DROP TABLE IF EXISTS `user_story_staus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_story_staus` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `STORY_ID` bigint(20) DEFAULT NULL,
  `SUBTASK_ID` bigint(20) DEFAULT NULL,
  `MODIFIED_DATE` date DEFAULT NULL,
  `TYPE` varchar(45) DEFAULT NULL,
  `SPRINT_ID` bigint(20) DEFAULT NULL,
  `STATUS` bigint(20) DEFAULT NULL,
  `MODIFIED_BY` bigint(20) DEFAULT NULL,
  `ASSIGNED_DATE` date DEFAULT NULL,
  `ASSIGNED_TO` bigint(20) DEFAULT NULL,
  `CREATED_DATE` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_4wiq9b9yngeywhei9bxg5tpa7` (`SPRINT_ID`),
  KEY `fk_storyid_idx` (`STORY_ID`),
  KEY `fk_subtask_id_idx` (`SUBTASK_ID`),
  KEY `fk_assigned_to_idx` (`ASSIGNED_TO`),
  KEY `FK_dkfqgjstr5ecas3cqg53pngnx` (`MODIFIED_BY`),
  KEY `FK_ouc84o4kbvnh2yfw9vnv2vt9l` (`STATUS`),
  CONSTRAINT `FK_4wiq9b9yngeywhei9bxg5tpa7` FOREIGN KEY (`SPRINT_ID`) REFERENCES `tms_sprint_mst` (`SPRINT_ID`),
  CONSTRAINT `fk_assigned_to` FOREIGN KEY (`ASSIGNED_TO`) REFERENCES `tms_users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_dkfqgjstr5ecas3cqg53pngnx` FOREIGN KEY (`MODIFIED_BY`) REFERENCES `tms_users` (`ID`),
  CONSTRAINT `FK_ouc84o4kbvnh2yfw9vnv2vt9l` FOREIGN KEY (`STATUS`) REFERENCES `tms_status_mst` (`STATUS_ID`),
  CONSTRAINT `fk_story_id` FOREIGN KEY (`STORY_ID`) REFERENCES `tms_story_mst` (`STORY_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_subtask_id` FOREIGN KEY (`SUBTASK_ID`) REFERENCES `tms_subtask` (`SUBTASK_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-20  8:59:33
