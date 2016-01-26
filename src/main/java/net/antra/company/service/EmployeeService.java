package net.antra.company.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import net.antra.company.model.Department;
import net.antra.company.model.Employee;

@WebService(name = "EmployeeService", targetNamespace = "http://service.company.antra.net/")
public interface EmployeeService {
	@WebMethod(operationName = "saveEmployee", action = "urn:SaveEmployee")
	void saveEmployee(@WebParam(name = "emp") Employee employee);

	@WebMethod(operationName = "updateEmployee", action = "urn:UpdateEmployee")
	void updateEmployee(@WebParam(name = "emp") Employee employee);

	@WebMethod(operationName = "deleteEmployee", action = "urn:DeleteEmployee")
	void deleteEmployee(@WebParam(name = "emp") Employee employee);

	void deleteEmployeeById(Integer Id);

	@WebMethod(operationName = "findEmployeeById", action = "urn:FindEmployeeById")
	Employee findEmployeeById(@WebParam(name = "id") Integer id);
	
	@WebMethod(operationName = "listAllEmployees", action = "urn:ListAllEmployees")
	public List<Employee> listAllEmployees();

	public List<Employee> listAllEmployees(Integer page, Integer size);
	
	@WebMethod(operationName = "deleteAllEmployees", action = "urn:DeleteAllEmployees")
	public void deleteAllEmployees();

	public Department getMyDept(Integer empId);
	
}
