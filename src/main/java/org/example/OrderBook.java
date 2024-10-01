package org.example;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class OrderBook {
    private final List<Order> buyOrders = new LinkedList<>();
    private final List<Order> sellOrders = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();

    public void addBuyOrder(Order order) {
        lock.lock();
        try {
            buyOrders.add(order);
            buyOrders.sort(Comparator.comparing(Order::getPrice).reversed());
        } finally {
            lock.unlock();
        }
    }
}
