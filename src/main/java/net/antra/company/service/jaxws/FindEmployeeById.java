
package net.antra.company.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.1.4
 * Mon Jan 18 20:49:09 EST 2016
 * Generated source version: 3.1.4
 */

@XmlRootElement(name = "findEmployeeById", namespace = "http://service.company.antra.net/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findEmployeeById", namespace = "http://service.company.antra.net/")

public class FindEmployeeById {

    @XmlElement(name = "id")
    private java.lang.Integer id;

    public java.lang.Integer getId() {
        return this.id;
    }

    public void setId(java.lang.Integer newId)  {
        this.id = newId;
    }

}

