package org.example;

import java.util.List;
import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class StockMarketSim {
    private final ExecutorService tradePool = Executors.newFixedThreadPool(10);

    public void startSim(List<Trader> traders){
        for (Trader trader : traders){
            tradePool.execute(trader);
        }
    }

    public void stopSim(){
        tradePool.shutdownNow();
        try{
            if (!tradePool.awaitTermination(60, TimeUnit.SECONDS)){
                tradePool.shutdownNow();
            }
        }catch (InterruptedException e){
            tradePool.shutdownNow();
        }
    }
    public static void main(String[] args) {

        Stock appleStock = new Stock("AAPL", 150.0);
        Stock googleStock = new Stock("GOOGL", 2000.0);

        List<Trader> traders = List.of(
                new Trader("T1", appleStock),
                new Trader("T2", appleStock),
                new Trader("T3", appleStock),
                new Trader("T4", googleStock),
                new Trader("T5", googleStock),
                new Trader("T6", googleStock)
        );

        StockMarketSim stockMarketSim = new StockMarketSim();
        stockMarketSim.startSim(traders);
        //sim stock price fluctuation

        ScheduledExecutorService stockPriceUpdater = Executors.newScheduledThreadPool(1);
        stockPriceUpdater.scheduleAtFixedRate(() -> {
            appleStock.updatePrice(appleStock.getPrice() + Math.random() * 10 - 5);
            googleStock.updatePrice(googleStock.getPrice() + Math.random() * 10 - 5);
        }, 0, 1, TimeUnit.SECONDS);

        try {
            Thread.sleep(10000); // Let the simulation run for 10 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Stop the simulation
            stockMarketSim.stopSim();
            stockPriceUpdater.shutdownNow(); // Stop the stock price updates
            try {
                if (!stockPriceUpdater.awaitTermination(60, TimeUnit.SECONDS)) {
                    stockPriceUpdater.shutdownNow();
                }
            } catch (InterruptedException e) {
                stockPriceUpdater.shutdownNow();
            }
        }

        System.out.println("Simulation ended.");
    }
}