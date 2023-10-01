public class Account {

    // Properties

    private int bankId;
    private int accountNumber;
    private String cpf;
    private String nameUserAccount;
    private AccountType accountType;
    private double balanceAccount;

    // Constructor

    Account(int bankId,
            int accountNumber,
            String cpf,
            String nameUserAccount,
            AccountType accountType,
            double balanceAccount) {
        this.bankId = bankId;
        this.accountNumber = accountNumber;
        this.cpf = cpf;
        this.nameUserAccount = nameUserAccount;
        this.accountType = accountType;
        this.balanceAccount = balanceAccount;
    }

    // Methods

    public void dataAccountPrint() {
        String dataAccount = String.format("""
                            ******************************
                            Number:          %d
                            Name:            %s
                            Account type:    %s
                            Balance:         R$ %.2f
                            ******************************
                            """,
                this.accountNumber,
                this.nameUserAccount,
                this.accountType,
                this.balanceAccount);
        System.out.println(dataAccount);
    }

    public void depositValue(double value) {
        this.balanceAccount += value;
    }

    public boolean takeMoney(Double value) {
        if(value > balanceAccount) return false;
        this.balanceAccount -= value;
        return true;
    }

    public boolean checkAccount(int accountNumber) {
        return this.accountNumber == accountNumber;
    }

    public boolean checkCpf(String cpf) {
        return this.cpf == cpf;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public String getNameUserAccount() {
        return this.nameUserAccount;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public double getBalanceAccount() {
        return this.balanceAccount;
    }
}
