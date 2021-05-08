package kz.bitlab.javaee.group29.db;

public class Users {

    private Long id;
    private String email;
    private String fullName;
    private int age;

    public Users(String email, String fullName, int age) {
        this.email = email;
        this.fullName = fullName;
        this.age = age;
    }

    public Users(Long id, String email, String fullName, int age) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.age = age;
    }

    public Users(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
