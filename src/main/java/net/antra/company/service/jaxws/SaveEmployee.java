
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

@XmlRootElement(name = "saveEmployee", namespace = "http://service.company.antra.net/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveEmployee", namespace = "http://service.company.antra.net/")

public class SaveEmployee {

    @XmlElement(name = "emp")
    private net.antra.company.model.Employee emp;

    public net.antra.company.model.Employee getEmp() {
        return this.emp;
    }

    public void setEmp(net.antra.company.model.Employee newEmp)  {
        this.emp = newEmp;
    }

}
