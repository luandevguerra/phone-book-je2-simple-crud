package me.shockyng.phonebook.api.daos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T, E> {

    private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);

    private final EntityManager entityManager;

    protected abstract Class<T> clazz();

    public BaseDAO() {
        String persistenceUnit = PersistenceUnitEnum.PHONE_BOOK.getName();

        logger.debug("DAO created with the following Persistence Unit: " + persistenceUnit);

        this.entityManager = Persistence
                .createEntityManagerFactory(persistenceUnit)
                .createEntityManager();
    }

    public List<T> getAll() {
        logger.info("All " + clazz().getSimpleName() + "s will be fetched at the database");
        ArrayList<T> list = (ArrayList<T>) this.entityManager
                .createQuery("from " + clazz().getSimpleName())
                .getResultList();

        logger.info(clazz().getSimpleName() + "s found");
        return list;
    }

    public T getById(E id) throws NoResultException {
        logger.info("A " + clazz().getSimpleName() + " will be fetched at the database with id " + id);
        T entity = (T) this.entityManager
                .createQuery("select o from " + clazz().getSimpleName() + " o where o.id = :id")
                .setParameter("id", id)
                .getSingleResult();

        logger.info(clazz().getSimpleName() + " found: {}", entity);
        return entity;
    }

    public T save(T e) {
        beginTransaction();
        logger.info(clazz().getSimpleName() + " will be saved/updated at the database");

        entityManager.merge(e);
        logger.info(clazz().getSimpleName() + " was saved/updated at the database successfully");

        commitTransaction();
        return e;
    }

    public T update(T e) {
        return save(e);
    }

    public void deleteById(E id) {
        T e = getById(id);
        beginTransaction();
        logger.info("Removing " + clazz().getSimpleName() + " at the database with the following id: " + id);
        entityManager.remove(e);
        logger.info(clazz().getSimpleName() + " with the id " + id + " was deleted from the database successfully");
        commitTransaction();
    }

    private void beginTransaction() {
        logger.info("A transactions was opened");
        entityManager.getTransaction().begin();
    }

    private void commitTransaction() {
        logger.info("A transactions was committed");
        entityManager.getTransaction().begin();
    }
}
