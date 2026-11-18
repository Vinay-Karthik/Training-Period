package Template_Pattern.With;

// TEMPLATE METHOD PATTERN
// The base class defines the ALGORITHM (final generate()) and lets
// subclasses fill in only the variable steps (abstract hooks).
// The order of steps can never be broken by a subclass.
public abstract class Report {

    // Template method - defines the skeleton. Subclasses CANNOT change it.
    public final void generate() {
        open();
        writeHeader();
        writeBody();
        writeFooter();
        close();
    }

    // Steps that are the same for every report
    private void open() {
        System.out.println("[" + format() + "] Opening...");
    }

    private void close() {
        System.out.println("[" + format() + "] Closing...");
    }

    // Variable steps - subclasses MUST implement
    protected abstract void writeHeader();

    protected abstract void writeBody();

    protected abstract void writeFooter();

    // Optional hook - subclasses may override
    protected String format() {
        return "REPORT";
    }
}
