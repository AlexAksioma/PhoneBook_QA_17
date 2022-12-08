package models;

public class Contact {
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String addres;
    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public Contact withName(String name) {
        this.name = name;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Contact withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Contact withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contact withEmail(String email) {
        this.email = email;
        return this;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public Contact withAddres(String addres) {
        this.addres = addres;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Contact withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddres() {
        return addres;
    }

    public String getDescription() {
        return description;
    }
}
