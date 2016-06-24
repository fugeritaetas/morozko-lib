/*
 * @(#)UserGroupuserBeanHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.bean.helpers
 * @creation   : 24/08/2009 15/20/41
 */
package org.morozko.java.mod.web.dg.user.bean.helpers;

import org.morozko.java.mod.web.dg.user.model.UserGroupuserModel;

/**
 * <p>Bean per oggetti di tipo UserGroupuserModel.</p>
 *
 * @author Morozko
 */
public class UserGroupuserBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 125112004105186L;

	private String idUserGroupuser;

    /** 
     * <p>Restituisce il valore di idUserGroupuser</p> 
     * 
     * @return      restituisce il valore di idUserGroupuser
     */ 
    public String getIdUserGroupuser() {
        return this.idUserGroupuser;
    }
    /** 
     * <p>Imposta il valore di idUserGroupuser</p> 
     * 
     * @param      idUserGroupuser il valore di idUserGroupuser da impostare
     */ 
    public void setIdUserGroupuser( String idUserGroupuser ) {
        this.idUserGroupuser = idUserGroupuser;
    }
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
	private String idUserGroup;

    /** 
     * <p>Restituisce il valore di idUserGroup</p> 
     * 
     * @return      restituisce il valore di idUserGroup
     */ 
    public String getIdUserGroup() {
        return this.idUserGroup;
    }
    /** 
     * <p>Imposta il valore di idUserGroup</p> 
     * 
     * @param      idUserGroup il valore di idUserGroup da impostare
     */ 
    public void setIdUserGroup( String idUserGroup ) {
        this.idUserGroup = idUserGroup;
    }
	private String groupName;

    /** 
     * <p>Restituisce il valore di groupName</p> 
     * 
     * @return      restituisce il valore di groupName
     */ 
    public String getGroupName() {
        return this.groupName;
    }
    /** 
     * <p>Imposta il valore di groupName</p> 
     * 
     * @param      groupName il valore di groupName da impostare
     */ 
    public void setGroupName( String groupName ) {
        this.groupName = groupName;
    }
    // alias della tabellea - START 
    // alias della tabellea - END 
    public UserGroupuserModel getModel() {
        UserGroupuserModel model = new UserGroupuserModel();
        model.setIdUserGroupuser( toDAOID(idUserGroupuser) );
        model.setIdUserAccount( toDAOID(idUserAccount) );
        model.setIdUserGroup( toDAOID(idUserGroup) );
        model.setGroupName( toString(groupName) );
        return model;
    }

}
