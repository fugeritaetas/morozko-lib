/*
 * @(#)PollStatModelHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.model.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.model.helpers;

import org.morozko.java.mod.web.poll.dg.bean.PollStatBean;

/**
 * <p>Oggetto di modello per PollStat.</p>
 *
 * @author Matteo Franci
 */
public class PollStatModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 125965584574374L;


	public static final String ATT_NAME = "pollStatModel";

    // campi relativi alla tabella - START 

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

	private org.morozko.java.mod.db.dao.DAOID idPollOption;

    /** 
     * <p>Restituisce il valore di idPollOption</p> 
     * 
     * @return      restituisce il valore di idPollOption
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdPollOption() {
        return this.idPollOption;
    }
    /** 
     * <p>Imposta il valore di idPollOption</p> 
     * 
     * @param      idPollOption il valore di idPollOption da impostare
     */ 
    public void setIdPollOption( org.morozko.java.mod.db.dao.DAOID idPollOption ) {
        this.idPollOption = idPollOption;
    }

	private Long answerNumber;

    /** 
     * <p>Restituisce il valore di answerNumber</p> 
     * 
     * @return      restituisce il valore di answerNumber
     */ 
    public Long getAnswerNumber() {
        return this.answerNumber;
    }
    /** 
     * <p>Imposta il valore di answerNumber</p> 
     * 
     * @param      answerNumber il valore di answerNumber da impostare
     */ 
    public void setAnswerNumber( Long answerNumber ) {
        this.answerNumber = answerNumber;
    }
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    public PollStatBean getBean() {
        PollStatBean bean = new PollStatBean();
        bean.setIdPollIndex( formatObject(idPollIndex) );
        bean.setIdPollOption( formatObject(idPollOption) );
        bean.setAnswerNumber( formatObject(answerNumber) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idPollIndex=" );
        buffer.append( this.idPollIndex );
        buffer.append( "; " );
        buffer.append( "idPollOption=" );
        buffer.append( this.idPollOption );
        buffer.append( "; " );
        buffer.append( "answerNumber=" );
        buffer.append( this.answerNumber );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
