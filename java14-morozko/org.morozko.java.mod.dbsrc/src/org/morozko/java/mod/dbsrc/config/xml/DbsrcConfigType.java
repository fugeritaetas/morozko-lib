/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id: DbsrcConfigType.java,v 1.2 2009/05/20 05:29:14 cvsuser3 Exp $
 */

package org.morozko.java.mod.dbsrc.config.xml;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class DbsrcConfigType.
 * 
 * @version $Revision: 1.2 $ $Date: 2009/05/20 05:29:14 $
 */
public class DbsrcConfigType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _generalProps.
     */
    private org.morozko.java.mod.dbsrc.config.xml.GeneralProps _generalProps;

    /**
     * Field _dataSourceList.
     */
    private org.morozko.java.mod.dbsrc.config.xml.DataSourceList _dataSourceList;

    /**
     * Field _dataOperationList.
     */
    private org.morozko.java.mod.dbsrc.config.xml.DataOperationList _dataOperationList;


      //----------------/
     //- Constructors -/
    //----------------/

    public DbsrcConfigType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'dataOperationList'.
     * 
     * @return the value of field 'DataOperationList'.
     */
    public org.morozko.java.mod.dbsrc.config.xml.DataOperationList getDataOperationList(
    ) {
        return this._dataOperationList;
    }

    /**
     * Returns the value of field 'dataSourceList'.
     * 
     * @return the value of field 'DataSourceList'.
     */
    public org.morozko.java.mod.dbsrc.config.xml.DataSourceList getDataSourceList(
    ) {
        return this._dataSourceList;
    }

    /**
     * Returns the value of field 'generalProps'.
     * 
     * @return the value of field 'GeneralProps'.
     */
    public org.morozko.java.mod.dbsrc.config.xml.GeneralProps getGeneralProps(
    ) {
        return this._generalProps;
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
     * Sets the value of field 'dataOperationList'.
     * 
     * @param dataOperationList the value of field
     * 'dataOperationList'.
     */
    public void setDataOperationList(
            final org.morozko.java.mod.dbsrc.config.xml.DataOperationList dataOperationList) {
        this._dataOperationList = dataOperationList;
    }

    /**
     * Sets the value of field 'dataSourceList'.
     * 
     * @param dataSourceList the value of field 'dataSourceList'.
     */
    public void setDataSourceList(
            final org.morozko.java.mod.dbsrc.config.xml.DataSourceList dataSourceList) {
        this._dataSourceList = dataSourceList;
    }

    /**
     * Sets the value of field 'generalProps'.
     * 
     * @param generalProps the value of field 'generalProps'.
     */
    public void setGeneralProps(
            final org.morozko.java.mod.dbsrc.config.xml.GeneralProps generalProps) {
        this._generalProps = generalProps;
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
     * org.morozko.java.mod.dbsrc.config.xml.DbsrcConfigType
     */
    public static org.morozko.java.mod.dbsrc.config.xml.DbsrcConfigType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.morozko.java.mod.dbsrc.config.xml.DbsrcConfigType) Unmarshaller.unmarshal(org.morozko.java.mod.dbsrc.config.xml.DbsrcConfigType.class, reader);
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
