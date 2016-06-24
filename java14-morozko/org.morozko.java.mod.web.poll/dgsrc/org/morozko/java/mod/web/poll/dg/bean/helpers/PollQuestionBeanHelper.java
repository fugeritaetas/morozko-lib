/*
 * @(#)PollQuestionBeanHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.bean.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.bean.helpers;

import org.morozko.java.mod.web.poll.dg.model.PollQuestionModel;

/**
 * <p>Bean per oggetti di tipo PollQuestionModel.</p>
 *
 * @author Matteo Franci
 */
public class PollQuestionBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.SimpleBasicBean {

	private final static long serialVersionUID = 125965584588398L;

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
	private String idPollQuestion;

    /** 
     * <p>Restituisce il valore di idPollQuestion</p> 
     * 
     * @return      restituisce il valore di idPollQuestion
     */ 
    public String getIdPollQuestion() {
        return this.idPollQuestion;
    }
    /** 
     * <p>Imposta il valore di idPollQuestion</p> 
     * 
     * @param      idPollQuestion il valore di idPollQuestion da impostare
     */ 
    public void setIdPollQuestion( String idPollQuestion ) {
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
	private String orderBy;

    /** 
     * <p>Restituisce il valore di orderBy</p> 
     * 
     * @return      restituisce il valore di orderBy
     */ 
    public String getOrderBy() {
        return this.orderBy;
    }
    /** 
     * <p>Imposta il valore di orderBy</p> 
     * 
     * @param      orderBy il valore di orderBy da impostare
     */ 
    public void setOrderBy( String orderBy ) {
        this.orderBy = orderBy;
    }
    // alias della tabellea - START 
    // alias della tabellea - END 
    public PollQuestionModel getModel() {
        PollQuestionModel model = new PollQuestionModel();
        model.setIdPollIndex( toDAOID(idPollIndex) );
        model.setIdPollQuestion( toDAOID(idPollQuestion) );
        model.setQuestionText( toString(questionText) );
        model.setOrderBy( toDAOID(orderBy) );
        return model;
    }

}
