/*
 * @(#)PollAnswerBeanHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.bean.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.bean.helpers;

import org.morozko.java.mod.web.poll.dg.model.PollAnswerModel;

/**
 * <p>Bean per oggetti di tipo PollAnswerModel.</p>
 *
 * @author Matteo Franci
 */
public class PollAnswerBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.SimpleBasicBean {

	private final static long serialVersionUID = 12596558459335L;

	private String idPollAnswer;

    /** 
     * <p>Restituisce il valore di idPollAnswer</p> 
     * 
     * @return      restituisce il valore di idPollAnswer
     */ 
    public String getIdPollAnswer() {
        return this.idPollAnswer;
    }
    /** 
     * <p>Imposta il valore di idPollAnswer</p> 
     * 
     * @param      idPollAnswer il valore di idPollAnswer da impostare
     */ 
    public void setIdPollAnswer( String idPollAnswer ) {
        this.idPollAnswer = idPollAnswer;
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
	private String optionValue;

    /** 
     * <p>Restituisce il valore di optionValue</p> 
     * 
     * @return      restituisce il valore di optionValue
     */ 
    public String getOptionValue() {
        return this.optionValue;
    }
    /** 
     * <p>Imposta il valore di optionValue</p> 
     * 
     * @param      optionValue il valore di optionValue da impostare
     */ 
    public void setOptionValue( String optionValue ) {
        this.optionValue = optionValue;
    }
    // alias della tabellea - START 
    // alias della tabellea - END 
    public PollAnswerModel getModel() {
        PollAnswerModel model = new PollAnswerModel();
        model.setIdPollAnswer( toDAOID(idPollAnswer) );
        model.setIdPollOption( toDAOID(idPollOption) );
        model.setIdPollData( toDAOID(idPollData) );
        model.setOptionValue( toString(optionValue) );
        return model;
    }

}
