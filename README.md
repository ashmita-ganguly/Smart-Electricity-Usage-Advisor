# Smart Energy Advisor

A command-line Java application that estimates monthly household electricity consumption and cost from appliance data, ranks appliances by usage, checks the estimated bill against a budget, and generates personalized energy-saving recommendations.

## Overview

Smart Energy Advisor walks the user through a straightforward console workflow: enter a monthly budget and the electricity rate, specify how many appliances to record, enter each appliance's details, and the application takes care of the rest — computing consumption, estimating the bill, ranking appliances, checking the result against the budget, generating saving recommendations, displaying a full report, and saving the session data for later reference.

It requires no smart meters, sensors, or internet connection, and runs entirely offline from the terminal.

### Application Workflow
<img width="1024" height="1536" alt="image" src="https://github.com/user-attachments/assets/b92ba661-159e-466b-9a15-9c93cd49a4dc" />

```
Enter Budget
      |
      v
Enter Electricity Rate
      |
      v
Enter Number of Appliances
      |
      v
Enter Appliance Details
      |
      v
Calculate Monthly Consumption
      |
      v
Calculate Estimated Bill
      |
      v
Rank Appliances
      |
      v
Check Budget
      |
      v
Generate Recommendations
      |
      v
Display Report
      |
      v
Save Data
```

## Features

- Set a monthly budget and electricity rate at the start of each session
- Enter a fixed number of appliances with their wattage and daily usage hours
- Calculate monthly energy consumption (kWh) per appliance and for the whole household
- Calculate the estimated monthly electricity bill from the entered rate
- Rank appliances from highest to lowest consumption
- Check the estimated bill against the entered budget and flag any overage
- Generate rule-based, appliance-specific saving recommendations
- Display a complete, formatted report in the terminal
- Save the session's input and results for later reference

## Technologies / Tools Used

- **Language:** Java (JDK 8+, no external libraries)
- **Build:** Plain `javac` compilation — no Maven/Gradle required
- **Input:** Interactive console input
- **Storage:** Local file output for saved session data
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

```bash
java -cp out energy.Main
```

You will be prompted in order:

```
Enter monthly budget: 2000
Enter electricity rate (per kWh): 7.5
Enter number of appliances: 3

Appliance 1 name: Air Conditioner
  Wattage (W): 1500
  Daily usage (hours): 6

Appliance 2 name: Refrigerator
  Wattage (W): 150
  Daily usage (hours): 24

Appliance 3 name: LED Bulb
  Wattage (W): 10
  Daily usage (hours): 6
```

After the last appliance is entered, the report is calculated, displayed, and saved automatically.

### Convenience script

```bash
./run.sh     # Linux / macOS
run.bat      # Windows
```

## Testing Instructions

1. Compile the project as described above.
2. Run the program and enter a **low budget** (e.g. 1000) with typical appliance data to confirm the report flags the result as over budget with a specific recommendation.
3. Run again with a **high budget** (e.g. 10000) to confirm the report shows the result as within budget.
4. Enter **0 appliances** when prompted and confirm the program handles it gracefully instead of crashing.
5. Enter an **invalid wattage or usage value** (e.g. negative number, or hours above 24) and confirm the program rejects it with a clear message and re-prompts rather than crashing.
6. After a successful run, confirm a data file has been saved (check the working directory for the output file) and that its contents match what was displayed on screen.
7. Re-run the program a second time and confirm the previous save does not interfere with a new session's input.

## Sample Output

```
================================================================
        SMART ENERGY CONSUMPTION REPORT
================================================================
Budget              : 2000.00
Electricity rate    : 7.50 per kWh
Total consumption   : 421.20 kWh
Estimated bill      : 3159.00
Status              : OVER BUDGET by 1159.00

APPLIANCES RANKED BY CONSUMPTION
1. Air Conditioner    270.00 kWh   64.1%
2. Refrigerator       108.00 kWh   25.6%
3. LED Bulb              1.80 kWh    0.4%

RECOMMENDATIONS
1. Air Conditioner accounts for 64% of total usage. Reducing
   daily use by 2 hours would save approximately 90 kWh/month.
2. Raise the Air Conditioner thermostat by 2 degrees to reduce
   consumption by an estimated 10-15%.

Report saved to session_report.txt
================================================================
```

## Screenshots

_Add a terminal screenshot of the input flow and the final report here, e.g.:_

```markdown
![Input flow](screenshots/input-flow.png)
![Sample report](screenshots/report-output.png)
```

## Project Structure

```
.
├── src/energy/
│   ├── Main.java            # CLI entry point and workflow driver
│   ├── Appliance.java       # Appliance model
│   ├── Calculator.java      # Consumption and bill calculations
│   ├── Analyzer.java        # Ranking and budget check
│   ├── Recommender.java     # Saving recommendations
│   ├── ReportPrinter.java   # Report formatting and display
│   └── DataStore.java       # Saves session data to file
├── run.sh / run.bat         # Compile-and-run helpers
└── README.md
```

## License

MIT
