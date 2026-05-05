public class Customer extends User {
    public Customer(String name) {
        super(name,false);
    } //construtor passando adm como false para o usuario não ter acesso às permissões adm
}
