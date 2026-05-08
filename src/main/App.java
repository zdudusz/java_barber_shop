package main;

import controller.ScheduleController;
import model.Barber;
import model.Customer;
import model.Schedule;
import view.MainMenu;

/**
 * Classe principal que inicia a aplicação da barbearia.
 * Responsável por configurar os componentes iniciais (modelos e controladores)
 * e iniciar o menu principal da aplicação.
 */
public class App {
    /**
     * Método principal (entry point) da aplicação Java.
     * @param args Argumentos de linha de comando (não utilizados nesta aplicação).
     */
    public static void main(String[] args) {
        // 1. Inicialização dos Modelos e Controladores de Negócio
        // Cria uma instância da agenda, que gerencia os horários disponíveis.
        Schedule schedule = new Schedule();
        // Cria o controlador central de agendamentos, que manipula a agenda.
        ScheduleController scheduleController = new ScheduleController(schedule);

        // 2. Criação de Usuários de Exemplo
        // Para fins de demonstração, um barbeiro e um cliente são criados.
        // Em uma aplicação real, estes seriam carregados de um banco de dados ou sistema de autenticação.
        Barber barber = new Barber("João Barbeiro");
        Customer customer = new Customer("Maria Cliente");

        // 3. Inicia a Interface do Usuário
        // Chama o método 'run' do MainMenu, passando o controlador de agendamentos
        // e os usuários de exemplo para que os menus possam interagir com eles.
        MainMenu.run(scheduleController, barber, customer);
    }
}
