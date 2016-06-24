/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id: DataOperationList.java,v 1.4 2009/05/20 05:29:14 cvsuser3 Exp $
 */

package org.morozko.java.mod.dbsrc.config.xml;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class DataOperationList.
 * 
 * @version $Revision: 1.4 $ $Date: 2009/05/20 05:29:14 $
 */
public class DataOperationList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _dataOperationList.
     */
    private java.util.Vector _dataOperationList;


      //----------------/
     //- Constructors -/
    //----------------/

    public DataOperationList() {
        super();
        this._dataOperationList = new java.util.Vector();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vDataOperation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDataOperation(
            final org.morozko.java.mod.dbsrc.config.xml.DataOperation vDataOperation)
    throws java.lang.IndexOutOfBoundsException {
        this._dataOperationList.addElement(vDataOperation);
    }

    /**
     * 
     * 
     * @param index
     * @param vDataOperation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDataOperation(
            final int index,
            final org.morozko.java.mod.dbsrc.config.xml.DataOperation vDataOperation)
    throws java.lang.IndexOutOfBoundsException {
        this._dataOperationList.add(index, vDataOperation);
    }

    /**
     * Method enumerateDataOperation.
     * 
     * @return an Enumeration over all
     * org.morozko.java.mod.dbsrc.config.xml.DataOperation elements
     */
    public java.util.Enumeration enumerateDataOperation(
    ) {
        return this._dataOperationList.elements();
    }

    /**
     * Method getDataOperation.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.morozko.java.mod.dbsrc.config.xml.DataOperation at the
     * given index
     */
    public org.morozko.java.mod.dbsrc.config.xml.DataOperation getDataOperation(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._dataOperationList.size()) {
            throw new IndexOutOfBoundsException("getDataOperation: Index value '" + index + "' not in range [0.." + (this._dataOperationList.size() - 1) + "]");
        }
        
        return (org.morozko.java.mod.dbsrc.config.xml.DataOperation) _dataOperationList.get(index);
    }

    /**
     * Method getDataOperation.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.morozko.java.mod.dbsrc.config.xml.DataOperation[] getDataOperation(
    ) {
        org.morozko.java.mod.dbsrc.config.xml.DataOperation[] array = new org.morozko.java.mod.dbsrc.config.xml.DataOperation[0];
        return (org.morozko.java.mod.dbsrc.config.xml.DataOperation[]) this._dataOperationList.toArray(array);
    }

    /**
     * Method getDataOperationCount.
     * 
     * @return the size of this collection
     */
    public int getDataOperationCount(
    ) {
        return this._dataOperationList.size();
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
    public void removeAllDataOperation(
    ) {
        this._dataOperationList.clear();
    }

    /**
     * Method removeDataOperation.
     * 
     * @param vDataOperation
     * @return true if the object was removed from the collection.
     */
    public boolean removeDataOperation(
            final org.morozko.java.mod.dbsrc.config.xml.DataOperation vDataOperation) {
        boolean removed = _dataOperationList.remove(vDataOperation);
        return removed;
    }

    /**
     * Method removeDataOperationAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.morozko.java.mod.dbsrc.config.xml.DataOperation removeDataOperationAt(
            final int index) {
        java.lang.Object obj = this._dataOperationList.remove(index);
        return (org.morozko.java.mod.dbsrc.config.xml.DataOperation) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vDataOperation
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setDataOperation(
            final int index,
            final org.morozko.java.mod.dbsrc.config.xml.DataOperation vDataOperation)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._dataOperationList.size()) {
            throw new IndexOutOfBoundsException("setDataOperation: Index value '" + index + "' not in range [0.." + (this._dataOperationList.size() - 1) + "]");
        }
        
        this._dataOperationList.set(index, vDataOperation);
    }

    /**
     * 
     * 
     * @param vDataOperationArray
     */
    public void setDataOperation(
            final org.morozko.java.mod.dbsrc.config.xml.DataOperation[] vDataOperationArray) {
        //-- copy array
        _dataOperationList.clear();
        
        for (int i = 0; i < vDataOperationArray.length; i++) {
                this._dataOperationList.add(vDataOperationArray[i]);
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
     * org.morozko.java.mod.dbsrc.config.xml.DataOperationList
     */
    public static org.morozko.java.mod.dbsrc.config.xml.DataOperationList unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.morozko.java.mod.dbsrc.config.xml.DataOperationList) Unmarshaller.unmarshal(org.morozko.java.mod.dbsrc.config.xml.DataOperationList.class, reader);
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
