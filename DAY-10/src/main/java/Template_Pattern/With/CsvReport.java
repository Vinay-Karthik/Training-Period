package Template_Pattern.With;

public class CsvReport extends Report {

    @Override
    protected void writeHeader() {
        System.out.println("[CSV] Writing header row");
    }

    @Override
    protected void writeBody() {
        System.out.println("[CSV] Writing data rows");
    }

    @Override
    protected void writeFooter() {
        System.out.println("[CSV] Writing footer row");
    }

    @Override
    protected String format() {
        return "CSV";
    }
}
