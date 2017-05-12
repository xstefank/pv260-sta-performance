/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.perfcake.examples.vertx;

import entities.Loan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author student
 */
public class DatabaseConnector {

    private EntityManager entityManager;

    public DatabaseConnector() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPU");
        entityManager = emf.createEntityManager();
        System.out.println("Database connection created");
    }

    public void refresh() {
        entityManager.flush();
        entityManager.clear();
    }

    public void closeConnector() {
        this.entityManager.close();
        System.out.println("Database connection closed");
    }

    public List<Loan> getUnreturnedBooksInComplicatedWay(Date year) {
        Query query = entityManager.createQuery("SELECT l FROM Loan l"
            + " WHERE l.bookItem.book.published >= :year "
            + " AND l.returned !=NULL"
            + " AND l.reader.birth >= :year "
            + " AND l.reader.birth <= :birth "
            + " AND l.bookItem.statusCode.code=:code");
        query.setParameter("year", year);
        query.setParameter("birth", new Date(2010, 1, 1));
        query.setParameter("code", "PRESENT_LONG");
        return query.getResultList();
    }

    public List<Loan> getRandomUnreturnedBooks() {
        Random rand = new Random();
        int randomNum = rand.nextInt((2015 - 1900) + 1) + 1900;
        System.out.println("Random year: " + randomNum);
//        Date bookDate = new Date(randomNum, 1, 1);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        Date bookDate = null;
        try {
            bookDate = formatter.parse("01-01-" + (Integer.toString(randomNum)));
        } catch (ParseException ex) {
            Logger.getLogger(HTTPEchoVerticle.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getUnreturnedBooksInComplicatedWay(bookDate);
    }

//    public String convertHQLToSql(String hql) {
//
//        ASTQueryTranslatorFactory queryTranslatorFactory = new ASTQueryTranslatorFactory();
//        SessionImplementor hibernateSession = entityManager.unwrap(SessionImplementor.class);
//        QueryTranslator queryTranslator = queryTranslatorFactory.createQueryTranslator("", hql, java.util.Collections.EMPTY_MAP, hibernateSession.getFactory());
//        queryTranslator.compile(java.util.Collections.EMPTY_MAP, false);
//        String sqlQueryString = queryTranslator.getSQLString();
//    }
}
