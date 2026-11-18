package Template_Pattern.With;

// Only the variable steps are implemented - the algorithm is inherited.
public class PdfReport extends Report {

    @Override
    protected void writeHeader() {
        System.out.println("[PDF] Writing header");
    }

    @Override
    protected void writeBody() {
        System.out.println("[PDF] Writing body");
    }

    @Override
    protected void writeFooter() {
        System.out.println("[PDF] Writing footer");
    }

    @Override
    protected String format() {
        return "PDF";
    }
}
