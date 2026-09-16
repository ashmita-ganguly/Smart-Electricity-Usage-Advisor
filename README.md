# Smart-Electricity-Usage-Advisor
Many households cannot identify which appliances consume the most electricity or how to reduce their usage. This system estimates appliance-wise consumption and cost, identifies high-consuming appliances, tracks monthly budget usage, and provides personalized recommendations to reduce electricity consumption and expenses.

## Features

- Add household appliances with wattage and daily usage hours
- Calculate monthly energy consumption (kWh) per appliance and in total
- Estimate the monthly electricity bill using a configurable tariff rate
- Rank appliances from highest to lowest consumption
- Compare the estimated bill against a monthly budget
- Identify the largest contributors to consumption
- Generate personalized saving recommendations

## Requirements

- Python 3.9 or higher
- pip

## Setup

Clone the repository:

```bash
git clone https://github.com/{github-username}/{repo-name}.git
cd {repo-name}
```

Create and activate a virtual environment:

```bash
python -m venv venv

# Linux / macOS
source venv/bin/activate

# Windows
venv\Scripts\activate
```

Install dependencies:

```bash
pip install -r requirements.txt
```

## Configuration

Tariff rate and default budget are set in `config.py` (or `config.json`):

```python
TARIFF_RATE = 7.50    # cost per kWh in your local currency
MONTHLY_BUDGET = 2000 # default budget, can be overridden at runtime
```

Edit these values to match your electricity provider's rates before running.

## Usage

Run the interactive mode:

```bash
python main.py
```

You will be prompted to enter appliance details one at a time:

```
Appliance name: Refrigerator
Wattage (W): 150
Daily usage (hours): 24
Add another appliance? (y/n): n
Monthly budget: 2000
```

Alternatively, load appliances from a CSV file:

```bash
python main.py --input data/appliances.csv --budget 2000
```

Expected CSV format:

```csv
name,wattage,hours_per_day
Refrigerator,150,24
Air Conditioner,1500,6
LED Bulb,10,8
```

### Command-line options

| Option | Description |
|---|---|
| `--input <path>` | Path to a CSV file of appliances |
| `--budget <amount>` | Monthly budget for comparison |
| `--rate <amount>` | Override the tariff rate per kWh |
| `--output <path>` | Save the generated report to a file |

## Sample Output

```
MONTHLY ENERGY REPORT
---------------------
Total consumption : 421.20 kWh
Estimated bill    : 3159.00
Monthly budget    : 2000.00
Status            : OVER BUDGET by 1159.00

TOP CONSUMERS
1. Air Conditioner   270.00 kWh   (64.1%)
2. Refrigerator      108.00 kWh   (25.6%)
3. LED Bulb            2.40 kWh    (0.6%)

RECOMMENDATIONS
- Air Conditioner accounts for 64% of your usage. Raising the
  thermostat by 2 degrees could reduce this by roughly 10-15%.
- Reducing Air Conditioner use by 2 hours/day would save ~90 kWh
  per month and bring you within budget.
```

## Project Structure

```
.
├── main.py              # CLI entry point
├── config.py            # Tariff rate and default settings
├── src/
│   ├── appliance.py     # Appliance model
│   ├── calculator.py    # Consumption and bill calculations
│   ├── analyzer.py      # Ranking and budget comparison
│   └── recommender.py   # Saving recommendations
├── data/
│   └── appliances.csv   # Sample input
├── requirements.txt
└── README.md
```

## Calculation Method

```
Monthly kWh = (Wattage × Hours per day × 30) ÷ 1000
Bill        = Monthly kWh × Tariff rate
```

