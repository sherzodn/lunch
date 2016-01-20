
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(50) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(50) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FK_krvotbtiqhudlkamvlpaqus0t` (`role_id`),
  CONSTRAINT `FK_krvotbtiqhudlkamvlpaqus0t` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


-- ----------------------------
-- Table structure for `restaurants`
-- ----------------------------
DROP TABLE IF EXISTS `restaurants`;
CREATE TABLE `restaurants` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hs57n29k4u6jfc5t978bq7wg9` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


-- ----------------------------
-- Table structure for `dishes`
-- ----------------------------
DROP TABLE IF EXISTS `dishes`;
CREATE TABLE `dishes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `restaurant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_345ijd3g2icxr9c6j6jt1n5ur` (`name`,`restaurant_id`),
  KEY `FK_i18fm2bexktp5c51ixaqj5tx4` (`restaurant_id`),
  CONSTRAINT `FK_i18fm2bexktp5c51ixaqj5tx4` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;


-- ----------------------------
-- Table structure for `votes`
-- ----------------------------
DROP TABLE IF EXISTS `votes`;
CREATE TABLE `votes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `voted_date` datetime DEFAULT NULL,
  `restaurant_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_as85mjjbi2e2yi7jj1s3q2f5` (`restaurant_id`),
  KEY `FK_jpy5cpqhdr870g41de432t0kp` (`user_id`),
  CONSTRAINT `FK_jpy5cpqhdr870g41de432t0kp` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_as85mjjbi2e2yi7jj1s3q2f5` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


-- ----------------------------
-- Records of restaurants
-- ----------------------------
INSERT INTO `restaurants` VALUES ('1', 'Restaurant 1');
INSERT INTO `restaurants` VALUES ('2', 'Restaurant 2');
INSERT INTO `restaurants` VALUES ('3', 'Restaurant 3');
INSERT INTO `restaurants` VALUES ('4', 'Restaurant 4');
INSERT INTO `restaurants` VALUES ('5', 'Restaurant 55');


-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `roles` VALUES ('2', 'ROLE_USER');


-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'Sherzod Nurjonov', '$2a$10$tWO30saVOWFQKk9gK3KSXuWGwGYQzim7UWoW49zx10EEJ.P/n.Ayu', 'admin', '1');
INSERT INTO `users` VALUES ('2', 'Ivan Ivanov', '$2a$10$ogvUr2Ad.GzuquXCCy.C0eSVfPm9.xH1z91WVOoWlWlKio2To.Za6', 'user1', '2');
INSERT INTO `users` VALUES ('3', 'Lionel Messi', '$2a$10$k7PISiRtt31ckAaGt//rKunAVnqx1Su0YakeKhSsNWUrRLTIv7At6', 'user2', '2');


-- ----------------------------
-- Records of dishes
-- ----------------------------
INSERT INTO `dishes` VALUES ('1', 'dish A1', '10', '1');
INSERT INTO `dishes` VALUES ('2', 'dish A2', '15', '1');
INSERT INTO `dishes` VALUES ('3', 'dish A3', '20', '1');
INSERT INTO `dishes` VALUES ('4', 'dish B1', '20', '2');
INSERT INTO `dishes` VALUES ('5', 'dish B2', '25', '2');
INSERT INTO `dishes` VALUES ('6', 'dish B3', '30', '2');
INSERT INTO `dishes` VALUES ('7', 'dish C1', '30', '3');
INSERT INTO `dishes` VALUES ('8', 'dish C2', '35', '3');
INSERT INTO `dishes` VALUES ('9', 'dish D1', '40', '4');
INSERT INTO `dishes` VALUES ('10', 'dish D2', '38', '4');
INSERT INTO `dishes` VALUES ('11', 'dish D3', '33', '4');



