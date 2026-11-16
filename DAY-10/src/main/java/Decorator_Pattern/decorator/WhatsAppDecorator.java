package Decorator_Pattern.decorator;

import Decorator_Pattern.interfaces.BankAccount;

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
