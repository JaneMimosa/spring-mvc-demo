package com.springmvc.demo.repository.impl;

import com.springmvc.demo.component.HibernateSessionFactory;
import com.springmvc.demo.domain.Product;
import com.springmvc.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final HibernateSessionFactory sessionFactory;

    @Override
    public List<Product> getProducts() {
        try (Session session = sessionFactory.getSession()) {
            session.beginTransaction();
            List<Product> productList = session.createQuery("FROM Product", Product.class)
                    .getResultList();
            session.getTransaction().commit();
            return productList;
        }
    }

    @Override
    public Optional<Product> getProductById(long id) {
        try (Session session = sessionFactory.getSession()) {
            session.beginTransaction();
            Optional<Product> productFromBD = Optional.ofNullable(session.createQuery("SELECT p from Product p WHERE p.id = :id", Product.class)
                    .setParameter("id", id)
                    .getSingleResult());
            session.getTransaction().commit();
            return productFromBD;
        }
    }

    @Override
    public boolean addProduct(Product product) {
        try (Session session = sessionFactory.getSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            return true;
        }
    }
}
