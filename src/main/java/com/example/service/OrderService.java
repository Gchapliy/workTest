package com.example.service;

import com.example.entity.Order;

public interface OrderService {
    void removeBookFromOrder(long orderId, long bookId);

    void removeOrder(long orderId);

    Order findOrderById(long orderId);

    void addBookToOrder(long orderId, long bookId);

    Order saveOrder(Order order, long userId);

    Order createOrder(Order order);
}
