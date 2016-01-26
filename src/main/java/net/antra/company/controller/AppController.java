package net.antra.company.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.antra.company.model.Department;
import net.antra.company.model.Employee;
import net.antra.company.service.DepartmentService;
import net.antra.company.service.EmployeeService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	DepartmentService departmentService;

	@ModelAttribute("departments")
	public List<Department> populateDepartmentList() {
		return departmentService.listAllDepartments();
	}

	@ModelAttribute("employees")
	public List<Employee> populateEmployeeList() {
		return employeeService.listAllEmployees();
	}

	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {

		return "allemployees";
	}

	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/", "/list" }, params = { "page", "size" }, method = RequestMethod.GET)
	public String listSomeEmployees(ModelMap model, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {

		model.addAttribute("page", page);
		model.addAttribute("size", size);

		model.addAttribute("someemployees", employeeService.listAllEmployees(page, size));
		
		return "someemployees";
	}

	/*
	 * This method will list all existing departments.
	 */
	@RequestMapping(value = { "/deptList" }, method = RequestMethod.GET)
	public String listDepartments(ModelMap model) {

		return "alldepartments";
	}

	/*
	 * This method will provide the medium to add a new employee.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)

	public String newEmployee(ModelMap model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("edit", false);

		return "registration";
	}

	/*
	 * This method will provide the medium to add a new department.
	 */
	@RequestMapping(value = { "/newDept" }, method = RequestMethod.GET)

	public String newDepartment(ModelMap model) {
		Department department = new Department();
		model.addAttribute("department", department);
		model.addAttribute("edit", false);

		return "deptregistration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveEmployee(@Valid Employee employee, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		employeeService.saveEmployee(employee);

		model.addAttribute("success",
				"Employee " + employee.getFirstName() + " " + employee.getLastName() + " registered successfully");
		return "success";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving department in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newDept" }, method = RequestMethod.POST)
	public String saveDepartment(@Valid Department department, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "deptregistration";
		}

		departmentService.saveDepartment(department);

		model.addAttribute("success", "Department " + department.getDeptName() + " " + department.getDeptEmail()
				+ " registered successfully");
		return "success";
	}

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{id}-employee" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable Integer id, ModelMap model) {
		Employee employee = employeeService.findEmployeeById(id);
		model.addAttribute("employee", employee);
		model.addAttribute("edit", true);

		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{id}-employee" }, method = RequestMethod.POST)
	public String updateEmployee(@Valid Employee employee, BindingResult result, ModelMap model,
			@PathVariable String id) {

		if (result.hasErrors()) {

			return "registration";
		}

		employeeService.updateEmployee(employee);

		model.addAttribute("success",
				"Employee " + employee.getFirstName() + " " + employee.getLastName() + " registered successfully");
		return "success";
	}

	/*
	 * This method will provide the medium to update an existing department.
	 */
	@RequestMapping(value = { "/edit-{id}-department" }, method = RequestMethod.GET)
	public String editDepartment(@PathVariable Integer id, ModelMap model) {
		Department department = departmentService.findDepartmentById(id);
		model.addAttribute("department", department);
		model.addAttribute("edit", true);

		return "deptregistration";
	}

	/*
	 * This method will provide the medium to update an existing department.
	 */
	@RequestMapping(value = { "/edit-{id}-department" }, method = RequestMethod.POST)
	public String updateDepartment(@Valid Department department, BindingResult result, ModelMap model,
			@PathVariable String id) {

		if (result.hasErrors()) {

			return "deptregistration";
		}

		departmentService.updateDepartment(department);

		model.addAttribute("success",
				"Department " + department.getDeptName() + " " + department.getDeptEmail() + " updated successfully");
		return "success";
	}

	/*
	 * This method will delete an employee by it's Id value.
	 */
	@RequestMapping(value = { "/delete-{id}-employee" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String id) {
		employeeService.deleteEmployeeById(Integer.parseInt(id));
		return "redirect:/list";
	}

	/*
	 * This method will delete an department by it's Id value.
	 */
	@RequestMapping(value = { "/delete-{id}-department" }, method = RequestMethod.GET)
	public String deleteDepartment(@PathVariable Integer id) {
		departmentService.deleteDepartmentById(id);
		return "redirect:/deptList";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");

		return model;

	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}

	@RequestMapping(value = "/welcome**", method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");
		return model;

	}
}
