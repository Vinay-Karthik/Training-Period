package Template_Pattern.with_template;

// Client works with the base type - polymorphism does the rest.
// Adding a new report type = new subclass, zero changes here.
public class Main {

    public static void main(String[] args) {
        Report pdf = new PdfReport();
        Report csv = new CsvReport();

        pdf.generate();
        System.out.println();
        csv.generate();
    }
}