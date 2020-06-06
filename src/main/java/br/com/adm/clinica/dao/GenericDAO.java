package br.com.adm.clinica.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public abstract class GenericDAO<T extends Serializable> {
	
	private Class<T> aClass;
	
	@Inject
	private EntityManager manager;
	
	protected GenericDAO(Class<T> aClass) {
		this.aClass = aClass;
	}
	
	public T findById(Long id) {
		// select * from exame where id = 8;
		manager.getTransaction().begin();
		T entity = (T) manager.find(aClass, id);
		manager.getTransaction().commit();
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		// Select * from paciente;
		manager.getTransaction().begin();
		Query query = manager.createQuery("from " + aClass.getSimpleName());
		List<T> entities = query.getResultList();
		manager.getTransaction().commit();
		return entities;
	}
	
	public void save(T entity) {
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.getTransaction().commit();
	}
	
	public void update(T entity) {
		manager.getTransaction().begin();
		manager.merge(entity);
		manager.getTransaction().commit();
	}
	
	public void delete(Long id) {
		manager.getTransaction().begin();
		manager.remove(manager.getReference(aClass, id));
		manager.getTransaction().commit();
	}

}
