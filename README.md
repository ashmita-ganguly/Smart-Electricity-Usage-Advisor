# Smart Energy Advisor

A command-line Java application that estimates monthly household electricity consumption and cost from appliance data, ranks appliances by usage, compares the projected bill against a budget, and generates personalized energy-saving recommendations.

## Overview

Most households know their appliance wattage and roughly how many hours a day each one runs, but have no easy way to turn that into a cost breakdown. Smart Energy Advisor takes that basic information — appliance name, wattage, daily usage hours, and quantity — and walks it through a full pipeline: monthly energy consumption → estimated electricity bill → ranking by consumption → budget comparison → identification of major consumption sources → personalized saving recommendations.

The tool requires no smart meters, sensors, or internet connection. It runs entirely offline from the terminal and produces a plain-text report that can be viewed on screen or saved to a file.

## Features

- Add appliances interactively via console prompts, or load them in bulk from a CSV file
- Calculate monthly energy consumption (kWh) per appliance and for the whole household
- Estimate the monthly electricity bill using a configurable tariff rate
- Rank appliances from highest to lowest consumption
- Compare the estimated bill against a user-defined monthly budget
- Automatically identify the appliances responsible for the bulk of total consumption
- Generate rule-based, appliance-specific saving recommendations (not generic tips)
- Save the generated report to a file for later reference

## Technologies / Tools Used

- **Language:** Java (JDK 8+, no external libraries)
- **Build:** Plain `javac` compilation — no Maven/Gradle required
- **Input formats:** Interactive console input, CSV files
- **Configuration:** `.properties` file for tariff rate, budget, and billing period
- **Tested on:** OpenJDK 21, should work on JDK 8 and above

## Installation & Setup

### Prerequisites

- JDK 8 or higher installed and available on your `PATH`

Verify with:

```bash
java -version
javac -version
```

### Clone the repository

```bash
git clone https://github.com/{github-username}/{repo-name}.git
cd {repo-name}
```

### Compile

```bash
mkdir -p out
javac -d out src/energy/*.java
```

On Windows:

```cmd
mkdir out
javac -d out src\energy\*.java
```

## Running the Project

### Interactive mode

```bash
java -cp out energy.Main
```

Follow the prompts to enter each appliance's name, wattage, daily usage hours, and quantity. Press Enter on a blank name to finish, then enter your monthly budget.

### CSV mode

```bash
java -cp out energy.Main --input data/appliances.csv --budget 2000
```

### Save the report to a file

```bash
java -cp out energy.Main --input data/appliances.csv --budget 2000 --output report.txt
```

### Convenience scripts

```bash
./run.sh --input data/appliances.csv --budget 2000   # Linux / macOS
run.bat --input data\appliances.csv --budget 2000    # Windows
```

### Command-line options

| Option | Description |
|---|---|
| `-i`, `--input <path>` | Read appliances from a CSV file |
| `-b`, `--budget <amt>` | Monthly budget for comparison |
| `-r`, `--rate <amt>` | Tariff rate per kWh (overrides config) |
| `-d`, `--days <n>` | Days in the billing period (default 30) |
| `-o`, `--output <path>` | Save the report to a file |
| `-h`, `--help` | Show usage information |

### Configuration

Defaults are read from `config.properties`:

```properties
tariff.rate=7.50
monthly.budget=2000
currency=INR
days.in.month=30
```

### Input file format

```csv
name,wattage,hours_per_day,quantity
Air Conditioner,1500,6,1
Refrigerator,150,24,1
LED Bulb,10,6,8
```

The `quantity` column is optional and defaults to 1.

## Testing Instructions

A sample dataset is provided at `data/appliances.csv` for quick verification.

1. Compile the project as described above.
2. Run with the sample data and a low budget to test the **over-budget** path:
   ```bash
   java -cp out energy.Main --input data/appliances.csv --budget 1000
   ```
   Expect the status line to read `OVER BUDGET` along with a recommendation quantifying the required reduction.
3. Run again with a high budget to test the **within-budget** path:
   ```bash
   java -cp out energy.Main --input data/appliances.csv --budget 10000
   ```
   Expect the status line to read `WITHIN BUDGET`.
4. Test interactive mode by running `java -cp out energy.Main` with no arguments and entering a couple of appliances manually.
5. Test invalid input handling: enter a wattage of `0` or usage hours above `24` — the appliance should be rejected with a validation message instead of crashing the program.
6. Test malformed CSV handling: add a row to a copy of the CSV with a missing column and confirm it is skipped with a warning while the rest of the file still loads.
7. Confirm the `--output` flag writes a copy of the report to disk, e.g.:
   ```bash
   java -cp out energy.Main --input data/appliances.csv --output report.txt
   cat report.txt
   ```

## Sample Output

```
================================================================
        SMART ENERGY CONSUMPTION REPORT
================================================================
Total consumption   : 614.10 kWh
Estimated bill      : 4605.75 INR
Monthly budget      : 2000.00 INR
Status              : OVER BUDGET by 2605.75 INR

APPLIANCES RANKED BY CONSUMPTION
1. Air Conditioner    270.00 kWh   44.0%
2. Refrigerator       108.00 kWh   17.6%
3. Ceiling Fan          67.50 kWh   11.0%

MAJOR SOURCES OF CONSUMPTION
Air Conditioner, Refrigerator, Ceiling Fan (72.5% of total usage)

PERSONALIZED SAVING RECOMMENDATIONS
1. Reducing Air Conditioner alone will not close the budget gap;
   a cut of about 347 kWh is needed across appliances.
2. Raise the Air Conditioner thermostat to 24-26 C — could save
   roughly 405 INR per month.
================================================================
```

## Screenshots

_Add a terminal screenshot of the interactive input flow and the final report here, e.g.:_

```markdown
![Interactive input](screenshots/interactive-input.png)
![Sample report](screenshots/report-output.png)
```

## Project Structure

```
.
├── src/energy/
│   ├── Main.java            # CLI entry point
│   ├── Appliance.java       # Appliance model
│   ├── ApplianceUsage.java  # Computed consumption record
│   ├── Calculator.java      # Consumption and bill calculations
│   ├── Analyzer.java        # Ranking, budget comparison, major sources
│   ├── Recommender.java     # Saving recommendations
│   ├── ReportPrinter.java   # Report formatting
│   ├── CsvLoader.java       # CSV parsing
│   └── Config.java          # Configuration loading
├── data/appliances.csv      # Sample input
├── config.properties        # Tariff rate and defaults
├── run.sh / run.bat         # Compile-and-run helpers
└── README.md
```
