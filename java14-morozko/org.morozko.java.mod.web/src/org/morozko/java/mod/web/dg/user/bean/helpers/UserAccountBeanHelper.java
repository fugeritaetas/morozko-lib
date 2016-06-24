/*
 * @(#)UserAccountBeanHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.bean.helpers
 * @creation   : 24/08/2009 15/20/40
 */
package org.morozko.java.mod.web.dg.user.bean.helpers;

import org.morozko.java.mod.web.dg.user.model.UserAccountModel;

/**
 * <p>Bean per oggetti di tipo UserAccountModel.</p>
 *
 * @author Morozko
 */
public class UserAccountBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 125112004098047L;

	private String idUserAccount;

    /** 
     * <p>Restituisce il valore di idUserAccount</p> 
     * 
     * @return      restituisce il valore di idUserAccount
     */ 
    public String getIdUserAccount() {
        return this.idUserAccount;
    }
    /** 
     * <p>Imposta il valore di idUserAccount</p> 
     * 
     * @param      idUserAccount il valore di idUserAccount da impostare
     */ 
    public void setIdUserAccount( String idUserAccount ) {
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
	private String userEnabled;

    /** 
     * <p>Restituisce il valore di userEnabled</p> 
     * 
     * @return      restituisce il valore di userEnabled
     */ 
    public String getUserEnabled() {
        return this.userEnabled;
    }
    /** 
     * <p>Imposta il valore di userEnabled</p> 
     * 
     * @param      userEnabled il valore di userEnabled da impostare
     */ 
    public void setUserEnabled( String userEnabled ) {
        this.userEnabled = userEnabled;
    }
    // alias della tabellea - START 
    // alias della tabellea - END 
    public UserAccountModel getModel() {
        UserAccountModel model = new UserAccountModel();
        model.setIdUserAccount( toDAOID(idUserAccount) );
        model.setUserName( toString(userName) );
        model.setUserPass( toString(userPass) );
        model.setUserEnabled( toInteger(userEnabled) );
        return model;
    }

}
