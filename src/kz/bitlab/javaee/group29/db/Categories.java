package kz.bitlab.javaee.group29.db;

public class Categories {
    private Long id;
    private String name;

    public Categories(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Categories() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
