package Builder_Pattern.With;


import java.math.BigDecimal;

public class BankAccount {

    // Mandatory fields
    private final String accountHolderName;
    private final String accountNumber;
    private final String accountType;
    private final BigDecimal initialDeposit;
    private final String branchName;
    private final String ifscCode;

    // Optional fields
    private final String nomineeName;
    private final String mobileNumber;
    private final String email;
    private final boolean debitCardEnabled;
    private final boolean internetBankingEnabled;

    // Private constructor forces object creation through Builder
    private BankAccount(Builder builder) {
        this.accountHolderName = builder.accountHolderName;
        this.accountNumber = builder.accountNumber;
        this.accountType = builder.accountType;
        this.initialDeposit = builder.initialDeposit;
        this.branchName = builder.branchName;
        this.ifscCode = builder.ifscCode;
        this.nomineeName = builder.nomineeName;
        this.mobileNumber = builder.mobileNumber;
        this.email = builder.email;
        this.debitCardEnabled = builder.debitCardEnabled;
        this.internetBankingEnabled = builder.internetBankingEnabled;
    }

    // Getters only (no setters to guarantee immutability)
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public BigDecimal getInitialDeposit() {
        return initialDeposit;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean isDebitCardEnabled() {
        return debitCardEnabled;
    }

    public boolean isInternetBankingEnabled() {
        return internetBankingEnabled;
    }

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

    public static class Builder {

        // Mandatory fields
        private final String accountHolderName;
        private final String accountNumber;
        private final String accountType;
        private final BigDecimal initialDeposit;
        private final String branchName;
        private final String ifscCode;

        // Optional fields with sensible defaults
        private String nomineeName;
        private String mobileNumber;
        private String email;
        private boolean debitCardEnabled = false;
        private boolean internetBankingEnabled = false;

        public Builder(String accountHolderName,
                       String accountNumber,
                       String accountType,
                       BigDecimal initialDeposit,
                       String branchName,
                       String ifscCode) {
            this.accountHolderName = accountHolderName;
            this.accountNumber = accountNumber;
            this.accountType = accountType;
            this.initialDeposit = initialDeposit;
            this.branchName = branchName;
            this.ifscCode = ifscCode;
        }

        public Builder nomineeName(String nomineeName) {
            this.nomineeName = nomineeName;
            return this;
        }

        public Builder mobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder debitCardEnabled(boolean debitCardEnabled) {
            this.debitCardEnabled = debitCardEnabled;
            return this;
        }

        public Builder internetBankingEnabled(boolean internetBankingEnabled) {
            this.internetBankingEnabled = internetBankingEnabled;
            return this;
        }

        public BankAccount build() {
            return new BankAccount(this);
        }

//        private void validate() {
//            if (isBlank(accountHolderName)) {
//                throw new IllegalArgumentException("Account holder name cannot be empty.");
//            }
//            if (isBlank(accountNumber)) {
//                throw new IllegalArgumentException("Account number cannot be empty.");
//            }
//            if (isBlank(accountType) ||
//                    (!accountType.equalsIgnoreCase("SAVINGS") && !accountType.equalsIgnoreCase("CURRENT"))) {
//                throw new IllegalArgumentException("Account type must be SAVINGS or CURRENT.");
//            }
//            if (initialDeposit == null || initialDeposit.compareTo(BigDecimal.ZERO) <= 0) {
//                throw new IllegalArgumentException("Initial deposit must be greater than zero.");
//            }
//            if (isBlank(branchName)) {
//                throw new IllegalArgumentException("Branch name cannot be empty.");
//            }
//            if (isBlank(ifscCode)) {
//                throw new IllegalArgumentException("Invalid IFSC code format. Expected format: e.g., HDFC0001234");
//            }
//            if (mobileNumber != null ) {
//                throw new IllegalArgumentException("Mobile number must contain exactly 10 digits.");
//            }
//            if (email != null) {
//                throw new IllegalArgumentException("Invalid email address format.");
//            }
//        }

//        private boolean isBlank(String value) {
//            return value == null || value.trim().isEmpty();
//        }
    }
}
