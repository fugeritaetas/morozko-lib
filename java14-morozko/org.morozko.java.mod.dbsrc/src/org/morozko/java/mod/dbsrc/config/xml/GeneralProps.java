/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id: GeneralProps.java,v 1.5 2009/05/20 05:29:14 cvsuser3 Exp $
 */

package org.morozko.java.mod.dbsrc.config.xml;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class GeneralProps.
 * 
 * @version $Revision: 1.5 $ $Date: 2009/05/20 05:29:14 $
 */
public class GeneralProps implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _generalPropertyList.
     */
    private java.util.Vector _generalPropertyList;


      //----------------/
     //- Constructors -/
    //----------------/

    public GeneralProps() {
        super();
        this._generalPropertyList = new java.util.Vector();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vGeneralProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addGeneralProperty(
            final org.morozko.java.mod.dbsrc.config.xml.GeneralProperty vGeneralProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._generalPropertyList.addElement(vGeneralProperty);
    }

    /**
     * 
     * 
     * @param index
     * @param vGeneralProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addGeneralProperty(
            final int index,
            final org.morozko.java.mod.dbsrc.config.xml.GeneralProperty vGeneralProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._generalPropertyList.add(index, vGeneralProperty);
    }

    /**
     * Method enumerateGeneralProperty.
     * 
     * @return an Enumeration over all
     * org.morozko.java.mod.dbsrc.config.xml.GeneralProperty element
     */
    public java.util.Enumeration enumerateGeneralProperty(
    ) {
        return this._generalPropertyList.elements();
    }

    /**
     * Method getGeneralProperty.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.morozko.java.mod.dbsrc.config.xml.GeneralProperty at the
     * given index
     */
    public org.morozko.java.mod.dbsrc.config.xml.GeneralProperty getGeneralProperty(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._generalPropertyList.size()) {
            throw new IndexOutOfBoundsException("getGeneralProperty: Index value '" + index + "' not in range [0.." + (this._generalPropertyList.size() - 1) + "]");
        }
        
        return (org.morozko.java.mod.dbsrc.config.xml.GeneralProperty) _generalPropertyList.get(index);
    }

    /**
     * Method getGeneralProperty.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.morozko.java.mod.dbsrc.config.xml.GeneralProperty[] getGeneralProperty(
    ) {
        org.morozko.java.mod.dbsrc.config.xml.GeneralProperty[] array = new org.morozko.java.mod.dbsrc.config.xml.GeneralProperty[0];
        return (org.morozko.java.mod.dbsrc.config.xml.GeneralProperty[]) this._generalPropertyList.toArray(array);
    }

    /**
     * Method getGeneralPropertyCount.
     * 
     * @return the size of this collection
     */
    public int getGeneralPropertyCount(
    ) {
        return this._generalPropertyList.size();
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName(
    ) {
        return this._name;
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
    public void removeAllGeneralProperty(
    ) {
        this._generalPropertyList.clear();
    }

    /**
     * Method removeGeneralProperty.
     * 
     * @param vGeneralProperty
     * @return true if the object was removed from the collection.
     */
    public boolean removeGeneralProperty(
            final org.morozko.java.mod.dbsrc.config.xml.GeneralProperty vGeneralProperty) {
        boolean removed = _generalPropertyList.remove(vGeneralProperty);
        return removed;
    }

    /**
     * Method removeGeneralPropertyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.morozko.java.mod.dbsrc.config.xml.GeneralProperty removeGeneralPropertyAt(
            final int index) {
        java.lang.Object obj = this._generalPropertyList.remove(index);
        return (org.morozko.java.mod.dbsrc.config.xml.GeneralProperty) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vGeneralProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setGeneralProperty(
            final int index,
            final org.morozko.java.mod.dbsrc.config.xml.GeneralProperty vGeneralProperty)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._generalPropertyList.size()) {
            throw new IndexOutOfBoundsException("setGeneralProperty: Index value '" + index + "' not in range [0.." + (this._generalPropertyList.size() - 1) + "]");
        }
        
        this._generalPropertyList.set(index, vGeneralProperty);
    }

    /**
     * 
     * 
     * @param vGeneralPropertyArray
     */
    public void setGeneralProperty(
            final org.morozko.java.mod.dbsrc.config.xml.GeneralProperty[] vGeneralPropertyArray) {
        //-- copy array
        _generalPropertyList.clear();
        
        for (int i = 0; i < vGeneralPropertyArray.length; i++) {
                this._generalPropertyList.add(vGeneralPropertyArray[i]);
        }
    }

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(
            final java.lang.String name) {
        this._name = name;
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
     * org.morozko.java.mod.dbsrc.config.xml.GeneralProps
     */
    public static org.morozko.java.mod.dbsrc.config.xml.GeneralProps unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.morozko.java.mod.dbsrc.config.xml.GeneralProps) Unmarshaller.unmarshal(org.morozko.java.mod.dbsrc.config.xml.GeneralProps.class, reader);
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
