/*
 * @(#)PollIndexBeanHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.bean.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.bean.helpers;

import org.morozko.java.mod.web.poll.dg.model.PollIndexModel;

/**
 * <p>Bean per oggetti di tipo PollIndexModel.</p>
 *
 * @author Matteo Franci
 */
public class PollIndexBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.SimpleBasicBean {

	private final static long serialVersionUID = 125965584586324L;

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
	private String questionaryType;

    /** 
     * <p>Restituisce il valore di questionaryType</p> 
     * 
     * @return      restituisce il valore di questionaryType
     */ 
    public String getQuestionaryType() {
        return this.questionaryType;
    }
    /** 
     * <p>Imposta il valore di questionaryType</p> 
     * 
     * @param      questionaryType il valore di questionaryType da impostare
     */ 
    public void setQuestionaryType( String questionaryType ) {
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
    // alias della tabellea - START 
    // alias della tabellea - END 
    public PollIndexModel getModel() {
        PollIndexModel model = new PollIndexModel();
        model.setIdPollIndex( toDAOID(idPollIndex) );
        model.setQuestionaryType( toDAOID(questionaryType) );
        model.setQuestionaryName( toString(questionaryName) );
        model.setQuestionaryDescription( toString(questionaryDescription) );
        return model;
    }

}
