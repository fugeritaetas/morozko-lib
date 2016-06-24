CREATE SEQUENCE seq_id_option START WITH 1 INCREMENT BY 1 CACHE 100;

CREATE TABLE option_catalog (
	   id_option_catalog NUMBER,
	   catalog_key VARCHAR(64),
	   description VARCHAR(1024),
	   catalog_type NUMBER
);
ALTER TABLE option_catalog ADD CONSTRAINT option_catalog_pk PRIMARY KEY ( id_option_catalog );
 
CREATE TABLE option_item (
	   id_option_item NUMBER,
	   item_value VARCHAR(1024),
	   enabled NUMBER,
	   ordering NUMBER,
	   id_option_catalog NUMBER
);
ALTER TABLE option_item ADD CONSTRAINT option_item_pk PRIMARY KEY ( id_option_item );
ALTER TABLE option_item ADD CONSTRAINT option_item_fk1 FOREIGN KEY ( id_option_catalog )
	  REFERENCES option_catalog ( id_option_catalog ) ON DELETE CASCADE;
