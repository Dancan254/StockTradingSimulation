# Stock Market Simulation

## Overview

The **Stock Market Simulation** is a multithreaded Java program that simulates a stock market where traders buy and sell shares. The project demonstrates advanced Java concepts such as concurrency, thread pools, and scheduled tasks.

## Features

- **Multithreading**: Each trader runs in its own thread for concurrent trading.
- **Dynamic Stock Prices**: Prices fluctuate periodically using scheduled tasks.
- **Trader Simulation**: Random buying and selling actions by traders.
- **Graceful Shutdown**: Ensures all threads stop correctly at the end.

## Technologies Used

- **Java 8+**
- **ExecutorService**: Manages trader threads.
- **ScheduledExecutorService**: Updates stock prices periodically.

## Project Structure

```
src/
│
├── Stock.java           // Stock class
├── Trader.java          // Trader class
├── StockMarketSim.java  // Main class for running the simulation
└── README.md            // This file
```

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Dancan254/StockTradingSimulation
   cd Stocktradingsimulation 
   ```
   
2. **Compile and Run**:
   ```bash
   javac -d out <main method>
   java -cp out org.example.StockMarketSim
   ```

## How It Works

1. **Stock and Trader Setup**: Creates stocks and assigns traders to buy/sell shares.
2. **Trader Execution**: Traders run on separate threads, simulating real-time trade activity.
3. **Stock Price Updates**: Prices fluctuate every second.
4. **Simulation End**: Runs for 10 seconds, then terminates all threads gracefully.

## Example Output

```text
T1 is buying 7 shares of AAPL at price 150.0
T2 is selling 3 shares of AAPL at price 150.0
T3 is buying 5 shares of AAPL at price 149.5
T4 is selling 2 shares of GOOGL at price 2000.0
T5 is buying 6 shares of GOOGL at price 1998.5
...
T6 was interrupted.
T1 was interrupted.
T2 was interrupted.
Simulation ended.
```
## Key Concepts

- **ExecutorService**: Handles concurrent execution of traders.
- **ScheduledExecutorService**: Updates stock prices at fixed intervals.
- **Graceful Shutdown**: Ensures proper termination of threads.

## License

This project is licensed under the MIT License.
