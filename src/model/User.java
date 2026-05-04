public abstract class User{
    // atributos de qualquer usuario
    private int id;
    private String name;
    private boolean adm;

    public User(String name) {
        this.id = id + 1;
        this.name = name;
    }

    public boolean isAdm() {
        return adm;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}