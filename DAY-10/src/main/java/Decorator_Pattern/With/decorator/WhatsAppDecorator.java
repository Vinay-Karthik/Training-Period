package Decorator_Pattern.With.decorator;

import Decorator_Pattern.With.interfaces.BankAccount;

public class WhatsAppDecorator extends BankAccountDecorator {
    public WhatsAppDecorator(BankAccount wrappedAccount) {
        super(wrappedAccount);
    }

    public void promotion() {
        if (super.getBalance() > 100000) {
            System.out.println("WhatsApp promotion you are eligible for loan");
        }
    }
}
