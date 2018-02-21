--liquibase formatted SQL
--changeset oitejjho:firstRun
-- COMMENT db creation INITIAL;


CREATE TABLE `form_data` (
  `id`      BIGINT(20)    NOT NULL,
  `form_id` INT(11)       NOT NULL,
  `data`    VARCHAR(5000) NOT NULL,
  `created` DATETIME      NOT NULL
);


CREATE TABLE `form_template` (
  `id`       INT(11)       NOT NULL,
  `form`     VARCHAR(5000) NOT NULL,
  `f_schema` VARCHAR(5000) NOT NULL,
  `created`  DATETIME      NOT NULL
);

ALTER TABLE `form_data`
  ADD PRIMARY KEY (`id`),
  ADD KEY `form_id` (`form_id`);

ALTER TABLE `form_template`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `form_data`
  MODIFY `id` BIGINT(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE `form_template`
  MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `form_data`
  ADD CONSTRAINT `fk_fData_fId_fTemplate_id` FOREIGN KEY (`form_id`) REFERENCES `form_template` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;