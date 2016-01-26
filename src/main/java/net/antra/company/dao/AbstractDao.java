package net.antra.company.dao;

import java.util.List;

public interface AbstractDao<K, E> {

	void persist(E entity);

	void update(E entity);

	void delete(E entity);

	void deleteById(K id);

	E findById(K id);
	
	List<E> listAll();

	List<E> listAll(Integer page, Integer size);

	void deleteAll();

}