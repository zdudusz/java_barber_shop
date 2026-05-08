package view;

import controller.BarberController;
import controller.CustomerController;
import controller.ScheduleController;
import model.Barber;
import model.Customer;
import util.BetterScanner;

/**
 * Classe responsável por exibir o menu principal da aplicação e gerenciar a seleção
 * do perfil de usuário (Barbeiro ou Cliente).
 * Atua como a View inicial do sistema, direcionando para os menus específicos.
 */
public class MainMenu {

    /**
     * Inicia o fluxo do menu principal, permitindo ao usuário escolher o perfil de acesso.
     * @param scheduleController O controlador central de agendamentos.
     * @param barber O objeto Barbeiro de exemplo.
     * @param customer O objeto Cliente de exemplo.
     */
    public static void run(ScheduleController scheduleController, Barber barber, Customer customer) {
        // Instancia os controladores específicos para cada tipo de usuário.
        // Estes controladores encapsulam a lógica de negócio para Barbeiros e Clientes.
        BarberController barberController = new BarberController(scheduleController, barber);
        CustomerController customerController = new CustomerController(scheduleController, customer);

        int option; // Variável para armazenar a opção escolhida pelo usuário.
        do {
            // Exibe as opções do menu principal.
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Entrar como Barbeiro");
            System.out.println("2. Entrar como Cliente");
            System.out.println("0. Sair");
            // Lê a opção do usuário usando a classe utilitária BetterScanner.
            option = BetterScanner.inputInt("Escolha uma opção: ");

            switch (option) {
                case 1:
                    // Opção para acesso como Barbeiro.
                    System.out.println("\n--- Acesso Barbeiro ---");
                    String password = BetterScanner.inputString("Digite a senha: "); // Solicita a senha.
                    if (password.equals("1234")) { // Verifica a senha (senha fixa para demonstração).
                        System.out.println("Acesso concedido ao Barbeiro.");
                        // Redireciona para o menu do Barbeiro, passando o controlador específico.
                        BarberMenu.run(barberController);
                    } else {
                        System.out.println("Senha incorreta. Acesso negado.");
                    }
                    break;
                case 2:
                    // Opção para acesso como Cliente.
                    System.out.println("\n--- Acesso Cliente ---");
                    // Redireciona para o menu do Cliente, passando o controlador específico.
                    CustomerMenu.run(customerController);
                    break;
                case 0:
                    // Opção para sair da aplicação.
                    System.out.println("Saindo do sistema. Até mais!");
                    break;
                default:
                    // Tratamento para opções inválidas.
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0); // Continua exibindo o menu até que o usuário escolha sair (opção 0).
    }
}
