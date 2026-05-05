public abstract class User{
    // atributos de qualquer usuario
    private int id;
    private String name;
    private boolean isAdm;

    //construtor de qualquer usuario
    public User(String name,boolean _adm) {
        this.id = id + 1;
        this.name = name;
        this.isAdm = _adm;
    }

    public boolean isAdm() {
        return this.isAdm;
    } // metodo para ter acesso à permissão de adm do usuario (se é adm ou não)

    public int getId() {
        return this.id;
    }//metodo para pegar o id

    public String getName() {
        return this.name;
    }//metodo para pegar o nome

    public void setName(String name) {
        this.name = name;
    }//metodo para setar o nome
}