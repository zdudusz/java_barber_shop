package controller;

import model.Barber;
import model.Appointment;
import model.Customer;
import model.Schedule;

import java.time.LocalTime;
import java.util.List;

/**
 * Controlador responsável por gerenciar as operações específicas do Barbeiro.
 * Atua como intermediário entre as Views do Barbeiro e o ScheduleController,
 * traduzindo as ações do usuário em operações na agenda.
 */
public class BarberController {
    // Referência ao controlador principal de agendamentos para manipular a agenda.
    private ScheduleController scheduleController;
    // O objeto Barbeiro atualmente logado ou em contexto.
    private Barber barber;

    /**
     * Construtor do BarberController.
     * @param scheduleController A instância do ScheduleController para gerenciar agendamentos.
     * @param barber O objeto Barber associado a este controlador.
     */
    public BarberController(ScheduleController scheduleController, Barber barber) {
        this.scheduleController = scheduleController;
        this.barber = barber;
    }

    /**
     * Retorna todos os agendamentos registrados no sistema.
     * @return Uma lista de todos os objetos Appointment.
     */
    public List<Appointment> getAllAppointments() {
        return scheduleController.getAppointments();
    }

    /**
     * Retorna os horários disponíveis na agenda.
     * @return Uma lista de objetos LocalTime representando os horários que podem ser agendados.
     */
    public List<LocalTime> getAvailableHours() {
        return scheduleController.getAvailableHours();
    }

    /**
     * Adiciona um novo agendamento ao sistema.
     * @param hour A hora desejada para o agendamento.
     * @param customer O cliente para o qual o agendamento será feito.
     * @return true se o agendamento foi bem-sucedido, false caso contrário.
     */
    public boolean addAppointment(LocalTime hour, Customer customer) {
        return scheduleController.bookAppointment(hour, customer, this.barber);
    }

    /**
     * Cancela um agendamento existente pelo seu ID.
     * @param id O ID do agendamento a ser cancelado.
     * @return true se o agendamento foi cancelado com sucesso, false caso contrário.
     */
    public boolean cancelAppointment(int id) {
        return scheduleController.cancelAppointment(id);
    }

    /**
     * Reagenda um agendamento existente para uma nova hora.
     * Primeiro, tenta cancelar o agendamento original e, se bem-sucedido, tenta criar um novo agendamento.
     * @param id O ID do agendamento a ser reagendado.
     * @param newHour A nova hora para o agendamento.
     * @return true se o agendamento foi reagendado com sucesso, false caso contrário.
     */
    public boolean rescheduleAppointment(int id, LocalTime newHour) {
        // Busca o agendamento antigo para obter as informações do cliente.
        Appointment oldApp = scheduleController.getAppointmentById(id);
        if (oldApp != null) {
            // Tenta cancelar o agendamento original.
            if (scheduleController.cancelAppointment(id)) {
                // Se o cancelamento for bem-sucedido, tenta agendar na nova hora com o mesmo cliente e barbeiro.
                return scheduleController.bookAppointment(newHour, oldApp.getCustomer(), this.barber);
            }
        }
        return false;
    }

    /**
     * Reinicia todo o sistema de agendamentos, limpando todos os agendamentos
     * e restaurando os horários disponíveis para o estado inicial.
     */
    public void resetSystem() {
        scheduleController.resetSystem();
    }

    /**
     * Busca um agendamento pelo seu ID.
     * @param id O ID do agendamento a ser encontrado.
     * @return O objeto Appointment correspondente ao ID, ou null se não for encontrado.
     */
    public Appointment getAppointmentById(int id) {
        return scheduleController.getAppointmentById(id);
    }
}
