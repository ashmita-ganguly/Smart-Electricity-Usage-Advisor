package smartenergy.utility;

import smartenergy.model.Appliance;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles file I/O operations for the application.
 * Supports saving/loading appliance data in CSV format
 * and exporting full reports to text files.
 *
 * @author Adhishree Singh
 * @version 2.0
 */
public class FileManager {

    private static final String DATA_DIR = "data";
    private static final String CSV_FILE = DATA_DIR + "/appliances.csv";
    private static final String REPORT_FILE = DATA_DIR + "/energy_report.txt";

    /**
     * Saves the list of appliances to a CSV file.
     * Creates the data directory if it doesn't exist.
     *
     * @param appliances the list of appliances to save
     */
    public void saveAppliances(
            ArrayList<Appliance> appliances) {

        try {

            File folder = new File(DATA_DIR);

            if (!folder.exists()) {
                folder.mkdir();
            }

            FileWriter writer =
                    new FileWriter(CSV_FILE);

            writer.write(
                    "Name,Wattage,DailyHours,MonthlyUnits,MonthlyCost\n");

            for (Appliance a : appliances) {

                writer.write(
                        a.getName() + ","
                        + a.getWattage() + ","
                        + a.getDailyHours() + ","
                        + a.getMonthlyUnits() + ","
                        + a.getMonthlyCost() + "\n");
            }

            writer.close();

            System.out.println(
                    "\nAppliance data saved to " + CSV_FILE);

        } catch (IOException e) {

            System.out.println(
                    "Error while saving appliance data: "
                    + e.getMessage());
        }
    }

    /**
     * Loads appliance data from the CSV file.
     * Returns an empty list if the file doesn't exist or an error occurs.
     *
     * @return ArrayList of Appliance objects loaded from the file
     */
    public ArrayList<Appliance> loadAppliances() {

        ArrayList<Appliance> appliances = new ArrayList<>();

        File file = new File(CSV_FILE);

        if (!file.exists()) {
            System.out.println("No saved data found.");
            return appliances;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(new FileReader(CSV_FILE));

            String line = reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length >= 4) {

                    String name = parts[0].trim();
                    double wattage = Double.parseDouble(parts[1].trim());
                    double hours = Double.parseDouble(parts[2].trim());

                    Appliance a = new Appliance(name, wattage, hours);

                    a.setMonthlyUnits(
                            Double.parseDouble(parts[3].trim()));

                    if (parts.length >= 5) {
                        a.setMonthlyCost(
                                Double.parseDouble(parts[4].trim()));
                    }

                    appliances.add(a);
                }
            }

            reader.close();

            System.out.println(
                    "Loaded " + appliances.size()
                    + " appliances from " + CSV_FILE);

        } catch (IOException e) {

            System.out.println(
                    "Error while loading appliance data: "
                    + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error: CSV file contains invalid data.");
        }

        return appliances;
    }

    /**
     * Exports a report string to a text file.
     *
     * @param reportContent the full report content as a string
     */
    public void saveReport(String reportContent) {

        try {

            File folder = new File(DATA_DIR);

            if (!folder.exists()) {
                folder.mkdir();
            }

            FileWriter writer =
                    new FileWriter(REPORT_FILE);

            writer.write(reportContent);
            writer.close();

            System.out.println(
                    "Report saved to " + REPORT_FILE);

        } catch (IOException e) {

            System.out.println(
                    "Error while saving report: "
                    + e.getMessage());
        }
    }
}
