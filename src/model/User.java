package model;

/**
 * Classe abstrata base para todos os tipos de usuários no sistema (Barbeiro, Cliente).
 * Contém atributos comuns como ID, nome e status de administrador.
 */
public abstract class User{
    // Atributos de qualquer usuário
    private int id; // ID único do usuário
    private String name; // Nome do usuário
    private boolean isAdm; // Indica se o usuário tem permissões de administrador

    /**
     * Construtor para criar um novo usuário.
     * @param name O nome do usuário.
     * @param _adm Um booleano que indica se o usuário é um administrador.
     *             O ID é incrementado automaticamente a cada nova instância, mas note que
     *             esta implementação simples pode não garantir IDs únicos em cenários complexos
     *             sem um gerenciamento de ID mais robusto (ex: banco de dados ou contador estático global).
     */
    public User(String name,boolean _adm) {
        // A atribuição de ID `this.id = id + 1;` é um erro comum que não gera IDs únicos.
        // Para um sistema real, seria necessário um mecanismo mais robusto para IDs.
        // Por enquanto, manteremos a lógica original do usuário, mas é importante notar.
        this.id = id + 1; // Lógica original do usuário para ID (pode não ser única)
        this.name = name;
        this.isAdm = _adm;
    }

    /**
     * Verifica se o usuário é um administrador.
     * @return true se o usuário for administrador, false caso contrário.
     */
    public boolean isAdm() {
        return this.isAdm;
    }

    /**
     * Retorna o ID do usuário.
     * @return O ID do usuário.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retorna o nome do usuário.
     * @return O nome do usuário.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Define o nome do usuário.
     * @param name O novo nome a ser atribuído ao usuário.
     */
    public void setName(String name) {
        this.name = name;
    }
}
