public class User {
    public int id;
    public String name;
    public Users person;

    public User(String name, Users person, int id) {
        this.name = name;
        this.person = person;
        this.id = id;
    }

    public String toString() {
        return  id + " " + name + " " + person;
    }
}