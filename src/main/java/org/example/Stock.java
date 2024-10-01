package org.example;

public class Stock {
    private final String symbol;
    private double price;
    private final OrderBook orderBook;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
        this.orderBook = new OrderBook();
    }
    public synchronized void updatePrice(double price) {
        System.out.println("Updating price of " + symbol + " from " + this.price + " to " + price);
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public OrderBook getOrderBook() {
        return orderBook;
    }
}
