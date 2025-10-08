# 🏦 Banking Transaction Processor (Console App)

### 💡 Overview
The **Banking Transaction Processor** is a **Core Java 8+** console-based application simulating a banking system capable of handling **concurrent deposits, withdrawals, and transfers** between accounts. It demonstrates proficiency in **Core Java**, **OOP**, **Collections**, **Multithreading**, **Concurrency**, and **Data Structures & Algorithms**.

---

## 🚀 Features

- 🧾 Manage multiple bank accounts
- 💰 Handle **deposit**, **withdraw**, and **transfer** transactions
- ⚙️ Process transactions **concurrently using multiple threads**
- 🔒 Ensure **thread safety** with `ReentrantLock`
- 🧠 Use **Queues** for pending transactions
- 🧮 Generate **transaction history** and **daily summary reports**
- 💬 Leverage **Streams**, **Optionals**, and **Method References** for modern Java coding style
- 🧩 Fully in-memory (no database required)
- 📈 Easily extendable to a **Spring Boot microservice** in the future

---

## 🧰 Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java 17 (compatible with 8+) |
| Build Tool | Maven |
| Concurrency | ReentrantLock, ExecutorService, BlockingQueue |
| Collections | Map, List, Queue |
| Logging | SLF4J |
| Testing | JUnit 5 |

---

## 📂 Project Structure

```
banking-transaction-processor/
 ┣ pom.xml
 ┣ README.md
 ┣ src/
 ┃ ┣ main/
 ┃ ┃ ┣ java/
 ┃ ┃ ┃ ┗ com/bank/
 ┃ ┃ ┃   ┣ model/
 ┃ ┃ ┃   ┃ ┣ Account.java
 ┃ ┃ ┃   ┃ ┗ Transaction.java
 ┃ ┃ ┃   ┣ service/
 ┃ ┃ ┃   ┃ ┣ TransactionProcessor.java
 ┃ ┃ ┃   ┃ ┗ TransactionQueue.java
 ┃ ┃ ┃   ┣ util/
 ┃ ┃ ┃   ┃ ┗ ReportGenerator.java
 ┃ ┃ ┃   ┗ Main.java
 ┃ ┗ resources/
 ┃     ┗ transactions.log (auto-generated)
 ┗ src/test/
     ┗ java/
         ┗ com/bank/
             ┗ TransactionProcessorTest.java
```

---

## ⚙️ Concurrency Design

- Each `Account` uses a **`ReentrantLock`** for thread-safe balance updates.
- `TransactionProcessor` employs an **`ExecutorService`** to process multiple transactions concurrently.
- A **`BlockingQueue<Transaction>`** holds pending transactions.
- Thread safety ensures consistency even under simultaneous transfers.

---

## 🧩 Usage

1. Clone the repository:
```bash
git clone <repository_url>
```

2. Navigate into the project directory:
```bash
cd banking-transaction-processor
```

3. Build the project using Maven:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn exec:java -Dexec.mainClass="com.bank.Main"
```

The application simulates multiple transactions being processed concurrently and generates a transaction summary in the console.

---

## 📈 Future Enhancements

- Add **Spring Boot REST APIs** to expose account and transaction endpoints
- Integrate **MySQL/PostgreSQL** for persistent storage
- Add a **web-based dashboard** for live transaction monitoring
- Support **scheduled reports** and **export to CSV/PDF**

---

## 📝 Author
- Vamshi Nallavalli | Java Developer | https://github.com/thevamshikrishna

---

## 📄 License
This project is licensed under the MIT License.