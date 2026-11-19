package com.company.payroll.solid_bad;

/**
 * BAD DESIGN: Fat Employee Class.
 * Violates LSP & ISP by bundling fields for all employee types into one class.
 */
public class BadEmployee {
    private String id;
    private String name;
    private String type; // "PERMANENT", "CONTRACT", "SALES", "EXECUTIVE"
    private double basicSalary;
    private double hra;
    private double allowances;
    private double contractAmount;
    private double salesAmount;
    private double commissionRate;
    private double bonus;
    private double benefits;
    private String taxRegime; // "OLD", "NEW"
    private String email;

    public BadEmployee(String id, String name, String type, double basicSalary, double hra, 
                       double allowances, double contractAmount, double salesAmount, 
                       double commissionRate, double bonus, double benefits, 
                       String taxRegime, String email) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.basicSalary = basicSalary;
        this.hra = hra;
        this.allowances = allowances;
        this.contractAmount = contractAmount;
        this.salesAmount = salesAmount;
        this.commissionRate = commissionRate;
        this.bonus = bonus;
        this.benefits = benefits;
        this.taxRegime = taxRegime;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getBasicSalary() { return basicSalary; }
    public double getHra() { return hra; }
    public double getAllowances() { return allowances; }
    public double getContractAmount() { return contractAmount; }
    public double getSalesAmount() { return salesAmount; }
    public double getCommissionRate() { return commissionRate; }
    public double getBonus() { return bonus; }
    public double getBenefits() { return benefits; }
    public String getTaxRegime() { return taxRegime; }
    public String getEmail() { return email; }

    // LSP VIOLATION: Method expected for all employees, but breaks for contractors!
    public double getHraAllowance() {
        return hra;
    }
}
