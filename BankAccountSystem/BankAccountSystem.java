import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountSystem {

    // ─── Account Class ───────────────────────────────────────────────────────────
    static class BankAccount {
        private String accountNumber;
        private String accountHolder;
        private double balance;

        public BankAccount(String accountNumber, String accountHolder, double initialDeposit) {
            this.accountNumber = accountNumber;
            this.accountHolder = accountHolder;
            this.balance = initialDeposit;
        }

        // Getters
        public String getAccountNumber() { return accountNumber; }
        public String getAccountHolder()  { return accountHolder; }
        public double getBalance()         { return balance; }

        // Deposit
        public boolean deposit(double amount) {
            if (amount <= 0) {
                System.out.println("  ✗ Deposit amount must be greater than zero.");
                return false;
            }
            balance += amount;
            System.out.printf("  ✓ Deposited $%.2f successfully. New balance: $%.2f%n", amount, balance);
            return true;
        }

        // Withdrawal
        public boolean withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("  ✗ Withdrawal amount must be greater than zero.");
                return false;
            }
            if (amount > balance) {
                System.out.printf("  ✗ Insufficient funds. Available balance: $%.2f%n", balance);
                return false;
            }
            balance -= amount;
            System.out.printf("  ✓ Withdrew $%.2f successfully. New balance: $%.2f%n", amount, balance);
            return true;
        }

        // Display account info
        public void displayInfo() {
            System.out.println("  ┌─────────────────────────────────┐");
            System.out.printf( "  │ Account Number : %-16s│%n", accountNumber);
            System.out.printf( "  │ Account Holder : %-16s│%n", accountHolder);
            System.out.printf( "  │ Balance        : $%-15.2f│%n", balance);
            System.out.println("  └─────────────────────────────────┘");
        }
    }

    // ─── Helper: generate account number ─────────────────────────────────────────
    static int accountCounter = 1001;
    static String generateAccountNumber() {
        return "ACC" + (accountCounter++);
    }

    // ─── Helper: read a valid double ──────────────────────────────────────────────
    static double readAmount(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                double val = Double.parseDouble(input);
                return val;
            } catch (NumberFormatException e) {
                System.out.println("  ✗ Invalid amount. Please enter a numeric value.");
            }
        }
    }

    // ─── Find account by number ───────────────────────────────────────────────────
    static BankAccount findAccount(ArrayList<BankAccount> accounts, String accNum) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equalsIgnoreCase(accNum)) return acc;
        }
        return null;
    }

    // ─── Main ─────────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   Bank Account Management System     ║");
        System.out.println("╚══════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            System.out.println("\n─── Main Menu ──────────────────────────");
            System.out.println("  1. Create New Account");
            System.out.println("  2. Deposit");
            System.out.println("  3. Withdraw");
            System.out.println("  4. Check Balance");
            System.out.println("  5. View All Accounts");
            System.out.println("  6. Exit");
            System.out.print("  Choose an option (1-6): ");

            String choice = sc.nextLine().trim();

            switch (choice) {

                // ── Create Account ──────────────────────────────────────────────
                case "1":
                    System.out.println("\n[ Create New Account ]");
                    System.out.print("  Enter account holder name: ");
                    String name = sc.nextLine().trim();

                    if (name.isEmpty()) {
                        System.out.println("  ✗ Name cannot be empty.");
                        break;
                    }

                    double initialDeposit = readAmount(sc, "  Enter initial deposit amount: $");
                    if (initialDeposit < 0) {
                        System.out.println("  ✗ Initial deposit cannot be negative.");
                        break;
                    }

                    String accNum = generateAccountNumber();
                    BankAccount newAcc = new BankAccount(accNum, name, initialDeposit);
                    accounts.add(newAcc);
                    System.out.println("  ✓ Account created successfully!");
                    newAcc.displayInfo();
                    break;

                // ── Deposit ─────────────────────────────────────────────────────
                case "2":
                    System.out.println("\n[ Deposit ]");
                    if (accounts.isEmpty()) { System.out.println("  ✗ No accounts found."); break; }
                    System.out.print("  Enter account number: ");
                    String depAcc = sc.nextLine().trim();
                    BankAccount depAccount = findAccount(accounts, depAcc);
                    if (depAccount == null) { System.out.println("  ✗ Account not found."); break; }
                    double depAmount = readAmount(sc, "  Enter deposit amount: $");
                    depAccount.deposit(depAmount);
                    break;

                // ── Withdraw ────────────────────────────────────────────────────
                case "3":
                    System.out.println("\n[ Withdraw ]");
                    if (accounts.isEmpty()) { System.out.println("  ✗ No accounts found."); break; }
                    System.out.print("  Enter account number: ");
                    String wdAcc = sc.nextLine().trim();
                    BankAccount wdAccount = findAccount(accounts, wdAcc);
                    if (wdAccount == null) { System.out.println("  ✗ Account not found."); break; }
                    double wdAmount = readAmount(sc, "  Enter withdrawal amount: $");
                    wdAccount.withdraw(wdAmount);
                    break;

                // ── Check Balance ───────────────────────────────────────────────
                case "4":
                    System.out.println("\n[ Check Balance ]");
                    if (accounts.isEmpty()) { System.out.println("  ✗ No accounts found."); break; }
                    System.out.print("  Enter account number: ");
                    String balAcc = sc.nextLine().trim();
                    BankAccount balAccount = findAccount(accounts, balAcc);
                    if (balAccount == null) { System.out.println("  ✗ Account not found."); break; }
                    balAccount.displayInfo();
                    break;

                // ── View All Accounts ───────────────────────────────────────────
                case "5":
                    System.out.println("\n[ All Accounts ]");
                    if (accounts.isEmpty()) {
                        System.out.println("  No accounts exist yet.");
                    } else {
                        for (BankAccount acc : accounts) {
                            acc.displayInfo();
                            System.out.println();
                        }
                    }
                    break;

                // ── Exit ────────────────────────────────────────────────────────
                case "6":
                    System.out.println("\n  Thank you for using the Bank Account Management System. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("  ✗ Invalid option. Please enter a number between 1 and 6.");
            }
        }

        sc.close();
    }
}
