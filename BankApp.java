import java.util.Scanner;

class Account {
    String accountNumber;
    double balance;
    String[] transactions = new String[50];
    int transactionCount = 0;

    // Constructor
    public Account(String accNo, double initialBalance) {
        this.accountNumber = accNo;
        this.balance = initialBalance;
        addTransaction("Account created with balance: " + initialBalance);
    }

    // Save a text line into transaction history
    private void addTransaction(String text) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount++] = text;
        }
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposited: " + amount + " | Balance: " + balance);
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Please enter a positive amount to deposit.");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Enter a positive amount to withdraw.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            addTransaction("Withdrew: " + amount + " | Balance: " + balance);
            System.out.println("Withdrew: " + amount);
        }
    }

    // Display current balance
    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }

    // Print all transactions
    public void printTransactions() {
        if (transactionCount == 0) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("Transaction History:");
        for (int i = 0; i < transactionCount; i++) {
            System.out.println((i + 1) + ". " + transactions[i]);
        }
    }
}

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1) Get basic account info from user
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double initialBalance = 0;
        try {
            initialBalance = sc.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid number. Setting initial balance to 0.");
            sc.nextLine(); // clear buffer if needed
        }

        // Create account object
        Account account = new Account(accNo, initialBalance);

        // 2) Menu loop - int choice initialized to zero
        int choice = 0;
        while (choice != 6) { // 6 will be Exit
            System.out.println("\n=== Bank Menu ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transactions");
            System.out.println("5. Help (show options)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            // read choice safely
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Please enter a valid number (1-6).");
                sc.next(); // discard invalid token
                continue;
            }

            // handle each option with if-else (easy to read for beginners)
            if (choice == 1) {
                System.out.print("Enter amount to deposit: ");
                if (sc.hasNextDouble()) {
                    double amt = sc.nextDouble();
                    account.deposit(amt);
                } else {
                    System.out.println("Invalid amount.");
                    sc.next(); // discard
                }
            } else if (choice == 2) {
                System.out.print("Enter amount to withdraw: ");
                if (sc.hasNextDouble()) {
                    double amt = sc.nextDouble();
                    account.withdraw(amt);
                } else {
                    System.out.println("Invalid amount.");
                    sc.next(); // discard
                }
            } else if (choice == 3) {
                account.checkBalance();
            } else if (choice == 4) {
                account.printTransactions();
            } else if (choice == 5) {
                System.out.println("Choose 1-Deposit, 2-Withdraw, 3-Balance, 4-Transactions, 6-Exit");
            } else if (choice == 6) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice. Please enter 1-6.");
            }
        }

        sc.close();
    }
}
