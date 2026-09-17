# Problem Statement

Household electricity consumption in India has been rising steadily, yet most consumers lack awareness of which appliances contribute most to their electricity bills. Without visibility into per-appliance energy usage, users cannot make informed decisions about reducing consumption or staying within their monthly budgets.

**The Smart Energy Consumption Advisor** addresses this problem by providing a console-based tool that allows users to input their household appliances, analyze per-appliance and total energy consumption, compare estimated bills against a monthly budget, and receive personalized recommendations for reducing electricity usage.

---

## Scope of the Project

This project focuses on:

1. **Per-appliance energy tracking** — Users enter appliance name, wattage, and daily usage hours. The system computes monthly kWh consumption using the standard formula.

2. **Bill estimation** — Based on a user-provided electricity rate (Rs/kWh), the system estimates the total monthly electricity bill and cost per appliance.

3. **Consumption analysis** — Appliances are ranked by consumption, classified into categories (Low/Medium/High/Very High), and analyzed with daily/monthly/yearly projections.

4. **Budget comparison** — The system compares the estimated bill against the user's monthly budget and provides surplus/deficit feedback.

5. **Energy-saving recommendations** — Personalized suggestions are generated for appliances that consume a disproportionate share of total energy.

6. **Data persistence** — Appliance data can be saved to and loaded from CSV files. Full reports can be exported to text files.

### Out of Scope

- Real-time smart meter integration
- Multi-user authentication
- GUI / web interface
- Database storage (uses CSV files)

---

## Target Users

- **Household consumers** who want to understand and reduce their electricity bills
- **Students and researchers** studying energy consumption patterns
- **Budget-conscious families** looking to optimize appliance usage to stay within budget
- **Energy auditors** performing basic residential energy assessments

---

## High-Level Features

1. **Appliance Management Module**
   - Add multiple appliances with wattage and daily usage
   - Load previously saved appliance data from CSV files
   - Dynamic list with no hardcoded size limits

2. **Energy Calculation & Bill Estimation Module**
   - Monthly energy consumption calculation per appliance
   - Total household consumption aggregation
   - Bill estimation based on configurable per-unit rate
   - Percentage share breakdown of each appliance

3. **Analysis & Recommendation Module**
   - Appliance ranking by energy consumption
   - Budget comparison with over/under analysis
   - Consumption category classification
   - Daily, monthly, and yearly consumption projections
   - Peak consumer identification
   - Personalized energy-saving recommendations

4. **Reporting & Data Persistence Module**
   - Formatted console report with tabular output
   - Report export to text file
   - CSV save/load for appliance data
   - Final summary on exit
