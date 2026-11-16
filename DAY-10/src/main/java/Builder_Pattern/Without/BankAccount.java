package Builder_Pattern.Without;


import java.math.BigDecimal;

/**
 * @param accountHolderName Mandatory fields
 * @param nomineeName       Optional fields
 */
public record BankAccount(String accountHolderName, String accountNumber, String accountType, BigDecimal initialDeposit,
                          String branchName, String ifscCode, String nomineeName, String mobileNumber, String email,
                          boolean debitCardEnabled, boolean internetBankingEnabled) {

    // Constructor 1: Mandatory fields only (Scenario 1)
    public BankAccount(String accountHolderName,
                       String accountNumber,
                       String accountType,
                       BigDecimal initialDeposit,
                       String branchName,
                       String ifscCode, String number, String mail, boolean b) {
        this(accountHolderName, accountNumber, accountType, initialDeposit, branchName, ifscCode,
                null, null, null, false, false);
    }

    // Constructor 3: All fields (Scenario 2)

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountHolderName='" + accountHolderName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", initialDeposit=" + initialDeposit +
                ", branchName='" + branchName + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", nomineeName='" + nomineeName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", debitCardEnabled=" + debitCardEnabled +
                ", internetBankingEnabled=" + internetBankingEnabled +
                '}';
    }
}
