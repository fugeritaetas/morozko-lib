/*
 * @(#)PollOptionModelHelper.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.model.helpers
 * @creation   : 01/12/2009 09/24/05
 */
package org.morozko.java.mod.web.poll.dg.model.helpers;

import org.morozko.java.mod.web.poll.dg.bean.PollOptionBean;

/**
 * <p>Oggetto di modello per PollOption.</p>
 *
 * @author Matteo Franci
 */
public class PollOptionModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 125965584590390L;


	public static final String ATT_NAME = "pollOptionModel";

    // campi relativi alla tabella - START 

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

	private Double optionType;

    /** 
     * <p>Restituisce il valore di optionType</p> 
     * 
     * @return      restituisce il valore di optionType
     */ 
    public Double getOptionType() {
        return this.optionType;
    }
    /** 
     * <p>Imposta il valore di optionType</p> 
     * 
     * @param      optionType il valore di optionType da impostare
     */ 
    public void setOptionType( Double optionType ) {
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

	private org.morozko.java.mod.db.dao.DAOID idPollOptionParent;

    /** 
     * <p>Restituisce il valore di idPollOptionParent</p> 
     * 
     * @return      restituisce il valore di idPollOptionParent
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdPollOptionParent() {
        return this.idPollOptionParent;
    }
    /** 
     * <p>Imposta il valore di idPollOptionParent</p> 
     * 
     * @param      idPollOptionParent il valore di idPollOptionParent da impostare
     */ 
    public void setIdPollOptionParent( org.morozko.java.mod.db.dao.DAOID idPollOptionParent ) {
        this.idPollOptionParent = idPollOptionParent;
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
    private java.util.List listPollOptionKids;

    /** 
     * <p>Restituisce il valore di listPollOptionKids</p> 
     * 
     * @return      restituisce il valore di listPollOptionKids
     */ 
    public java.util.List getListPollOptionKids() {
        return this.listPollOptionKids;
    }

    /** 
     * <p>Imposta il valore di listPollOptionKids</p> 
     * 
     * @param      listPollOptionKids il valore di listPollOptionKids da impostare
     */ 
    public void setListPollOptionKids( java.util.List listPollOptionKids ) {
        this.listPollOptionKids = listPollOptionKids;
    }

    // campi relativi a relazioni - END 

    public PollOptionBean getBean() {
        PollOptionBean bean = new PollOptionBean();
        bean.setIdPollOption( formatObject(idPollOption) );
        bean.setIdPollQuestion( formatObject(idPollQuestion) );
        bean.setOptionText( formatObject(optionText) );
        bean.setOptionType( formatObject(optionType) );
        bean.setOptionDefault( formatObject(optionDefault) );
        bean.setIdPollOptionParent( formatObject(idPollOptionParent) );
        bean.setOrderBy( formatObject(orderBy) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idPollOption=" );
        buffer.append( this.idPollOption );
        buffer.append( "; " );
        buffer.append( "idPollQuestion=" );
        buffer.append( this.idPollQuestion );
        buffer.append( "; " );
        buffer.append( "optionText=" );
        buffer.append( this.optionText );
        buffer.append( "; " );
        buffer.append( "optionType=" );
        buffer.append( this.optionType );
        buffer.append( "; " );
        buffer.append( "optionDefault=" );
        buffer.append( this.optionDefault );
        buffer.append( "; " );
        buffer.append( "idPollOptionParent=" );
        buffer.append( this.idPollOptionParent );
        buffer.append( "; " );
        buffer.append( "orderBy=" );
        buffer.append( this.orderBy );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
