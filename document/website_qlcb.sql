CREATE TABLE `stfc_users` (
`user_id` bigint(20) NOT NULL AUTO_INCREMENT,
`user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Tên đăng nhập',
`first_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Tên người dùng',
`last_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Họ và tên đệm',
`email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Email người dùng',
`birthday` date NULL COMMENT 'Ngày sinh',
`password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'Mật khẩu',
`role` int NULL COMMENT 'Vai trò:\r\n1: Admin - Toàn quyền\r\n2: Editor - Đăng bài và quản lý các post của người khác\r\n3: Author - Đăng bài và quản lý các post của mình\r\n4: Contributor - Viết bài để kiểm duyệt',
`create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày tạo',
PRIMARY KEY (`user_id`) 
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Bảng quản lý người dùng';

CREATE TABLE `stfc_banner` (
`banner_id` bigint(20) NOT NULL,
`banner_name` varchar(200) NULL,
`banner_type` int(11) NULL COMMENT '1: Logo; 2: Banner',
`banner_order` int(11) NULL,
`status` int(11) NULL COMMENT '1: Hoạt động; 0: Không hoạt động',
`effect_from_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày bắt đầu hiệu lực',
`effect_to_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày kết thúc hiệu lực',
`create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Thời gian tạo',
`modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Thời gian sửa',
PRIMARY KEY (`banner_id`) 
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Bảng quản lý banner, logo';

CREATE TABLE `stfc_category` (
`category_id` bigint(20) NOT NULL,
`category_parent` bigint(20) NULL,
`category_name` varchar(200) NULL,
`category_slug` varchar(200) NULL COMMENT 'Đường dẫn nhóm bài viết',
`category_order` int(11) NULL,
`status` int(11) NULL COMMENT '1: Hoạt động; 0: Không hoạt động',
`create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày tạo',
`modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày sửa',
PRIMARY KEY (`category_id`) 
)
COMMENT = 'Bảng nhóm bài viết';

CREATE TABLE `stfc_posts` (
`post_id` bigint(20) NOT NULL AUTO_INCREMENT,
`user_id` bigint(20) NULL DEFAULT NULL COMMENT 'Người tạo bài viết, user_name',
`post_title` varchar(500) NULL DEFAULT NULL COMMENT 'Tên bài viết',
`post_excerpt` text NULL COMMENT 'Trích đoạn bài viết',
`post_content` text NULL COMMENT 'Nội dung bài viết',
`status` int(11) NULL DEFAULT NULL COMMENT '1: Hoạt động, 0: Không hoạt động',
`post_tag` varchar(500) NULL DEFAULT NULL COMMENT 'Tag bài viết',
`is_pin` int(11) NULL DEFAULT NULL COMMENT 'Ghim bài viết',
`featured_image` varchar(200) NULL DEFAULT NULL COMMENT 'Đường dẫn hình ảnh title',
`post_slug` varchar(200) NULL DEFAULT NULL COMMENT 'Url bài viết',
`post_order` int(11) NULL DEFAULT NULL COMMENT 'Thứ tự bài viết',
`post_type` int(11) NULL COMMENT '1: Public; 2:Private',
`post_date` timestamp NULL COMMENT 'Ngày publish bài viết',
`effect_from_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`effect_to_date` timestamp NULL ON UPDATE CURRENT_TIMESTAMP,
`create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày sửa bài viết',
PRIMARY KEY (`post_id`) 
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Bảng bài viết'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;

CREATE TABLE `stfc_widget` (
`widget_id` bigint(20) NOT NULL AUTO_INCREMENT,
`widget_code` varchar(50) NULL DEFAULT NULL,
`widget_name` varchar(200) NULL DEFAULT NULL COMMENT 'Tên widget',
`widget_type` varchar(5) NULL DEFAULT NULL COMMENT 'Loại widget',
`widget_position` varchar(5) NULL DEFAULT NULL COMMENT '1 Header, 2 Content, 3 Footer',
`widget_order` int(11) NULL DEFAULT NULL COMMENT 'Thứ tự, vị trí widget',
`description` varchar(200) NULL DEFAULT NULL COMMENT 'Mô tả',
`status` int(11) NULL DEFAULT 1 COMMENT '1: Hoạt động; 0: Không hoạt động',
`create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`widget_id`) 
)
ENGINE = InnoDB
AUTO_INCREMENT = 4
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Bảng Widget'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;

CREATE TABLE `stfc_widget_content` (
`widget_content_id` bigint(20) NOT NULL AUTO_INCREMENT,
`widget_id` bigint(20) NULL DEFAULT NULL,
`widget_content_name` varchar(200) NULL DEFAULT NULL COMMENT 'Tên content',
`widget_content` varchar(500) NULL DEFAULT NULL COMMENT 'Nếu widget map vơi category thì sẽ là category_id, Nếu ko thì là text nội dung',
`widget_image` varchar(100) NULL DEFAULT NULL COMMENT 'Đường dẫn ảnh nếu có',
`widget_content_order` int(11) NULL DEFAULT NULL COMMENT 'Thứ tự, vị trí content widget, category',
`widget_content_type` varchar(5) NULL DEFAULT NULL COMMENT 'Loại widget content',
`status` int(11) NULL DEFAULT 1 COMMENT '1: Hoạt động; 0: Không hoạt động',
`create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`widget_content_id`) 
)
ENGINE = InnoDB
AUTO_INCREMENT = 4
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Bảng nội dung Widget'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;

CREATE TABLE `stfc_menu` (
`menu_id` bigint(20) NOT NULL,
`menu_parent` bigint(20) NULL,
`menu_name` varchar(200) NULL,
`menu_slug` varchar(200) NULL COMMENT 'Đường dẫn nhóm bài viết',
`menu_order` int(11) NULL,
`menu_type` int(11) NULL COMMENT '1: Public; 2: Private',
`status` int(11) NULL COMMENT '1: Hoạt động; 0: Không hoạt động',
`create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày tạo',
`modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày sửa',
PRIMARY KEY (`menu_id`) 
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Bảng quản lý menu';

CREATE TABLE `stfc_category_post` (
`map_id` bigint(20) NOT NULL AUTO_INCREMENT,
`category_id` bigint(20) NULL DEFAULT NULL,
`post_id` bigint(20) NULL DEFAULT NULL,
`status` int(11) NULL DEFAULT NULL COMMENT '1: Hoạt động, 0: Không hoạt động',
`create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`map_id`) 
)
ENGINE = InnoDB
AUTO_INCREMENT = 20
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Bảng map category and post'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;

CREATE TABLE `stfc_document` (
`document_id` bigint(20) NOT NULL,
`document_name` varchar(255) NULL,
`document_type` int(11) NULL COMMENT '1: Thời khóa biểu; 2: Văn bản',
`document_path` varchar(255) NULL COMMENT 'Tên file',
`category_id` bigint(20) NULL,
`user_id` bigint(20) NULL COMMENT 'Người tạo',
`status` int(11) NULL,
`create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`document_id`) 
)
COMMENT = 'Bảng lưu tài liệu: thời khóa biểu, văn bản';

CREATE TABLE `stfc_params` (
`param_id` bigint(20) NOT NULL AUTO_INCREMENT,
`param_key` varchar(200) NULL DEFAULT NULL,
`param_name` varchar(200) NULL DEFAULT NULL,
`param_value` varchar(200) NULL DEFAULT NULL,
`param_description` varchar(2000) NULL DEFAULT NULL,
`param_status` int(11) NULL DEFAULT NULL COMMENT '1: Hoạt động, 0: Không hoạt động',
`create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`param_id`) 
)
ENGINE = InnoDB
AUTO_INCREMENT = 3
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Bảng stfc_params'
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;

CREATE TABLE `stfc_class` (
`class_id` bigint(20) NOT NULL,
`class_name` varchar(255) NULL,
`description` varchar(255) NULL,
`status` int(11) NULL,
`create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`class_id`) 
)
COMMENT = 'Bảng lớp học';

