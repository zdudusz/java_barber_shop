package model;

import java.time.LocalTime;

/**
 * Representa um agendamento individual no sistema da barbearia.
 * Contém informações sobre o horário, o cliente, o barbeiro e um ID único.
 */
public class Appointment {
    // Contador estático para gerar IDs únicos para cada agendamento.
    // É incrementado a cada nova instância de Appointment.
    private static int counter = 0;
    private int id; // ID único do agendamento
    private Barber barber; // O barbeiro associado a este agendamento
    private Customer customer; // O cliente que fez este agendamento
    private LocalTime appointmentHour; // A hora específica do agendamento

    /**
     * Construtor para criar um novo agendamento.
     * @param appointmentHour A hora do agendamento.
     * @param customer O cliente que está agendando.
     * @param barber O barbeiro que realizará o serviço.
     */
    public Appointment(LocalTime appointmentHour, Customer customer, Barber barber) {
        this.appointmentHour = appointmentHour;
        // Atribui o ID atual do contador e depois o incrementa.
        this.id = this.counter++;
        this.customer = customer;
        this.barber = barber;
    }

    // --- Métodos Getters ---

    /**
     * Retorna a hora do agendamento.
     * @return A hora do agendamento (LocalTime).
     */
    public LocalTime getAppointmentHour() {
        return appointmentHour;
    }

    /**
     * Retorna o ID único do agendamento.
     * @return O ID do agendamento.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna o cliente associado a este agendamento.
     * @return O objeto Customer.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Retorna o contador estático de agendamentos (não o ID de uma instância específica).
     * Pode ser útil para saber quantos agendamentos foram criados no total.
     * @return O valor atual do contador.
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Retorna o barbeiro associado a este agendamento.
     * @return O objeto Barber.
     */
    public Barber getBarber() {
        return barber;
    }
}
