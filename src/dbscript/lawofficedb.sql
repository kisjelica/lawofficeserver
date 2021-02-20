-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema lawofficedb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lawofficedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lawofficedb` DEFAULT CHARACTER SET utf8mb4 ;
USE `lawofficedb` ;

-- -----------------------------------------------------
-- Table `lawofficedb`.`administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lawofficedb`.`administrator` (
  `administratorid` BIGINT(20) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`administratorid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `lawofficedb`.`lawyer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lawofficedb`.`lawyer` (
  `lawyerid` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `firstname` VARCHAR(45) NULL DEFAULT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  `dateofemployment` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`lawyerid`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `lawofficedb`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lawofficedb`.`client` (
  `clientid` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `cfirstname` VARCHAR(45) NULL DEFAULT NULL,
  `clastname` VARCHAR(45) NULL DEFAULT NULL,
  `phonenumber` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `lawyerid` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`clientid`),
  INDEX `fk_client_lawyer_idx` (`lawyerid` ASC),
  CONSTRAINT `fk_client_lawyer`
    FOREIGN KEY (`lawyerid`)
    REFERENCES `lawofficedb`.`lawyer` (`lawyerid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `lawofficedb`.`invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lawofficedb`.`invoice` (
  `invoiceid` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `invoicenumber` VARCHAR(20) NULL DEFAULT NULL,
  `invoicetotal` DOUBLE(10,2) NULL DEFAULT NULL,
  `invoicedate` DATETIME NULL DEFAULT NULL,
  `cancelled` TINYINT(1) NULL DEFAULT 0,
  `processed` TINYINT(1) NULL DEFAULT 0,
  `lawyerid` BIGINT(20) NULL DEFAULT NULL,
  `clientid` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`invoiceid`),
  INDEX `fk_invoice_lawyer_idx` (`lawyerid` ASC),
  INDEX `fk_invoice_client_idx` (`clientid` ASC),
  CONSTRAINT `fk_invoice_client`
    FOREIGN KEY (`clientid`)
    REFERENCES `lawofficedb`.`client` (`clientid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_lawyer`
    FOREIGN KEY (`lawyerid`)
    REFERENCES `lawofficedb`.`lawyer` (`lawyerid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `lawofficedb`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lawofficedb`.`service` (
  `serviceid` BIGINT(20) NOT NULL,
  `servicename` VARCHAR(45) NOT NULL,
  `servicedescription` VARCHAR(1000) NOT NULL,
  `fee` DOUBLE(10,2) NOT NULL,
  PRIMARY KEY (`serviceid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `lawofficedb`.`invoiceitem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lawofficedb`.`invoiceitem` (
  `invoiceid` BIGINT(20) NOT NULL,
  `ordernumber` INT(11) NOT NULL,
  `price` DOUBLE(10,2) NOT NULL,
  `quantity` INT(11) NOT NULL,
  `amount` DOUBLE(10,2) NOT NULL,
  `serviceid` BIGINT(20) NOT NULL,
  PRIMARY KEY (`invoiceid`, `ordernumber`),
  INDEX `fk_invoiceitem_service_idx` (`serviceid` ASC),
  CONSTRAINT `fk_invoiceitem_invoice`
    FOREIGN KEY (`invoiceid`)
    REFERENCES `lawofficedb`.`invoice` (`invoiceid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoiceitem_service`
    FOREIGN KEY (`serviceid`)
    REFERENCES `lawofficedb`.`service` (`serviceid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `lawofficedb`.`obligation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lawofficedb`.`obligation` (
  `obligationid` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `obligationsubject` VARCHAR(45) NULL DEFAULT NULL,
  `obligationdescription` VARCHAR(1000) NULL DEFAULT NULL,
  `dateofobligation` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`obligationid`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `lawofficedb`.`obligationattendance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lawofficedb`.`tobligationattendance` (
  `lawyerid` BIGINT(20) NOT NULL,
  `obligationid` BIGINT(20) NOT NULL,
  `attendedobligation` TINYINT(1) NOT NULL,
  PRIMARY KEY (`lawyerid`, `obligationid`),
  INDEX `fk_obligationattendance_obligation_idx` (`obligationid` ASC),
  CONSTRAINT `fk_obligationattendance_lawyer`
    FOREIGN KEY (`lawyerid`)
    REFERENCES `lawofficedb`.`lawyer` (`lawyerid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_obligationattendance_obligation`
    FOREIGN KEY (`obligationid`)
    REFERENCES `lawofficedb`.`obligation` (`obligationid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


INSERT INTO `lawofficedb`.`service` (`serviceid`, `servicename`, `servicedescription`, `fee`) VALUES ('1', 'Consultation (Hourly)', 'General type of legal consultations.', '50.00');
INSERT INTO `lawofficedb`.`service` (`serviceid`, `servicename`, `servicedescription`, `fee`) VALUES ('2', 'Field work (Hourly)', 'Any type of field work.', '80.00');
INSERT INTO `lawofficedb`.`service` (`serviceid`, `servicename`, `servicedescription`, `fee`) VALUES ('3', 'General hourly rate', 'The rate for unspecified services.', '60.00');
INSERT INTO `lawofficedb`.`service` (`serviceid`, `servicename`, `servicedescription`, `fee`) VALUES ('4', 'Criminal case defense (Hourly)', 'Rate for criminal cases.', '100.00');
INSERT INTO `lawofficedb`.`service` (`serviceid`, `servicename`, `servicedescription`, `fee`) VALUES ('5', 'Litigation (Hourly)', 'Rate for civil litigation cases.', '90.00');
INSERT INTO `lawofficedb`.`service` (`serviceid`, `servicename`, `servicedescription`, `fee`) VALUES ('6', 'Writing a contract.', 'Any type of contract.', '75.00');
INSERT INTO `lawofficedb`.`service` (`serviceid`, `servicename`, `servicedescription`, `fee`) VALUES ('7', 'Mediation (Hourly)', 'Neutral mediation between parties.', '70.00');
INSERT INTO `lawofficedb`.`service` (`serviceid`, `servicename`, `servicedescription`, `fee`) VALUES ('8', 'Writing a will.', 'Creation and evidention of a last will and testament.', '150.00');
INSERT INTO `lawofficedb`.`service` (`serviceid`, `servicename`, `servicedescription`, `fee`) VALUES ('9', 'Writing a lawsuit.', 'Creating and evidenting a lawsuit.', '120.00');
INSERT INTO `lawofficedb`.`administrator` (`administratorid`, `username`, `password`) VALUES ('1', 'admin1', 'admin1');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
