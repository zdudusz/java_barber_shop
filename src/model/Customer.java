public class Client extends User {
    public Client(String name) {
        super(name,false);
    } //construtor passando adm como false para o usuario não ter acesso às permissões adm
}
