CREATE SEQUENCE seq_id_mor_poll START WITH 1000000000 INCREMENT BY 1 CACHE 100; 

CREATE TABLE poll_index (
	   id_poll_index INTEGER NOT NULL,
	   questionary_type INTEGER NOT NULL,
	   questionary_name VARCHAR(128),
	   questionary_description VARCHAR(512)
);
ALTER TABLE poll_index ADD CONSTRAINT poll_index_pk PRIMARY KEY ( id_poll_index );

CREATE TABLE poll_question (
	   id_poll_index INTEGER NOT NULL,
	   id_poll_question INTEGER NOT NULL,
	   question_text VARCHAR(512),
	   order_by INTEGER NOT NULL
);
ALTER TABLE poll_question ADD CONSTRAINT poll_question_pk PRIMARY KEY ( id_poll_question );
ALTER TABLE poll_question ADD CONSTRAINT poll_question_index_fk FOREIGN KEY ( id_poll_index ) 
	  REFERENCES poll_index ( id_poll_index ) ON DELETE CASCADE;

CREATE TABLE poll_option (
	   id_poll_option INTEGER NOT NULL,
	   id_poll_question INTEGER NOT NULL,
	   option_text VARCHAR(512),
	   option_type INTEGER NOT NULL,
	   option_default VARCHAR(512),
	   id_poll_option_parent INTEGER,
	   order_by INTEGER NOT NULL
);
ALTER TABLE poll_option ADD CONSTRAINT poll_option_pk PRIMARY KEY ( id_poll_option );
ALTER TABLE poll_option ADD CONSTRAINT poll_option_question_fk FOREIGN KEY ( id_poll_question ) 
	  REFERENCES poll_question ( id_poll_question ) ON DELETE CASCADE;

CREATE TABLE poll_data (
	   id_poll_data INTEGER NOT NULL,
	   data_time TIMESTAMP NOT NULL,
	   id_poll_index INTEGER NOT NULL	   
);
ALTER TABLE poll_data ADD CONSTRAINT poll_data_pk PRIMARY KEY ( id_poll_data );
ALTER TABLE poll_data ADD CONSTRAINT poll_data_index_fk FOREIGN KEY ( id_poll_index ) 
	  REFERENCES poll_index ( id_poll_index ) ON DELETE CASCADE;

CREATE TABLE poll_answer (
	   id_poll_answer INTEGER NOT NULL,	   
	   id_poll_option INTEGER NOT NULL,
	   id_poll_data INTEGER NOT NULL,	   
	   option_value VARCHAR(512)
);
ALTER TABLE poll_answer ADD CONSTRAINT poll_answer_pk PRIMARY KEY ( id_poll_answer );
ALTER TABLE poll_answer ADD CONSTRAINT poll_answer_option_fk FOREIGN KEY ( id_poll_option ) 
	  REFERENCES poll_option ( id_poll_option ) ON DELETE CASCADE;
ALTER TABLE poll_answer ADD CONSTRAINT poll_answer_data_fk FOREIGN KEY ( id_poll_data ) 
	  REFERENCES poll_data ( id_poll_data ) ON DELETE CASCADE;	 