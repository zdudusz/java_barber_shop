package model;

/**
 * Representa um Barbeiro no sistema da barbearia.
 * Estende a classe abstrata User, definindo um barbeiro como um usuário administrador.
 */
public class Barber extends User{
    /**
     * Construtor para criar um novo objeto Barbeiro.
     * @param name O nome do barbeiro.
     *             Chama o construtor da classe pai (User) passando 'true' para isAdm,
     *             indicando que um barbeiro tem permissões de administrador.
     */
    public Barber(String name){
        super(name,true);
    }
}
