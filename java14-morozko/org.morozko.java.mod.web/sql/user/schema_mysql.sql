DROP TABLE user_groupuser;
DROP TABLE user_group;
DROP TABLE user_account;

CREATE TABLE user_account (
	id_user_account INTEGER NOT NULL,
	user_name VARCHAR(128) NOT NULL,
	user_pass VARCHAR(128) NOT NULL,
	user_enabled INTEGER NOT NULL
);
ALTER TABLE user_account ADD CONSTRAINT user_account_pk PRIMARY KEY ( id_user_account );
ALTER TABLE user_account ADD CONSTRAINT user_account_sk1 UNIQUE ( user_name );
ALTER TABLE  user_account CHANGE  id_user_account  id_user_account INTEGER NOT NULL AUTO_INCREMENT;

CREATE TABLE user_group (
	id_user_group INTEGER NOT NULL,
	group_name VARCHAR(128) NOT NULL
);
ALTER TABLE user_group ADD CONSTRAINT user_group_pk PRIMARY KEY ( id_user_group );
ALTER TABLE user_group ADD CONSTRAINT user_group_sk1 UNIQUE ( group_name );
ALTER TABLE user_group CHANGE  id_user_group  id_user_group INTEGER NOT NULL AUTO_INCREMENT;

CREATE TABLE user_groupuser (
	id_user_groupuser INTEGER NOT NULL,
	id_user_account INTEGER NOT NULL,
	id_user_group INTEGER NOT NULL
);
ALTER TABLE user_groupuser ADD CONSTRAINT user_groupuser_pk PRIMARY KEY ( id_user_groupuser );
ALTER TABLE user_groupuser ADD CONSTRAINT user_groupuser_sk1 UNIQUE ( id_user_account, id_user_group );
ALTER TABLE user_groupuser CHANGE  id_user_groupuser  id_user_groupuser INTEGER NOT NULL AUTO_INCREMENT;

