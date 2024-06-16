package com.asyraf.whizware.domain;

import com.asyraf.whizware.infrastructure.util.HibernateUtil;
import com.google.inject.Inject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class BaseRepository<T, ID> {

    @Inject
    private HibernateUtil hibernateUtil;

    private final Class<T> classType;

    public BaseRepository(Class<T> classType) {
        this.classType = classType;
    }

    public List<T> getAll() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM " + classType.getSimpleName(), classType).getResultList();
        }
    }

    public Optional<T> get(ID id) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            T item = session.get(classType, id);
            if (item == null) {
                return Optional.empty();
            }
            return Optional.of(item);
        }
    }

    public T save(T entity) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            T savedT = session.merge(entity);
            transaction.commit();
            return savedT;
        } catch (ConstraintViolationException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (ConstraintViolationException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    protected List<T> query(String queryString) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Query<T> query = session.createQuery(queryString, classType);
            return query.getResultList();
        }
    }

    protected List<T> query(String queryString, boolean nativeQuery) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Query<T> query = nativeQuery ? session.createNativeQuery(queryString, classType) : session.createQuery(queryString, classType);
            return query.getResultList();
        }
    }

    protected List<T> query(String queryString, Map<String, Object> parameter) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Query<T> query = session.createQuery(queryString, classType);
            for (String key : parameter.keySet()) {
                query.setParameter(key, parameter.get(key));
            }
            return query.getResultList();
        }
    }

    protected List<T> query(String queryString, Map<String, Object> parameter, boolean nativeQuery) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Query<T> query = nativeQuery ? session.createNativeQuery(queryString, classType) : session.createQuery(queryString, classType);
            for (String key : parameter.keySet()) {
                query.setParameter(key, parameter.get(key));
            }
            return query.getResultList();
        }
    }

}
