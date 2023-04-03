package _3ButtonsForSelectedRole.skeleton;
public class Authors {
     int id;
     String surname;
     String name;
     String patronymic;
    public Authors(int id, String surname, String name, String patronymic) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }
    public int getId() {
        return id;
    }
    public String getSurname() {
        return surname;
    }
    public String getName() {
        return name;
    }
    public String getPatronymic() {
        return patronymic;
    }
}
