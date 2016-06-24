/*
 * @(#)UploadModelHelper.java
 *
 * @project    : Upload
 * @package    : org.morozko.java.mod.web.upload.dg.model.helpers
 * @creation   : 18/09/2011 20/29/27
 */
package org.morozko.java.mod.web.upload.dg.model.helpers;

import org.morozko.java.mod.web.upload.dg.bean.UploadBean;

/**
 * <p>Oggetto di modello per Upload.</p>
 *
 * @author Morozko
 */
public class UploadModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 131637056783266L;


	public static final String ATT_NAME = "uploadModel";

    // campi relativi alla tabella - START 

	private org.morozko.java.mod.db.dao.DAOID idUpload;

    /** 
     * <p>Restituisce il valore di idUpload</p> 
     * 
     * @return      restituisce il valore di idUpload
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdUpload() {
        return this.idUpload;
    }
    /** 
     * <p>Imposta il valore di idUpload</p> 
     * 
     * @param      idUpload il valore di idUpload da impostare
     */ 
    public void setIdUpload( org.morozko.java.mod.db.dao.DAOID idUpload ) {
        this.idUpload = idUpload;
    }

	private org.morozko.java.mod.db.dao.DAOID idUser;

    /** 
     * <p>Restituisce il valore di idUser</p> 
     * 
     * @return      restituisce il valore di idUser
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdUser() {
        return this.idUser;
    }
    /** 
     * <p>Imposta il valore di idUser</p> 
     * 
     * @param      idUser il valore di idUser da impostare
     */ 
    public void setIdUser( org.morozko.java.mod.db.dao.DAOID idUser ) {
        this.idUser = idUser;
    }

	private org.morozko.java.mod.db.dao.DAOID idEntity;

    /** 
     * <p>Restituisce il valore di idEntity</p> 
     * 
     * @return      restituisce il valore di idEntity
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdEntity() {
        return this.idEntity;
    }
    /** 
     * <p>Imposta il valore di idEntity</p> 
     * 
     * @param      idEntity il valore di idEntity da impostare
     */ 
    public void setIdEntity( org.morozko.java.mod.db.dao.DAOID idEntity ) {
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

	private java.sql.Timestamp timeUpload;

    /** 
     * <p>Restituisce il valore di timeUpload</p> 
     * 
     * @return      restituisce il valore di timeUpload
     */ 
    public java.sql.Timestamp getTimeUpload() {
        return this.timeUpload;
    }
    /** 
     * <p>Imposta il valore di timeUpload</p> 
     * 
     * @param      timeUpload il valore di timeUpload da impostare
     */ 
    public void setTimeUpload( java.sql.Timestamp timeUpload ) {
        this.timeUpload = timeUpload;
    }

	private Integer state;

    /** 
     * <p>Restituisce il valore di state</p> 
     * 
     * @return      restituisce il valore di state
     */ 
    public Integer getState() {
        return this.state;
    }
    /** 
     * <p>Imposta il valore di state</p> 
     * 
     * @param      state il valore di state da impostare
     */ 
    public void setState( Integer state ) {
        this.state = state;
    }

	private Integer size;

    /** 
     * <p>Restituisce il valore di size</p> 
     * 
     * @return      restituisce il valore di size
     */ 
    public Integer getSize() {
        return this.size;
    }
    /** 
     * <p>Imposta il valore di size</p> 
     * 
     * @param      size il valore di size da impostare
     */ 
    public void setSize( Integer size ) {
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

	private Integer fileMode;

    /** 
     * <p>Restituisce il valore di fileMode</p> 
     * 
     * @return      restituisce il valore di fileMode
     */ 
    public Integer getFileMode() {
        return this.fileMode;
    }
    /** 
     * <p>Imposta il valore di fileMode</p> 
     * 
     * @param      fileMode il valore di fileMode da impostare
     */ 
    public void setFileMode( Integer fileMode ) {
        this.fileMode = fileMode;
    }

	private Integer fileType;

    /** 
     * <p>Restituisce il valore di fileType</p> 
     * 
     * @return      restituisce il valore di fileType
     */ 
    public Integer getFileType() {
        return this.fileType;
    }
    /** 
     * <p>Imposta il valore di fileType</p> 
     * 
     * @param      fileType il valore di fileType da impostare
     */ 
    public void setFileType( Integer fileType ) {
        this.fileType = fileType;
    }
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    public UploadBean getBean() {
        UploadBean bean = new UploadBean();
        bean.setIdUpload( formatObject(idUpload) );
        bean.setIdUser( formatObject(idUser) );
        bean.setIdEntity( formatObject(idEntity) );
        bean.setFileSection( formatObject(fileSection) );
        bean.setTimeUpload( formatObject(timeUpload) );
        bean.setState( formatObject(state) );
        bean.setSize( formatObject(size) );
        bean.setFileName( formatObject(fileName) );
        bean.setFilePath( formatObject(filePath) );
        bean.setFileMode( formatObject(fileMode) );
        bean.setFileType( formatObject(fileType) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idUpload=" );
        buffer.append( this.idUpload );
        buffer.append( "; " );
        buffer.append( "idUser=" );
        buffer.append( this.idUser );
        buffer.append( "; " );
        buffer.append( "idEntity=" );
        buffer.append( this.idEntity );
        buffer.append( "; " );
        buffer.append( "fileSection=" );
        buffer.append( this.fileSection );
        buffer.append( "; " );
        buffer.append( "timeUpload=" );
        buffer.append( this.timeUpload );
        buffer.append( "; " );
        buffer.append( "state=" );
        buffer.append( this.state );
        buffer.append( "; " );
        buffer.append( "size=" );
        buffer.append( this.size );
        buffer.append( "; " );
        buffer.append( "fileName=" );
        buffer.append( this.fileName );
        buffer.append( "; " );
        buffer.append( "filePath=" );
        buffer.append( this.filePath );
        buffer.append( "; " );
        buffer.append( "fileMode=" );
        buffer.append( this.fileMode );
        buffer.append( "; " );
        buffer.append( "fileType=" );
        buffer.append( this.fileType );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
