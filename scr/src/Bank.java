import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bank {
    private List<Account> accountDB = new ArrayList<Account>();
    private int bankId;

    Bank(int bankId) {
        this.bankId = bankId;
    }

    public Optional<Account> newAccount(String cpf,
                              String nameUserAccount,
                              AccountType accountType,
                              double balanceAccount) {
        if(!checkCpfAccount(cpf)) return null;
        Account account = new Account(bankId,
                accountDB.size(),
                cpf,
                nameUserAccount,
                accountType,
                balanceAccount);
        accountDB.add(account);
        return Optional.of(account);
    }

    public boolean checkBank(int bankId) {
        return this.bankId == bankId;
    }

    public Optional<Account> getAccount(int accountNumber) {
        for(Account account: accountDB) {
            if(account.checkAccount(accountNumber)) return Optional.of(account);
        }
        return null;
    }

    public void menuPrint() {
        String menu = """
                    Operations:
                    
                    1 - Consult data account
                    2 - Consult balance
                    3 - Deposit value
                    4 - Transfer value
                    5 - Quit
                    """;
        System.out.println(menu);
    }

    public void balanceAccountPrint(Account account) {
        String balance = String.format("Balance:         R$ %.2f", account.getBalanceAccount());
        System.out.println(balance);
    }

    private boolean checkAccount(Account account) {
        for(Account accountElement: accountDB) {
            if(accountElement.equals(account)) return true;
        }
        return false;
    }

    private boolean checkCpfAccount(String cpf) {
        for(Account accountElement: accountDB) {
            if(accountElement.checkCpf(cpf)) return false;
        }
        return true;
    }
}