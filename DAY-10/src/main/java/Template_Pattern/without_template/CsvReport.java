package Template_Pattern.without_template;

// Same steps duplicated again - only the details differ.
public class CsvReport {

    public void generate() {
        System.out.println("[CSV] Opening stream...");
        System.out.println("[CSV] Writing header row");
        System.out.println("[CSV] Writing data rows");
        System.out.println("[CSV] Writing footer row");
        System.out.println("[CSV] Closing stream");
    }
}