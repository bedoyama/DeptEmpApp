package net.antra.company.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import net.antra.company.model.Department;

@WebService(name = "DepartmentService", targetNamespace = "http://service.company.antra.net/")
public interface DepartmentService {

	@WebMethod(operationName = "saveDepartment", action = "urn:SaveDepartment")
	void saveDepartment(@WebParam(name = "dept") Department department);

	@WebMethod(operationName = "updateDepartment", action = "urn:UpdateDepartment")
	void updateDepartment(@WebParam(name = "dept") Department department);

	@WebMethod(operationName = "deleteDepartment", action = "urn:DeleteDepartment")
	void deleteDepartment(@WebParam(name = "dept") Department department);

	void deleteDepartmentById(Integer Id);
	
	@WebMethod(operationName = "findDepartmentById", action = "urn:FindDepartmentById")
	Department findDepartmentById(@WebParam(name = "id") Integer id);
	
	@WebMethod(operationName = "listAllDepartments", action = "urn:ListAllDepartments")
	public List<Department> listAllDepartments();
		
	public List<Department> listAllDepartments(Integer page, Integer size);

	@WebMethod(operationName = "deleteAllDepartments", action = "urn:DeleteAllDepartments")
	public void deleteAllDepartments();

}
