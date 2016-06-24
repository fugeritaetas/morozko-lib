/*
 * @(#)UserGroupModelHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.model.helpers
 * @creation   : 24/08/2009 15/20/41
 */
package org.morozko.java.mod.web.dg.user.model.helpers;

import org.morozko.java.mod.web.dg.user.bean.UserGroupBean;

/**
 * <p>Oggetto di modello per UserGroup.</p>
 *
 * @author Morozko
 */
public class UserGroupModelHelper extends org.morozko.java.mod.daogen.helpers.model.BasicModel {

	private final static long serialVersionUID = 125112004102153L;


	public static final String ATT_NAME = "userGroupModel";

    // campi relativi alla tabella - START 

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
    public UserGroupBean getBean() {
        UserGroupBean bean = new UserGroupBean();
        bean.setIdUserGroup( formatObject(idUserGroup) );
        bean.setGroupName( formatObject(groupName) );
        return bean;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( this.getClass().getName() );
        buffer.append( "[ " );
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
