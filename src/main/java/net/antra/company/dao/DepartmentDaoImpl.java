package net.antra.company.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import net.antra.company.model.Department;
import net.antra.company.model.Employee;

@Repository("departmentDao")
public class DepartmentDaoImpl extends AbstractDaoImpl<Integer, Department> implements DepartmentDao {

//	@PostConstruct
//	public void init() {
//		System.out.println("DAO created" + this.getSessionFactory() == null);
//	}

	@Override
	public Department findById(Integer id) {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Department dept = (Department) session.get(Department.class, id);
		Hibernate.initialize(dept.getEmployees());
		tx.commit();
		return dept;
	}

	public List<Department> listAll() {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		List<Department> deptList = session.createCriteria(Department.class).list();
		for (Department dept : deptList) {
			Hibernate.initialize(dept.getEmployees());
		}
		tx.commit();
		return deptList;
	}

	@Override
	public List<Department> listAll(Integer page, Integer size) {
		Session session = getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Department.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);

		List<Department> departmentList = (List<Department>) criteria.list();
		for (Department dept : departmentList) {
			Hibernate.initialize(dept.getEmployees());
		}

		return departmentList;
	}

	public void deleteAll() {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		Query query = session.createQuery("delete from Department");
		query.executeUpdate();
		tx.commit();
	}

}
