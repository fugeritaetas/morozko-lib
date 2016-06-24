/*
 * @(#)PollIndexModelHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.model.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.model.helpers;

import org.morozko.java.mod.web.poll.dg.bean.PollIndexBean;

/**
 * <p>Oggetto di modello per PollIndex.</p>
 *
 * @author Matteo Franci
 */
public class PollIndexModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 12596558458437L;


	public static final String ATT_NAME = "pollIndexModel";

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

	private org.morozko.java.mod.db.dao.DAOID questionaryType;

    /** 
     * <p>Restituisce il valore di questionaryType</p> 
     * 
     * @return      restituisce il valore di questionaryType
     */ 
    public org.morozko.java.mod.db.dao.DAOID getQuestionaryType() {
        return this.questionaryType;
    }
    /** 
     * <p>Imposta il valore di questionaryType</p> 
     * 
     * @param      questionaryType il valore di questionaryType da impostare
     */ 
    public void setQuestionaryType( org.morozko.java.mod.db.dao.DAOID questionaryType ) {
        this.questionaryType = questionaryType;
    }

	private String questionaryName;

    /** 
     * <p>Restituisce il valore di questionaryName</p> 
     * 
     * @return      restituisce il valore di questionaryName
     */ 
    public String getQuestionaryName() {
        return this.questionaryName;
    }
    /** 
     * <p>Imposta il valore di questionaryName</p> 
     * 
     * @param      questionaryName il valore di questionaryName da impostare
     */ 
    public void setQuestionaryName( String questionaryName ) {
        this.questionaryName = questionaryName;
    }

	private String questionaryDescription;

    /** 
     * <p>Restituisce il valore di questionaryDescription</p> 
     * 
     * @return      restituisce il valore di questionaryDescription
     */ 
    public String getQuestionaryDescription() {
        return this.questionaryDescription;
    }
    /** 
     * <p>Imposta il valore di questionaryDescription</p> 
     * 
     * @param      questionaryDescription il valore di questionaryDescription da impostare
     */ 
    public void setQuestionaryDescription( String questionaryDescription ) {
        this.questionaryDescription = questionaryDescription;
    }
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    // campi relativi a relazioni - START 
    private java.util.List listPollQuestion;

    /** 
     * <p>Restituisce il valore di listPollQuestion</p> 
     * 
     * @return      restituisce il valore di listPollQuestion
     */ 
    public java.util.List getListPollQuestion() {
        return this.listPollQuestion;
    }

    /** 
     * <p>Imposta il valore di listPollQuestion</p> 
     * 
     * @param      listPollQuestion il valore di listPollQuestion da impostare
     */ 
    public void setListPollQuestion( java.util.List listPollQuestion ) {
        this.listPollQuestion = listPollQuestion;
    }

    // campi relativi a relazioni - END 

    public PollIndexBean getBean() {
        PollIndexBean bean = new PollIndexBean();
        bean.setIdPollIndex( formatObject(idPollIndex) );
        bean.setQuestionaryType( formatObject(questionaryType) );
        bean.setQuestionaryName( formatObject(questionaryName) );
        bean.setQuestionaryDescription( formatObject(questionaryDescription) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idPollIndex=" );
        buffer.append( this.idPollIndex );
        buffer.append( "; " );
        buffer.append( "questionaryType=" );
        buffer.append( this.questionaryType );
        buffer.append( "; " );
        buffer.append( "questionaryName=" );
        buffer.append( this.questionaryName );
        buffer.append( "; " );
        buffer.append( "questionaryDescription=" );
        buffer.append( this.questionaryDescription );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
