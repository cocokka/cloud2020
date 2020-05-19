CREATE TABLE `payment`(
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
	`serial` varchar(200) DEFAULT '',
	PRIMARY KEY (`id`)
) ENGING=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into "payment" (id, "serial") value (1, )