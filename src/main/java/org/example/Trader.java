package org.example;

public class Trader implements Runnable{
    private final String traderId;
    private Stock stock;

    public Trader(String traderId, Stock stock) {
        this.traderId = traderId;
        this.stock = stock;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Simulate random trading activity
                double currentPrice = stock.getPrice();
                int action = (int) (Math.random() * 2); // 0 = buy, 1 = sell
                int quantity = (int) (Math.random() * 10 + 1); // random quantity between 1 and 10

                if (action == 0) {
                    System.out.println(traderId + " is buying " + quantity + " shares of " + stock.getSymbol() + " at price " + currentPrice);
                } else {
                    System.out.println(traderId + " is selling " + quantity + " shares of " + stock.getSymbol() + " at price " + currentPrice);
                }

                // Simulate time delay between trades
                Thread.sleep((long) (Math.random() * 1000)); // Random sleep between 0 and 1 second

            } catch (InterruptedException e) {
                System.out.println(traderId + " was interrupted.");
                Thread.currentThread().interrupt(); // Restore interrupted status
                break; // Exit the loop when interrupted
            }
        }
    }
}
