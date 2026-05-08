package model;

/**
 * Representa um Cliente no sistema da barbearia.
 * Estende a classe abstrata User, definindo um cliente como um usuário não-administrador.
 */
public class Customer extends User {
    /**
     * Construtor para criar um novo objeto Cliente.
     * @param name O nome do cliente.
     *             Chama o construtor da classe pai (User) passando 'false' para isAdm,
     *             indicando que um cliente não tem permissões de administrador.
     */
    public Customer(String name) {
        super(name,false);
    }
}
