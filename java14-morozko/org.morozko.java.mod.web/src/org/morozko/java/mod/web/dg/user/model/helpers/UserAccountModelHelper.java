/*
 * @(#)UserAccountModelHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.model.helpers
 * @creation   : 24/08/2009 15/20/40
 */
package org.morozko.java.mod.web.dg.user.model.helpers;

import org.morozko.java.mod.web.dg.user.bean.UserAccountBean;

/**
 * <p>Oggetto di modello per UserAccount.</p>
 *
 * @author Morozko
 */
public class UserAccountModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 125112004097023L;


	public static final String ATT_NAME = "userAccountModel";

    // campi relativi alla tabella - START 

	private org.morozko.java.mod.db.dao.DAOID idUserAccount;

    /** 
     * <p>Restituisce il valore di idUserAccount</p> 
     * 
     * @return      restituisce il valore di idUserAccount
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdUserAccount() {
        return this.idUserAccount;
    }
    /** 
     * <p>Imposta il valore di idUserAccount</p> 
     * 
     * @param      idUserAccount il valore di idUserAccount da impostare
     */ 
    public void setIdUserAccount( org.morozko.java.mod.db.dao.DAOID idUserAccount ) {
        this.idUserAccount = idUserAccount;
    }

	private String userName;

    /** 
     * <p>Restituisce il valore di userName</p> 
     * 
     * @return      restituisce il valore di userName
     */ 
    public String getUserName() {
        return this.userName;
    }
    /** 
     * <p>Imposta il valore di userName</p> 
     * 
     * @param      userName il valore di userName da impostare
     */ 
    public void setUserName( String userName ) {
        this.userName = userName;
    }

	private String userPass;

    /** 
     * <p>Restituisce il valore di userPass</p> 
     * 
     * @return      restituisce il valore di userPass
     */ 
    public String getUserPass() {
        return this.userPass;
    }
    /** 
     * <p>Imposta il valore di userPass</p> 
     * 
     * @param      userPass il valore di userPass da impostare
     */ 
    public void setUserPass( String userPass ) {
        this.userPass = userPass;
    }

	private Integer userEnabled;

    /** 
     * <p>Restituisce il valore di userEnabled</p> 
     * 
     * @return      restituisce il valore di userEnabled
     */ 
    public Integer getUserEnabled() {
        return this.userEnabled;
    }
    /** 
     * <p>Imposta il valore di userEnabled</p> 
     * 
     * @param      userEnabled il valore di userEnabled da impostare
     */ 
    public void setUserEnabled( Integer userEnabled ) {
        this.userEnabled = userEnabled;
    }
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    // campi relativi a relazioni - START 
    private java.util.List listGroup;

    /** 
     * <p>Restituisce il valore di listGroup</p> 
     * 
     * @return      restituisce il valore di listGroup
     */ 
    public java.util.List getListGroup() {
        return this.listGroup;
    }

    /** 
     * <p>Imposta il valore di listGroup</p> 
     * 
     * @param      listGroup il valore di listGroup da impostare
     */ 
    public void setListGroup( java.util.List listGroup ) {
        this.listGroup = listGroup;
    }

    // campi relativi a relazioni - END 

    public UserAccountBean getBean() {
        UserAccountBean bean = new UserAccountBean();
        bean.setIdUserAccount( formatObject(idUserAccount) );
        bean.setUserName( formatObject(userName) );
        bean.setUserPass( formatObject(userPass) );
        bean.setUserEnabled( formatObject(userEnabled) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idUserAccount=" );
        buffer.append( this.idUserAccount );
        buffer.append( "; " );
        buffer.append( "userName=" );
        buffer.append( this.userName );
        buffer.append( "; " );
        buffer.append( "userPass=" );
        buffer.append( this.userPass );
        buffer.append( "; " );
        buffer.append( "userEnabled=" );
        buffer.append( this.userEnabled );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
