//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.19 at 05:11:33 PM CEST 
//


package UF04.P02Dao_Dao;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for empleadoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="empleadoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="apellidos" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sueldo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="anyos_trabajados" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "empleadoType", propOrder = {
    "id",
    "nombre",
    "apellidos",
    "sueldo",
    "anyosTrabajados"
})
@XmlRootElement(name = "empleado")
public class EmpleadoType {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(required = true)
    protected String apellidos;
    @XmlElement(required = true)
    protected String sueldo;
    @XmlElement(name = "anyos_trabajados", required = true)
    protected String anyosTrabajados;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the apellidos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Sets the value of the apellidos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidos(String value) {
        this.apellidos = value;
    }

    /**
     * Gets the value of the sueldo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSueldo() {
        return sueldo;
    }

    /**
     * Sets the value of the sueldo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSueldo(String value) {
        this.sueldo = value;
    }

    /**
     * Gets the value of the anyosTrabajados property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnyosTrabajados() {
        return anyosTrabajados;
    }

    /**
     * Sets the value of the anyosTrabajados property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnyosTrabajados(String value) {
        this.anyosTrabajados = value;
    }

}