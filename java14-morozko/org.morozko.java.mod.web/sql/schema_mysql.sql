CREATE TABLE seq_id_jlib_mod_web (id INT NOT NULL);
INSERT INTO seq_id_jlib_mod_web VALUES (1000000000);

-- UPDATE seq_id_jlib_mod_web SET id=LAST_INSERT_ID(id+1);
-- SELECT LAST_INSERT_ID();

CREATE TABLE web_access_data (
	id_web_access_data BIGINT,
	session_id VARCHAR(128),	
	request_uri VARCHAR(1024),		
	request_url VARCHAR(1024),			
	request_method VARCHAR(64),				
	request_query_string VARCHAR(2048),
	request_context_path VARCHAR(64),
	request_remote_addr VARCHAR(64),
	request_remote_host VARCHAR(64),
	request_remote_user VARCHAR(64),	
	request_remote_port INTEGER,		
	request_server_name VARCHAR(64),
	request_server_port INTEGER,	
	request_scheme VARCHAR(64),
	response_status_code INTEGER,
	application_host VARCHAR(64),
	request_start TIMESTAMP,
	request_end TIMESTAMP
);

ALTER TABLE web_access_data ADD CONSTRAINT web_access_data_pk PRIMARY KEY ( id_web_access_data );
