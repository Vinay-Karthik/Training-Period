package pipeline;


import model.Transaction;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class LedgerConsumer implements Runnable {
    private final BlockingQueue<Transaction> ledgerQueue;
    private final List<String> auditLog = Collections.synchronizedList(new ArrayList<>());
    private final AtomicInteger processedCount = new AtomicInteger(0);

    public LedgerConsumer(BlockingQueue<Transaction> ledgerQueue) {
        this.ledgerQueue = ledgerQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Blocks cleanly in WAITING state until a transaction arrives
                Transaction txn = ledgerQueue.take();
                auditLog.add(Instant.now() + " -> " + txn);
                processedCount.incrementAndGet();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getProcessedCount() {
        return processedCount.get();
    }
}
