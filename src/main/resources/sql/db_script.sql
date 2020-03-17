CREATE DATABASE `web_service`;

CREATE TABLE web_service.topic (id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL ,
PRIMARY KEY (id));


INSERT INTO `topic` ( name) VALUES ('consultation');
INSERT INTO `topic` (name) VALUES ('mobile connection issue');
INSERT INTO `topic` (name) VALUES ('internet connection issue');
INSERT INTO `topic` (name) VALUES ('forgot pincode');
INSERT INTO `topic` (name) VALUES ('mobile phone lost');

CREATE TABLE `web_service`.`inquiry_attribute`
( `ID` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(50) NOT NULL ,
  `value` VARCHAR(250) NOT NULL ,
  PRIMARY KEY (`ID`)) ENGINE = InnoDB;

CREATE TABLE `web_service`.`inquiry`
( `ID` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(50) NOT NULL ,
  `date_of_inquiry` DATE NOT NULL ,
  `attribute_id` INT NULL ,
  `topic_id` INT NOT NULL ,
  PRIMARY KEY (`ID`)) ENGINE = InnoDB;

ALTER TABLE `web_service`.`inquiry` ADD CONSTRAINT `fk_topic-id` FOREIGN KEY
  (`topic_id`) REFERENCES `topic`(`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

ALTER TABLE `web_service`.`inquiry` ADD CONSTRAINT `attribute_id` FOREIGN KEY
  (`attribute_id`) REFERENCES `inquiry_attribute`(`ID`) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE `inquiry` CHANGE `date_of_inquiry` `date_of_inquiry` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP();
ALTER TABLE `inquiry` DROP FOREIGN KEY `attribute_id`; ALTER TABLE `inquiry` ADD CONSTRAINT `fk_attribute_id` FOREIGN KEY (`attribute_id`) REFERENCES `attribute_of_inquiry`(`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `inquiry` ADD CONSTRAINT `fk_attribute_id` FOREIGN KEY (`attribute_id`) REFERENCES `attribute_of_inquiry`(`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `inquiry` ADD `customerName` VARCHAR(50) NOT NULL AFTER `customer_name`;
ALTER TABLE `inquiry` ADD CONSTRAINT `fk_attribute_id` FOREIGN KEY (`attribute_id`) REFERENCES `inquiry_attribute`(`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO `inquiry`(`id`, `customer_name`, `date_of_inquiry`, `description`, `topic_id`) 
VALUES ('21','ArtemTest','2020-03-16 12:02:50','Test','1');
INSERT INTO `inquiry`(`id`, `customer_name`, `date_of_inquiry`, `description`, `topic_id`)
VALUES ('2','ArtemTest','2020-03-18 12:02:50','Test2','2')
