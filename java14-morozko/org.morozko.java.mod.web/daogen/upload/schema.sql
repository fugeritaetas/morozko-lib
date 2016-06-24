DROP TABLE IF EXISTS upload;

CREATE TABLE IF NOT EXISTS `upload` (
  `id_upload` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_user` bigint(20) NOT NULL,
  `id_entity` bigint(20) NOT NULL,
  `file_section` varchar(32) NOT NULL,
  `time_upload` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state` int(11) NOT NULL,
  `size` int(11) NOT NULL,
  `file_name` varchar(128) NOT NULL,
  `file_path` varchar(256) NOT NULL,
   file_mode integer NOT NULL,
   file_type integer NOT NULL,
  PRIMARY KEY (`id_upload`)
);
