package kz.bitlab.javaee.group29.db;

public class Publications {
    private Long id;
    private String name;
    private String description;
    private Double rating;


    public Publications(String name, String description, Double rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public Publications(Long id, String name, String description, Double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public Publications(){}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
