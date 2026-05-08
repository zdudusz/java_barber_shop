package view;

import controller.CustomerController;
import model.Appointment;
import model.Barber;
import util.BetterScanner;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Classe responsável por exibir o menu de opções para o Cliente
 * e interagir com o CustomerController para executar as ações desejadas.
 * Atua como a View para as funcionalidades do Cliente.
 */
public class CustomerMenu {

    /**
     * Inicia o fluxo do menu do Cliente, exibindo opções e processando a entrada do usuário.
     * @param customerController O controlador específico do Cliente, que contém a lógica de negócio.
     */
    public static void run(CustomerController customerController) {
        int option; // Variável para armazenar a opção escolhida pelo cliente.
        do {
            // Exibe as opções disponíveis para o cliente, incluindo seu nome.
            System.out.println("\n--- Menu do Cliente: " + customerController.getCustomer().getName() + " ---");
            System.out.println("1. Ver Meus Agendamentos");
            System.out.println("2. Marcar Agendamento");
            System.out.println("3. Cancelar Meu Agendamento");
            System.out.println("0. Voltar ao Menu Principal");
            // Lê a opção do cliente usando a classe utilitária BetterScanner.
            option = BetterScanner.inputInt("Escolha uma opção: ");

            switch (option) {
                case 1:
                    // Chama o método para visualizar os agendamentos do cliente.
                    viewMyAppointments(customerController);
                    break;
                case 2:
                    // Chama o método para marcar um novo agendamento.
                    bookAppointment(customerController);
                    break;
                case 3:
                    // Chama o método para cancelar um agendamento do cliente.
                    cancelMyAppointment(customerController);
                    break;
                case 0:
                    // Opção para voltar ao menu principal.
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    // Tratamento para opções inválidas.
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0); // Continua exibindo o menu até que o cliente escolha sair (opção 0).
    }

    /**
     * Exibe os agendamentos que pertencem ao cliente atual.
     * @param customerController O controlador do Cliente para acessar os agendamentos específicos.
     */
    private static void viewMyAppointments(CustomerController customerController) {
        System.out.println("\n--- Meus Agendamentos ---");
        // Obtém a lista de agendamentos do cliente através do controlador.
        List<Appointment> appointments = customerController.getCustomerAppointments();
        if (appointments.isEmpty()) {
            System.out.println("Você não possui agendamentos marcados.");
        } else {
            // Itera e exibe os detalhes de cada agendamento do cliente.
            for (Appointment app : appointments) {
                System.out.printf("ID: %d | Barbeiro: %s | Hora: %s%n",
                        app.getId(), app.getBarber().getName(), app.getAppointmentHour());
            }
        }
    }

    /**
     * Permite ao cliente marcar um novo agendamento.
     * Exibe os horários disponíveis e solicita a escolha do cliente.
     * @param customerController O controlador do Cliente para realizar a operação de agendamento.
     */
    private static void bookAppointment(CustomerController customerController) {
        System.out.println("\n--- Marcar Agendamento ---");
        System.out.println("Horários Disponíveis:");
        // Obtém a lista de horários disponíveis através do controlador.
        List<LocalTime> availableHours = customerController.getAvailableHours();
        if (availableHours.isEmpty()) {
            System.out.println("Desculpe, não há horários disponíveis no momento.");
            return;
        }
        // Exibe cada horário disponível.
        for (LocalTime hour : availableHours) {
            System.out.println(hour);
        }

        LocalTime hour = null;
        boolean validHour = false;
        while (!validHour) { // Loop para garantir que uma hora válida seja inserida.
            try {
                String hourStr = BetterScanner.inputString("Escolha a hora do agendamento (HH:MM): ");
                hour = LocalTime.parse(hourStr); // Converte a string para LocalTime.
                // Verifica se a hora está entre os horários disponíveis.
                if (!customerController.getAvailableHours().contains(hour)) {
                    System.out.println("Erro: Hora não disponível ou formato inválido. Verifique os horários disponíveis.");
                } else {
                    validHour = true;
                }
            } catch (DateTimeParseException e) {
                // Captura exceção se o formato da hora for inválido.
                System.out.println("Erro: Formato de hora inválido. Use HH:MM (ex: 09:00).");
            }
        }

        // Para simplificar, o cliente marca com um barbeiro padrão.
        // Em um sistema mais complexo, ele poderia escolher o barbeiro.
        Barber defaultBarber = new Barber("João Barbeiro");

        // Tenta marcar o agendamento através do controlador.
        if (customerController.bookAppointment(hour, defaultBarber)) {
            System.out.println("Agendamento marcado com sucesso!");
        } else {
            System.out.println("Falha ao marcar agendamento. Tente novamente.");
        }
    }

    /**
     * Permite ao cliente cancelar um de seus próprios agendamentos.
     * Exibe os agendamentos do cliente e solicita o ID do agendamento a ser cancelado.
     * @param customerController O controlador do Cliente para realizar a operação de cancelamento.
     */
    private static void cancelMyAppointment(CustomerController customerController) {
        System.out.println("\n--- Cancelar Meu Agendamento ---");
        // Obtém a lista de agendamentos do cliente.
        List<Appointment> customerAppointments = customerController.getCustomerAppointments();
        if (customerAppointments.isEmpty()) {
            System.out.println("Você não possui agendamentos para cancelar.");
            return;
        }

        // Exibe os agendamentos do cliente para que ele possa escolher qual cancelar.
        for (Appointment app : customerAppointments) {
            System.out.printf("ID: %d | Barbeiro: %s | Hora: %s%n",
                    app.getId(), app.getBarber().getName(), app.getAppointmentHour());
        }

        int id = BetterScanner.inputInt("Digite o ID do seu agendamento a ser cancelado: ");
        
        // Tenta cancelar o agendamento através do controlador.
        // O controlador já verifica se o agendamento pertence a este cliente.
        if (customerController.cancelMyAppointment(id)) {
            System.out.println("Agendamento cancelado com sucesso!");
        } else {
            System.out.println("Falha ao cancelar agendamento ou ID inválido.");
        }
    }
}
