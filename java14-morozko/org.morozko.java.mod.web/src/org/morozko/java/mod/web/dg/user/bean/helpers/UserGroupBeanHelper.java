/*
 * @(#)UserGroupBeanHelper.java
 *
 * @project    : org.morozko.java.mod.web
 * @package    : org.morozko.java.mod.web.dg.user.bean.helpers
 * @creation   : 24/08/2009 15/20/41
 */
package org.morozko.java.mod.web.dg.user.bean.helpers;

import org.morozko.java.mod.web.dg.user.model.UserGroupModel;

/**
 * <p>Bean per oggetti di tipo UserGroupModel.</p>
 *
 * @author Morozko
 */
public class UserGroupBeanHelper extends org.morozko.java.mod.daogen.helpers.bean.BasicBean {

	private final static long serialVersionUID = 125112004103173L;

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
    public UserGroupModel getModel() {
        UserGroupModel model = new UserGroupModel();
        model.setIdUserGroup( toDAOID(idUserGroup) );
        model.setGroupName( toString(groupName) );
        return model;
    }

}
