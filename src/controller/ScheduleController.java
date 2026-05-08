package controller;

import model.Appointment;
import model.Barber;
import model.Customer;
import model.Schedule;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controlador central responsável por gerenciar a lógica de negócios dos agendamentos.
 * Ele interage diretamente com o modelo Schedule para manipular os horários disponíveis
 * e com a lista de Appointment para gerenciar os agendamentos feitos.
 */
public class ScheduleController {
    // A instância da agenda que contém os horários disponíveis.
    private Schedule schedule;
    // A lista de todos os agendamentos realizados.
    private List<Appointment> appointments;

    /**
     * Construtor do ScheduleController.
     * @param schedule A instância de Schedule que será gerenciada por este controlador.
     */
    public ScheduleController(Schedule schedule) {
        this.schedule = schedule;
        this.appointments = new ArrayList<>();
    }

    // --- MÉTODOS DE AÇÃO ---

    /**
     * Tenta agendar um horário para um cliente com um barbeiro específico.
     * Se o horário estiver disponível, ele é removido da lista de horários disponíveis
     * e um novo agendamento é criado e adicionado à lista de agendamentos.
     * @param hour A hora desejada para o agendamento.
     * @param customer O cliente que está fazendo o agendamento.
     * @param barber O barbeiro que realizará o serviço.
     * @return true se o agendamento foi bem-sucedido, false caso contrário (ex: horário não disponível).
     */
    public boolean bookAppointment(LocalTime hour, Customer customer, Barber barber) {
        // Verifica se o horário desejado está na lista de horários disponíveis.
        if (schedule.getAvaibleHours().contains(hour)) {
            // Remove o horário da lista de disponíveis.
            schedule.getAvaibleHours().remove(hour);
            // Cria um novo objeto Appointment.
            Appointment newApp = new Appointment(hour, customer, barber);
            // Adiciona o novo agendamento à lista de agendamentos.
            appointments.add(newApp);
            return true;
        }
        return false;
    }

    /**
     * Cancela um agendamento existente pelo seu ID.
     * Se o agendamento for encontrado, ele é removido da lista de agendamentos,
     * e o horário correspondente é devolvido à lista de horários disponíveis.
     * A lista de horários disponíveis é então reordenada.
     * @param id O ID do agendamento a ser cancelado.
     * @return true se o agendamento foi cancelado com sucesso, false caso contrário (ex: ID não encontrado).
     */
    public boolean cancelAppointment(int id) {
        // Busca o agendamento pelo ID.
        Appointment toRemove = findById(id);
        if (toRemove != null) {
            // Devolve o horário do agendamento cancelado para a lista de horários disponíveis.
            schedule.getAvaibleHours().add(toRemove.getAppointmentHour());
            // Reordena a lista de horários disponíveis para manter a consistência.
            Collections.sort(schedule.getAvaibleHours());

            // Remove o agendamento da lista de agendamentos.
            appointments.remove(toRemove);
            return true;
        }
        return false;
    }

    /**
     * Reinicia o sistema de agendamentos.
     * Limpa todos os agendamentos existentes e restaura a agenda para seus horários fixos originais.
     */
    public void resetSystem() {
        // Limpa a lista de todos os agendamentos.
        appointments.clear();
        // Restaura os horários disponíveis para o estado inicial.
        schedule.resetToFixedHours();
    }

    // --- MÉTODOS DE CONSULTA ---

    /**
     * Método auxiliar privado para encontrar um agendamento pelo seu ID.
     * @param id O ID do agendamento a ser buscado.
     * @return O objeto Appointment correspondente ao ID, ou null se nenhum for encontrado.
     */
    private Appointment findById(int id) {
        return appointments.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Retorna a lista de todos os agendamentos realizados.
     * @return Uma lista de objetos Appointment.
     */
    public List<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Retorna a lista de horários atualmente disponíveis na agenda.
     * @return Uma lista de objetos LocalTime.
     */
    public List<LocalTime> getAvailableHours() {
        return schedule.getAvaibleHours();
    }

    /**
     * Retorna um agendamento específico pelo seu ID.
     * @param id O ID do agendamento a ser retornado.
     * @return O objeto Appointment correspondente ao ID, ou null se não for encontrado.
     */
    public Appointment getAppointmentById(int id) {
        return findById(id);
    }
}
