package net.antra.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import net.antra.company.service.*;
import net.antra.company.model.Department;
import net.antra.company.model.Employee;

@RestController
public class RestServicesController {

	@Autowired
	DepartmentService departmentService; // Service which will do all data
											// retrieval/manipulation work
	@Autowired
	EmployeeService employeeService; // Service which will do all data
										// retrieval/manipulation work

	// -------------------Retrieve All
	// Departments--------------------------------------------------------

	@RequestMapping(value = "/department/", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> listAllDepartments() {
		// List<Department> departments =
		// departmentService.findAllDepartments();
		List<Department> departments = departmentService.listAllDepartments();
		if (departments.isEmpty()) {
			return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);// You
																				// many
																				// decide
																				// to
																				// return
																				// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
	}

	// -------------------Retrieve All
	// Employees--------------------------------------------------------

	@RequestMapping(value = "/employee", params = { "page", "size" }, method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> listAllEmployeesPaginated(@RequestParam("page") Integer page,
			@RequestParam("size") Integer size) {
		// List<Employee> departments =
		// departmentService.findAllEmployees();
		System.out.println("page " + page + ", size" + size);
		List<Employee> employees = employeeService.listAllEmployees(page, size);
		if (employees.isEmpty()) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);// You
																				// many
																				// decide
																				// to
																				// return
																				// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	// -------------------Retrieve All
	// Employees--------------------------------------------------------

	@RequestMapping(value = "/employee/", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> listAllEmployees() {
		// List<Employee> departments =
		// departmentService.findAllEmployees();
		List<Employee> employees = employeeService.listAllEmployees();
		if (employees.isEmpty()) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);// You
																				// many
																				// decide
																				// to
																				// return
																				// HttpStatus.NOT_FOUND
		}

//		for (Employee emp : employees) {
//			System.out.println("B" + emp.getDepartment());
//			emp.setDepartment(employeeService.getMyDept(emp.getEmpId()));
//			System.out.println("A" + emp.getDepartment());
//		}

		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Employee--------------------------------------------------------

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer id) {
		System.out.println("Fetching Employee with id " + id);
		Employee employee = employeeService.findEmployeeById(id);
		if (employee == null) {
			System.out.println("Employee with id " + id + " not found");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Department--------------------------------------------------------

	@RequestMapping(value = "/department/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> getDepartment(@PathVariable("id") Integer id) {
		System.out.println("Fetching Department with id " + id);
		Department department = departmentService.findDepartmentById(id);
		if (department == null) {
			System.out.println("Department with id " + id + " not found");
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Department>(department, HttpStatus.OK);
	}

	// -------------------Retrieve Employees from Single
	// Department--------------------------------------------------------

	@RequestMapping(value = "/department/{id}/employee/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getEmployeesFromDepartment(@PathVariable("id") Integer id) {
		System.out.println("Fetching Employees for Department with id " + id);
		List<Employee> empList = departmentService.findDepartmentById(id).getEmployees();
		if (empList == null) {
			System.out.println("No Employees for department with id " + id);
			return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}

	// -------------------Retrieve Single Employee from Single
	// Department--------------------------------------------------------

	@RequestMapping(value = "/department/{id}/employee/{empid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeFromDepartment(@PathVariable("id") Integer id,
			@PathVariable("empid") Integer empid) {
		System.out.println("Fetching Employee for Department with id " + id);
		List<Employee> empList = departmentService.findDepartmentById(id).getEmployees();

		Employee foundEmp = null;

		for (Employee emp : empList) {
			if (emp.getEmpId() == empid) {
				foundEmp = emp;
			}
		}
		if (foundEmp == null) {
			System.out.println("No Employee for department with id " + id);
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(foundEmp, HttpStatus.OK);
	}

	// -------------------Add Single Employee to Single
	// Department--------------------------------------------------------

	@RequestMapping(value = "/department/{id}/employee/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createEmployeeFromDepartment(@PathVariable("id") Integer id,
			@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Employee for Department with id " + id);

		Department department = departmentService.findDepartmentById(id);
		if (department == null) {
			System.out.println("Department with id " + id + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		employee.setDepartment(department);

		employeeService.saveEmployee(employee);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/department/{id}").buildAndExpand(department.getDeptId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// -------------------Create a
	// Department--------------------------------------------------------

	@RequestMapping(value = "/department/", method = RequestMethod.POST)
	public ResponseEntity<Void> createDepartment(@RequestBody Department department, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Department " + department.getDeptName());

		// if (departmentService.isDepartmentExist(department)) {
		// System.out.println("A Department with name " + department.getName() +
		// " already exist");
		// return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		// }

		departmentService.saveDepartment(department);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/department/{id}").buildAndExpand(department.getDeptId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Department
	// --------------------------------------------------------

	@RequestMapping(value = "/department/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Department> updateDepartment(@PathVariable("id") Integer id,
			@RequestBody Department department) {
		System.out.println("Updating Department " + id);

		Department currentDepartment = departmentService.findDepartmentById(id);

		if (currentDepartment == null) {
			System.out.println("Department with id " + id + " not found");
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		}

		currentDepartment.setDeptName(department.getDeptName());
		currentDepartment.setDeptEmail(department.getDeptEmail());

		departmentService.updateDepartment(currentDepartment);
		return new ResponseEntity<Department>(currentDepartment, HttpStatus.OK);
	}

	// ------------------- Delete a Department
	// --------------------------------------------------------

	@RequestMapping(value = "/department/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Department> deleteDepartment(@PathVariable("id") Integer id) {
		System.out.println("Fetching & Deleting Department with id " + id);

		Department department = departmentService.findDepartmentById(id);
		if (department == null) {
			System.out.println("Unable to delete. Department with id " + id + " not found");
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		}

		departmentService.deleteDepartment(department);
		return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Departments
	// --------------------------------------------------------

	@RequestMapping(value = "/department/", method = RequestMethod.DELETE)
	public ResponseEntity<Department> deleteAllDepartments() {
		System.out.println("Deleting All Departments");

		departmentService.deleteAllDepartments();
		return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
	}

}
