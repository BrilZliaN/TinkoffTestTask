CREATE TABLE `applications` (
	`APPLICATION_ID` INT(11) NOT NULL AUTO_INCREMENT,
	`CONTACT_ID` INT(11) NOT NULL,
	`DT_CREATED` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`PRODUCT_NAME` VARCHAR(63) NOT NULL,
	PRIMARY KEY (`APPLICATION_ID`)
);