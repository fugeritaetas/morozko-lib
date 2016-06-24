/*
 * @(#)UploadModel.java
 *
 * @project    : Upload
 * @package    : org.morozko.java.mod.web.upload.dg.model
 * @creation   : 18/09/2011 20/29/27
 */
package org.morozko.java.mod.web.upload.dg.model;

import org.morozko.java.mod.web.upload.dg.model.helpers.UploadModelHelper;

/**
 * <p>Classe UploadModel.</p>
 *
 * @author Morozko
 */
public class UploadModel extends UploadModelHelper {

	public static final int TYPE_FILE = 1;
	public static final int TYPE_IMAGE = 2;
	
	public static final int STATE_ACTIVE = 1;
	public static final int STATE_DELETE = 2;
	
	public static final int MODE_REALFILE = 1;
	public static final int MODE_DBFILE = 2;
	
	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	private byte[] fileData;
	
	private final static long serialVersionUID = 131637056795759L;

}
