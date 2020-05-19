
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE
IF EXISTS `content`;

CREATE TABLE `content` (
	`id` INT (11) NOT NULL AUTO_INCREMENT,
	`text` text,
	`text_content` text,
	PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 2 DEFAULT CHARSET = utf8;

INSERT INTO `content`
VALUES
	(
		'1',
		'第一天文本内容'
	);