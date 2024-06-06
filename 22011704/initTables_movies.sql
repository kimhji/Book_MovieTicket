

GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' WITH GRANT OPTION;

DROP TABLE IF EXISTS db1.ticket;
DROP TABLE IF EXISTS db1.seat;
DROP TABLE IF EXISTS db1.payInfo;
DROP TABLE IF EXISTS db1.showSchedule;
DROP TABLE IF EXISTS db1.movie;
DROP TABLE IF EXISTS db1.room;
DROP TABLE IF EXISTS db1.userInfo;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db1` DEFAULT CHARACTER SET utf8 ;
USE `db1` ;

-- -----------------------------------------------------
-- Table `db1`.`movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db1`.`movie` (
  `movieId` INT AUTO_INCREMENT NOT NULL,
  `movieName` VARCHAR(20) NULL,
  `movieGrade` INT NULL COMMENT '0 : 전체 관람가 \n1 : 12세 이상 관람가\n2 : 15세 이상 관람가\n3 : 청소년 관람불가\n4  : 제한관람가',
  `director` VARCHAR(40) NULL,
  `actor` VARCHAR(60) NULL,
  `genre` VARCHAR(30) NULL,
  `movieSummary` VARCHAR(200) NULL,
  `openDate` DATE NULL,
  `rate` INT NULL,
  PRIMARY KEY (`movieId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db1`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db1`.`room` (
  `roomId` INT AUTO_INCREMENT  NOT NULL,
  `seatCol` INT NULL,
  `seatRow` INT NULL,
  `isUsed` TINYINT NULL,
  PRIMARY KEY (`roomId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db1`.`showSchedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db1`.`showSchedule` (
  `showScheduleId` INT AUTO_INCREMENT NOT NULL,
  `movieId` INT NULL,
  `roomId` INT NULL,
  `showingStartDay` DATE NULL,
  `dayType` VARCHAR(3) NULL,
  `showRound` INT NULL,
  `startTime` TIME NULL,
  PRIMARY KEY (`showScheduleId`),
  INDEX `showSchedule_movieId_idx` (`movieId` ASC) VISIBLE,
  INDEX `showSchedule_roomId_idx` (`roomId` ASC) VISIBLE,
  FOREIGN KEY (movieId)
  REFERENCES movie(movieId) ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (roomId)
  REFERENCES room(roomId) ON UPDATE CASCADE ON DELETE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db1`.`userInfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db1`.`userInfo` (
  `userId` VARCHAR(20) NOT NULL,
  `name` VARCHAR(30) NULL,
  `phoneNumber` VARCHAR(12) NULL,
  `addressEmail` VARCHAR(30) NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db1`.`payInfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db1`.`payInfo` (
  `payId` INT AUTO_INCREMENT NOT NULL,
  `payMethod` VARCHAR(100) NULL,
  `payState` TINYINT NULL,
  `price` INT NULL,
  `userId` VARCHAR(20) NULL,
  `payDate` DATE NULL,
  PRIMARY KEY (`payId`),
  INDEX `payInfo_userId_idx` (`userId` ASC) VISIBLE,
  FOREIGN KEY (userId)
  REFERENCES userInfo(userId) ON UPDATE CASCADE ON DELETE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db1`.`seat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db1`.`seat` (
  `seatId` INT AUTO_INCREMENT NOT NULL,
  `roomId` INT NULL,
  `isUsed` TINYINT NULL,
  `colX` INT NULL,
  `rowY` INT NULL,
  `showScheduleId` INT NULL,
  PRIMARY KEY (`seatId`),
  INDEX `seat_roomId_idx` (`roomId` ASC) VISIBLE,
  INDEX `seat_showScheduleId_idx` (`showScheduleId` ASC) VISIBLE,
  FOREIGN KEY (roomId)
  REFERENCES room(roomId) ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (showScheduleId)
  REFERENCES showSchedule(showScheduleId) ON UPDATE CASCADE ON DELETE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db1`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db1`.`ticket` (
  `ticketId` INT AUTO_INCREMENT NOT NULL,
  `showScheduleId` INT NULL,
  `roomId` INT NULL,
  `seatId` INT NULL,
  `payId` INT NULL,
  `isPrinted` TINYINT NULL,
  `averageSale` INT NULL,
  `price` INT NULL,
  PRIMARY KEY (`ticketId`),
  INDEX `ticket_showScheduleId_idx` (`showScheduleId` ASC) VISIBLE,
  INDEX `ticket_roomId_idx` (`roomId` ASC) VISIBLE,
  INDEX `ticket_payId_idx` (`payId` ASC) VISIBLE,
  INDEX `fk_ticket_seatId_idx` (`seatId` ASC) VISIBLE,
  FOREIGN KEY (showScheduleId)
  REFERENCES showSchedule(showScheduleId) ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (roomId)
  REFERENCES room(roomId) ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (seatId)
  REFERENCES seat(seatId) ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (payId)
  REFERENCES payInfo(payId) ON UPDATE CASCADE ON DELETE RESTRICT)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
DROP USER IF EXISTS 'user1'@'localhost' ;
CREATE USER 'user1'@'localhost' IDENTIFIED BY 'user1';
GRANT ALL PRIVILEGES ON db1.* TO 'user1'@'localhost';

