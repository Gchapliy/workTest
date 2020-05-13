package com.example.service;

import com.example.dao.BookDAO;
import com.example.dao.OrderDAO;
import com.example.dao.UserDAO;
import com.example.entity.Book;
import com.example.entity.Order;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDao;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private BookDAO bookDAO;

    @Override
    public void removeBookFromOrder(long orderId, long bookId) {
        Order order = orderDao.findById(orderId).get();
        order.setBooks(order.getBooks().stream().filter(f -> f.getId() != bookId).collect(Collectors.toSet()));
        orderDao.save(order);
    }

    @Override
    public void removeOrder(long orderId) {
        orderDao.deleteById(orderId);
    }

    @Override
    public Order findOrderById(long orderId) {
        return orderDao.findById(orderId).get();
    }

    @Override
    public void addBookToOrder(long orderId, long bookId) {
        Order order = findOrderById(orderId);
        Book book = bookDAO.findById(bookId).get();

        order.getBooks().add(book);

        orderDao.save(order);
    }

    @Override
    public Order saveOrder(Order order, long userId) {
        User user = userDAO.findById(userId).get();
        Order temp = findOrderById(order.getId());
        temp.setUser(user);
        return orderDao.save(temp);
    }

    @Override
    public Order createOrder(Order order) {

        return orderDao.save(order);
    }
}
