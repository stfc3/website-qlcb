/*
 Navicat Premium Data Transfer

 Source Server         : QLCB_DEV
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 35.198.218.250:3306
 Source Schema         : qlcb

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 03/04/2018 20:40:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for stfc_banner
-- ----------------------------
DROP TABLE IF EXISTS `stfc_banner`;
CREATE TABLE `stfc_banner`  (
  `banner_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `banner_name` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `banner_image` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ảnh Logo, Banner',
  `banner_url` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Link liên kết ảnh',
  `banner_type` int(11) NULL DEFAULT NULL COMMENT '1: Logo, 2: Banner',
  `banner_order` int(11) NULL DEFAULT NULL,
  `banner_status` int(11) NULL DEFAULT NULL COMMENT '1: Hoạt động, 0: Không hoạt động',
  `effect_from_date` datetime(0) NULL DEFAULT NULL,
  `effect_to_date` datetime(0) NULL DEFAULT NULL,
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày sửa',
  PRIMARY KEY (`banner_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng Banner' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_banner
-- ----------------------------
INSERT INTO `stfc_banner` VALUES (1, 'banner 1', 'media/banner/1.jpg', 'banner-1', 2, 1, 1, '2018-02-15 07:35:10', '2019-02-15 07:35:10', '2018-03-15 07:35:10', '2018-03-15 07:35:10');
INSERT INTO `stfc_banner` VALUES (2, 'banner 2', 'media/banner/2.jpg', 'banner-2', 2, 2, 1, '2018-02-15 07:35:10', '2019-02-15 07:35:10', '2018-03-15 07:36:02', '2018-03-15 07:36:02');
INSERT INTO `stfc_banner` VALUES (3, 'banner 3', 'media/banner/3.jpg', 'banner-3', 2, 3, 1, '2018-02-15 07:35:10', NULL, '2018-03-15 07:36:02', '2018-03-15 07:36:02');

-- ----------------------------
-- Table structure for stfc_categories
-- ----------------------------
DROP TABLE IF EXISTS `stfc_categories`;
CREATE TABLE `stfc_categories`  (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category_parent` bigint(20) NULL DEFAULT NULL,
  `category_slug` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category_order` int(11) NULL DEFAULT NULL,
  `category_status` int(11) NULL DEFAULT NULL COMMENT '1: Hoạt động, 0: Không hoạt động',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày sửa',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng Categories' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_categories
-- ----------------------------
INSERT INTO `stfc_categories` VALUES (1, 'Tin hot', NULL, '/category/tin-hot', 1, 1, '2018-03-15 03:38:57', '2018-03-15 03:38:57');
INSERT INTO `stfc_categories` VALUES (2, 'Tin tuc - su kien', NULL, '/category/tin-tuc-su-kien', 2, 1, '2018-03-15 03:39:17', '2018-03-15 03:39:17');
INSERT INTO `stfc_categories` VALUES (3, 'Tuyển sinh', NULL, '/category/tuyen-sinh', 3, 1, '2018-03-15 04:31:34', '2018-03-15 04:31:34');
INSERT INTO `stfc_categories` VALUES (4, 'Đào tạo', NULL, '/category/dao-tao', 4, 1, '2018-03-22 02:04:03', '2018-03-22 02:04:03');
INSERT INTO `stfc_categories` VALUES (5, 'Thông báo', NULL, '/category/thong-bao', 5, 1, '2018-03-22 02:04:22', '2018-03-22 02:04:22');
INSERT INTO `stfc_categories` VALUES (6, 'Văn bản nội bộ', NULL, '/category/van-ban-noi-bo', 1, 1, '2018-04-02 08:22:03', '2018-04-02 08:22:03');
INSERT INTO `stfc_categories` VALUES (7, 'Nghi quyet 1', NULL, '/category/nghi-quyet-1', 2, 1, '2018-04-02 08:27:18', '2018-04-02 08:27:18');
INSERT INTO `stfc_categories` VALUES (8, 'Nghi quyet 2', NULL, '/category/nghi-quyet-2', 3, 1, '2018-04-02 08:27:18', '2018-04-02 08:27:18');
INSERT INTO `stfc_categories` VALUES (9, 'Nghi quyet 3', NULL, '/category/nghi-quyet-3', 4, 1, '2018-04-02 08:27:18', '2018-04-02 08:27:18');

-- ----------------------------
-- Table structure for stfc_category_post
-- ----------------------------
DROP TABLE IF EXISTS `stfc_category_post`;
CREATE TABLE `stfc_category_post`  (
  `map_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) NULL DEFAULT NULL,
  `post_id` bigint(20) NULL DEFAULT NULL,
  `post_status` int(11) NULL DEFAULT NULL COMMENT '1: Hoạt động, 0: Không hoạt động',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`map_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng map category and post' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_category_post
-- ----------------------------
INSERT INTO `stfc_category_post` VALUES (1, 2, 1, 1, '2018-03-15 03:40:30');
INSERT INTO `stfc_category_post` VALUES (2, 2, 2, 1, '2018-03-15 03:40:30');
INSERT INTO `stfc_category_post` VALUES (3, 2, 3, 1, '2018-03-15 06:57:56');
INSERT INTO `stfc_category_post` VALUES (4, 2, 4, 1, '2018-03-15 06:57:56');
INSERT INTO `stfc_category_post` VALUES (5, 2, 5, 1, '2018-03-22 02:22:54');
INSERT INTO `stfc_category_post` VALUES (6, 2, 6, 1, '2018-03-22 02:22:54');
INSERT INTO `stfc_category_post` VALUES (7, 3, 7, 1, '2018-03-22 02:22:54');
INSERT INTO `stfc_category_post` VALUES (8, 3, 8, 1, '2018-03-22 02:42:22');
INSERT INTO `stfc_category_post` VALUES (9, 3, 9, 1, '2018-03-22 02:42:22');
INSERT INTO `stfc_category_post` VALUES (10, 3, 10, 1, '2018-03-22 02:42:22');
INSERT INTO `stfc_category_post` VALUES (11, 3, 11, 1, '2018-03-22 02:42:22');
INSERT INTO `stfc_category_post` VALUES (12, 4, 12, 1, '2018-03-22 02:58:56');
INSERT INTO `stfc_category_post` VALUES (13, 4, 13, 1, '2018-03-22 02:58:56');
INSERT INTO `stfc_category_post` VALUES (14, 4, 14, 1, '2018-03-22 02:58:57');
INSERT INTO `stfc_category_post` VALUES (15, 4, 15, 1, '2018-03-22 02:58:57');
INSERT INTO `stfc_category_post` VALUES (16, 5, 8, 1, '2018-03-22 03:01:08');
INSERT INTO `stfc_category_post` VALUES (17, 5, 9, 1, '2018-03-22 03:01:09');
INSERT INTO `stfc_category_post` VALUES (18, 5, 10, 1, '2018-03-22 03:01:09');
INSERT INTO `stfc_category_post` VALUES (19, 5, 11, 1, '2018-03-22 03:01:09');
INSERT INTO `stfc_category_post` VALUES (20, 2, 7, NULL, '2018-03-23 03:57:47');
INSERT INTO `stfc_category_post` VALUES (21, 2, 8, NULL, '2018-03-23 03:57:47');
INSERT INTO `stfc_category_post` VALUES (22, 2, 9, NULL, '2018-03-23 03:57:47');

-- ----------------------------
-- Table structure for stfc_class
-- ----------------------------
DROP TABLE IF EXISTS `stfc_class`;
CREATE TABLE `stfc_class`  (
  `class_id` bigint(20) NOT NULL,
  `class_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = 'Bảng lớp học' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for stfc_document
-- ----------------------------
DROP TABLE IF EXISTS `stfc_document`;
CREATE TABLE `stfc_document`  (
  `document_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `document_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `document_type` int(11) NULL DEFAULT NULL COMMENT '1: Thời khóa biểu; 2: Văn bản',
  `document_path` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'Tên file',
  `category_id` bigint(20) NULL DEFAULT NULL,
  `author` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'Người tạo',
  `document_order` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `modified_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`document_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = 'Bảng lưu tài liệu: thời khóa biểu, văn bản' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_document
-- ----------------------------
INSERT INTO `stfc_document` VALUES (1, 'nghi quyet 1.1', 2, 'abc', 7, 'daond', 1, 1, '2018-04-02 08:27:54', '2018-04-02 08:27:54');
INSERT INTO `stfc_document` VALUES (2, 'nghi quyet 1.2', 2, 'abc', 7, 'daond', 2, 1, '2018-04-02 08:40:18', '2018-04-02 08:40:18');
INSERT INTO `stfc_document` VALUES (3, 'nghi quyet 2.1', 2, 'abc', 8, 'daond', 3, 1, '2018-04-02 08:40:18', '2018-04-02 08:40:18');
INSERT INTO `stfc_document` VALUES (4, 'nghi quyet 1.3', 2, 'abc', 7, 'daond', 4, 1, '2018-04-02 08:40:18', '2018-04-02 08:40:18');

-- ----------------------------
-- Table structure for stfc_menu
-- ----------------------------
DROP TABLE IF EXISTS `stfc_menu`;
CREATE TABLE `stfc_menu`  (
  `menu_id` bigint(20) NOT NULL,
  `menu_parent` bigint(20) NULL DEFAULT NULL,
  `menu_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_slug` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Đường dẫn nhóm bài viết',
  `menu_order` int(11) NULL DEFAULT NULL,
  `menu_type` int(11) NULL DEFAULT NULL COMMENT '1: Public; 2: Private',
  `status` int(11) NULL DEFAULT NULL COMMENT '1: Hoạt động; 0: Không hoạt động',
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày tạo',
  `modified_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày sửa',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng quản lý menu' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for stfc_params
-- ----------------------------
DROP TABLE IF EXISTS `stfc_params`;
CREATE TABLE `stfc_params`  (
  `param_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `param_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `param_value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `param_description` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `param_status` int(11) NULL DEFAULT NULL COMMENT '1: Hoạt động, 0: Không hoạt động',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`param_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng stfc_params' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_params
-- ----------------------------
INSERT INTO `stfc_params` VALUES (1, 'CATEGORY_NOTICE_BANNER', 'Notice', '2', 'Category hiển thị Notece Banner', 1, '2018-03-22 14:53:33');
INSERT INTO `stfc_params` VALUES (2, 'CATEGORY_WIDGET_POST_DETAIL_1', 'Tin nổi bật', '2', 'Tin nổi bật trong chi tiết bài viết', 1, '2018-03-23 07:47:26');
INSERT INTO `stfc_params` VALUES (3, 'HOME_PAGE_URL_IMAGE', 'Path ảnh trang chủ', '/', 'Path ảnh trang chủ', 1, '2018-03-26 07:09:20');
INSERT INTO `stfc_params` VALUES (4, 'INTERNAL_PAGE_URL_IMAGE', 'Path ảnh trang nội bộ', '../', 'Path ảnh trang nội bộ', 1, '2018-03-26 07:09:20');
INSERT INTO `stfc_params` VALUES (5, 'DOCUMENT_PAGE_URL_IMAGE', 'Path ảnh, document trang văn bản', '../', 'Path ảnh, document trang văn bản', 1, '2018-04-02 07:42:24');

-- ----------------------------
-- Table structure for stfc_posts
-- ----------------------------
DROP TABLE IF EXISTS `stfc_posts`;
CREATE TABLE `stfc_posts`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Người tạo bài viết, user_name',
  `post_title` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Tên bài viết',
  `post_excerpt` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Trích đoạn bài viết',
  `post_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Nội dung bài viết',
  `post_status` int(11) NULL DEFAULT NULL COMMENT '0: Không hoạt động, 1: Bản nháp ,2: Hoạt động, 3: Xuất bản, 4: Bài viết chờ duyệt, 5: Bài viết đang gỡ',
  `post_tag` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Tag bài viết',
  `is_pin` int(11) NULL DEFAULT NULL COMMENT 'Ghim bài viết',
  `is_private` int(11) NULL DEFAULT NULL COMMENT '0: Public 1: Private 2: Public and Private',
  `featured_image` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Đường dẫn hình ảnh title',
  `post_slug` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Url bài viết',
  `post_order` int(11) NULL DEFAULT NULL COMMENT 'Thứ tự bài viết',
  `post_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày xuất bản bài viết',
  `effect_from_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `effect_to_date` timestamp(0) NULL DEFAULT NULL,
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày sửa bài viết',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng bài viết' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_posts
-- ----------------------------
INSERT INTO `stfc_posts` VALUES (1, 'daond', 'Gặp mặt đầu xuân Mậu Tuất 2018 Trường cán bộ quản lý Giao thông vận tải 2018', 'Gặp mặt đầu xuân Mậu Tuất 2018 Trường cán bộ quản lý Giao thông vận tải', '<p><strong>Đời l&agrave; bể khổ !</strong></p><p><u>Tiền </u>Tiền <span style=\"color:#B22222;\">Tiền </span>Tiền <span style=\"color:#00FF00;\">Tiền </span>Tiền <span style=\"color:#0000FF;\">Tiền </span>Tiền <span style=\"color:#FFA07A;\">Tiền </span>Tiền <span style=\"color:#4B0082;\">Tiền </span>Tiền <span style=\"color:#00FFFF;\">Tiền </span>Tiền</p><p><u>Tiền </u>Tiền <span style=\"color:#B22222;\">Tiền </span>Tiền <span style=\"color:#00FF00;\">Tiền </span>Tiền <span style=\"color:#0000FF;\">Tiền </span>Tiền <span style=\"color:#FFA07A;\">Tiền </span>Tiền <span style=\"color:#4B0082;\">Tiền </span>Tiền <span style=\"color:#00FFFF;\">Tiền </span>Tiền</p><p><u>Tiền </u>Tiền <span style=\"color:#B22222;\">Tiền </span>Tiền <span style=\"color:#00FF00;\">Tiền </span>Tiền <span style=\"color:#0000FF;\">Tiền </span>Tiền <span style=\"color:#FFA07A;\">Tiền </span>Tiền <span style=\"color:#4B0082;\">Tiền </span>Tiền <span style=\"color:#00FFFF;\">Tiền </span>Tiền</p><p><u>Tiền </u>Tiền <span style=\"color:#B22222;\">Tiền </span>Tiền <span style=\"color:#00FF00;\">Tiền </span>Tiền <span style=\"color:#0000FF;\">Tiền </span>Tiền <span style=\"color:#FFA07A;\">Tiền </span>Tiền <span style=\"color:#4B0082;\">Tiền </span>Tiền <span style=\"color:#00FFFF;\">Tiền </span>Tiền</p><p><u>Tiền </u>Tiền <span style=\"color:#B22222;\">Tiền </span>Tiền <span style=\"color:#00FF00;\">Tiền </span>Tiền <span style=\"color:#0000FF;\">Tiền </span>Tiền <span style=\"color:#FFA07A;\">Tiền </span>Tiền <span style=\"color:#4B0082;\">Tiền </span>Tiền <span style=\"color:#00FFFF;\">Tiền </span>Tiền</p><p><u>Tiền </u>Tiền <span style=\"color:#B22222;\">Tiền </span>Tiền <span style=\"color:#00FF00;\">Tiền </span>Tiền <span style=\"color:#0000FF;\">Tiền </span>Tiền <span style=\"color:#FFA07A;\">Tiền </span>Tiền <span style=\"color:#4B0082;\">Tiền </span>Tiền <span style=\"color:#00FFFF;\">Tiền </span>Tiền</p><p><u>Tiền </u>Tiền <span style=\"color:#B22222;\">Tiền </span>Tiền <span style=\"color:#00FF00;\">Tiền </span>Tiền <span style=\"color:#0000FF;\">Tiền </span>Tiền <span style=\"color:#FFA07A;\">Tiền </span>Tiền <span style=\"color:#4B0082;\">Tiền </span>Tiền <span style=\"color:#00FFFF;\">Tiền </span>Tiền</p><p><u>Tiền </u>Tiền <span style=\"color:#B22222;\">Tiền </span>Tiền <span style=\"color:#00FF00;\">Tiền </span>Tiền <span style=\"color:#0000FF;\">Tiền </span>Tiền <span style=\"color:#FFA07A;\">Tiền </span>Tiền <span style=\"color:#4B0082;\">Tiền </span>Tiền <span style=\"color:#00FFFF;\">Tiền </span>Tiền</p><p><u>Tiền </u>Tiền <span style=\"color:#B22222;\">Tiền </span>Tiền <span style=\"color:#00FF00;\">Tiền </span>Tiền <span style=\"color:#0000FF;\">Tiền </span>Tiền <span style=\"color:#FFA07A;\">Tiền </span>Tiền <span style=\"color:#4B0082;\">Tiền </span>Tiền <span style=\"color:#00FFFF;\">Tiền </span>Tiền</p>', 3, '2018', 1, 2, 'media/tinhot/1/1.jpg', '/post/gap-mat-2018', 1, '2018-03-15 03:34:23', '2018-02-15 03:34:23', '2019-03-15 03:34:23', '2018-03-15 03:34:23', '2018-03-15 03:34:23');
INSERT INTO `stfc_posts` VALUES (2, 'daond', 'Lễ Kỷ niệm 35 năm Ngày Nhà giáo Việt Nam (20/11/1982 - 20/11/2017) ', 'Lễ Kỷ niệm 35 năm Ngày Nhà giáo Việt Nam (20/11/1982 - 20/11/2017) ', 'Sáng ngày 21/2/2018, Trường Cán bộ quản lý GTVT đã long trọng tổ chức buổi gặp mặt và chúc Tết các cán bộ, công chức, viên chức, Nhà trường nhân dịp đầu năm mới.Dự buổi gặp mặt có ông Ngô Anh Tuấn Bí thư Đảng ủy, Q. Hiệu trưởng; bà Trương Thị Hà, Phó bí thư Đảng ủy, Phó hiệu trưởng; ông Vũ Thanh Phương, phó hiệu trưởng cùng toàn thể cán bộ, công chức, viên chức của Nhà trường.', 3, '2019', 0, 0, 'media/tinhot/1/1.jpg', '/post/gap-mat-2019', 2, '2018-03-15 03:35:42', '2018-02-15 03:35:42', '2019-03-15 03:35:42', '2018-03-15 03:35:42', '2018-03-15 03:35:42');
INSERT INTO `stfc_posts` VALUES (3, 'daond', 'Hội nghị tổng kết năm 2017 và hội nghị cán bộ viên chức năm 2018', 'Sáng 28/12/2017, Trường Cán bộ quản lý GTVT tổ chức hội nghị tổng kết năm 2017 và hội nghị cán bộ viên chức năm 2018. Hội nghị đã tổng kết, đánh giá công tác năm 2017 và tập trung ý kiến dân chủ, đưa ra phương hướng, nhiệm vụ trong năm 2018', '<p><strong>S&aacute;ng 28/12/2017, Trường C&aacute;n bộ quản l&yacute; GTVT tổ chức hội nghị tổng kết năm 2017 v&agrave; hội nghị c&aacute;n bộ vi&ecirc;n chức năm 2018. Hội nghị đ&atilde; tổng kết, đ&aacute;nh gi&aacute; c&ocirc;ng t&aacute;c năm 2017 v&agrave; tập trung &yacute; kiến d&acirc;n chủ, đưa ra phương hướng, nhiệm vụ trong năm 2018.</strong></p>', 3, '1', 0, 0, 'media/tinhot/4/4.jpg', '/post/post-1', 3, '2018-03-15 03:35:42', '2018-02-15 03:35:42', '2019-03-15 03:35:42', '2018-03-15 03:35:42', '2018-03-15 03:35:42');
INSERT INTO `stfc_posts` VALUES (4, 'daond', 'Diễn tập phòng cháy chữa cháy và cứu nạn, cứu hộ tại Trường Cán bộ quản lý GTVT', 'Diễn tập phòng cháy chữa cháy và cứu nạn, cứu hộ tại Trường Cán bộ quản lý GTVT', 'Diễn tập phòng cháy chữa cháy và cứu nạn, cứu hộ tại Trường Cán bộ quản lý GTVT', 3, '2', 0, 0, 'media/tintuc_sukien/1/1.jpg', '/post/post-3', 4, '2018-03-15 03:35:42', '2018-02-15 03:35:42', '2019-03-15 03:35:42', '2018-03-15 03:35:42', '2018-03-15 03:35:42');
INSERT INTO `stfc_posts` VALUES (5, 'daond', 'Thông báo tuyển dụng viên chức năm 2017  Trường QLCB Giao thông vận tải', 'Thông báo tuyển dụng viên chức năm 2017 ', 'Thông báo tuyển dụng viên chức năm 2017 Trường QLCB Giao thông vận tải', 3, '1', 0, 0, 'media/tintuc_sukien/2/1.jpg', '/post/post-4', 5, '2018-03-22 02:19:39', '2018-03-22 02:19:39', '2019-03-15 03:35:42', '2018-03-22 02:19:39', '2018-03-22 02:19:39');
INSERT INTO `stfc_posts` VALUES (6, 'daond', 'Lễ công bố Quyết định bổ nhiệm Trưởng Khoa Quản lý nhà nước ', 'Lễ công bố Quyết định bổ nhiệm Trưởng Khoa Quản lý nhà nước ', 'Lễ công bố Quyết định bổ nhiệm Trưởng Khoa Quản lý nhà nước ', 3, '1', 0, 0, 'media/tintuc_sukien/1/1.jpg', '/post/post-5', 6, '2018-03-22 02:19:39', '2018-03-22 02:19:39', '2019-03-15 03:35:42', '2018-03-22 02:19:39', '2018-03-22 02:19:39');
INSERT INTO `stfc_posts` VALUES (7, 'daond', 'Thông báo chiêu sinh lớp Trung cấp lý luận chính trị - Hành chính tại TP. Hồ Chí Minh', 'Thông báo chiêu sinh lớp Trung cấp lý luận chính trị - Hành chính tại TP. Hồ Chí Minh (Khai giảng 4/2018) ', 'Thông báo chiêu sinh lớp Trung cấp lý luận chính trị - Hành chính tại TP. Hồ Chí Minh (Khai giảng 4/2018) ', 3, '1', 0, 0, 'media/tinhot/1/1.jpg', '/post/post-5', 7, '2018-03-22 02:19:39', '2018-03-22 02:19:39', '2019-03-15 03:35:42', '2018-03-22 02:19:39', '2018-03-22 02:19:39');
INSERT INTO `stfc_posts` VALUES (8, 'daond', 'Thông báo chiêu sinh lớp “Bồi dưỡng ngạch Chuyên viên chính” tại Hải Phòng', 'Thông báo chiêu sinh lớp “Bồi dưỡng ngạch Chuyên viên chính” tại Hải Phòng (Khai giảng 1/2018) ', 'Thông báo chiêu sinh lớp “Bồi dưỡng ngạch Chuyên viên chính” tại Hải Phòng (Khai giảng 1/2018) ', 3, '1', 0, 0, 'media/tinhot/1/1.jpg', '/post/post-5', 8, '2018-03-22 02:39:28', '2018-03-22 02:39:28', '2019-03-15 03:35:42', '2018-03-22 02:39:28', '2018-03-22 02:39:28');
INSERT INTO `stfc_posts` VALUES (9, 'daond', 'Thông báo chiêu sinh lớp “Bồi dưỡng ngạch Chuyên viên” tại TPHCM (Khai giảng 1/2018) ', 'Thông báo chiêu sinh lớp “Bồi dưỡng ngạch Chuyên viên” tại TPHCM (Khai giảng 1/2018) ', 'Thông báo chiêu sinh lớp “Bồi dưỡng ngạch Chuyên viên” tại TPHCM (Khai giảng 1/2018) ', 3, '1', 0, 0, 'media/tinhot/1/1.jpg', '/post/post-5', 9, '2018-03-22 02:39:28', '2018-03-22 02:39:28', '2019-03-15 03:35:42', '2018-03-22 02:39:28', '2018-03-22 02:39:28');
INSERT INTO `stfc_posts` VALUES (10, 'daond', 'Thông báo chiêu sinh lớp Trung cấp lý luận chính trị - Hành chính tại Hà Nội', 'Thông báo chiêu sinh lớp Trung cấp lý luận chính trị - Hành chính tại Hà Nội', 'Thông báo chiêu sinh lớp Trung cấp lý luận chính trị - Hành chính tại Hà Nội', 3, '1', 0, 0, 'media/tinhot/1/1.jpg', '/post/post-5', 10, '2018-03-22 02:39:28', '2018-03-22 02:39:28', '2019-03-15 03:35:42', '2018-03-22 02:39:28', '2018-03-22 02:39:28');
INSERT INTO `stfc_posts` VALUES (11, 'daond', 'Thông báo chiêu sinh lớp “Bồi dưỡng ngạch Chuyên viên” tại Hải Phòng (Khai giảng 12/2017) ', 'Thông báo chiêu sinh lớp “Bồi dưỡng ngạch Chuyên viên” tại Hải Phòng (Khai giảng 12/2017) ', 'Thông báo chiêu sinh lớp “Bồi dưỡng ngạch Chuyên viên” tại Hải Phòng (Khai giảng 12/2017) ', 3, '1', 0, 0, 'media/tinhot/1/1.jpg', '/post/post-5', 12, '2018-03-22 02:39:28', '2018-03-22 02:39:28', '2019-03-15 03:35:42', '2018-03-22 02:39:28', '2018-03-22 02:39:28');
INSERT INTO `stfc_posts` VALUES (12, 'daond', 'Khai giảng lớp Bồi dưỡng kiến thức Quốc phòng - An ninh đối tượng 4 đợt 2 năm 2017 ', 'Khai giảng lớp Bồi dưỡng kiến thức Quốc phòng - An ninh đối tượng 4 đợt 2 năm 2017 ', 'Khai giảng lớp Bồi dưỡng kiến thức Quốc phòng - An ninh đối tượng 4 đợt 2 năm 2017 ', 3, '1', 0, 0, 'media/tinhot/1/1.jpg', '/post/post-5', 13, '2018-03-22 02:58:20', '2018-03-22 02:58:20', '2019-03-15 03:35:42', '2018-03-22 02:58:20', '2018-03-22 02:58:20');
INSERT INTO `stfc_posts` VALUES (13, 'daond', 'Lễ khai giảng lớp Bồi dưỡng ngạch chuyên viên chính khoá 22 tại Hà Nội ', 'Lễ khai giảng lớp Bồi dưỡng ngạch chuyên viên chính khoá 22 tại Hà Nội ', 'Lễ khai giảng lớp Bồi dưỡng ngạch chuyên viên chính khoá 22 tại Hà Nội ', 3, '1', 0, 0, 'media/tinhot/1/1.jpg', '/post/post-5', 14, '2018-03-22 02:58:20', '2018-03-22 02:58:20', '2019-03-15 03:35:42', '2018-03-22 02:58:20', '2018-03-22 02:58:20');
INSERT INTO `stfc_posts` VALUES (14, 'daond', 'Khai giảng lớp “Trung cấp lý luận Chính trị - Hành chính K49” tại TP. Hồ Chí Minh ', 'Khai giảng lớp “Trung cấp lý luận Chính trị - Hành chính K49” tại TP. Hồ Chí Minh ', 'Khai giảng lớp “Trung cấp lý luận Chính trị - Hành chính K49” tại TP. Hồ Chí Minh ', 3, '1', 0, 0, 'media/tinhot/1/1.jpg', '/post/post-5', 15, '2018-03-22 02:58:20', '2018-03-22 02:58:20', '2019-03-15 03:35:42', '2018-03-22 02:58:20', '2018-03-22 02:58:20');
INSERT INTO `stfc_posts` VALUES (15, 'daond', 'Khai giảng lớp Bồi dưỡng kiến thức quốc phòng - an ninh đối tượng 3 năm 2017 ', 'Khai giảng lớp Bồi dưỡng kiến thức quốc phòng - an ninh đối tượng 3 năm 2017 ', 'Khai giảng lớp Bồi dưỡng kiến thức quốc phòng - an ninh đối tượng 3 năm 2017 ', 3, '1', 0, 0, 'media/tinhot/1/1.jpg', '/post/post-5', 16, '2018-03-22 02:58:20', '2018-03-22 02:58:20', '2019-03-15 03:35:42', '2018-03-22 02:58:20', '2018-03-22 02:58:20');
INSERT INTO `stfc_posts` VALUES (16, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, 17, '2018-03-22 02:58:20', '2018-03-22 02:58:20', '2019-03-15 03:35:42', '2018-03-22 02:58:20', '2018-03-22 02:58:20');
INSERT INTO `stfc_posts` VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2018-03-28 06:43:25', '2018-03-28 06:43:25', NULL, '2018-03-28 06:43:25', '2018-03-28 06:43:25');
INSERT INTO `stfc_posts` VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, '2018-03-28 06:43:25', '2018-03-28 06:43:25', NULL, '2018-03-28 06:43:25', '2018-03-28 06:43:25');

-- ----------------------------
-- Table structure for stfc_users
-- ----------------------------
DROP TABLE IF EXISTS `stfc_users`;
CREATE TABLE `stfc_users`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Tên đăng nhập',
  `first_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Tên người dùng',
  `last_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Họ và tên đệm',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Email người dùng',
  `birthday` date NULL DEFAULT NULL COMMENT 'Ngày sinh',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Mật khẩu',
  `role` int(11) NULL DEFAULT NULL COMMENT 'Vai trò:\r\n1: Admin - Toàn quyền\r\n2: Editor - Đăng bài và quản lý các post của người khác\r\n3: Author - Đăng bài và quản lý các post của mình\r\n4: Contributor - Viết bài để kiểm duyệt',
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày tạo',
  `status` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng quản lý người dùng' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_users
-- ----------------------------
INSERT INTO `stfc_users` VALUES (1, 'daond', 'Nguyễn Đức', 'Đạo', 'daond226@gmail.com', NULL, '123456', 1, '2018-03-28 04:33:53', 0);
INSERT INTO `stfc_users` VALUES (2, 'viettx', 'Trần Xuân', 'Việt', 'viettx.dev@gmail.com', '2018-03-06', '5vxpPo0f5jM+7BSLkxQJfg==', -1, '2018-03-30 23:59:04', 0);
INSERT INTO `stfc_users` VALUES (3, 'dongdv', 'Đặng Văn', 'Đông', 'viettx.dev@gmail.com', '2018-03-31', '4ylePbrUykmfvdcNLXRGIw==', -1, '2018-03-31 00:04:31', 0);
INSERT INTO `stfc_users` VALUES (4, 'admin', 'admin', 'admin', 'viettx.dev@gmail.com', '2018-03-07', 'MDrx7VF0TVMKoTYby1t7Jg==', -1, '2018-03-31 00:14:14', 0);
INSERT INTO `stfc_users` VALUES (5, 'viettx1', 'Trần Xuân', 'Việt', 'viettx.dev@gmail.com', '2018-02-27', 'BOooLvYBJurMNEN+q+x1BA==', -1, '2018-03-31 00:16:04', 0);
INSERT INTO `stfc_users` VALUES (6, 'super_admin', 'Super', 'admin', 'viettx.dev@gmail.com', '2018-03-14', 'adZjoINmt3rCtetTxgeGPQ==', -1, '2018-03-31 00:17:42', 0);
INSERT INTO `stfc_users` VALUES (7, 'viettx1234', 'Trần Xuân', 'Việt', 'tranviet.dev@gmail.com', '2018-03-31', 'wt8XF48RvzOpCmwN8y2sHA==', 0, '2018-03-31 11:36:25', 0);

-- ----------------------------
-- Table structure for stfc_widget
-- ----------------------------
DROP TABLE IF EXISTS `stfc_widget`;
CREATE TABLE `stfc_widget`  (
  `widget_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `widget_code` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `widget_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Tên widget',
  `widget_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `widget_position` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1 Header, 2 Content, 3 Footer',
  `widget_order` int(11) NULL DEFAULT NULL COMMENT 'Thứ tự, vị trí widget',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Mô tả',
  `status` int(11) NULL DEFAULT 1 COMMENT '1: Hoạt động; 0: Không hoạt động',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`widget_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng Widget' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_widget
-- ----------------------------
INSERT INTO `stfc_widget` VALUES (1, 'hotnews', 'Tin nổi bật', '1', 'CONTENT', 1, 'Tin nổi bật', 1, '2018-03-13 09:00:56');
INSERT INTO `stfc_widget` VALUES (2, 'news_event', 'Tin tức - Sự kiện', '2', 'CONTENT', 2, 'Tin tức - Sự kiện', 1, '2018-03-13 09:01:41');
INSERT INTO `stfc_widget` VALUES (3, 'multiwidget', 'Tuyển sinh - Đào tạo', '3', 'CONTENT', 3, 'Tuyển sinh - Đào tạo', 1, '2018-03-13 09:01:41');
INSERT INTO `stfc_widget` VALUES (4, 'info', 'Giới thiệu', '4', 'CONTENT', 4, 'Giới thiệu', 1, '2018-03-15 06:47:26');
INSERT INTO `stfc_widget` VALUES (5, 'footer', 'Footer', '5', 'FOOTER', 1, 'Footer', 1, '2018-03-21 04:25:46');
INSERT INTO `stfc_widget` VALUES (6, 'internal', 'Widget nội bộ', '6', 'INTERNAL_CONTENT', 1, 'Widget cho trang nội bộ', 1, '2018-03-26 06:22:13');
INSERT INTO `stfc_widget` VALUES (7, 'right', 'Tin nổi bật', '0', 'RIGHT_CONTENT', 1, 'Widget bên phải', 1, '2018-04-03 03:58:31');

-- ----------------------------
-- Table structure for stfc_widget_content
-- ----------------------------
DROP TABLE IF EXISTS `stfc_widget_content`;
CREATE TABLE `stfc_widget_content`  (
  `widget_content_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `widget_id` bigint(20) NULL DEFAULT NULL,
  `widget_content_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Tên content',
  `widget_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Nếu widget map vơi category thì sẽ là category_id, Nếu ko thì là text nội dung',
  `widget_image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Đường dẫn ảnh nếu có',
  `widget_content_order` int(11) NULL DEFAULT NULL COMMENT 'Thứ tự, vị trí content widget, category',
  `widget_content_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Loại widget content',
  `status` int(11) NULL DEFAULT 1 COMMENT '1: Hoạt động; 0: Không hoạt động',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`widget_content_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng nội dung Widget' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_widget_content
-- ----------------------------
INSERT INTO `stfc_widget_content` VALUES (1, 3, 'Tuyển sinh', '3', 'images/tuyen_sinh.png', 1, 'CATEGORY', 1, '2018-03-13 09:04:21');
INSERT INTO `stfc_widget_content` VALUES (2, 3, 'Đào tạo', '4', 'images/dao_tao.png', 2, 'CATEGORY', 1, '2018-03-13 09:04:21');
INSERT INTO `stfc_widget_content` VALUES (3, 3, 'Thông báo', '5', 'images/notifi.png', 3, 'CATEGORY', 1, '2018-03-13 09:04:21');
INSERT INTO `stfc_widget_content` VALUES (4, 2, 'Tin tuc - Su kien', '2', NULL, 1, 'CATEGORY', 1, '2018-03-15 03:38:11');
INSERT INTO `stfc_widget_content` VALUES (5, 1, 'Tin noi bat', '2', NULL, 1, 'CATEGORY', 1, '2018-03-15 06:41:58');
INSERT INTO `stfc_widget_content` VALUES (6, 4, 'Giới thiệu', 'Giới thiệu test', NULL, 1, 'TEXT', 1, '2018-03-15 06:48:14');
INSERT INTO `stfc_widget_content` VALUES (7, 5, 'Các Phòng chuyên môn, nghiệp vụ', '<p>Phòng Đào tạo và Quản lý khoa học</p><p>Phòng Tài chính kế toán</p><p>Phòng Quản trị thiết bị</p>', NULL, 1, 'TEXT', 1, '2018-03-21 04:28:39');
INSERT INTO `stfc_widget_content` VALUES (8, 5, 'Các Khoa', '<p>Khoa Cơ bản</p><p>Khoa Quản lý nhà nước</p><p>Khoa Quản lý doanh nghiệp</p>', NULL, 2, 'TEXT', 1, '2018-03-21 04:31:39');
INSERT INTO `stfc_widget_content` VALUES (9, 5, 'Trụ sở chính', '<p><i class=\"fa fa-envelope\"></i> Email: itamc@mt.gov.vn</p><p><i class=\"fa fa-phone\" aria-hidden=\"true\"></i> (84-4) 38389477</p><p><i class=\"fa fa-map\"></i> Xóm 19B - Cổ Nhuế - Từ Liêm - Hà Nội</p>', NULL, 3, 'TEXT', 1, '2018-03-21 04:31:39');
INSERT INTO `stfc_widget_content` VALUES (10, 6, 'Tin nổi bật', '2', NULL, 1, 'CATEGORY_INTERNAL', 1, '2018-03-26 06:47:10');
INSERT INTO `stfc_widget_content` VALUES (11, 6, 'Tin nổi bật 1', '3', NULL, 2, 'CATEGORY_INTERNAL', 1, '2018-03-26 06:50:05');
INSERT INTO `stfc_widget_content` VALUES (12, 7, 'Tin mới', '2', NULL, 1, 'CATEGORY', 1, '2018-04-03 03:59:13');
INSERT INTO `stfc_widget_content` VALUES (13, 7, 'Hỗ trợ', '<p><span style=\"color:#c0392b\"><strong>Hotline: 0979 366382</strong></span></p><div class=\"tuvan1\"><h4>&nbsp;&nbsp; <span style=\"color:#9b59b6\"><strong>HO&Agrave;NG V&Acirc;N (Ms)</strong></span></h4><p>Điện thoại:<span style=\"color:#2ecc71\"> 0949 300093</span></p><p>Email: <span style=\"color:#2ecc71\">tuvan.japan@gmail.com</span></p><div class=\"tuvan1\"><h4>&nbsp;&nbsp; <strong><span style=\"color:#9b59b6\">PHƯƠNG CHIẾN (Ms)</span></strong></h4><p>Điện thoại:<span style=\"color:#2ecc71\"> 0962 582858</span></p><p>Email: <span style=\"color:#2ecc71\">chienmtphuong@gmail.com</span></p></div></div>', NULL, 2, 'TEXT', 1, '2018-04-03 04:00:22');
INSERT INTO `stfc_widget_content` VALUES (14, 7, 'Thông báo', '5', NULL, 3, 'CATEGORY', 0, '2018-04-03 06:19:48');
INSERT INTO `stfc_widget_content` VALUES (15, 7, 'Quảng cáo', '<a href=\"#\"><img src=\"media/ktm/baner.JPG\" alt=\"\" style=\"height: 400px;\"></img></a>', NULL, 4, 'TEXT', 1, '2018-04-03 06:25:21');

SET FOREIGN_KEY_CHECKS = 1;
