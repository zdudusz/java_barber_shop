package controller;

import model.Appointment;
import model.Barber;
import model.Customer;
import model.Schedule;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador responsável por gerenciar as operações específicas do Cliente.
 * Atua como intermediário entre as Views do Cliente e o ScheduleController,
 * permitindo que o cliente visualize, marque e cancele seus próprios agendamentos.
 */
public class CustomerController {
    // Referência ao controlador principal de agendamentos para manipular a agenda.
    private ScheduleController scheduleController;
    // O objeto Customer atualmente logado ou em contexto.
    private Customer customer;

    /**
     * Construtor do CustomerController.
     * @param scheduleController A instância do ScheduleController para gerenciar agendamentos.
     * @param customer O objeto Customer associado a este controlador.
     */
    public CustomerController(ScheduleController scheduleController, Customer customer) {
        this.scheduleController = scheduleController;
        this.customer = customer;
    }

    /**
     * Retorna os horários disponíveis na agenda.
     * @return Uma lista de objetos LocalTime representando os horários que podem ser agendados.
     */
    public List<LocalTime> getAvailableHours() {
        return scheduleController.getAvailableHours();
    }

    /**
     * Marca um novo agendamento para o cliente associado a este controlador.
     * @param hour A hora desejada para o agendamento.
     * @param barber O barbeiro que realizará o serviço (pode ser um barbeiro padrão).
     * @return true se o agendamento foi bem-sucedido, false caso contrário.
     */
    public boolean bookAppointment(LocalTime hour, Barber barber) {
        return scheduleController.bookAppointment(hour, this.customer, barber);
    }

    /**
     * Retorna uma lista de agendamentos que pertencem ao cliente associado a este controlador.
     * @return Uma lista de objetos Appointment do cliente.
     */
    public List<Appointment> getCustomerAppointments() {
        return scheduleController.getAppointments().stream()
                .filter(app -> app.getCustomer().getName().equals(this.customer.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Retorna o objeto Customer associado a este controlador.
     * @return O objeto Customer.
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * Cancela um agendamento específico, verificando se ele pertence ao cliente atual.
     * @param id O ID do agendamento a ser cancelado.
     * @return true se o agendamento foi cancelado com sucesso e pertencia ao cliente, false caso contrário.
     */
    public boolean cancelMyAppointment(int id) {
        // Busca o agendamento pelo ID.
        Appointment appToCancel = scheduleController.getAppointmentById(id);
        // Verifica se o agendamento existe e se pertence ao cliente atual.
        if (appToCancel != null && appToCancel.getCustomer().getName().equals(this.customer.getName())) {
            return scheduleController.cancelAppointment(id);
        }
        return false;
    }
}
