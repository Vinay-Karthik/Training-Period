package Template_Pattern.without_template;

// Client must know each report type and call generate() on each.
// The shared algorithm is invisible - buried inside each class.
public class Main {

    public static void main(String[] args) {
        new PdfReport().generate();
        System.out.println();
        new CsvReport().generate();
    }
}