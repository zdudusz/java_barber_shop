package view;

import controller.BarberController;
import model.Appointment;
import model.Customer;
import util.BetterScanner;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Classe responsável por exibir o menu de opções para o Barbeiro
 * e interagir com o BarberController para executar as ações desejadas.
 * Atua como a View para as funcionalidades do Barbeiro.
 */
public class BarberMenu {

    /**
     * Inicia o fluxo do menu do Barbeiro, exibindo opções e processando a entrada do usuário.
     * @param barberController O controlador específico do Barbeiro, que contém a lógica de negócio.
     */
    public static void run(BarberController barberController) {
        int option; // Variável para armazenar a opção escolhida pelo barbeiro.
        do {
            // Exibe as opções disponíveis para o barbeiro.
            System.out.println("\n--- Menu do Barbeiro ---");
            System.out.println("1. Ver Agendamentos");
            System.out.println("2. Adicionar Agendamento (CRUD - Create)");
            System.out.println("3. Cancelar Agendamento (CRUD - Delete)");
            System.out.println("4. Reagendar Agendamento (CRUD - Update)");
            System.out.println("5. Reiniciar Lista de Agendamentos");
            System.out.println("0. Voltar ao Menu Principal");
            // Lê a opção do barbeiro usando a classe utilitária BetterScanner.
            option = BetterScanner.inputInt("Escolha uma opção: ");

            switch (option) {
                case 1:
                    // Chama o método para visualizar todos os agendamentos.
                    viewAppointments(barberController);
                    break;
                case 2:
                    // Chama o método para adicionar um novo agendamento.
                    addAppointment(barberController);
                    break;
                case 3:
                    // Chama o método para cancelar um agendamento existente.
                    cancelAppointment(barberController);
                    break;
                case 4:
                    // Chama o método para reagendar um agendamento existente.
                    rescheduleAppointment(barberController);
                    break;
                case 5:
                    // Chama o método para reiniciar todos os agendamentos.
                    resetAppointments(barberController);
                    break;
                case 0:
                    // Opção para voltar ao menu principal.
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    // Tratamento para opções inválidas.
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0); // Continua exibindo o menu até que o barbeiro escolha sair (opção 0).
    }

    /**
     * Exibe todos os agendamentos atuais e os horários disponíveis.
     * @param barberController O controlador do Barbeiro para acessar os dados da agenda.
     */
    private static void viewAppointments(BarberController barberController) {
        System.out.println("\n--- Agendamentos Atuais ---");
        // Obtém a lista de todos os agendamentos através do controlador.
        List<Appointment> appointments = barberController.getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("Nenhum agendamento marcado.");
        } else {
            // Itera e exibe os detalhes de cada agendamento.
            for (Appointment app : appointments) {
                System.out.printf("ID: %d | Cliente: %s | Barbeiro: %s | Hora: %s%n",
                        app.getId(), app.getCustomer().getName(), app.getBarber().getName(), app.getAppointmentHour());
            }
        }
        System.out.println("\n--- Horários Disponíveis ---");
        // Obtém a lista de horários disponíveis através do controlador.
        List<LocalTime> availableHours = barberController.getAvailableHours();
        if (availableHours.isEmpty()) {
            System.out.println("Nenhum horário disponível.");
        } else {
            // Exibe cada horário disponível.
            for (LocalTime hour : availableHours) {
                System.out.println(hour);
            }
        }
    }

    /**
     * Permite ao barbeiro adicionar um novo agendamento.
     * Solicita o nome do cliente e a hora do agendamento.
     * @param barberController O controlador do Barbeiro para realizar a operação de adição.
     */
    private static void addAppointment(BarberController barberController) {
        System.out.println("\n--- Adicionar Novo Agendamento ---");
        String customerName = BetterScanner.inputString("Nome do Cliente: ");
        // Cria um novo objeto Customer. Em um sistema real, este cliente poderia ser buscado ou registrado.
        Customer newCustomer = new Customer(customerName);

        LocalTime hour = null;
        boolean validHour = false;
        while (!validHour) { // Loop para garantir que uma hora válida seja inserida.
            try {
                String hourStr = BetterScanner.inputString("Hora do agendamento (HH:MM): ");
                hour = LocalTime.parse(hourStr); // Converte a string para LocalTime.
                // Verifica se a hora está entre os horários disponíveis.
                if (!barberController.getAvailableHours().contains(hour)) {
                    System.out.println("Erro: Hora não disponível ou formato inválido. Verifique os horários disponíveis.");
                } else {
                    validHour = true;
                }
            } catch (DateTimeParseException e) {
                // Captura exceção se o formato da hora for inválido.
                System.out.println("Erro: Formato de hora inválido. Use HH:MM (ex: 09:00).");
            }
        }

        // Tenta adicionar o agendamento através do controlador.
        if (barberController.addAppointment(hour, newCustomer)) {
            System.out.println("Agendamento adicionado com sucesso!");
        } else {
            System.out.println("Falha ao adicionar agendamento. Hora já ocupada ou inválida.");
        }
    }

    /**
     * Permite ao barbeiro cancelar um agendamento existente.
     * Solicita o ID do agendamento a ser cancelado.
     * @param barberController O controlador do Barbeiro para realizar a operação de cancelamento.
     */
    private static void cancelAppointment(BarberController barberController) {
        System.out.println("\n--- Cancelar Agendamento ---");
        if (barberController.getAllAppointments().isEmpty()) {
            System.out.println("Nenhum agendamento para cancelar.");
            return;
        }
        viewAppointments(barberController); // Exibe os agendamentos para que o barbeiro possa escolher o ID.
        int id = BetterScanner.inputInt("Digite o ID do agendamento a ser cancelado: ");
        // Tenta cancelar o agendamento através do controlador.
        if (barberController.cancelAppointment(id)) {
            System.out.println("Agendamento cancelado com sucesso!");
        } else {
            System.out.println("Falha ao cancelar agendamento. ID não encontrado.");
        }
    }

    /**
     * Permite ao barbeiro reagendar um agendamento existente.
     * Solicita o ID do agendamento e a nova hora desejada.
     * @param barberController O controlador do Barbeiro para realizar a operação de reagendamento.
     */
    private static void rescheduleAppointment(BarberController barberController) {
        System.out.println("\n--- Reagendar Agendamento ---");
        if (barberController.getAllAppointments().isEmpty()) {
            System.out.println("Nenhum agendamento para reagendar.");
            return;
        }
        viewAppointments(barberController); // Exibe os agendamentos para que o barbeiro possa escolher o ID.
        int id = BetterScanner.inputInt("Digite o ID do agendamento a ser reagendado: ");

        LocalTime newHour = null;
        boolean validHour = false;
        while (!validHour) { // Loop para garantir que uma nova hora válida seja inserida.
            try {
                String hourStr = BetterScanner.inputString("Digite a NOVA hora do agendamento (HH:MM): ");
                newHour = LocalTime.parse(hourStr); // Converte a string para LocalTime.
                // Verifica se a nova hora está entre os horários disponíveis.
                if (!barberController.getAvailableHours().contains(newHour)) {
                    System.out.println("Erro: Nova hora não disponível ou formato inválido. Verifique os horários disponíveis.");
                } else {
                    validHour = true;
                }
            } catch (DateTimeParseException e) {
                // Captura exceção se o formato da hora for inválido.
                System.out.println("Erro: Formato de hora inválido. Use HH:MM (ex: 09:00).");
            }
        }

        // Tenta reagendar o agendamento através do controlador.
        // O controlador já cuida da lógica de cancelar o antigo e criar um novo.
        if (barberController.rescheduleAppointment(id, newHour)) {
            System.out.println("Agendamento reagendado com sucesso!");
        } else {
            System.out.println("Falha ao reagendar agendamento. Verifique o ID e a nova hora.");
        }
    }

    /**
     * Permite ao barbeiro reiniciar todo o sistema de agendamentos.
     * Solicita confirmação antes de executar a ação, que limpa todos os agendamentos.
     * @param barberController O controlador do Barbeiro para realizar a operação de reset.
     */
    private static void resetAppointments(BarberController barberController) {
        System.out.println("\n--- Reiniciar Todos os Agendamentos ---");
        String confirmation = BetterScanner.inputString("Tem certeza que deseja reiniciar TODOS os agendamentos? (s/n): ");
        if (confirmation.equalsIgnoreCase("s")) { // Confirmação do usuário.
            barberController.resetSystem(); // Chama o método de reset no controlador.
            System.out.println("Todos os agendamentos foram reiniciados e horários restaurados.");
        } else {
            System.out.println("Reinicialização cancelada.");
        }
    }
}
