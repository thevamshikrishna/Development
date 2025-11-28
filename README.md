# Trading Order Matching Engine

## Overview
This project implements an in-memory Trading Order Matching Engine that processes BUY and SELL orders for EQUITY, FOREX, and CRYPTO markets. It validates orders, manages order books, performs price–time priority matching, handles concurrency, and generates trade execution results. The system loads input from CSV files and produces a summary of trades and order statuses.

## Goal
- Validate incoming orders based on assignment rules  
- Maintain separate order books per TradeType  
- Match BUY and SELL orders using price–time FIFO  
- Support partial fills  
- Handle concurrency safely  
- Provide summary output and per-asset matching details  

## Input File Layout
Both input CSV files (buy_orders.csv and sell_orders.csv) follow this structure:

```
orderId,traderId,tradeType,orderType,price,quantity,countryCode,timestamp
SELL-1763965655393-001827,TRADER-0327,FOREX,SELL,1.10,338,US,2025-01-16 01:35:15
```

Columns:
- **orderId**: Unique identifier from the file  
- **traderId**: Trader’s ID  
- **tradeType**: EQUITY / FOREX / CRYPTO  
- **orderType**: BUY / SELL  
- **price**: Order price  
- **quantity**: Integer quantity  
- **countryCode**: ISO 2-letter country  
- **timestamp**: `yyyy-MM-dd HH:mm:ss`  

## High-Level Flow
1. Load CSV orders  
2. For each order:
   - Increment counters  
   - Validate order (amount, country, structure)  
   - Persist to in-memory order book  
   - Perform matching using FIFO price–time logic  
3. After matching, generate:
   - Total orders summary  
   - Rejected orders  
   - Trades executed  
   - Filled vs Active orders  
   - Per-trade-type metrics  

## Module & Package Structure

```
com.atyeti.trading.engine
│── model/              # POJOs (Order, Trade, Enums)
│── service/            # OrderService, TradeService, validation, matching
│── repository_InMemory # In-memory repositories
│── concurrency/        # LockManager for thread safety
│── util/               # IdGenerator, CsvOrderReader
│── Main.java           # Entry point
```

## Design Patterns Used
(As requested, excluding Comparator Strategy & Repository Pattern)

### 1. Strategy Pattern
Used for the matching engine:
- `MatchingStrategy` (interface)
- `DefaultMatchingStrategy` (implementation)

Allows easy addition of new matching algorithms (e.g., pro-rata, auction).

### 2. Template Method Pattern
Used in validators:
- `CompositeValidator` executes a chain of:
  - `BasicDataValidator`
  - `CountryValidator`
  - `AmountValidator`

Ensures consistent validation flow.

### 3. Factory Pattern
`IdGenerator` encapsulates logic for Order ID generation.

### 4. Singleton Pattern
`LockManager` ensures a single lock per TradeType for safe concurrency.

## Error Handling
The system throws and handles the following errors:
- `InvalidOrderException`
- `AmountLimitExceededException`
- `CountryNotAllowedException`
- `DuplicateOrderException`
- `MatchingException`

CSV processor ignores invalid lines and continues safely.

## Output Summary (Automatic)
At runtime, the engine prints:

```
=== SUMMARY ===
Total Orders Loaded: XXXXX
Orders Rejected: XXXXX
Valid Orders Processed: XXXXX
Total Trades Executed: XXXXX
FILLED Orders: XXXXX
ACTIVE (Unmatched) Orders: XXXXX

=== MATCHING SUMMARY BY TRADE TYPE ===
EQUITY → Trades Executed: X, Filled Orders: X, Active Orders: X
FOREX  → Trades Executed: X, Filled Orders: X, Active Orders: X
CRYPTO → Trades Executed: X, Filled Orders: X, Active Orders: X
```

## Compliance with Submission Guidelines
✔ Clean, compilable code  
✔ No external DB — fully in-memory  
✔ Well‑commented service & matching classes  
✔ Uses Java naming conventions  
✔ Includes README with setup instructions  
✔ Logic covered through modular components suitable for testing  

## How to Run
1. Place CSV files in `src/main/resources/`  
2. Update file paths in `Main.java`  
3. Run:

```
mvn clean install
java -cp target/classes com.atyeti.trading.engine.Main
```

## Conclusion
This project fulfills all functional and non-functional requirements of the Trading Order Matching Engine assignment, following clean architecture, extensible design, and safe concurrency.
