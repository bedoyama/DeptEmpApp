package net.antra.company.service;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.antra.company.dao.DepartmentDao;
import net.antra.company.dao.EmployeeDao;
import net.antra.company.model.Department;
import net.antra.company.model.Employee;

@WebService(targetNamespace = "http://service.company.antra.net/", endpointInterface = "net.antra.company.service.EmployeeService", portName = "EmployeeServiceImplPort", serviceName = "EmployeeServiceImplService")
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void saveEmployee(Employee employee) {
		employeeDao.persist(employee);
	}

	public void updateEmployee(Employee employee) {
		employeeDao.update(employee);
	}

	public void deleteEmployee(Employee employee) {
		employeeDao.delete(employee);
	}

	public Employee findEmployeeById(Integer id) {
		return employeeDao.findById(id);
	}

	public List<Employee> listAllEmployees() {
		return employeeDao.listAll();
	}

	public void deleteAllEmployees() {
		employeeDao.deleteAll();
	}

	public void deleteEmployeeById(Integer Id) {
		employeeDao.deleteById(Id);
	}

	public List<Employee> listAllEmployees(Integer page, Integer size) {
		return employeeDao.listAll(page, size);
	}

	public Department getMyDept(Integer empId) {
		return employeeDao.findDeptByEmpId(empId);
	}

}
