/*
 * @(#)PollOptionBeanHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.bean.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.bean.helpers;

import org.morozko.java.mod.web.poll.dg.model.PollOptionModel;

/**
 * <p>Bean per oggetti di tipo PollOptionModel.</p>
 *
 * @author Matteo Franci
 */
public class PollOptionBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.SimpleBasicBean {

	private final static long serialVersionUID = 125965584591355L;

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
	private String optionText;

    /** 
     * <p>Restituisce il valore di optionText</p> 
     * 
     * @return      restituisce il valore di optionText
     */ 
    public String getOptionText() {
        return this.optionText;
    }
    /** 
     * <p>Imposta il valore di optionText</p> 
     * 
     * @param      optionText il valore di optionText da impostare
     */ 
    public void setOptionText( String optionText ) {
        this.optionText = optionText;
    }
	private String optionType;

    /** 
     * <p>Restituisce il valore di optionType</p> 
     * 
     * @return      restituisce il valore di optionType
     */ 
    public String getOptionType() {
        return this.optionType;
    }
    /** 
     * <p>Imposta il valore di optionType</p> 
     * 
     * @param      optionType il valore di optionType da impostare
     */ 
    public void setOptionType( String optionType ) {
        this.optionType = optionType;
    }
	private String optionDefault;

    /** 
     * <p>Restituisce il valore di optionDefault</p> 
     * 
     * @return      restituisce il valore di optionDefault
     */ 
    public String getOptionDefault() {
        return this.optionDefault;
    }
    /** 
     * <p>Imposta il valore di optionDefault</p> 
     * 
     * @param      optionDefault il valore di optionDefault da impostare
     */ 
    public void setOptionDefault( String optionDefault ) {
        this.optionDefault = optionDefault;
    }
	private String idPollOptionParent;

    /** 
     * <p>Restituisce il valore di idPollOptionParent</p> 
     * 
     * @return      restituisce il valore di idPollOptionParent
     */ 
    public String getIdPollOptionParent() {
        return this.idPollOptionParent;
    }
    /** 
     * <p>Imposta il valore di idPollOptionParent</p> 
     * 
     * @param      idPollOptionParent il valore di idPollOptionParent da impostare
     */ 
    public void setIdPollOptionParent( String idPollOptionParent ) {
        this.idPollOptionParent = idPollOptionParent;
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
    public PollOptionModel getModel() {
        PollOptionModel model = new PollOptionModel();
        model.setIdPollOption( toDAOID(idPollOption) );
        model.setIdPollQuestion( toDAOID(idPollQuestion) );
        model.setOptionText( toString(optionText) );
        model.setOptionType( toDouble(optionType) );
        model.setOptionDefault( toString(optionDefault) );
        model.setIdPollOptionParent( toDAOID(idPollOptionParent) );
        model.setOrderBy( toDAOID(orderBy) );
        return model;
    }

}
