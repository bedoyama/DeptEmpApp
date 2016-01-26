package net.antra.company.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.antra.company.model.Department;
import net.antra.company.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDaoImpl<Integer, Employee> implements EmployeeDao {

	public List<Employee> listAll() {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		List<Employee> empList = session.createCriteria(Employee.class).list();
		tx.commit();
		return empList;
	}

	public List<Employee> listAll(Integer page, Integer size) {
		Session session = getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);

		List<Employee> employees = (List<Employee>) criteria.list();
		return employees;
	}

	public void deleteAll() {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Query query = session.createQuery("delete from Employee");
		query.executeUpdate();
		tx.commit();
	}

	public Department findDeptByEmpId(Integer empId) {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.property("department"));
		criteria.add(Restrictions.eq("empId", empId));

		Department dept = (Department) criteria.list().get(0);
		Hibernate.initialize(dept.getEmployees());

		tx.commit();

		return dept;
	}

}
