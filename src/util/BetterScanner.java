package util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe utilitária para facilitar a leitura de entradas do usuário via console,
 * incluindo validação básica para tipos numéricos e tratamento de erros.
 */
public class BetterScanner {
    // O objeto Scanner é estático para ser compartilhado por todos os métodos da classe.
    // Isso evita a criação de múltiplas instâncias de Scanner, o que pode causar problemas
    // com o fechamento de recursos e a leitura de entrada.
    private static Scanner sc = new Scanner(System.in);

    /**
     * Solicita e lê um número inteiro do usuário, com tratamento de erro para entradas inválidas.
     * Continua solicitando até que um número inteiro válido seja fornecido.
     * @param mensagem A mensagem a ser exibida ao usuário antes da entrada.
     * @return O número inteiro válido digitado pelo usuário.
     */
    public static int inputInt(String mensagem) {
        while (true) { // Loop infinito até que uma entrada válida seja recebida.
            try {
                System.out.print(mensagem); // Exibe a mensagem para o usuário.
                int num = sc.nextInt(); // Tenta ler um inteiro.
                sc.nextLine(); // Consome a quebra de linha pendente após nextInt(), evitando problemas com nextLine() subsequentes.
                return num; // Retorna o número inteiro válido.
            } catch (InputMismatchException e) {
                // Captura a exceção se o usuário digitar algo que não é um inteiro.
                System.err.println(" Erro: Digite um número inteiro válido."); // Informa o erro.
                sc.nextLine(); // Limpa a entrada inválida do buffer do Scanner.
            }
        }
    }

    /**
     * Solicita e lê um número decimal (double) do usuário, com tratamento de erro para entradas inválidas.
     * Continua solicitando até que um número decimal válido seja fornecido.
     * @param mensagem A mensagem a ser exibida ao usuário antes da entrada.
     * @return O número decimal válido digitado pelo usuário.
     */
    public static double inputDouble(String mensagem) {
        while (true) { // Loop infinito até que uma entrada válida seja recebida.
            try {
                System.out.print(mensagem); // Exibe a mensagem para o usuário.
                double num = sc.nextDouble(); // Tenta ler um double.
                sc.nextLine(); // Consome a quebra de linha pendente.
                return num; // Retorna o número decimal válido.
            } catch (InputMismatchException e) {
                // Captura a exceção se o usuário digitar algo que não é um double.
                System.err.println(" Erro: Digite um número decimal válido (use vírgula ou ponto conforme seu sistema)."); // Informa o erro.
                sc.nextLine(); // Limpa a entrada inválida do buffer.
            }
        }
    }

    /**
     * Solicita e lê uma linha de texto (String) do usuário.
     * @param mensagem A mensagem a ser exibida ao usuário antes da entrada.
     * @return A string digitada pelo usuário.
     */
    public static String inputString(String mensagem) {
        System.out.print(mensagem); // Exibe a mensagem para o usuário.
        return sc.nextLine(); // Lê a linha completa de texto.
    }
}
