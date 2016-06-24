/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id: ConfigurationType.java,v 1.5 2009/05/20 05:29:14 cvsuser3 Exp $
 */

package org.morozko.java.mod.dbsrc.config.xml;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class ConfigurationType.
 * 
 * @version $Revision: 1.5 $ $Date: 2009/05/20 05:29:14 $
 */
public class ConfigurationType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _propertyList.
     */
    private java.util.Vector _propertyList;

    /**
     * Field _configurationList.
     */
    private java.util.Vector _configurationList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ConfigurationType() {
        super();
        this._propertyList = new java.util.Vector();
        this._configurationList = new java.util.Vector();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vConfiguration
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addConfiguration(
            final org.morozko.java.mod.dbsrc.config.xml.Configuration vConfiguration)
    throws java.lang.IndexOutOfBoundsException {
        this._configurationList.addElement(vConfiguration);
    }

    /**
     * 
     * 
     * @param index
     * @param vConfiguration
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addConfiguration(
            final int index,
            final org.morozko.java.mod.dbsrc.config.xml.Configuration vConfiguration)
    throws java.lang.IndexOutOfBoundsException {
        this._configurationList.add(index, vConfiguration);
    }

    /**
     * 
     * 
     * @param vProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addProperty(
            final org.morozko.java.mod.dbsrc.config.xml.Property vProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._propertyList.addElement(vProperty);
    }

    /**
     * 
     * 
     * @param index
     * @param vProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addProperty(
            final int index,
            final org.morozko.java.mod.dbsrc.config.xml.Property vProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._propertyList.add(index, vProperty);
    }

    /**
     * Method enumerateConfiguration.
     * 
     * @return an Enumeration over all
     * org.morozko.java.mod.dbsrc.config.xml.Configuration elements
     */
    public java.util.Enumeration enumerateConfiguration(
    ) {
        return this._configurationList.elements();
    }

    /**
     * Method enumerateProperty.
     * 
     * @return an Enumeration over all
     * org.morozko.java.mod.dbsrc.config.xml.Property elements
     */
    public java.util.Enumeration enumerateProperty(
    ) {
        return this._propertyList.elements();
    }

    /**
     * Method getConfiguration.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.morozko.java.mod.dbsrc.config.xml.Configuration at the
     * given index
     */
    public org.morozko.java.mod.dbsrc.config.xml.Configuration getConfiguration(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._configurationList.size()) {
            throw new IndexOutOfBoundsException("getConfiguration: Index value '" + index + "' not in range [0.." + (this._configurationList.size() - 1) + "]");
        }
        
        return (org.morozko.java.mod.dbsrc.config.xml.Configuration) _configurationList.get(index);
    }

    /**
     * Method getConfiguration.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.morozko.java.mod.dbsrc.config.xml.Configuration[] getConfiguration(
    ) {
        org.morozko.java.mod.dbsrc.config.xml.Configuration[] array = new org.morozko.java.mod.dbsrc.config.xml.Configuration[0];
        return (org.morozko.java.mod.dbsrc.config.xml.Configuration[]) this._configurationList.toArray(array);
    }

    /**
     * Method getConfigurationCount.
     * 
     * @return the size of this collection
     */
    public int getConfigurationCount(
    ) {
        return this._configurationList.size();
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
     * Method getProperty.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * org.morozko.java.mod.dbsrc.config.xml.Property at the given
     * index
     */
    public org.morozko.java.mod.dbsrc.config.xml.Property getProperty(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._propertyList.size()) {
            throw new IndexOutOfBoundsException("getProperty: Index value '" + index + "' not in range [0.." + (this._propertyList.size() - 1) + "]");
        }
        
        return (org.morozko.java.mod.dbsrc.config.xml.Property) _propertyList.get(index);
    }

    /**
     * Method getProperty.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public org.morozko.java.mod.dbsrc.config.xml.Property[] getProperty(
    ) {
        org.morozko.java.mod.dbsrc.config.xml.Property[] array = new org.morozko.java.mod.dbsrc.config.xml.Property[0];
        return (org.morozko.java.mod.dbsrc.config.xml.Property[]) this._propertyList.toArray(array);
    }

    /**
     * Method getPropertyCount.
     * 
     * @return the size of this collection
     */
    public int getPropertyCount(
    ) {
        return this._propertyList.size();
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
    public void removeAllConfiguration(
    ) {
        this._configurationList.clear();
    }

    /**
     */
    public void removeAllProperty(
    ) {
        this._propertyList.clear();
    }

    /**
     * Method removeConfiguration.
     * 
     * @param vConfiguration
     * @return true if the object was removed from the collection.
     */
    public boolean removeConfiguration(
            final org.morozko.java.mod.dbsrc.config.xml.Configuration vConfiguration) {
        boolean removed = _configurationList.remove(vConfiguration);
        return removed;
    }

    /**
     * Method removeConfigurationAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.morozko.java.mod.dbsrc.config.xml.Configuration removeConfigurationAt(
            final int index) {
        java.lang.Object obj = this._configurationList.remove(index);
        return (org.morozko.java.mod.dbsrc.config.xml.Configuration) obj;
    }

    /**
     * Method removeProperty.
     * 
     * @param vProperty
     * @return true if the object was removed from the collection.
     */
    public boolean removeProperty(
            final org.morozko.java.mod.dbsrc.config.xml.Property vProperty) {
        boolean removed = _propertyList.remove(vProperty);
        return removed;
    }

    /**
     * Method removePropertyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public org.morozko.java.mod.dbsrc.config.xml.Property removePropertyAt(
            final int index) {
        java.lang.Object obj = this._propertyList.remove(index);
        return (org.morozko.java.mod.dbsrc.config.xml.Property) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vConfiguration
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setConfiguration(
            final int index,
            final org.morozko.java.mod.dbsrc.config.xml.Configuration vConfiguration)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._configurationList.size()) {
            throw new IndexOutOfBoundsException("setConfiguration: Index value '" + index + "' not in range [0.." + (this._configurationList.size() - 1) + "]");
        }
        
        this._configurationList.set(index, vConfiguration);
    }

    /**
     * 
     * 
     * @param vConfigurationArray
     */
    public void setConfiguration(
            final org.morozko.java.mod.dbsrc.config.xml.Configuration[] vConfigurationArray) {
        //-- copy array
        _configurationList.clear();
        
        for (int i = 0; i < vConfigurationArray.length; i++) {
                this._configurationList.add(vConfigurationArray[i]);
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
     * 
     * 
     * @param index
     * @param vProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setProperty(
            final int index,
            final org.morozko.java.mod.dbsrc.config.xml.Property vProperty)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._propertyList.size()) {
            throw new IndexOutOfBoundsException("setProperty: Index value '" + index + "' not in range [0.." + (this._propertyList.size() - 1) + "]");
        }
        
        this._propertyList.set(index, vProperty);
    }

    /**
     * 
     * 
     * @param vPropertyArray
     */
    public void setProperty(
            final org.morozko.java.mod.dbsrc.config.xml.Property[] vPropertyArray) {
        //-- copy array
        _propertyList.clear();
        
        for (int i = 0; i < vPropertyArray.length; i++) {
                this._propertyList.add(vPropertyArray[i]);
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
     * org.morozko.java.mod.dbsrc.config.xml.ConfigurationType
     */
    public static org.morozko.java.mod.dbsrc.config.xml.ConfigurationType unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (org.morozko.java.mod.dbsrc.config.xml.ConfigurationType) Unmarshaller.unmarshal(org.morozko.java.mod.dbsrc.config.xml.ConfigurationType.class, reader);
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
