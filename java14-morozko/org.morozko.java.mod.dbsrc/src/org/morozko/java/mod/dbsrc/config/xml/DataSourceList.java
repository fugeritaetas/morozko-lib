/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id: DataSourceList.java,v 1.5 2009/05/20 05:29:14 cvsuser3 Exp $
 */

package org.morozko.java.mod.dbsrc.config.xml;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class DataSourceList.
 * 
 * @version $Revision: 1.5 $ $Date: 2009/05/20 05:29:14 $
 */
public class DataSourceList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _dataSourceList.
     */
    private java.util.Vector _dataSourceList;


      //----------------/
     //- Constructors -/
    //----------------/

    public DataSourceList() {
        super();
        this._dataSourceList = new java.util.Vector();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vDataSource
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDataSource(
            final org.morozko.java.mod.dbsrc.config.xml.DataSource vDataSource)
    throws java.lang.IndexOutOfBoundsException {
        this._dataSourceList.addElement(vDataSource);
    }

    /**
     * 
     * 
     * @param index
     * @param vDataSource
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDataSource(
            final int index,
            final org.morozko.java.mod.dbsrc.config.xml.DataSource vDataSource)
    throws java.lang.IndexOutOfBoundsException {
        this._dataSourceList.add(index, vDataSource);
    }

    /**
     * Method enumerateDataSource.
     * 
     * @return an Enumeration over all
     * org.morozko.java.mod.dbsrc.config.xml.DataSource elements
     */
    public java.util.Enumeration enumerateDataSource(
    ) {
        return this._dataSourceList.elements();
    }

    /**
     * Method getDataSource.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.morozko.java.mod.dbsrc.config.xml.DataSource at the
     * given index
     */
    public org.morozko.java.mod.dbsrc.config.xml.DataSource getDataSource(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._dataSourceList.size()) {
            throw new IndexOutOfBoundsException("getDataSource: Index value '" + index + "' not in range [0.." + (this._dataSourceList.size() - 1) + "]");
        }
        
        return (org.morozko.java.mod.dbsrc.config.xml.DataSource) _dataSourceList.get(index);
    }

    /**
     * Method getDataSource.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.morozko.java.mod.dbsrc.config.xml.DataSource[] getDataSource(
    ) {
        org.morozko.java.mod.dbsrc.config.xml.DataSource[] array = new org.morozko.java.mod.dbsrc.config.xml.DataSource[0];
        return (org.morozko.java.mod.dbsrc.config.xml.DataSource[]) this._dataSourceList.toArray(array);
    }

    /**
     * Method getDataSourceCount.
     * 
     * @return the size of this collection
     */
    public int getDataSourceCount(
    ) {
        return this._dataSourceList.size();
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
     */
    public void removeAllDataSource(
    ) {
        this._dataSourceList.clear();
    }

    /**
     * Method removeDataSource.
     * 
     * @param vDataSource
     * @return true if the object was removed from the collection.
     */
    public boolean removeDataSource(
            final org.morozko.java.mod.dbsrc.config.xml.DataSource vDataSource) {
        boolean removed = _dataSourceList.remove(vDataSource);
        return removed;
    }

    /**
     * Method removeDataSourceAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.morozko.java.mod.dbsrc.config.xml.DataSource removeDataSourceAt(
            final int index) {
        java.lang.Object obj = this._dataSourceList.remove(index);
        return (org.morozko.java.mod.dbsrc.config.xml.DataSource) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vDataSource
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setDataSource(
            final int index,
            final org.morozko.java.mod.dbsrc.config.xml.DataSource vDataSource)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._dataSourceList.size()) {
            throw new IndexOutOfBoundsException("setDataSource: Index value '" + index + "' not in range [0.." + (this._dataSourceList.size() - 1) + "]");
        }
        
        this._dataSourceList.set(index, vDataSource);
    }

    /**
     * 
     * 
     * @param vDataSourceArray
     */
    public void setDataSource(
            final org.morozko.java.mod.dbsrc.config.xml.DataSource[] vDataSourceArray) {
        //-- copy array
        _dataSourceList.clear();
        
        for (int i = 0; i < vDataSourceArray.length; i++) {
                this._dataSourceList.add(vDataSourceArray[i]);
        }
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
     * org.morozko.java.mod.dbsrc.config.xml.DataSourceList
     */
    public static org.morozko.java.mod.dbsrc.config.xml.DataSourceList unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.morozko.java.mod.dbsrc.config.xml.DataSourceList) Unmarshaller.unmarshal(org.morozko.java.mod.dbsrc.config.xml.DataSourceList.class, reader);
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
