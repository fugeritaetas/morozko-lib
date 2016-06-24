/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id: DataOperationType.java,v 1.2 2009/05/20 05:29:14 cvsuser3 Exp $
 */

package org.morozko.java.mod.dbsrc.config.xml;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class DataOperationType.
 * 
 * @version $Revision: 1.2 $ $Date: 2009/05/20 05:29:14 $
 */
public class DataOperationType extends org.morozko.java.mod.dbsrc.config.xml.ClassType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _fromDs.
     */
    private java.lang.String _fromDs;

    /**
     * Field _toDs.
     */
    private java.lang.String _toDs;

    /**
     * Field _depends.
     */
    private java.lang.String _depends;

    /**
     * Field _configurationOp.
     */
    private org.morozko.java.mod.dbsrc.config.xml.ConfigurationOp _configurationOp;


      //----------------/
     //- Constructors -/
    //----------------/

    public DataOperationType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'configurationOp'.
     * 
     * @return the value of field 'ConfigurationOp'.
     */
    public org.morozko.java.mod.dbsrc.config.xml.ConfigurationOp getConfigurationOp(
    ) {
        return this._configurationOp;
    }

    /**
     * Returns the value of field 'depends'.
     * 
     * @return the value of field 'Depends'.
     */
    public java.lang.String getDepends(
    ) {
        return this._depends;
    }

    /**
     * Returns the value of field 'fromDs'.
     * 
     * @return the value of field 'FromDs'.
     */
    public java.lang.String getFromDs(
    ) {
        return this._fromDs;
    }

    /**
     * Returns the value of field 'toDs'.
     * 
     * @return the value of field 'ToDs'.
     */
    public java.lang.String getToDs(
    ) {
        return this._toDs;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'configurationOp'.
     * 
     * @param configurationOp the value of field 'configurationOp'.
     */
    public void setConfigurationOp(
            final org.morozko.java.mod.dbsrc.config.xml.ConfigurationOp configurationOp) {
        this._configurationOp = configurationOp;
    }

    /**
     * Sets the value of field 'depends'.
     * 
     * @param depends the value of field 'depends'.
     */
    public void setDepends(
            final java.lang.String depends) {
        this._depends = depends;
    }

    /**
     * Sets the value of field 'fromDs'.
     * 
     * @param fromDs the value of field 'fromDs'.
     */
    public void setFromDs(
            final java.lang.String fromDs) {
        this._fromDs = fromDs;
    }

    /**
     * Sets the value of field 'toDs'.
     * 
     * @param toDs the value of field 'toDs'.
     */
    public void setToDs(
            final java.lang.String toDs) {
        this._toDs = toDs;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * org.morozko.java.mod.dbsrc.config.xml.ClassType
     */
    public static org.morozko.java.mod.dbsrc.config.xml.ClassType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.morozko.java.mod.dbsrc.config.xml.ClassType) Unmarshaller.unmarshal(org.morozko.java.mod.dbsrc.config.xml.DataOperationType.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
