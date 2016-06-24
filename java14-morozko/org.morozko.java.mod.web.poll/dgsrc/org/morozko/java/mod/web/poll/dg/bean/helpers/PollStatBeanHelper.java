/*
 * @(#)PollStatBeanHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.bean.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.bean.helpers;

import org.morozko.java.mod.web.poll.dg.model.PollStatModel;

/**
 * <p>Bean per oggetti di tipo PollStatModel.</p>
 *
 * @author Matteo Franci
 */
public class PollStatBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.SimpleBasicBean {

	private final static long serialVersionUID = 125965584575397L;

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
	private String idPollOption;

    /** 
     * <p>Restituisce il valore di idPollOption</p> 
     * 
     * @return      restituisce il valore di idPollOption
     */ 
    public String getIdPollOption() {
        return this.idPollOption;
    }
    /** 
     * <p>Imposta il valore di idPollOption</p> 
     * 
     * @param      idPollOption il valore di idPollOption da impostare
     */ 
    public void setIdPollOption( String idPollOption ) {
        this.idPollOption = idPollOption;
    }
	private String answerNumber;

    /** 
     * <p>Restituisce il valore di answerNumber</p> 
     * 
     * @return      restituisce il valore di answerNumber
     */ 
    public String getAnswerNumber() {
        return this.answerNumber;
    }
    /** 
     * <p>Imposta il valore di answerNumber</p> 
     * 
     * @param      answerNumber il valore di answerNumber da impostare
     */ 
    public void setAnswerNumber( String answerNumber ) {
        this.answerNumber = answerNumber;
    }
    // alias della tabellea - START 
    // alias della tabellea - END 
    public PollStatModel getModel() {
        PollStatModel model = new PollStatModel();
        model.setIdPollIndex( toDAOID(idPollIndex) );
        model.setIdPollOption( toDAOID(idPollOption) );
        model.setAnswerNumber( toLong(answerNumber) );
        return model;
    }

}
