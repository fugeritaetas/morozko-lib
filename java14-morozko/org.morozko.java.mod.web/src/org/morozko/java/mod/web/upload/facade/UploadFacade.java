package org.morozko.java.mod.web.upload.facade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletContext;

import org.apache.struts.upload.FormFile;
import org.morozko.java.core.io.FileIO;
import org.morozko.java.core.io.StreamIO;
import org.morozko.java.mod.db.connect.ConnectionFactory;
import org.morozko.java.mod.db.dao.BasicDAOFactory;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.web.upload.dg.dao.UploadDAO;
import org.morozko.java.mod.web.upload.dg.dao.UploadDAOFactory;
import org.morozko.java.mod.web.upload.dg.model.UploadModel;

public class UploadFacade {

	private void loadWorker( UploadModel upload ) throws IOException {
		File file = new File( this.getSavePath() , upload.getFilePath() );
		upload.setFileData( FileIO.readBytes( file ) );
	}
	
	public UploadModel load( DAOID idUpload ) throws Exception {
		UploadDAO dao = this.daoFactory.getUploadDAO();
		UploadModel upload = dao.loadOnePrimary( idUpload );
		loadWorker( upload );
		return upload;
	}

	public UploadModel load( String fileSection, String idEntity, String fileName ) throws Exception {
		return load( createPath(fileSection, idEntity, fileName) );
	}
	
	public UploadModel load( String filePath ) throws Exception {
		UploadDAO dao = this.daoFactory.getUploadDAO();
		UploadModel upload = dao.loadOneByFilePath( filePath );
		loadWorker( upload );
		return upload;
	}
	
	public static String createPath( String fileSection, String idEntity, String fileName ) {
		return fileSection+"_"+idEntity+"_"+fileName;
	}

	public String save( FormFile file , String fileSecion, String idEntity, String idUser, Integer fileType ) throws Exception {
		boolean res = true;
		UploadModel model = new UploadModel();
		model.setFileMode( new Integer( UploadModel.MODE_REALFILE ) );
		model.setFileName( file.getFileName() );
		model.setIdUser( DAOID.valueOf( idUser ) );
		model.setIdEntity( DAOID.valueOf( idEntity ) );
		model.setFileSection( fileSecion );
		// salvo il file
		String fileName = createPath( fileSecion, idEntity, file.getFileName() );
		File realFile = new File( this.getSavePath(), fileName );
		model.setFilePath( realFile.getName() );
		model.setState( new Integer( UploadModel.STATE_ACTIVE ) );
		model.setSize(  new Integer( file.getFileSize() ) );
		model.setTimeUpload( new Timestamp( System.currentTimeMillis() ) );
		model.setFileType( fileType );
		UploadDAO uploadDAO = this.daoFactory.getUploadDAO();
		uploadDAO.insert( model );
		FileOutputStream fos = new FileOutputStream( realFile );
		StreamIO.pipeStream( file.getInputStream() , fos , StreamIO.MODE_CLOSE_BOTH );
		fos.close();
		return fileName;
	}
	
	public static final String ATT_NAME = "UploadFacade";
	
	public static UploadFacade getFromContext( ServletContext context ) {
		return (UploadFacade)context.getAttribute( ATT_NAME );
	}
	
	private String savePath;

	private UploadDAOFactory daoFactory;
	
	public UploadFacade(String savePath, ConnectionFactory cf) {
		this( savePath, "", cf );
	}
	
	public UploadFacade(String savePath, String prefix, ConnectionFactory cf) {
		super();
		this.savePath = savePath;
		BasicDAOFactory bdf = new BasicDAOFactory( cf );
		Object[] args = { prefix };
		bdf.setSqlArgs( args );
		this.daoFactory = new UploadDAOFactory( bdf );
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
}
