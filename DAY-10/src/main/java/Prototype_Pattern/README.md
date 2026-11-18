# Prototype Design Pattern Demonstration

A complete, beginner-friendly project demonstrating the **Prototype Design Pattern** in Java, structured into two
separate folders: `Without` and `With`.

---

## 🎮 Real-World Analogy: Game Character / Monster Spawner

In a gaming engine, spawning characters (Warriors, Mages, Goblins) requires loading expensive 3D polygon meshes,
high-resolution textures, audio clips, and calculating baseline statistics:

- **Without Prototype**: Every time an enemy or NPC is spawned with `new Enemy(...)`, the system reloads heavy textures
  and sound files from disk. If 1,000 enemies spawn on a battlefield, loading time is disastrous. Moreover, attempting
  to copy objects manually leads to the notorious **Shallow Copy Bug**, where changing one character's weapon or skill
  accidentally mutates all other characters because they share the same list reference in memory!
- **With Prototype**: The game loads a master prototype template **once** into memory (`CharacterRegistry`). When new
  characters are needed, they are created in **sub-milliseconds** by cloning the prototype. A proper **Deep Copy**
  ensures mutable data (like skill inventories) is independent, while heavy immutable assets (3D meshes) are shared
  safely.

---

## 📁 Project Structure

```
Prototype_Pattern/
├── Without/
│   ├── GameCharacter.java      # Object with heavy constructor; no clone support
│   └── Main.java               # Demonstrates slow creation & shallow copy mutation bug
│
├── With/
│   ├── Prototype.java          # Generic Prototype interface declaring clonePrototype()
│   ├── GameCharacter.java      # Abstract base prototype with copy constructor (Deep Copy)
│   ├── Warrior.java            # Concrete prototype for melee characters
│   ├── Mage.java               # Concrete prototype for spellcasters
│   ├── CharacterRegistry.java  # Prototype Cache / Registry managing master templates
│   └── Main.java               # Demonstrates sub-millisecond cloning & deep copy isolation
└── README.md
```

---

## 📊 Comparison: Without vs With Prototype

| Feature                   | Without Prototype (`Without/`)                                        | With Prototype (`With/`)                                                 |
|:--------------------------|:----------------------------------------------------------------------|:-------------------------------------------------------------------------|
| **Object Creation Time**  | **Slow**: Re-runs heavy disk I/O and asset loading every time         | **Instant ($< 1$ ms)**: Duplicates memory directly via copy constructor  |
| **Encapsulation**         | **Broken**: Client must know internal parameters to duplicate         | **Preserved**: Object handles its own duplication via `clonePrototype()` |
| **Copy Safety**           | **Buggy (Shallow Copy)**: Clones share mutable lists/arrays in memory | **Safe (Deep Copy)**: Mutable collections are independently duplicated   |
| **Resource Optimization** | Duplicates heavy 3D asset memory repeatedly                           | Safely shares immutable asset pointers while isolating state             |
| **Management**            | Cluttered `new` statements across client code                         | Centralized `CharacterRegistry` for template catalog                     |

---

## 🚀 How to Run

From `C:\Users\TrueID\Downloads\Training Period\DAY-10`:

### 1. Run the "Without Prototype" Demonstration

```bash
javac -d target/classes src/main/java/Prototype_Pattern/Without/*.java
java -cp target/classes Prototype_Pattern.Without.Main
```

### 2. Run the "With Prototype" Demonstration

```bash
javac -d target/classes src/main/java/Prototype_Pattern/With/*.java
java -cp target/classes Prototype_Pattern.With.Main
```
