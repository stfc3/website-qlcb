DROP TABLE IF EXISTS stfc_widget;
CREATE TABLE IF NOT EXISTS stfc_widget
(
    widget_id BIGINT NOT NULL AUTO_INCREMENT,
    widget_code TEXT,
    widget_name VARCHAR(200) COMMENT 'Tên widget',
    widget_type VARCHAR(50) COMMENT 'Loại widget',
    widget_position VARCHAR(50) COMMENT '1 Header, 2 Content, 3 Footer',
    widget_order int COMMENT 'Thứ tự, vị trí widget',
    description VARCHAR(200) COMMENT 'Mô tả',
    status INT DEFAULT 1 COMMENT '1: Hoạt động; 0: Không hoạt động',
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(widget_id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8 COMMENT 'Bảng Widget';


CREATE TABLE IF NOT EXISTS stfc_widget_content
(
    widget_content_id BIGINT NOT NULL AUTO_INCREMENT,
    widget_id BIGINT,
    widget_content_name VARCHAR(200) COMMENT 'Tên content',
    widget_content VARCHAR(500) COMMENT 'Nếu widget map vơi category thì sẽ là category_id, Nếu ko thì là text nội dung',
    widget_image VARCHAR(100) COMMENT 'Đường dẫn ảnh nếu có',
    widget_content_order int COMMENT 'Thứ tự, vị trí content widget, category',
    widget_content_type VARCHAR(50) COMMENT 'Loại widget content',
    status INT DEFAULT 1 COMMENT '1: Hoạt động; 0: Không hoạt động',
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(widget_content_id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8 COMMENT 'Bảng nội dung Widget';


DROP TABLE IF EXISTS stfc_posts;
CREATE TABLE IF NOT EXISTS stfc_posts
(
    post_id BIGINT NOT NULL AUTO_INCREMENT,
    author VARCHAR(100) COMMENT 'Người tạo bài viết, user_name',
    post_title VARCHAR(2000) COMMENT 'Tên bài viết',
	post_excerpt TEXT COMMENT 'Trích đoạn bài viết',
    post_content TEXT COMMENT 'Nội dung bài viết',
    post_status int COMMENT '0: Không hoạt động, 1: Bản nháp ,2: Hoạt động, 3: Xuất bản, 4: Bài viết chờ duyệt, 5: Bài viết đang gỡ',
    #post_parent int COMMENT 'ID bài viết cha',
    post_tag VARCHAR(2000) COMMENT 'Tag bài viết',
    #category_id INT COMMENT 'Category Id',
	is_pin int comment 'Ghim bài viết',
	#is_publish int comment '0: Bài viết đang gỡ, 1: Xuất bản, 2: Bài viết chờ duyệt',
	featured_image VARCHAR(200) COMMENT 'Đường dẫn hình ảnh title',
	post_slug VARCHAR(2000) COMMENT 'Url bài viết',
    post_order int comment 'Thứ tự bài viết',
	post_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày xuất bản bài viết',
	effect_from_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	effect_to_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày sửa bài viết',
    PRIMARY KEY(post_id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8 COMMENT 'Bảng bài viết';


DROP TABLE IF EXISTS stfc_categories;
CREATE TABLE IF NOT EXISTS stfc_categories
(
    category_id BIGINT NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(2000),
    category_parent BIGINT,
	category_slug VARCHAR(2000),
    category_order int,
    category_status int COMMENT '1: Hoạt động, 0: Không hoạt động',
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày sửa',
    PRIMARY KEY(category_id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8 COMMENT 'Bảng Categories';

DROP TABLE IF EXISTS stfc_banner;
CREATE TABLE IF NOT EXISTS stfc_banner
(
    banner_id BIGINT NOT NULL AUTO_INCREMENT,
    banner_name VARCHAR(2000),
    banner_image VARCHAR(2000) COMMENT 'ảnh Logo, Banner',
    banner_url VARCHAR(2000) COMMENT 'Link liên kết ảnh',
	banner_type int COMMENT '1: Logo, 2: Banner',
	banner_order int,
    banner_status int COMMENT '1: Hoạt động, 0: Không hoạt động',
	effect_from_date datetime ,
	#effect_to_date TIMESTAMP ,
    effect_to_date datetime,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày sửa',
    PRIMARY KEY(banner_id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8 COMMENT 'Bảng Banner';

DROP TABLE IF EXISTS stfc_category_post;
CREATE TABLE IF NOT EXISTS stfc_category_post
(
    map_id BIGINT NOT NULL AUTO_INCREMENT,
    category_id BIGINT,
    post_id BIGINT,
    post_status int COMMENT '1: Hoạt động, 0: Không hoạt động',
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(map_id)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8 COMMENT 'Bảng map category and post';