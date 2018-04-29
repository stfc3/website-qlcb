/*
 Navicat Premium Data Transfer

 Source Server         : QLCB_DEV
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 35.198.218.250:3306
 Source Schema         : qlcb

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 29/04/2018 21:30:31
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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng Banner' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_banner
-- ----------------------------
INSERT INTO `stfc_banner` VALUES (1, 'banner 1', 'media/banner/1.jpg', 'banner-1', 2, 1, 1, '2018-02-15 07:35:10', '2019-02-15 07:35:10', '2018-03-15 07:35:10', '2018-03-15 07:35:10');
INSERT INTO `stfc_banner` VALUES (2, 'banner 2', 'media/banner/2.jpg', 'banner-2', 2, 2, 1, '2018-02-15 07:35:10', '2019-02-15 07:35:10', '2018-03-15 07:36:02', '2018-03-15 07:36:02');
INSERT INTO `stfc_banner` VALUES (3, 'banner 3', 'media/banner/3.jpg', 'banner-3', 2, 3, 1, '2018-02-15 07:35:10', NULL, '2018-03-15 07:36:02', '2018-03-15 07:36:02');
INSERT INTO `stfc_banner` VALUES (4, 'Trần Xuân Việt', '', 'Trần Xuân Việt', 1, 1, 1, '2018-04-04 00:00:00', '2018-04-20 00:00:00', '2018-04-24 16:03:01', '2018-04-24 23:03:01');
INSERT INTO `stfc_banner` VALUES (5, 'test1112', 'D:\\daond\\OS\\WebsiteQLCB\\website-qlcb\\img\\4.PNG', 'test1112', 2, 3, 1, '2018-04-26 00:00:00', NULL, '2018-04-26 00:00:00', '2018-04-26 00:00:00');
INSERT INTO `stfc_banner` VALUES (6, 'trangthai', 'D:\\daond\\OS\\WebsiteQLCB\\website-qlcb\\img\\3.PNG', 'trangthaiđ', 1, NULL, 0, NULL, NULL, '2018-04-26 00:00:00', '2018-04-26 00:00:00');
INSERT INTO `stfc_banner` VALUES (7, 'time!@#', 'D:\\daond\\OS\\WebsiteQLCB\\website-qlcb\\img\\2.PNG', 'time', 1, NULL, 1, NULL, NULL, '2018-04-26 00:00:00', '2018-04-26 00:00:00');

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
  `status` int(11) NULL DEFAULT NULL COMMENT '1: Hoạt động, 0: Không hoạt động',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày sửa',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng Categories' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_categories
-- ----------------------------
INSERT INTO `stfc_categories` VALUES (1, 'Tin hot', NULL, '/category/tin-hot', 1, 1, '2018-03-15 03:38:57', '2018-03-15 03:38:57');
INSERT INTO `stfc_categories` VALUES (2, 'Tin tức - sự kiện', NULL, '/category/tin-tuc-su-kien', 2, 1, '2018-03-15 03:39:17', '2018-04-26 09:16:22');
INSERT INTO `stfc_categories` VALUES (3, 'Tuyển sinh', NULL, '/category/tuyen-sinh', 3, 1, '2018-03-15 04:31:34', '2018-04-25 20:57:03');
INSERT INTO `stfc_categories` VALUES (4, 'Đào tạo', NULL, '/category/dao-tao', 4, 1, '2018-03-22 02:04:03', '2018-03-22 02:04:03');
INSERT INTO `stfc_categories` VALUES (5, 'Thông báo', NULL, '/category/thong-bao', 5, 1, '2018-03-22 02:04:22', '2018-03-22 02:04:22');
INSERT INTO `stfc_categories` VALUES (6, 'Văn bản nội bộ', NULL, '/category/van-ban-noi-bo', 1, 1, '2018-04-02 08:22:03', '2018-04-02 08:22:03');
INSERT INTO `stfc_categories` VALUES (7, 'Nghi quyet 1', 6, '/category/nghi-quyet-1', 2, 1, '2018-04-02 08:27:18', '2018-04-25 20:55:57');
INSERT INTO `stfc_categories` VALUES (8, 'Nghi quyet 2', NULL, '/category/nghi-quyet-2', 3, 1, '2018-04-02 08:27:18', '2018-04-02 08:27:18');
INSERT INTO `stfc_categories` VALUES (9, 'Nghi quyet 3', NULL, '/category/nghi-quyet-3', 4, 1, '2018-04-02 08:27:18', '2018-04-02 08:27:18');
INSERT INTO `stfc_categories` VALUES (15, 'test_edit', NULL, '/category/test_edit', NULL, 0, '2018-04-26 09:09:16', '2018-04-26 09:16:43');
INSERT INTO `stfc_categories` VALUES (16, 'a', NULL, '/category/a', NULL, 0, '2018-04-26 09:17:50', '2018-04-26 09:17:57');
INSERT INTO `stfc_categories` VALUES (17, '123', 1, '/category/123', NULL, 0, '2018-04-26 09:17:59', '2018-04-26 20:59:05');

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
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng map category and post' ROW_FORMAT = Dynamic;

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
INSERT INTO `stfc_category_post` VALUES (25, 1, 32, 1, '2018-04-26 22:15:54');
INSERT INTO `stfc_category_post` VALUES (26, 2, 32, 1, '2018-04-26 22:15:55');
INSERT INTO `stfc_category_post` VALUES (27, 1, 33, 1, '2018-04-28 14:40:59');
INSERT INTO `stfc_category_post` VALUES (28, 2, 33, 1, '2018-04-28 14:40:59');

-- ----------------------------
-- Table structure for stfc_class
-- ----------------------------
DROP TABLE IF EXISTS `stfc_class`;
CREATE TABLE `stfc_class`  (
  `class_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_order` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT 1 COMMENT '1: Hoạt động; 0: Không hoạt động',
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng lớp học' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_class
-- ----------------------------
INSERT INTO `stfc_class` VALUES (1, 'Lớp 1', 'Lớp 1', 1, 1, '2018-04-16 03:11:28');
INSERT INTO `stfc_class` VALUES (2, 'Lớp 2', 'Lớp 2', 2, 1, '2018-04-16 07:07:07');

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
INSERT INTO `stfc_document` VALUES (1, 'dao', 2, 'D:\\daond\\OS\\WebsiteQLCB\\website-qlcb\\img\\3.PNG', 1, '', NULL, 1, NULL, '2018-04-26 14:58:48');
INSERT INTO `stfc_document` VALUES (2, '1231', 2, 'D:\\daond\\OS\\WebsiteQLCB\\website-qlcb\\img\\3.PNG', 2, '', NULL, 1, NULL, '2018-04-26 14:58:23');
INSERT INTO `stfc_document` VALUES (3, 'dao', 2, 'D:\\daond\\OS\\WebsiteQLCB\\website-qlcb\\img\\3.PNG', 3, '', NULL, 1, NULL, '2018-04-26 14:58:59');
INSERT INTO `stfc_document` VALUES (4, '123', 2, 'D:\\daond\\OS\\WebsiteQLCB\\website-qlcb\\img\\3.PNG', 4, '', NULL, 1, NULL, '2018-04-26 14:59:26');

-- ----------------------------
-- Table structure for stfc_enroll_students
-- ----------------------------
DROP TABLE IF EXISTS `stfc_enroll_students`;
CREATE TABLE `stfc_enroll_students`  (
  `student_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_id` bigint(20) NULL DEFAULT NULL,
  `register_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng đăng ký sinh viên' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_enroll_students
-- ----------------------------
INSERT INTO `stfc_enroll_students` VALUES (1, 'dao', '16/04/2018', 'HN', 'dao@gmail.com', '0987654321', 1, '2018-04-16 07:27:20', '2018-04-16 07:27:20');
INSERT INTO `stfc_enroll_students` VALUES (2, 'dao', '16/04/2018', 'HN', 'dao@gmail.com', '0987654321', 1, '2018-04-16 07:27:35', '2018-04-16 07:27:35');
INSERT INTO `stfc_enroll_students` VALUES (3, 'dao', '16/04/2018', 'HN', 'dao@gmail.com', '0987654321', 2, '2018-04-16 07:27:56', '2018-04-16 07:27:56');

-- ----------------------------
-- Table structure for stfc_menu
-- ----------------------------
DROP TABLE IF EXISTS `stfc_menu`;
CREATE TABLE `stfc_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_parent` bigint(20) NULL DEFAULT NULL,
  `menu_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_slug` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Đường dẫn nhóm bài viết',
  `menu_order` int(11) NULL DEFAULT NULL,
  `menu_type` int(11) NULL DEFAULT NULL COMMENT '0: Public; 1: Private',
  `status` int(11) NULL DEFAULT NULL COMMENT '1: Hoạt động; 0: Không hoạt động',
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày tạo',
  `modified_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày sửa',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng quản lý menu' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_menu
-- ----------------------------
INSERT INTO `stfc_menu` VALUES (3, NULL, 'Đào tạo', '/category/dao-tao', 3, 0, 1, '2018-04-11 13:46:53', '2018-04-25 22:18:17');
INSERT INTO `stfc_menu` VALUES (9, NULL, 'Tin tức - Sự kiện', '/category/tin-tuc-su-kien', NULL, 0, 1, '2018-04-25 22:22:19', '2018-04-25 22:23:00');
INSERT INTO `stfc_menu` VALUES (10, 9, 'Tuyển sinh', '/category/tuyen-sinh', NULL, 0, 1, '2018-04-25 22:22:43', '2018-04-25 22:22:43');
INSERT INTO `stfc_menu` VALUES (11, NULL, 'Văn bản nội bộ', '/category/van-ban-noi-bo', NULL, 0, 1, '2018-04-25 22:23:29', '2018-04-25 22:23:29');
INSERT INTO `stfc_menu` VALUES (12, 3, 'test', '/category/dao-tao', NULL, 0, 0, '2018-04-26 09:25:06', '2018-04-26 09:47:43');
INSERT INTO `stfc_menu` VALUES (13, NULL, '1', '/category/tin-hot', NULL, 0, 0, '2018-04-26 09:37:51', '2018-04-26 09:38:17');
INSERT INTO `stfc_menu` VALUES (14, NULL, '1', '/category/nghi-quyet-1', NULL, 0, 0, '2018-04-26 09:38:32', '2018-04-26 09:38:45');
INSERT INTO `stfc_menu` VALUES (15, 12, '11', '/category/nghi-quyet-1', NULL, 0, 0, '2018-04-26 09:39:39', '2018-04-26 09:39:50');
INSERT INTO `stfc_menu` VALUES (16, NULL, 'bai viet', '/post/gap-mat-2019', NULL, 0, 1, '2018-04-26 09:45:27', '2018-04-26 09:45:27');
INSERT INTO `stfc_menu` VALUES (17, 12, 'test1', '/category/tin-hot', NULL, 0, 1, '2018-04-26 09:47:38', '2018-04-26 09:47:38');

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng stfc_params' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_params
-- ----------------------------
INSERT INTO `stfc_params` VALUES (1, 'CATEGORY_NOTICE_BANNER', 'Notice', '2', 'Category hiển thị Notece Banner', 1, '2018-03-22 14:53:33');
INSERT INTO `stfc_params` VALUES (2, 'CATEGORY_WIDGET_POST_DETAIL_1', 'Tin nổi bật', '2', 'Tin nổi bật trong chi tiết bài viết', 1, '2018-03-23 07:47:26');
INSERT INTO `stfc_params` VALUES (3, 'HOME_PAGE_URL_IMAGE', 'Path ảnh trang chủ', '/', 'Path ảnh trang chủ', 1, '2018-03-26 07:09:20');
INSERT INTO `stfc_params` VALUES (4, 'INTERNAL_PAGE_URL_IMAGE', 'Path ảnh trang nội bộ', '../', 'Path ảnh trang nội bộ', 1, '2018-03-26 07:09:20');
INSERT INTO `stfc_params` VALUES (5, 'DOCUMENT_PAGE_URL_IMAGE', 'Path ảnh, document trang văn bản', '../', 'Path ảnh, document trang văn bản', 1, '2018-04-02 07:42:24');
INSERT INTO `stfc_params` VALUES (6, 'ENROLL_STUDENTS_URL_IMAGE', 'Path ảnh trang tuyển sinh', '/', 'Path ảnh trang tuyển sinh', 1, '2018-04-12 06:37:03');

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
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng bài viết' ROW_FORMAT = Dynamic;

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
INSERT INTO `stfc_posts` VALUES (32, 'guest', 'Phế phẩm cà phê nhuộm bột pin được trộn vào hồ tiêu', 'fdsfdsf', '<h2>Mua 3 tấn phế phẩm c&agrave; ph&ecirc; trộn bột pin của b&agrave; Loan, nữ gi&aacute;m đốc ở B&igrave;nh Phước cho trộn v&agrave;o hồ ti&ecirc;u để tăng trọng lượng khi b&aacute;n.</h2>\n\n<p><a href=\"https://vnexpress.net/tin-tuc/phap-luat/co-so-nhuom-den-ca-phe-phe-thai-bang-bot-pin-bi-bat-qua-tang-3737737.html#ctr=related_news_click\" title=\"Cơ sở nhuộm đen cà phê phế thải bằng bột pin bị bắt quả tang\">Cơ sở nhuộm đen c&agrave; ph&ecirc; phế thải bằng bột pin bị bắt quả tang</a></p>\n\n<p>Chiều 26/4, UBND tỉnh Đăk N&ocirc;ng tổ chức họp b&aacute;o th&ocirc;ng tin kết quả điều tra vụ pha trộn c&aacute;t sỏi, l&otilde;i pin v&agrave;o phế phẩm c&agrave; ph&ecirc; tại cơ sở của b&agrave; Nguyễn Thị Thanh Loan ở x&atilde; Đăk Wer, huyện Đăk R&#39;L&acirc;p.</p>\n\n<p>Theo thượng t&aacute; Phạm Thanh B&igrave;nh - Trưởng ph&ograve;ng Tham mưu C&ocirc;ng an tỉnh Đăk N&ocirc;ng, cơ sở của b&agrave; Loan hoạt động từ năm 2016, c&oacute; giấy đăng k&yacute; kinh doanh thu mua n&ocirc;ng sản. B&agrave; Loan thừa nhận sử dụng vỏ c&agrave; ph&ecirc;, sỏi đ&aacute; 2-3 mm v&agrave; than pin trộn lại với nhau để tạo ra &quot;sản phẩm hỗn hợp&quot; b&aacute;n kiếm lời. Quy tr&igrave;nh nhuộm đen n&agrave;y do vợ chồng b&agrave; Loan tự nghĩ ra.</p>\n\n<table align=\"center\" border=\"0\" cellpadding=\"3\" cellspacing=\"0\">\n	<tbody>\n		<tr>\n			<td><img alt=\"Hỗn hợp vỏ cà phê trộn than pin, sỏi đá nhỏ (trái) và hỗn hợp đã được trộn với hạt tiêu (phải). Ảnh: Thiện Nhân.\" src=\"https://i-vnexpress.vnecdn.net/2018/04/26/hon-hop-9448-1524741838.jpg\" /></td>\n		</tr>\n		<tr>\n			<td>\n			<p>Mẫu hỗn hợp vỏ c&agrave; ph&ecirc; trộn than pin, sỏi đ&aacute;&nbsp;(tr&aacute;i) v&agrave; hỗn hợp đ&atilde; được trộn với hạt ti&ecirc;u (phải) do cơ quan điều tra thu giữ được trưng ra tại buổi họp b&aacute;o. Ảnh:&nbsp;<em>Thiện Nh&acirc;n.</em></p>\n			</td>\n		</tr>\n	</tbody>\n</table>\n\n<p>Vợ chồng Loan khai nhận chỉ mới&nbsp;b&aacute;n 3 tấn sản phẩm n&agrave;y cho L&ecirc; Thị Hồng Thơ v&agrave; Trần Văn Tuấn với gi&aacute; 9.000 đồng mỗi kg. Hai người n&agrave;y b&aacute;n lại cho Phan Thị Dung (56 tuổi, Gi&aacute;m đốc C&ocirc;ng ty TNHH sản xuất thương mại Thảo Dung) với gi&aacute; 12.000 đồng một kg.</p>\n\n<p>Sau khi mua về, b&agrave; Dung đ&atilde; cho c&ocirc;ng nh&acirc;n trộn hơn 1,5 tấn hỗn hợp v&agrave;o hạt ti&ecirc;u kh&ocirc;, nhằm&nbsp;tăng trọng lượng. Hồ ti&ecirc;u chứa tạp chất n&agrave;y được đ&oacute;ng bao dự kiến b&aacute;n cho c&aacute;c&nbsp;doanh nghiệp.&nbsp;Số c&ograve;n lại (gần 1,5 tấn),&nbsp;sau khi cơ sở của vợ chồng Loan bị ph&aacute;t hiện, Dung đ&atilde; chỉ đạo em chồng&nbsp;pha trộn với v&ocirc;i, ph&acirc;n heo, ủ l&agrave;m ph&acirc;n b&oacute;n,&nbsp;sau đ&oacute; đem đổ trong vườn cao su nhằm mục đ&iacute;ch tẩu t&aacute;n.</p>\n\n<p>Tại kho n&ocirc;ng sản của Dung ở huyện Lộc Ninh (B&igrave;nh Phước), cơ quan điều tra đ&atilde; ph&aacute;t hiện v&agrave; thu giữ&nbsp;9 tấn chứa hạt ti&ecirc;u kh&ocirc; được trộn với sản phẩm mua từ cơ sở của b&agrave; Loan, đ&oacute;ng trong 360 bao. Ngo&agrave;i ra, số hỗn hợp tẩu t&aacute;n cũng được thu giữ.&nbsp;</p>\n\n<p>&quot;3 tấn hỗn hợp đ&atilde; mua, b&agrave; Dung chưa chế biến ra bất cứ sản phẩm hồ ti&ecirc;u n&agrave;o b&aacute;n ra thị trường&quot;, thượng t&aacute; B&igrave;nh khẳng định.</p>\n\n<p>Theo đại t&aacute; L&ecirc; Vinh Quy, Ph&oacute; gi&aacute;m đốc C&ocirc;ng an tỉnh Đăk N&ocirc;ng,&nbsp;đến nay mới đủ cơ sở khẳng định b&agrave; Loan l&agrave;m sản phẩm hỗn hợp độc hại từ đầu năm nay. Hiện mẫu hỗn hợp đ&atilde; được gửi đi kiểm nghiệm tại Viện Khoa học H&igrave;nh sự (Bộ C&ocirc;ng an).</p>\n\n<p>Cũng theo đại t&aacute; Quy,&nbsp;cơ quan điều tra đang củng cố hồ sơ v&agrave; sẽ sớm khởi tố 5 bị can về&nbsp;h&agrave;nh vi&nbsp;<em>Vi phạm quy định về vệ sinh an to&agrave;n thực phẩm</em>&nbsp;theo điều 317 Bộ Luật H&igrave;nh sự.</p>\n', 3, NULL, 0, 0, '/images/notice.jpg', '/post/phe-pham-ca-phe-nhuom-bot-pin-duoc-tron-vao-ho-tieu', 17, '2018-04-26 10:15:53', '2018-04-26 10:15:53', '2019-03-15 03:35:42', '2018-04-26 10:15:53', '2018-04-26 10:15:53');
INSERT INTO `stfc_posts` VALUES (33, 'guest', 'Mourinho hối hận vì đã khẩu chiến với Wenger', 'Mourinho đến Anh làm việc hè 2004 và lập tức có những màn khẩu chiến nảy lửa với Wenger, nhà đương kim vô địch Ngoại hạng Anh thời điểm đó. Sự xuất hiện của \"Người đặc biệt\" phá vỡ cuộc đua song mã giữa Man Utd và Arsenal kéo dài một thập niên.', '<h2>HLV Man Utd k&ecirc;u gọi người h&acirc;m mộ t&ocirc;n trọng đồng nghiệp người Ph&aacute;p trong trận cầu tại Old Trafford cuối tuần n&agrave;y.</h2>\n\n<p><a href=\"https://thethao.vnexpress.net/tin-tuc/giai-ngoai-hang-anh/mourinho-so-bi-giet-neu-khong-doat-cup-fa-3740460.html?ctr=related_news_click\" title=\"Mourinho sợ bị giết nếu không đoạt Cup FA\">Mourinho sợ bị giết nếu kh&ocirc;ng đoạt Cup FA</a>&nbsp;/&nbsp;<a href=\"https://thethao.vnexpress.net/tin-tuc/cac-giai-khac/man-utd-danh-bai-tottenham-vao-chung-ket-cup-fa-3740036.html?ctr=related_news_click\" title=\"Man Utd đánh bại Tottenham, vào chung kết Cup FA\">Man Utd đ&aacute;nh bại Tottenham, v&agrave;o chung kết Cup FA</a></p>\n\n<p><em>*Trận đấu Man Utd - Arsenal diễn ra l&uacute;c 22h30 ng&agrave;y 29/4, theo giờ H&agrave; Nội.</em></p>\n\n<p>&quot;C&oacute; những thứ sẽ tốt hơn, nếu kh&ocirc;ng c&oacute; một v&agrave;i cử chỉ hay c&acirc;u n&oacute;i của cả t&ocirc;i lẫn &ocirc;ng ấy. Kh&ocirc;ng nghi ngờ g&igrave; về điều đ&oacute;&quot;, Jose Mourinho trả lời khi được hỏi, liệu &ocirc;ng c&oacute; thấy tiếc v&igrave; những sự cố với Arsene Wenger trong qu&aacute; khứ.&nbsp;</p>\n\n<table align=\"center\" border=\"0\" cellpadding=\"3\" cellspacing=\"0\">\n	<tbody>\n		<tr>\n			<td><img alt=\"Mourinho từng ẩu đả với Wenger ngoài đường biên năm 2014. Ảnh: PA.\" src=\"https://i-thethao.vnecdn.net/2018/04/28/4B9D9F4000000578-5664879-image-3829-4025-1524875798.jpg\" /></td>\n		</tr>\n		<tr>\n			<td>\n			<p>Mourinho từng ẩu đả với Wenger ngo&agrave;i đường bi&ecirc;n năm 2014. Ảnh:&nbsp;<em>PA.</em></p>\n			</td>\n		</tr>\n	</tbody>\n</table>\n\n<p>Mourinho đến Anh l&agrave;m việc h&egrave; 2004 v&agrave; lập tức c&oacute; những m&agrave;n khẩu chiến nảy lửa với Wenger, nh&agrave; đương kim v&ocirc; địch Ngoại hạng Anh thời điểm đ&oacute;. Sự xuất hiện của &quot;Người đặc biệt&quot; ph&aacute; vỡ cuộc đua song m&atilde; giữa Man Utd v&agrave; Arsenal k&eacute;o d&agrave;i một thập ni&ecirc;n.</p>\n\n<p>Cựu HLV Porto d&ugrave;ng nhiều từ khiếm nh&atilde; để n&oacute;i về đồng nghiệp hơn tuổi. &Ocirc;ng gọi Wenger l&agrave; &quot;chuy&ecirc;n gia thất bại&quot;, v&agrave; từng mỉa mai rằng nếu &ocirc;ng giải nghệ v&agrave;o thời điểm năm 2015, th&agrave;nh t&iacute;ch của bản th&acirc;n vẫn hơn Wenger. Th&aacute;ng 10/2014, cả hai c&ograve;n x&ocirc; đẩy nhau ngo&agrave;i đường bi&ecirc;n, sau một t&igrave;nh huống tranh chấp giữa cầu thủ Arsenal v&agrave; Chelsea.</p>\n\n<p>Quan hệ giữa Mourinho v&agrave; Wenger bắt đầu ấm l&ecirc;n sau trận tranh Si&ecirc;u Cup Anh năm 2015. HLV người Bồ Đ&agrave;o Nha ch&uacute;c mừng đồng nghiệp, sau khi &quot;Ph&aacute;o thủ&quot; gi&agrave;nh chiến thắng.</p>\n', 3, NULL, 0, 0, '/images/notifi.png', '/post/mourinho-hoi-han-vi-da-khau-chien-voi-wenger', NULL, '2018-04-28 14:40:57', '2018-04-28 14:39:52', NULL, '2018-04-28 14:40:57', '2018-04-28 14:40:57');

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
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Bảng quản lý người dùng' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stfc_users
-- ----------------------------
INSERT INTO `stfc_users` VALUES (1, 'daond', 'Nguyễn Đức', 'Đạo', 'daond226@gmail.com', '2018-04-24', NULL, 1, '2018-04-24 22:52:45', 0);
INSERT INTO `stfc_users` VALUES (2, 'viettx', 'Trần Xuân', 'Việt', 'viettx.dev@gmail.com', '2018-03-06', NULL, 0, '2018-04-24 22:53:06', 0);
INSERT INTO `stfc_users` VALUES (3, 'dongdv', 'Đặng Văn', 'Đông', 'viettx.dev@gmail.com', '2018-03-31', NULL, 1, '2018-04-24 22:22:02', 0);
INSERT INTO `stfc_users` VALUES (4, 'admin', 'admin', 'admin', 'viettx.dev@gmail.com', '2018-03-07', NULL, 1, '2018-04-24 09:17:02', 0);
INSERT INTO `stfc_users` VALUES (5, 'viettx1', 'Trần Xuân', 'Việt', 'viettx.dev@gmail.com', '2018-02-27', NULL, 2, '2018-04-24 09:17:15', 0);
INSERT INTO `stfc_users` VALUES (6, 'super_admin', 'Super', 'admin', 'viettx.dev@gmail.com', '2018-03-14', 'ngpKdxuw6Dn/zwBs8kZ2Ew==', 1, '2018-04-18 22:49:06', 0);
INSERT INTO `stfc_users` VALUES (7, 'viettx1234', 'Trần Xuân', 'Việt', 'tranviet.dev@gmail.com', '2018-03-31', 'wt8XF48RvzOpCmwN8y2sHA==', 0, '2018-03-31 00:00:00', 1);
INSERT INTO `stfc_users` VALUES (8, 'test', 'Test1', 'Pro aa1', 'test@stfc.group', '2018-04-01', NULL, 0, '2018-04-26 09:54:20', 0);
INSERT INTO `stfc_users` VALUES (9, 'testtest', 'testtest', 'testtest', 'testtest@yahoo.com', '2018-04-24', NULL, 0, '2018-04-24 22:52:18', 0);
INSERT INTO `stfc_users` VALUES (10, 'viettx11', '', '', 'viettx.dev@gmail.com', NULL, '2ppx+oYVY+OrfhTYadXfAQ==', 0, '2018-04-24 22:53:34', 0);
INSERT INTO `stfc_users` VALUES (11, 'fddA8', 'df', '3', 'dfd', '2018-04-26', NULL, 2, '2018-04-26 00:00:00', 0);
INSERT INTO `stfc_users` VALUES (12, 'pass', '', '', 'pass', NULL, NULL, 2, '2018-04-26 14:48:20', 0);

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
