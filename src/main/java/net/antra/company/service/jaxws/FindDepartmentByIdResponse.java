
package net.antra.company.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.1.4
 * Mon Jan 18 20:48:12 EST 2016
 * Generated source version: 3.1.4
 */

@XmlRootElement(name = "findDepartmentByIdResponse", namespace = "http://service.company.antra.net/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findDepartmentByIdResponse", namespace = "http://service.company.antra.net/")

public class FindDepartmentByIdResponse {

    @XmlElement(name = "return")
    private net.antra.company.model.Department _return;

    public net.antra.company.model.Department getReturn() {
        return this._return;
    }

    public void setReturn(net.antra.company.model.Department new_return)  {
        this._return = new_return;
    }

}

