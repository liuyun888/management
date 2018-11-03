
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `parentIds` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `resourceType` enum('menu','button') DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '', '用户管理', '0', '0/', 'userInfo:view', 'menu', 'userInfo');
INSERT INTO `sys_permission` VALUES ('2', '', '用户添加', '1', '0/1', 'userInfo:add', 'button', 'userInfo/userAdd');
INSERT INTO `sys_permission` VALUES ('3', '', '用户删除', '1', '0/1', 'userInfo:del', 'button', 'userInfo/userDel');
INSERT INTO `sys_permission` VALUES ('4', '', '用户角色', '1', '0/1', 'userInfo:setRole', 'button', 'userInfo/setUserRole');
INSERT INTO `sys_permission` VALUES ('5', '', '用户查询', '1', '0/1', 'userInfo:list', 'button', 'userInfo/userList');
INSERT INTO `sys_permission` VALUES ('6', '', '用户更新', '1', '0/1', 'userInfo:update', 'button', 'userInfo/userUpdate');

INSERT INTO `sys_permission` VALUES ('7', '', '角色管理', '2', '2/', 'sysRole:view', 'menu', 'role');
INSERT INTO `sys_permission` VALUES ('8', '', '角色添加', '3', '2/3', 'sysRole:add', 'button', 'role/roleAdd');
INSERT INTO `sys_permission` VALUES ('9', '', '角色删除', '3', '2/3', 'sysRole:del', 'button', 'role/roleDel');
INSERT INTO `sys_permission` VALUES ('10', '', '角色权限', '3', '2/3', 'sysRole:setPermission', 'button', 'role/setRolePermission');
INSERT INTO `sys_permission` VALUES ('11', '', '角色查询', '3', '2/3', 'sysRole:list', 'button', 'role/roleList');
INSERT INTO `sys_permission` VALUES ('12', '', '角色更新', '3', '2/3', 'sysRole:update', 'button', 'role/roleUpdate');

INSERT INTO `sys_permission` VALUES ('13', '', '权限管理', '4', '4/', 'sysPermission:view', 'menu', 'permission');
INSERT INTO `sys_permission` VALUES ('14', '', '权限添加', '5', '4/5', 'sysPermission:add', 'button', 'permission/permissionAdd');
INSERT INTO `sys_permission` VALUES ('15', '', '权限删除', '5', '4/5', 'sysPermission:del', 'button', 'permission/permissionDel');
INSERT INTO `sys_permission` VALUES ('16', '', '权限查询', '5', '4/5', 'sysPermission:list', 'button', 'permission/permissionList');
INSERT INTO `sys_permission` VALUES ('17', '', '权限更新', '5', '4/5', 'sysPermission:update', 'button', 'permission/permissionUpdate');

INSERT INTO `sys_permission` VALUES ('18', '', '用户相关', '6', '6/', 'user:view', 'menu', '');
INSERT INTO `sys_permission` VALUES ('19', '', '师徒关系', '7', '6/7', 'user:plan', 'button', 'plan');
INSERT INTO `sys_permission` VALUES ('20', '', '头像审查', '7', '6/7', 'user:avatar', 'button', 'avatar');
INSERT INTO `sys_permission` VALUES ('21', '', '检测审核', '7', '6/7', 'user:testing', 'button', 'testing ');

INSERT INTO `sys_permission` VALUES ('22', '', '钱包相关', '8', '8/', 'coin:view', 'menu', '');
INSERT INTO `sys_permission` VALUES ('23', '', '金币流水', '9', '8/9', 'coin:goldCoin', 'button', 'goldCoin');
INSERT INTO `sys_permission` VALUES ('24', '', '提现记录', '9', '8/9', 'coin:record', 'button', 'record');
INSERT INTO `sys_permission` VALUES ('25', '', '提现审核', '9', '8/9', 'coin:recordcheck', 'button', 'recordcheck ');

INSERT INTO `sys_permission` VALUES ('26', '', '竞猜相关', '10', '10/', 'guess:view', 'menu', '');
INSERT INTO `sys_permission` VALUES ('27', '', '发布竞猜', '11', '10/11', 'guess:guessing', 'button', 'guessing');
INSERT INTO `sys_permission` VALUES ('28', '', '竞猜查询', '11', '10/11', 'guess:guess', 'button', 'guess');

INSERT INTO `sys_permission` VALUES ('29', '', '拼单相关', '12', '12/', 'commodity:view', 'menu', '');
INSERT INTO `sys_permission` VALUES ('30', '', '商品录入', '13', '12/13', 'commodity:commodityEntry', 'button', 'commodityEntry');
INSERT INTO `sys_permission` VALUES ('31', '', '商品展示', '13', '12/13', 'commodity:commodityShow', 'button', 'commodityShow');
INSERT INTO `sys_permission` VALUES ('32', '', '拼单审核', '13', '12/13', 'commodity:ordercheck', 'button', 'ordercheck ');

INSERT INTO `sys_permission` VALUES ('33', '', '活动相关', '14', '14/', 'game:view', 'menu', '');
INSERT INTO `sys_permission` VALUES ('34', '', '国庆', '15', '14/15', 'game:nationaldayswing', 'button', 'nationaldayswing');
INSERT INTO `sys_permission` VALUES ('35', '', '锦绣山河', '15', '14/15', 'game:cardGame', 'button', 'cardGame');

INSERT INTO `sys_permission` VALUES ('36', '', '新闻相关', '16', '16/', 'news:view', 'menu', '');
INSERT INTO `sys_permission` VALUES ('37', '', '评论管理', '17', '16/17', 'news:comment', 'button', 'comment');
INSERT INTO `sys_permission` VALUES ('38', '', '新闻推送', '17', '16/17', 'news:pushnews', 'button', 'pushnews');

INSERT INTO `sys_permission` VALUES ('39', '', '反馈管理', '18', '18/', 'feedback:view', 'menu', '');
INSERT INTO `sys_permission` VALUES ('40', '', '反馈信息', '19', '18/19', 'feedback:feedback', 'button', 'feedback');

INSERT INTO `sys_permission` VALUES ('41', '', '功能性需求', '20', '20/', 'function:view', 'menu', '');
INSERT INTO `sys_permission` VALUES ('42', '', '图片上传', '21', '20/21', 'function:feedback', 'button', 'upload');

INSERT INTO `sys_permission` VALUES ('43', '', '运营小需求', '20', '20/', 'operation:view', 'menu', '');
INSERT INTO `sys_permission` VALUES ('44', '', '运营小需求', '21', '20/21', 'operation:operatedneed', 'button', 'operatedneed');



-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '', '管理员', 'admin');
INSERT INTO `sys_role` VALUES ('2', '', 'VIP会员', 'vip');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleId` bigint(20) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  KEY `FK_pn90qffgw1e6lo1xhw964qadf` (`roleId`),
  KEY `FK_qr3wmwfxapktvdv5g6d4mbtta` (`permissionId`),
  CONSTRAINT `FK_pn90qffgw1e6lo1xhw964qadf` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_qr3wmwfxapktvdv5g6d4mbtta` FOREIGN KEY (`permissionId`) REFERENCES `sys_permission` (`id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1','1', '1');
INSERT INTO `sys_role_permission` VALUES ('2','1', '2');
INSERT INTO `sys_role_permission` VALUES ('3','1', '3');
INSERT INTO `sys_role_permission` VALUES ('4','1', '4');
INSERT INTO `sys_role_permission` VALUES ('5','1', '5');
INSERT INTO `sys_role_permission` VALUES ('6','1', '6');
INSERT INTO `sys_role_permission` VALUES ('7','1', '7');
INSERT INTO `sys_role_permission` VALUES ('8','1', '8');
INSERT INTO `sys_role_permission` VALUES ('9','1', '9');
INSERT INTO `sys_role_permission` VALUES ('10','1', '10');
INSERT INTO `sys_role_permission` VALUES ('11','1', '11');
INSERT INTO `sys_role_permission` VALUES ('12','1', '12');
INSERT INTO `sys_role_permission` VALUES ('13','1', '13');
INSERT INTO `sys_role_permission` VALUES ('14','1', '14');
INSERT INTO `sys_role_permission` VALUES ('15','1', '15');
INSERT INTO `sys_role_permission` VALUES ('16','1', '16');
INSERT INTO `sys_role_permission` VALUES ('17','1', '17');
INSERT INTO `sys_role_permission` VALUES ('18','1', '18');
INSERT INTO `sys_role_permission` VALUES ('19','1', '19');
INSERT INTO `sys_role_permission` VALUES ('20','1', '20');
INSERT INTO `sys_role_permission` VALUES ('21','1', '21');
INSERT INTO `sys_role_permission` VALUES ('22','1', '22');
INSERT INTO `sys_role_permission` VALUES ('23','1', '23');
INSERT INTO `sys_role_permission` VALUES ('24','1', '24');
INSERT INTO `sys_role_permission` VALUES ('25','1', '25');
INSERT INTO `sys_role_permission` VALUES ('26','1', '26');
INSERT INTO `sys_role_permission` VALUES ('27','1', '27');
INSERT INTO `sys_role_permission` VALUES ('28','1', '28');
INSERT INTO `sys_role_permission` VALUES ('29','1', '29');
INSERT INTO `sys_role_permission` VALUES ('30','1', '30');
INSERT INTO `sys_role_permission` VALUES ('31','1', '31');
INSERT INTO `sys_role_permission` VALUES ('32','1', '32');
INSERT INTO `sys_role_permission` VALUES ('33','1', '33');
INSERT INTO `sys_role_permission` VALUES ('34','1', '34');
INSERT INTO `sys_role_permission` VALUES ('35','1', '35');
INSERT INTO `sys_role_permission` VALUES ('36','1', '36');
INSERT INTO `sys_role_permission` VALUES ('37','1', '37');
INSERT INTO `sys_role_permission` VALUES ('38','1', '38');
INSERT INTO `sys_role_permission` VALUES ('39','1', '39');
INSERT INTO `sys_role_permission` VALUES ('40','1', '40');
INSERT INTO `sys_role_permission` VALUES ('41','1', '41');
INSERT INTO `sys_role_permission` VALUES ('42','1', '42');
INSERT INTO `sys_role_permission` VALUES ('43','1', '43');
INSERT INTO `sys_role_permission` VALUES ('44','1', '44');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  KEY `FK_io5ssq2ol6uqcx9nll8gfnm4n` (`uid`),
  KEY `FK_suwqmd7mystg1lwv8o4ffxaag` (`roleId`),
  CONSTRAINT `FK_io5ssq2ol6uqcx9nll8gfnm4n` FOREIGN KEY (`uid`) REFERENCES `user_info` (`uid`),
  CONSTRAINT `FK_suwqmd7mystg1lwv8o4ffxaag` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1','1', '1');
INSERT INTO `sys_user_role` VALUES ('2','1', '2');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `state` tinyint(4) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `UK_45fvrme4q2wy85b1vbf55hm6s` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '管理员', 'admin', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '0');
