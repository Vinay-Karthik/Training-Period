package SOLID_Principle.With.model;

import java.math.BigDecimal;

// ISP: small, focused interfaces instead of one fat interface
public interface Depositable {
    void deposit(BigDecimal amount);
}
