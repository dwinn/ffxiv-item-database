--liquibase formatted sql

--changeset dwinn:1
CREATE TABLE item (
        id int(11) NOT NULL,
        name VARCHAR(200) NOT NULL,
        level_equip int(11) NOT NULL,
        level_item int(11),
        class_job_category VARCHAR(200),
        icon VARCHAR(200),
        PRIMARY KEY (id)
);
--rollback DROP TABLE item;
