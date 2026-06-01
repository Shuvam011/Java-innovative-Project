import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Expense implements Serializable {

    double amount;
    String category;
    LocalDate date;

    Expense(double amount, String category, LocalDate date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String toString() {
        return "Amount: " + amount +
                ", Category: " + category +
                ", Date: " + date;
    }
}

public class ExpenseTracker {

    static ArrayList<Expense> expenses = new ArrayList<>();

    // Add Expense
    public static void addExpense(Scanner sc) {

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String dateInput = sc.nextLine();

        LocalDate date = LocalDate.parse(dateInput);

        expenses.add(new Expense(amount, category, date));

        System.out.println("Expense Added Successfully!\n");
    }

    // Display Expenses
    public static void displayExpenses() {

        System.out.println("\n===== Expense List =====");

        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    // Monthly Report
    public static void monthlyReport() {

        double total = 0;

        for (Expense e : expenses) {
            total += e.amount;
        }

        System.out.println("\nTotal Monthly Expense: " + total);
    }

    // Highest Expense Category
    public static void highestCategory() {

        double max = 0;
        String category = "";

        for (Expense e : expenses) {

            if (e.amount > max) {
                max = e.amount;
                category = e.category;
            }
        }

        System.out.println("\nHighest Expense Category: " +
                category + " (₹" + max + ")");
    }

    // Save Data
    public static void saveData() {

        try {
            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream("expenses.dat"));

            out.writeObject(expenses);

            out.close();

            System.out.println("Data Saved Successfully!");

        } catch (Exception e) {
            System.out.println("Error Saving File");
        }
    }

    // Load Data
    public static void loadData() {

        try {
            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream("expenses.dat"));

            expenses = (ArrayList<Expense>) in.readObject();

            in.close();

            System.out.println("Data Loaded Successfully!");

        } catch (Exception e) {
            System.out.println("No Previous Data Found");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        loadData();

        int choice;

        do {

            System.out.println("\n===== Personal Expense Tracker =====");
            System.out.println("1. Add Expense");
            System.out.println("2. Display Expenses");
            System.out.println("3. Monthly Report");
            System.out.println("4. Highest Expense Category");
            System.out.println("5. Save Data");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addExpense(sc);
                    break;

                case 2:
                    displayExpenses();
                    break;

                case 3:
                    monthlyReport();
                    break;

                case 4:
                    highestCategory();
                    break;

                case 5:
                    saveData();
                    break;

                case 6:
                    saveData();
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 6);

        sc.close();
    }
}
