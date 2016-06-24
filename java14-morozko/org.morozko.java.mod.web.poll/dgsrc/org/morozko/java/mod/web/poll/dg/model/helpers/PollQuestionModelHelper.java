/*
 * @(#)PollQuestionModelHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.model.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.model.helpers;

import org.morozko.java.mod.web.poll.dg.bean.PollQuestionBean;

/**
 * <p>Oggetto di modello per PollQuestion.</p>
 *
 * @author Matteo Franci
 */
public class PollQuestionModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 125965584588352L;


	public static final String ATT_NAME = "pollQuestionModel";

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

	private org.morozko.java.mod.db.dao.DAOID idPollQuestion;

    /** 
     * <p>Restituisce il valore di idPollQuestion</p> 
     * 
     * @return      restituisce il valore di idPollQuestion
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdPollQuestion() {
        return this.idPollQuestion;
    }
    /** 
     * <p>Imposta il valore di idPollQuestion</p> 
     * 
     * @param      idPollQuestion il valore di idPollQuestion da impostare
     */ 
    public void setIdPollQuestion( org.morozko.java.mod.db.dao.DAOID idPollQuestion ) {
        this.idPollQuestion = idPollQuestion;
    }

	private String questionText;

    /** 
     * <p>Restituisce il valore di questionText</p> 
     * 
     * @return      restituisce il valore di questionText
     */ 
    public String getQuestionText() {
        return this.questionText;
    }
    /** 
     * <p>Imposta il valore di questionText</p> 
     * 
     * @param      questionText il valore di questionText da impostare
     */ 
    public void setQuestionText( String questionText ) {
        this.questionText = questionText;
    }

	private org.morozko.java.mod.db.dao.DAOID orderBy;

    /** 
     * <p>Restituisce il valore di orderBy</p> 
     * 
     * @return      restituisce il valore di orderBy
     */ 
    public org.morozko.java.mod.db.dao.DAOID getOrderBy() {
        return this.orderBy;
    }
    /** 
     * <p>Imposta il valore di orderBy</p> 
     * 
     * @param      orderBy il valore di orderBy da impostare
     */ 
    public void setOrderBy( org.morozko.java.mod.db.dao.DAOID orderBy ) {
        this.orderBy = orderBy;
    }
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    // campi relativi a relazioni - START 
    private java.util.List listPollOption;

    /** 
     * <p>Restituisce il valore di listPollOption</p> 
     * 
     * @return      restituisce il valore di listPollOption
     */ 
    public java.util.List getListPollOption() {
        return this.listPollOption;
    }

    /** 
     * <p>Imposta il valore di listPollOption</p> 
     * 
     * @param      listPollOption il valore di listPollOption da impostare
     */ 
    public void setListPollOption( java.util.List listPollOption ) {
        this.listPollOption = listPollOption;
    }

    // campi relativi a relazioni - END 

    public PollQuestionBean getBean() {
        PollQuestionBean bean = new PollQuestionBean();
        bean.setIdPollIndex( formatObject(idPollIndex) );
        bean.setIdPollQuestion( formatObject(idPollQuestion) );
        bean.setQuestionText( formatObject(questionText) );
        bean.setOrderBy( formatObject(orderBy) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idPollIndex=" );
        buffer.append( this.idPollIndex );
        buffer.append( "; " );
        buffer.append( "idPollQuestion=" );
        buffer.append( this.idPollQuestion );
        buffer.append( "; " );
        buffer.append( "questionText=" );
        buffer.append( this.questionText );
        buffer.append( "; " );
        buffer.append( "orderBy=" );
        buffer.append( this.orderBy );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
