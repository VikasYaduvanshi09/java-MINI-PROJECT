/*_______________________________________________________________________________________________________________________________________________
                                          Welcome
                                            To
                                       ATM Interface
______________________________________________________________________________________________________________________________________
 */

import java.util.*;

class Account {
    private String userId;
    private String userPin;
    private double balance;
    private List<String> transactionHistory;

    public Account(String userId, String userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    // Getters and setters for userId, userPin, balance, and transactionHistory
    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void addToTransactionHistory(String transaction) {
        this.transactionHistory.add(transaction);
    }

    // Other methods if needed (e.g., withdraw, deposit, transfer)
    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            System.out.println("Invalid withdrawal amount.");
        } else {
            balance -= amount;
            addToTransactionHistory("Withdraw: " + amount);
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
        }
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
        } else {
            balance += amount;
            addToTransactionHistory("Deposit: " + amount);
            System.out.println("Deposit successful. New balance: " + balance);
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount <= 0 || amount > balance) {
            System.out.println("Invalid transfer amount.");
        } else {
            balance -= amount;
            recipient.balance += amount;

            addToTransactionHistory("Transfer to " + recipient.getUserId() + ": " + amount);
            recipient.addToTransactionHistory("Transfer from " + getUserId() + ": " + amount);

            System.out.println("Transfer successful. Remaining balance: " + balance);
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Create an account for testing (you can extend this with multiple accounts in real scenarios)
        Account account = new Account("testuser", "1234", 1000.0);

        System.out.println("Welcome to the ATM!");
        System.out.print("Enter your user ID: ");
        String inputUserId = scanner.nextLine();

        System.out.print("Enter your PIN: ");
        String inputPin = scanner.nextLine();

        if (inputUserId.equals(account.getUserId()) && inputPin.equals(account.getUserPin())) {
            System.out.println("Login successful!");
            showMainMenu(account, scanner);
        } else {
            System.out.println("Invalid user ID or PIN. Exiting...");
        }
    }

    public static void showMainMenu(Account account, Scanner scanner) {
        int choice;
        do {
            System.out.println("\nMain Menu");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the integer input

            switch (choice) {
                case 1:
                    showTransactionHistory(account);
                    break;
                case 2:
                    performWithdraw(account, scanner);
                    break;
                case 3:
                    performDeposit(account, scanner);
                    break;
                case 4:
                    performTransfer(account, scanner);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
    }

    public static void showTransactionHistory(Account account) {
        List<String> transactionHistory = account.getTransactionHistory();
        System.out.println("\nTransaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public static void performWithdraw(Account account, Scanner scanner) {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character after reading the double input
        account.withdraw(amount);
    }

    public static void performDeposit(Account account, Scanner scanner) {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character after reading the double input
        account.deposit(amount);
    }

    public static void performTransfer(Account account, Scanner scanner) {
        System.out.print("Enter the recipient's user ID: ");
        String recipientId = scanner.nextLine();

        // In a real scenario, you would have a proper mechanism to find the recipient account
        // For the demo purpose, we'll just use a second account with ID "recipientuser" and PIN "5678"
        Account recipientAccount = new Account("recipientuser", "5678", 0);

        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character after reading the double input

        if (recipientId.equals(recipientAccount.getUserId())) {
            account.transfer(recipientAccount, amount);
        } else {
            System.out.println("Recipient user ID not found.");
        }
    }
}
//==================================================================================================================================================