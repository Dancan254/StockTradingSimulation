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
            //sort the buy orders in descending order of price
            buyOrders.sort(Comparator.comparing(Order::getPrice).reversed());
        } finally {
            lock.unlock();
        }
    }

    public void addSellOrder(Order order){
        lock.lock();
        try{
            sellOrders.add(order);
            //sort the sell orders in ascending order of price
            sellOrders.sort(Comparator.comparing(Order::getPrice));
        } finally {
            lock.unlock();
        }
    }
    public void matchOrders(){
        lock.lock();
        try{
            while (!buyOrders.isEmpty() && !sellOrders.isEmpty()){
                Order buyOrder = buyOrders.getFirst();
                Order sellOrder = sellOrders.getFirst();

                if(buyOrder.getPrice() >= sellOrder.getPrice()){
                    //process the transaction
                    int quantityTraded = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());
                    buyOrder.reduceQuantity(quantityTraded);
                    sellOrder.reduceQuantity(quantityTraded);

                    if (buyOrder.getQuantity() == 0){
                        buyOrders.remove(buyOrder);
                    }
                    if (sellOrder.getQuantity() == 0){
                        sellOrders.remove(sellOrder);
                    }
                } else{
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
