--	@(#)cmsFileList.jsp
--	@project  : org.morozko.java.mod.app.cms
--	@package  : org.morozko.java.mod.app.cms.update.sql.generic
--	@creation : 2006-08-25
--	@license  : META-INF/LICENSE.TXT
--	@author   : Matteo Franci
--
--  script di aggiornamento del db alla versione 0.8.2

CREATE TABLE cms_filter_chain ( 
	id_cms_filter_chain BIGINT NOT NULL, 
	chain_name VARCHAR(64), 
	chain_description VARCHAR(512) 
);
ALTER TABLE cms_filter_chain ADD CONSTRAINT cms_filter_chain_pk PRIMARY KEY ( id_cms_filter_chain );
ALTER TABLE cms_filter_chain ADD CONSTRAINT ccms_filter_chain_un1 UNIQUE ( chain_name );

CREATE TABLE cms_filter_handler ( 
	id_cms_filter_handler BIGINT NOT NULL, 
	handler_type VARCHAR(512) NOT NULL, 
	handler_config VARCHAR(4000) NOT NULL, 
	handler_description VARCHAR(512), 
	id_cms_filter_chain BIGINT NOT NULL 
);
ALTER TABLE cms_filter_handler ADD CONSTRAINT cms_filter_handler_pk PRIMARY KEY ( id_cms_filter_handler );

ALTER TABLE cms_page ADD id_cms_filter_chain BIGINT;

ALTER TABLE cms_filter_handler ADD CONSTRAINT cms_filter_handler_fk1 FOREIGN KEY ( id_cms_filter_chain ) REFERENCES cms_filter_chain ( id_cms_filter_chain ) ON UPDATE RESTRICT ON DELETE CASCADE;

ALTER TABLE cms_page ADD CONSTRAINT cms_page_fk2 FOREIGN KEY ( id_cms_filter_chain ) REFERENCES cms_filter_chain ( id_cms_filter_chain ) ON UPDATE RESTRICT ON DELETE CASCADE;
