/*
 * @(#)PollDataModelHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.model.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.model.helpers;

import org.morozko.java.mod.web.poll.dg.bean.PollDataBean;

/**
 * <p>Oggetto di modello per PollData.</p>
 *
 * @author Matteo Franci
 */
public class PollDataModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 125965584592380L;


	public static final String ATT_NAME = "pollDataModel";

    // campi relativi alla tabella - START 

	private org.morozko.java.mod.db.dao.DAOID idPollData;

    /** 
     * <p>Restituisce il valore di idPollData</p> 
     * 
     * @return      restituisce il valore di idPollData
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdPollData() {
        return this.idPollData;
    }
    /** 
     * <p>Imposta il valore di idPollData</p> 
     * 
     * @param      idPollData il valore di idPollData da impostare
     */ 
    public void setIdPollData( org.morozko.java.mod.db.dao.DAOID idPollData ) {
        this.idPollData = idPollData;
    }

	private java.sql.Timestamp dataTime;

    /** 
     * <p>Restituisce il valore di dataTime</p> 
     * 
     * @return      restituisce il valore di dataTime
     */ 
    public java.sql.Timestamp getDataTime() {
        return this.dataTime;
    }
    /** 
     * <p>Imposta il valore di dataTime</p> 
     * 
     * @param      dataTime il valore di dataTime da impostare
     */ 
    public void setDataTime( java.sql.Timestamp dataTime ) {
        this.dataTime = dataTime;
    }

	private org.morozko.java.mod.db.dao.DAOID idPollIndex;

    /** 
     * <p>Restituisce il valore di idPollIndex</p> 
     * 
     * @return      restituisce il valore di idPollIndex
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdPollIndex() {
        return this.idPollIndex;
    }
    /** 
     * <p>Imposta il valore di idPollIndex</p> 
     * 
     * @param      idPollIndex il valore di idPollIndex da impostare
     */ 
    public void setIdPollIndex( org.morozko.java.mod.db.dao.DAOID idPollIndex ) {
        this.idPollIndex = idPollIndex;
    }
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    // campi relativi a relazioni - START 
    private java.util.List listPollAnswer;

    /** 
     * <p>Restituisce il valore di listPollAnswer</p> 
     * 
     * @return      restituisce il valore di listPollAnswer
     */ 
    public java.util.List getListPollAnswer() {
        return this.listPollAnswer;
    }

    /** 
     * <p>Imposta il valore di listPollAnswer</p> 
     * 
     * @param      listPollAnswer il valore di listPollAnswer da impostare
     */ 
    public void setListPollAnswer( java.util.List listPollAnswer ) {
        this.listPollAnswer = listPollAnswer;
    }

    // campi relativi a relazioni - END 

    public PollDataBean getBean() {
        PollDataBean bean = new PollDataBean();
        bean.setIdPollData( formatObject(idPollData) );
        bean.setDataTime( formatObject(dataTime) );
        bean.setIdPollIndex( formatObject(idPollIndex) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idPollData=" );
        buffer.append( this.idPollData );
        buffer.append( "; " );
        buffer.append( "dataTime=" );
        buffer.append( this.dataTime );
        buffer.append( "; " );
        buffer.append( "idPollIndex=" );
        buffer.append( this.idPollIndex );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
