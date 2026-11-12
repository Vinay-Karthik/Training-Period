package TicketBooking;

import java.util.concurrent.locks.ReentrantLock;

public class TicketBooking {

    private int availableSeats = 10;
    private final ReentrantLock lock = new ReentrantLock();
    public void bookTicket(String customerName) {
        lock.lock();
        try {
            if (availableSeats > 0) {

                System.out.println(
                        customerName + " is checking seats"
                );

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                availableSeats--;

                System.out.println(
                        customerName + " booked successfully!"
                );

            } else {

                System.out.println(
                        customerName + " - No seats available"
                );
            }
        }finally {
            lock.unlock();
        }
    }

    public int getAvailableSeats() {
        lock.lock();

        try {
            return availableSeats;
        } finally {
            lock.unlock();
        }    }
}
