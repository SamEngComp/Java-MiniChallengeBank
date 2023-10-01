import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static List<Bank> bankDB = new ArrayList<Bank>();
    private static void depositValue(Account account) {
        System.out.println("How much do you want to save?");
        double valueDeposit = input.nextDouble();

        account.depositValue(valueDeposit);
        String newBalance = String.format("New balance:         R$ %.2f\n", account.getBalanceAccount());
        System.out.println(newBalance);
    }

    private static void transferValue(Account account) {
        System.out.println("How much do you want to transfer?");
        double valueTransfer = input.nextDouble();

        System.out.println("Which account do you want to send?");
        System.out.printf("Account: ");
        int accountNumber = input.nextInt();

        System.out.printf("Bank: ");
        int bankId = input.nextInt();

        for(Bank bankDestination: bankDB) {
            if(bankDestination.checkBank(bankId)) {
                Optional<Account> accountDestinationOptional = bankDestination.getAccount(accountNumber);

                if(accountDestinationOptional.isPresent()) {
                    if(account.takeMoney(valueTransfer)) {
                        Account accountDestination = accountDestinationOptional.get();
                        accountDestination.depositValue(valueTransfer);
                        System.out.println("Transfer success.");
                    } else {
                        System.out.println("Fail.");
                    }
                    return;
                }
            }
        }

        System.out.println("Bank not found.");
    }

    public static boolean startOperation(Bank bank, Account account, int operation) {
        switch (operation) {
            case 1:
                account.dataAccountPrint();
                break;
            case 2:
                bank.balanceAccountPrint(account);
                break;
            case 3:
                depositValue(account);
                break;
            case 4:
                transferValue(account);
                break;
            case 5:
                return false;
            default: return true;
        }
        return true;
    }

    public static void main(String[] args) {
        Bank bank1 = new Bank(0);
        Optional<Account> account1 = bank1.newAccount("01234567890",
                "Samuel Sales",
                AccountType.Current,
                2500.0);
        Optional<Account> account2 = bank1.newAccount("01234567891",
                "Nathan Sales",
                AccountType.Digital,
                250.0);
        bankDB.add(bank1);
        int operation = 0;
        do {
            bank1.menuPrint();

            operation = input.nextInt();

            if (account1.isPresent()) {
                if (!startOperation(bank1, account1.get(), operation)) break;
            }
        } while (true);

    }
}
