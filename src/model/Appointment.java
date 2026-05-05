import java.time.LocalTime;

public class Appointment {
    private static int counter = 0; //para o id subir a cada agendamento
    private int id;
    private Barber barber;
    private Customer customer;
    private LocalTime appointmentHour;

    public Appointment(LocalTime appointmentHour, Customer customer, Barber barber) {
        this.appointmentHour = appointmentHour;
        this.id = this.counter++;
        this.customer = customer;
        this.barber = barber;
    }
// getters
    public LocalTime getAppointmentHour() {
        return appointmentHour;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getCounter() {
        return counter;
    }

    public Barber getBarber() {
        return barber;
    }
}
