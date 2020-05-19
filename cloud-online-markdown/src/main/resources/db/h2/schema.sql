CREATE TABLE IF NOT EXISTS `content` (
	`id` INT (11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`title` varchar,
	`text` clob,
	`text_content` clob,
	PRIMARY KEY (`id`)
);