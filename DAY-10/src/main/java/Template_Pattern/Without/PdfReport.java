package Template_Pattern.Without;

// WITHOUT TEMPLATE PATTERN
// Each report type duplicates the SAME algorithm steps:
// open -> header -> body -> footer -> close.
// If the order changes, every class must be edited.
public class PdfReport {

    public void generate() {
        System.out.println("[PDF] Opening document...");
        System.out.println("[PDF] Writing header");
        System.out.println("[PDF] Writing body");
        System.out.println("[PDF] Writing footer");
        System.out.println("[PDF] Closing document");
    }
}
