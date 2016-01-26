package net.antra.company.validator;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.convert.converter.Converter;
import net.antra.company.model.Department;
import net.antra.company.service.DepartmentService;

public class StringToDepartment implements Converter<String, Department> {

	@Autowired
	private DepartmentService departmentService;

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public StringToDepartment() {
	}

	public StringToDepartment(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Override
	public Department convert(String arg0) {
		return departmentService.findDepartmentById(Integer.parseInt(arg0));
	}

}
