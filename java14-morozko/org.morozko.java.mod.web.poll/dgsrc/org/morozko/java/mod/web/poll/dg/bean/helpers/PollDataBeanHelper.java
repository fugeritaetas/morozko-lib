/*
 * @(#)PollDataBeanHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.bean.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.bean.helpers;

import org.morozko.java.mod.web.poll.dg.model.PollDataModel;

/**
 * <p>Bean per oggetti di tipo PollDataModel.</p>
 *
 * @author Matteo Franci
 */
public class PollDataBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.SimpleBasicBean {

	private final static long serialVersionUID = 125965584592326L;

	private String idPollData;

    /** 
     * <p>Restituisce il valore di idPollData</p> 
     * 
     * @return      restituisce il valore di idPollData
     */ 
    public String getIdPollData() {
        return this.idPollData;
    }
    /** 
     * <p>Imposta il valore di idPollData</p> 
     * 
     * @param      idPollData il valore di idPollData da impostare
     */ 
    public void setIdPollData( String idPollData ) {
        this.idPollData = idPollData;
    }
	private String dataTime;

    /** 
     * <p>Restituisce il valore di dataTime</p> 
     * 
     * @return      restituisce il valore di dataTime
     */ 
    public String getDataTime() {
        return this.dataTime;
    }
    /** 
     * <p>Imposta il valore di dataTime</p> 
     * 
     * @param      dataTime il valore di dataTime da impostare
     */ 
    public void setDataTime( String dataTime ) {
        this.dataTime = dataTime;
    }
	private String idPollIndex;

    /** 
     * <p>Restituisce il valore di idPollIndex</p> 
     * 
     * @return      restituisce il valore di idPollIndex
     */ 
    public String getIdPollIndex() {
        return this.idPollIndex;
    }
    /** 
     * <p>Imposta il valore di idPollIndex</p> 
     * 
     * @param      idPollIndex il valore di idPollIndex da impostare
     */ 
    public void setIdPollIndex( String idPollIndex ) {
        this.idPollIndex = idPollIndex;
    }
    // alias della tabellea - START 
    // alias della tabellea - END 
    public PollDataModel getModel() {
        PollDataModel model = new PollDataModel();
        model.setIdPollData( toDAOID(idPollData) );
        model.setDataTime( toTimestamp(dataTime) );
        model.setIdPollIndex( toDAOID(idPollIndex) );
        return model;
    }

}
