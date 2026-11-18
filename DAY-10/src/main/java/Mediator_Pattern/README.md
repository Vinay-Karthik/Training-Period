# Mediator Design Pattern Demonstration

A complete, beginner-friendly project demonstrating the **Mediator Design Pattern** in Java, structured into two
separate folders: `Without` and `With`.

---

## 🛫 Real-World Analogy: Airport Air Traffic Control

Imagine an airport with multiple flights.

- **Without a Mediator (Air Traffic Control Tower)**: Every airplane would have to radio every single other airplane in
  the sky to ask if the runway is clear. If there are 10 planes, that requires $10 \times 9 = 90$ direct communication
  channels! Adding a new airplane means updating every existing airplane with its contact frequency.
- **With a Mediator**: Airplanes communicate **only** with the central **Control Tower** (`AirTrafficControlMediator`).
  The tower decides who lands, who waits in a holding pattern, and notifies other flights. The airplanes do not know
  each other directly.

---

## 📁 Project Structure

```
Mediator_Pattern/
├── Without/                             # The Anti-Pattern (Mesh Network)
│   ├── Airplane.java                   # Colleague holding direct references to all peers
│   └── Main.java                       # Demonstration of tight coupling & O(N^2) complexity
│
├── With/                                # The Mediator Pattern (Star Topology)
│   ├── AirTrafficControlMediator.java  # Mediator Interface
│   ├── ControlTower.java               # Concrete Mediator (manages runway & queue)
│   ├── Airplane.java                   # Abstract Colleague (holds reference to Mediator only)
│   ├── CommercialAirplane.java         # Concrete Colleague (Passenger flight)
│   ├── CargoAirplane.java              # Concrete Colleague (Freight flight)
│   └── Main.java                       # Demonstration of loose coupling & centralized control
└── README.md
```

---

## 📊 Comparison: Without vs With Mediator

| Feature                     | Without Mediator (`Without/`)                                 | With Mediator (`With/`)                                 |
|:----------------------------|:--------------------------------------------------------------|:--------------------------------------------------------|
| **Topology**                | Many-to-Many Mesh ($N \times (N-1)$ connections)              | One-to-Many Star ($N$ connections to Mediator)          |
| **Coupling**                | **Tight**: Each airplane knows all other airplanes            | **Loose**: Airplanes only know the `ControlTower`       |
| **Adding New Colleague**    | Must manually link to all existing airplanes ($O(N)$ updates) | Just register with the mediator ($O(1)$ update)         |
| **Responsibility**          | Airplanes handle flight logic **and** traffic coordination    | Airplanes focus on flying; Mediator coordinates traffic |
| **Runway Queue Management** | Dispersed and prone to race conditions / collisions           | Centralized, orderly FIFO holding pattern queue         |

---

## 🚀 How to Run

### 1. Run the "Without Mediator" Demonstration

```bash
mvn compile
java -cp target/classes Mediator_Pattern.Without.Main
```

### 2. Run the "With Mediator" Demonstration

```bash
mvn compile
java -cp target/classes Mediator_Pattern.With.Main
```
