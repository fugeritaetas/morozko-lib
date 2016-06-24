/*
 * @(#)UploadBeanHelper.java
 *
 * @project    : Upload
 * @package    : org.morozko.java.mod.web.upload.dg.bean.helpers
 * @creation   : 18/09/2011 20/29/27
 */
package org.morozko.java.mod.web.upload.dg.bean.helpers;

import org.morozko.java.mod.web.upload.dg.model.UploadModel;

/**
 * <p>Bean per oggetti di tipo UploadModel.</p>
 *
 * @author Morozko
 */
public class UploadBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 131637056786389L;

	private String idUpload;

    /** 
     * <p>Restituisce il valore di idUpload</p> 
     * 
     * @return      restituisce il valore di idUpload
     */ 
    public String getIdUpload() {
        return this.idUpload;
    }
    /** 
     * <p>Imposta il valore di idUpload</p> 
     * 
     * @param      idUpload il valore di idUpload da impostare
     */ 
    public void setIdUpload( String idUpload ) {
        this.idUpload = idUpload;
    }
	private String idUser;

    /** 
     * <p>Restituisce il valore di idUser</p> 
     * 
     * @return      restituisce il valore di idUser
     */ 
    public String getIdUser() {
        return this.idUser;
    }
    /** 
     * <p>Imposta il valore di idUser</p> 
     * 
     * @param      idUser il valore di idUser da impostare
     */ 
    public void setIdUser( String idUser ) {
        this.idUser = idUser;
    }
	private String idEntity;

    /** 
     * <p>Restituisce il valore di idEntity</p> 
     * 
     * @return      restituisce il valore di idEntity
     */ 
    public String getIdEntity() {
        return this.idEntity;
    }
    /** 
     * <p>Imposta il valore di idEntity</p> 
     * 
     * @param      idEntity il valore di idEntity da impostare
     */ 
    public void setIdEntity( String idEntity ) {
        this.idEntity = idEntity;
    }
	private String fileSection;

    /** 
     * <p>Restituisce il valore di fileSection</p> 
     * 
     * @return      restituisce il valore di fileSection
     */ 
    public String getFileSection() {
        return this.fileSection;
    }
    /** 
     * <p>Imposta il valore di fileSection</p> 
     * 
     * @param      fileSection il valore di fileSection da impostare
     */ 
    public void setFileSection( String fileSection ) {
        this.fileSection = fileSection;
    }
	private String timeUpload;

    /** 
     * <p>Restituisce il valore di timeUpload</p> 
     * 
     * @return      restituisce il valore di timeUpload
     */ 
    public String getTimeUpload() {
        return this.timeUpload;
    }
    /** 
     * <p>Imposta il valore di timeUpload</p> 
     * 
     * @param      timeUpload il valore di timeUpload da impostare
     */ 
    public void setTimeUpload( String timeUpload ) {
        this.timeUpload = timeUpload;
    }
	private String state;

    /** 
     * <p>Restituisce il valore di state</p> 
     * 
     * @return      restituisce il valore di state
     */ 
    public String getState() {
        return this.state;
    }
    /** 
     * <p>Imposta il valore di state</p> 
     * 
     * @param      state il valore di state da impostare
     */ 
    public void setState( String state ) {
        this.state = state;
    }
	private String size;

    /** 
     * <p>Restituisce il valore di size</p> 
     * 
     * @return      restituisce il valore di size
     */ 
    public String getSize() {
        return this.size;
    }
    /** 
     * <p>Imposta il valore di size</p> 
     * 
     * @param      size il valore di size da impostare
     */ 
    public void setSize( String size ) {
        this.size = size;
    }
	private String fileName;

    /** 
     * <p>Restituisce il valore di fileName</p> 
     * 
     * @return      restituisce il valore di fileName
     */ 
    public String getFileName() {
        return this.fileName;
    }
    /** 
     * <p>Imposta il valore di fileName</p> 
     * 
     * @param      fileName il valore di fileName da impostare
     */ 
    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }
	private String filePath;

    /** 
     * <p>Restituisce il valore di filePath</p> 
     * 
     * @return      restituisce il valore di filePath
     */ 
    public String getFilePath() {
        return this.filePath;
    }
    /** 
     * <p>Imposta il valore di filePath</p> 
     * 
     * @param      filePath il valore di filePath da impostare
     */ 
    public void setFilePath( String filePath ) {
        this.filePath = filePath;
    }
	private String fileMode;

    /** 
     * <p>Restituisce il valore di fileMode</p> 
     * 
     * @return      restituisce il valore di fileMode
     */ 
    public String getFileMode() {
        return this.fileMode;
    }
    /** 
     * <p>Imposta il valore di fileMode</p> 
     * 
     * @param      fileMode il valore di fileMode da impostare
     */ 
    public void setFileMode( String fileMode ) {
        this.fileMode = fileMode;
    }
	private String fileType;

    /** 
     * <p>Restituisce il valore di fileType</p> 
     * 
     * @return      restituisce il valore di fileType
     */ 
    public String getFileType() {
        return this.fileType;
    }
    /** 
     * <p>Imposta il valore di fileType</p> 
     * 
     * @param      fileType il valore di fileType da impostare
     */ 
    public void setFileType( String fileType ) {
        this.fileType = fileType;
    }
    // alias della tabellea - START 
    // alias della tabellea - END 
    public UploadModel getModel() {
        UploadModel model = new UploadModel();
        model.setIdUpload( toDAOID(idUpload) );
        model.setIdUser( toDAOID(idUser) );
        model.setIdEntity( toDAOID(idEntity) );
        model.setFileSection( toString(fileSection) );
        model.setTimeUpload( toTimestamp(timeUpload) );
        model.setState( toInteger(state) );
        model.setSize( toInteger(size) );
        model.setFileName( toString(fileName) );
        model.setFilePath( toString(filePath) );
        model.setFileMode( toInteger(fileMode) );
        model.setFileType( toInteger(fileType) );
        return model;
    }

}
