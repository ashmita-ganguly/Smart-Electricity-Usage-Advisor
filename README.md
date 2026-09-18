# Smart Energy Consumption Advisor

A Java-based console application that helps household users **track, analyze, and optimize** their electricity consumption. The system allows users to input appliance details (wattage, daily usage hours), calculates monthly energy consumption and estimated bills, ranks appliances by consumption, compares spending against a budget, and provides personalized energy-saving recommendations.

---

## Features

| Module | Description |
|--------|-------------|
| **Appliance Management** | Add household appliances with name, wattage, and daily usage hours |
| **Energy Calculation** | Computes monthly energy consumption (kWh) using the formula: `(Watts × Hours × 30) / 1000` |
| **Bill Estimation** | Calculates estimated monthly electricity bill based on per-unit rate |
| **Appliance Ranking** | Ranks appliances from highest to lowest energy consumption |
| **Budget Analysis** | Compares estimated bill against user's monthly budget with surplus/deficit reporting |
| **Recommendation Engine** | Generates personalized energy-saving suggestions for high-consumption appliances |
| **Consumption Analyzer** | Classifies consumption (Low/Medium/High/Very High), provides daily/monthly/yearly projections, identifies peak consumers |
| **Data Persistence** | Save appliance data to CSV and load previously saved data |
| **Report Export** | Export full analysis report to a text file |

---

## Technologies & Tools Used

- **Language:** Java (JDK 8 or above)
- **Core Libraries:** `java.util` (Scanner, ArrayList, Collections, Comparator), `java.io` (FileWriter, BufferedReader, FileReader), `java.util.logging` (Logger)
- **Data Storage:** CSV file-based persistence (`data/appliances.csv`)
- **Build:** Manual compilation using `javac` (no external build tool required)
- **Version Control:** Git & GitHub

---

## Project Structure

```
smart-energy-advisor/
├── src/
│   └── smartenergy/
│       ├── Main.java                         # Entry point (menu-driven interface)
│       ├── model/
│       │   └── Appliance.java                # Data model for household appliances
│       ├── service/
│       │   ├── EnergyCalculator.java         # Monthly energy computation
│       │   ├── BillCalculator.java           # Bill estimation
│       │   ├── ApplianceRanking.java         # Sorting by consumption
│       │   ├── BudgetAnalyzer.java           # Budget comparison & analysis
│       │   ├── RecommendationEngine.java     # Energy-saving suggestions
│       │   └── ConsumptionAnalyzer.java      # Advanced analytics & projections
│       ├── report/
│       │   └── ReportGenerator.java          # Formatted report generation
│       └── utility/
│           ├── InputValidator.java           # Input validation & error handling
│           └── FileManager.java              # CSV file I/O & report export
├── test/
│   └── smartenergy/
│       ├── service/
│       │   ├── EnergyCalculatorTest.java     # Energy calculation tests
│       │   ├── BillCalculatorTest.java       # Bill calculation tests
│       │   └── BudgetAnalyzerTest.java       # Budget analysis tests
│       └── model/
│           └── ApplianceTest.java            # Appliance model tests
├── data/                                     # Runtime output directory
│   ├── appliances.csv                        # Saved appliance data
│   └── energy_report.txt                     # Exported report
├── README.md
├── statement.md
└── .gitignore
```

---

## How to Install & Run the Project

### Prerequisites

- **Java Development Kit (JDK) 8** or above must be installed
- **VS Code** with the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) installed (recommended)
- Verify Java installation by opening a terminal and running:
  ```bash
  java -version
  javac -version
  ```

### Step 1: Clone the Repository

```bash
git clone https://github.com/<your-username>/smart-energy-advisor.git
cd smart-energy-advisor
```

### Step 2: Open in VS Code

```bash
code .
```

Or open VS Code → **File → Open Folder** → select the `smart-energy-advisor` folder.

### Step 3: Compile All Source Files

Open the **Terminal** in VS Code by pressing `` Ctrl + ` `` (backtick key), then run:

```bash
javac -d out src/smartenergy/model/Appliance.java src/smartenergy/service/*.java src/smartenergy/utility/*.java src/smartenergy/report/ReportGenerator.java src/smartenergy/Main.java
```

This compiles all Java files and places the `.class` files in the `out/` directory.

### Step 4: Run the Application

#### Method 1: Using VS Code Terminal (Recommended)

In the same VS Code terminal, run:

```bash
java -cp out smartenergy.Main
```

> **Important:** Make sure you are typing in the **TERMINAL** tab at the bottom of VS Code (not the "Debug Console" or "Output" tab). The Terminal tab supports keyboard input; the Debug Console does not.

#### Method 2: Using VS Code Debugger (F5)

1. The project includes a `.vscode/launch.json` configuration file
2. Press **F5** or go to **Run → Start Debugging**
3. Select **"Smart Energy Advisor"** from the dropdown if prompted
4. The program will start in the **integrated terminal** where you can type inputs

> **Note:** If input is not working when using F5, make sure `launch.json` contains `"console": "integratedTerminal"`. This is already configured in the project.

#### Method 3: Using External Command Prompt

If VS Code terminal still has issues, open **Windows Command Prompt** or **PowerShell** directly:

1. Press `Win + R`, type `cmd`, press Enter
2. Navigate to the project folder:
   ```bash
   cd "C:\Users\techa\OneDrive\Desktop\smart energy advisor"
   ```
3. Run the program:
   ```bash
   java -cp out smartenergy.Main
   ```

### Step 5: Using the Application

When the application starts, you will see a welcome banner and be asked to enter:
1. **Monthly electricity budget** (in Rs)
2. **Electricity rate per unit** (in Rs/kWh)

Then you will see the main menu:

```
==================== MENU ====================
  1. Add Appliances
  2. View Energy Report
  3. View Appliance Ranking
  4. View Budget Analysis
  5. View Energy Saving Recommendations
  6. View Detailed Consumption Analysis
  7. Load Previously Saved Data
  8. Save Data & Export Report
  9. Exit
================================================
```

Type a number (1-9) and press **Enter** to select an option.

### Troubleshooting: Input Not Working

If you type a number but pressing Enter does nothing:

| Problem | Solution |
|---------|----------|
| Using **Debug Console** tab | Switch to the **Terminal** tab at the bottom of VS Code |
| VS Code terminal frozen | Open an external Command Prompt and run `java -cp out smartenergy.Main` |
| Program not compiled | Run the `javac` compile command from Step 3 first |

---

## How to Run Tests

### Step 1: Compile Test Files

```bash
javac -d out -cp out test/smartenergy/model/ApplianceTest.java test/smartenergy/service/EnergyCalculatorTest.java test/smartenergy/service/BillCalculatorTest.java test/smartenergy/service/BudgetAnalyzerTest.java
```

### Step 2: Run Each Test

```bash
java -cp out smartenergy.model.ApplianceTest
java -cp out smartenergy.service.EnergyCalculatorTest
java -cp out smartenergy.service.BillCalculatorTest
java -cp out smartenergy.service.BudgetAnalyzerTest
```

## Sample Input / Output
<img width="1207" height="866" alt="image" src="https://github.com/user-attachments/assets/91d2c33d-6e3b-4c24-ba50-4a42a6004fc8" />
<img width="1175" height="860" alt="Screenshot 2026-09-18 084352" src="https://github.com/user-attachments/assets/05142c3e-341d-46aa-9963-34b300def465" />
<img width="1168" height="892" alt="Screenshot 2026-09-18 084436" src="https://github.com/user-attachments/assets/898f9a8c-b824-4675-ae8d-b0bfd4580412" />
<img width="1181" height="882" alt="Screenshot 2026-09-18 084548" src="https://github.com/user-attachments/assets/0c16a7bd-78e0-465a-be93-35d48ffb412c" />
<img width="1170" height="872" alt="Screenshot 2026-09-18 084628" src="https://github.com/user-attachments/assets/f89a9156-b46e-4a08-b97e-acaa48ed1ae6" />
<img width="1158" height="875" alt="Screenshot 2026-09-18 084729" src="https://github.com/user-attachments/assets/df29a2a1-406f-4c93-9ac3-f4d9285f88bf" />


## Non-Functional Requirements

| Requirement | Implementation |
|-------------|---------------|
| **Performance** | O(n log n) sorting via `Collections.sort()`, single-pass calculations |
| **Usability** | Menu-driven interface, clear prompts, formatted tabular reports |
| **Reliability** | Input validation with retry loops prevents runtime crashes |
| **Error Handling** | `try-catch` blocks for file I/O and `NumberFormatException` |
| **Maintainability** | Modular 4-package architecture with separation of concerns |
| **Scalability** | `ArrayList` for dynamic sizing, no hardcoded limits |

---

## Future Enhancements

- Graphical User Interface (GUI) using JavaFX or Swing
- Database integration (MySQL/SQLite) for persistent storage
- Multi-user support with login authentication
- Time-of-Use (ToU) tariff calculations
- Appliance scheduling and automation suggestions
- Comparison with national/state energy consumption averages
- Integration with smart meter APIs for real-time data

---

## Author

**Ashmita Ganguly**  
VIT Bhopal University

---

## License

This project is developed for academic purposes as part of the VITyarthi coursework.
