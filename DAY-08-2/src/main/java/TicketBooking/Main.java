package TicketBooking;


public class Main {

    public static void main(String[] args) throws InterruptedException {

        TicketBooking booking = new TicketBooking();
        for (int i = 1; i <= 50; i++) {
            String customer = "Customer-" + i;
            Thread thread = new Thread(
                    () -> booking.bookTicket(customer));
            thread.start();
        }

        Thread.sleep(2000);
        System.out.println(
                "Available seats: " + booking.getAvailableSeats()
        );
    }
}

