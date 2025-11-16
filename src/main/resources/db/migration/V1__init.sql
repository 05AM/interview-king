CREATE TABLE `member`
(
    `id`         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255)    NOT NULL,
    `email`      VARCHAR(255)    NOT NULL,
    `created_at` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `deleted_at` TIMESTAMP       NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `member_email_unique`
        UNIQUE (`email`)
);

CREATE TABLE `member_oauth_account`
(
    `id`               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_id`        BIGINT UNSIGNED NOT NULL,
    `provider`         VARCHAR(20)     NOT NULL,
    `provider_user_id` VARCHAR(100)    NOT NULL,
    `email`            VARCHAR(255)    NOT NULL,
    `refresh_token`    VARCHAR(255)    NOT NULL,
    `created_at`       DATETIME(6)     NOT NULL DEFAULT CURRENT_TIMESTAMP(6),

    PRIMARY KEY (`id`),

    CONSTRAINT `fk_member_oauth_account_member`
        FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),

    CONSTRAINT `uk_member_oauth_provider_user`
        UNIQUE (`provider`, `provider_user_id`),

    CONSTRAINT `uk_member_oauth_member_provider`
        UNIQUE (`member_id`, `provider`)
);

CREATE TABLE `question_category`
(
    `id`   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255)    NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `question_category_name_unique`
        UNIQUE (`name`)
);

CREATE TABLE `question`
(
    `id`               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `category_id`      BIGINT UNSIGNED NOT NULL,
    `member_id`        BIGINT UNSIGNED NOT NULL,
    `text`             VARCHAR(255)    NOT NULL,
    `model_answer`     TEXT            NOT NULL,
    `created_at`       TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_answered_at` TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),

    CONSTRAINT `question_member_id_foreign`
        FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),

    CONSTRAINT `question_category_id_foreign`
        FOREIGN KEY (`category_id`) REFERENCES `question_category` (`id`)
);

CREATE TABLE `answer`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `question_id` BIGINT UNSIGNED NOT NULL,
    `comment`     VARCHAR(500)    NOT NULL,
    `my_answer`   TEXT            NOT NULL,
    `pros`        TEXT            NOT NULL,
    `cons`        TEXT            NOT NULL,
    `score`       INT             NOT NULL,
    `grade`       VARCHAR(20)     NOT NULL,
    `created_at`  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),

    CONSTRAINT `answer_question_id_foreign`
        FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
);

CREATE TABLE `keyword`
(
    `id`   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255)    NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `keyword_name_unique`
        UNIQUE (`name`)
);

CREATE TABLE `question_keyword`
(
    `id`              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `keyword_id`      BIGINT UNSIGNED NOT NULL,
    `question_id`     BIGINT UNSIGNED NOT NULL,
    `is_last_matched` BOOLEAN         NOT NULL,
    `created_at`      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),

    CONSTRAINT `question_keyword_keyword_id_foreign`
        FOREIGN KEY (`keyword_id`) REFERENCES `keyword` (`id`),

    CONSTRAINT `question_keyword_question_id_foreign`
        FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
);

CREATE TABLE `question_tag`
(
    `id`          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `question_id` BIGINT UNSIGNED NOT NULL,
    `name`        VARCHAR(255)    NOT NULL,

    PRIMARY KEY (`id`),

    CONSTRAINT `question_tag_question_id_foreign`
        FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
);

CREATE TABLE `member_keyword_stat`
(
    `id`              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_id`       BIGINT UNSIGNED NOT NULL,
    `keyword_id`      BIGINT UNSIGNED NOT NULL,
    `tried_count`     INT             NOT NULL,
    `matched_count`   INT             NOT NULL,
    `last_matched_at` TIMESTAMP       NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),

    CONSTRAINT `member_keyword_stat_member_id_foreign`
        FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),

    CONSTRAINT `member_keyword_stat_keyword_id_foreign`
        FOREIGN KEY (`keyword_id`) REFERENCES `keyword` (`id`),

    CONSTRAINT `uk_member_keyword_stat_member_keyword`
        UNIQUE (`member_id`, `keyword_id`)
);
