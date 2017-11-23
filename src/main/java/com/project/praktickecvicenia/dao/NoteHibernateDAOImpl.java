package com.project.praktickecvicenia.dao;

import com.project.praktickecvicenia.entities.Note;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteHibernateDAOImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Note> getAllNotes() {
        String hql = "from Note";
        Query query = getSessionFactory().openSession().createQuery(hql);
        return query.list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
