package SOLID_PRINCPLE.With.model;

import java.math.BigDecimal;

// ISP: small, focused interfaces instead of one fat interface
public interface Withdrawable {
    void withdraw(BigDecimal amount);
}
