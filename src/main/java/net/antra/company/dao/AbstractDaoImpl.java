package net.antra.company.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDaoImpl<K extends Serializable, E> implements AbstractDao<K, E> {
	protected Class<E> entityClass;

	@Autowired
	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public AbstractDaoImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
	
	public void persist(E entity) {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		session.save(entity);
		tx.commit();
	}

	public void update(E entity) {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		session.update(entity);
		tx.commit();
	}

	public void delete(E entity) {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		session.delete(entity);
		tx.commit();
	}

	public void deleteById(K id) {
		E entity = findById(id);
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		session.delete(entity);
		tx.commit();
	}

	public E findById(K id) {
		Session session = getSession();
		Transaction tx = session.getTransaction();
		tx.begin();

		@SuppressWarnings("unchecked")
		E entity = (E) session.get(entityClass, id);
		tx.commit();
		return entity;
	}
}