/*
 * @(#)PollAnswerModelHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.model.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.model.helpers;

import org.morozko.java.mod.web.poll.dg.bean.PollAnswerBean;

/**
 * <p>Oggetto di modello per PollAnswer.</p>
 *
 * @author Matteo Franci
 */
public class PollAnswerModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 125965584593365L;


	public static final String ATT_NAME = "pollAnswerModel";

    // campi relativi alla tabella - START 

	private org.morozko.java.mod.db.dao.DAOID idPollAnswer;

    /** 
     * <p>Restituisce il valore di idPollAnswer</p> 
     * 
     * @return      restituisce il valore di idPollAnswer
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdPollAnswer() {
        return this.idPollAnswer;
    }
    /** 
     * <p>Imposta il valore di idPollAnswer</p> 
     * 
     * @param      idPollAnswer il valore di idPollAnswer da impostare
     */ 
    public void setIdPollAnswer( org.morozko.java.mod.db.dao.DAOID idPollAnswer ) {
        this.idPollAnswer = idPollAnswer;
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
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    public PollAnswerBean getBean() {
        PollAnswerBean bean = new PollAnswerBean();
        bean.setIdPollAnswer( formatObject(idPollAnswer) );
        bean.setIdPollOption( formatObject(idPollOption) );
        bean.setIdPollData( formatObject(idPollData) );
        bean.setOptionValue( formatObject(optionValue) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idPollAnswer=" );
        buffer.append( this.idPollAnswer );
        buffer.append( "; " );
        buffer.append( "idPollOption=" );
        buffer.append( this.idPollOption );
        buffer.append( "; " );
        buffer.append( "idPollData=" );
        buffer.append( this.idPollData );
        buffer.append( "; " );
        buffer.append( "optionValue=" );
        buffer.append( this.optionValue );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
