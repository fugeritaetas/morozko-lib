/*
 * @(#)UserGroupuserModelHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.model.helpers
 * @creation   : 24/08/2009 15/20/41
 */
package org.morozko.java.mod.web.dg.user.model.helpers;

import org.morozko.java.mod.web.dg.user.bean.UserGroupuserBean;

/**
 * <p>Oggetto di modello per UserGroupuser.</p>
 *
 * @author Morozko
 */
public class UserGroupuserModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 125112004104167L;


	public static final String ATT_NAME = "userGroupuserModel";

    // campi relativi alla tabella - START 

	private org.morozko.java.mod.db.dao.DAOID idUserGroupuser;

    /** 
     * <p>Restituisce il valore di idUserGroupuser</p> 
     * 
     * @return      restituisce il valore di idUserGroupuser
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdUserGroupuser() {
        return this.idUserGroupuser;
    }
    /** 
     * <p>Imposta il valore di idUserGroupuser</p> 
     * 
     * @param      idUserGroupuser il valore di idUserGroupuser da impostare
     */ 
    public void setIdUserGroupuser( org.morozko.java.mod.db.dao.DAOID idUserGroupuser ) {
        this.idUserGroupuser = idUserGroupuser;
    }

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

	private org.morozko.java.mod.db.dao.DAOID idUserGroup;

    /** 
     * <p>Restituisce il valore di idUserGroup</p> 
     * 
     * @return      restituisce il valore di idUserGroup
     */ 
    public org.morozko.java.mod.db.dao.DAOID getIdUserGroup() {
        return this.idUserGroup;
    }
    /** 
     * <p>Imposta il valore di idUserGroup</p> 
     * 
     * @param      idUserGroup il valore di idUserGroup da impostare
     */ 
    public void setIdUserGroup( org.morozko.java.mod.db.dao.DAOID idUserGroup ) {
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
    // campi relativi alla tabella - END 

    // alias della tabellea - START 
    // alias della tabellea - END 
    public UserGroupuserBean getBean() {
        UserGroupuserBean bean = new UserGroupuserBean();
        bean.setIdUserGroupuser( formatObject(idUserGroupuser) );
        bean.setIdUserAccount( formatObject(idUserAccount) );
        bean.setIdUserGroup( formatObject(idUserGroup) );
        bean.setGroupName( formatObject(groupName) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
        buffer.append( "idUserGroupuser=" );
        buffer.append( this.idUserGroupuser );
        buffer.append( "; " );
        buffer.append( "idUserAccount=" );
        buffer.append( this.idUserAccount );
        buffer.append( "; " );
        buffer.append( "idUserGroup=" );
        buffer.append( this.idUserGroup );
        buffer.append( "; " );
        buffer.append( "groupName=" );
        buffer.append( this.groupName );
        buffer.append( "; " );
        buffer.append( "]" );
        return buffer.toString();
    }

}
