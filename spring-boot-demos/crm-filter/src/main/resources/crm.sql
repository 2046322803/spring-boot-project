-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES ('1', 'system', NULL, 'glyphicon glyphicon-cog', 'system manage', NULL, 10);
INSERT INTO `system_menu` VALUES ('96e0843b35e54dc1bc5eda891c53004b', 'menu', '/system/menu/list/1', NULL, 'menu', '1', 13);
INSERT INTO `system_menu` VALUES ('a80e48389e7d4e4ba9001f30a31f71fc', 'user', '/system/user/list/1', NULL, 'user', '1', 11);
INSERT INTO `system_menu` VALUES ('ad6143b987d5412caa9158b682171261', 'operate', '/system/operate/list/1', NULL, 'operate', '1', 14);
INSERT INTO `system_menu` VALUES ('cc80a63cd43a434eaaad4f8a1cc7e82d', 'role', '/system/role/list/1', NULL, 'role', '1', 12);

-- ----------------------------
-- Records of system_operate
-- ----------------------------
INSERT INTO `system_operate` VALUES ('0ce8fc89115f419982dd385ccfcce2c0', '/system/menu/add', NULL, 'add', '3', 121);
INSERT INTO `system_operate` VALUES ('0da2cfdebca84ef293a380046d139d44', '/system/role/list', NULL, 'list', '2', 114);
INSERT INTO `system_operate` VALUES ('1', NULL, NULL, 'user power', NULL, 100);
INSERT INTO `system_operate` VALUES ('10f4aabeddd54f719236bb0e8d9109e9', '/system/user/save', NULL, 'save', '1', 105);
INSERT INTO `system_operate` VALUES ('2', NULL, NULL, 'role power', NULL, 110);
INSERT INTO `system_operate` VALUES ('286baf44cfe244bf84a4cae133c4e411', '/system/role/eidt', NULL, 'edit', '2', 113);
INSERT INTO `system_operate` VALUES ('3', NULL, NULL, 'menu power', NULL, 120);
INSERT INTO `system_operate` VALUES ('36bb6f971d1c4c949c891f1913670eed', '/system/operate/add', NULL, 'add', '4', 131);
INSERT INTO `system_operate` VALUES ('4', NULL, NULL, 'operate power', NULL, 130);
INSERT INTO `system_operate` VALUES ('416a56d3904b4ffdab29fc236d7a55c8', '/system/menu/list', NULL, 'list', '3', 124);
INSERT INTO `system_operate` VALUES ('6a118fa1b456450daec3bd653df7a29f', '/system/role/delete', NULL, 'delete', '2', 112);
INSERT INTO `system_operate` VALUES ('6fa3ab100d574ef8a29af8385dcd3538', '/system/menu/eidt', NULL, 'edit', '3', 123);
INSERT INTO `system_operate` VALUES ('741efa85653f4d94a12787c71f80082b', '/system/operate/update', NULL, 'update', '4', 136);
INSERT INTO `system_operate` VALUES ('7507490f3fe543fdb91a0b0f0c6bdabd', '/system/operate/list', NULL, 'list', '4', 134);
INSERT INTO `system_operate` VALUES ('7aca776b67d64cc38954ad767591fdad', '/system/operate/delete', NULL, 'delete', '4', 132);
INSERT INTO `system_operate` VALUES ('810bbfa43c614b1d86c9fb2cecbc9c80', '/system/user/delete', NULL, 'delete', '1', 102);
INSERT INTO `system_operate` VALUES ('8e8394e977c946a5bbfb09717af67e03', '/system/user/eidt', NULL, 'edit', '1', 103);
INSERT INTO `system_operate` VALUES ('91d5ee4e7e5c42ce9fe5ead9416f0a05', '/system/role/add', NULL, 'add', '2', 111);
INSERT INTO `system_operate` VALUES ('93e8d1e555a547548e3723193bf7e5c6', '/system/operate/save', NULL, 'save', '4', 135);
INSERT INTO `system_operate` VALUES ('95cb8d190d884f3ca0a0195a10bfbba1', '/system/user/update', NULL, 'update', '1', 106);
INSERT INTO `system_operate` VALUES ('9a3eac75f8894f45a88eeac74ee7f45f', '/system/menu/update', NULL, 'update', '3', 126);
INSERT INTO `system_operate` VALUES ('a14291fa99804cb2a96ecf53f2936ae6', '/system/user/list', NULL, 'list', '1', 104);
INSERT INTO `system_operate` VALUES ('ca5d2505b5f24a52811a0f4c679a1d75', '/system/user/add', NULL, 'add', '1', 101);
INSERT INTO `system_operate` VALUES ('dfa6a9fe458f4690b25a86bbdba4bf03', '/system/role/save', NULL, 'save', '2', 115);
INSERT INTO `system_operate` VALUES ('e4e7ec13a267455e80ced5f23a744997', '/system/menu/save', NULL, 'save', '3', 125);
INSERT INTO `system_operate` VALUES ('ed09dde86d2044f8b2a74b5756e58049', '/system/role/update', NULL, 'update', '2', 116);
INSERT INTO `system_operate` VALUES ('f43feeaa92b24fee932c0906600a0b77', '/system/operate/eidt', NULL, 'edit', '4', 133);
INSERT INTO `system_operate` VALUES ('fa4bf145f3224b67a3d950645a4540f9', '/system/menu/delete', NULL, 'delete', '3', 122);

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('5888b89ada1f4f6e903db48ff2dd4291', 'SUPER', '超级管理员');

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('5a260710235b41e5a23af0e598cfca21', 'super@bsp.com', 'super', '123456', NULL, '5888b89ada1f4f6e903db48ff2dd4291');