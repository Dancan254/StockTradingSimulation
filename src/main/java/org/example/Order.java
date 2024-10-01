package org.example;

public class Order {
    private final String traderId;
    private final OrderType orderType;
    private int quantity;
    private final double price;

    public Order(String traderId, OrderType orderType, double price, int quantity) {
        this.traderId = traderId;
        this.orderType = orderType;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTraderId() {
        return traderId;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void reduceQuantity(int tradedQuantity){
        if (tradedQuantity > this.quantity) {
            throw new IllegalArgumentException("Traded quantity is greater than order quantity");
        }
        this.quantity -= tradedQuantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "traderId='" + traderId + '\'' +
                ", orderType=" + orderType +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
