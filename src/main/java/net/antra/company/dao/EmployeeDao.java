package net.antra.company.dao;

import net.antra.company.model.Department;
import net.antra.company.model.Employee;

public interface EmployeeDao extends AbstractDao<Integer, Employee> {

	Department findDeptByEmpId(Integer empId);
	
}
