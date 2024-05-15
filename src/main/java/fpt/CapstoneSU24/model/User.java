package fpt.CapstoneSU24.model;

import jakarta.persistence.*;

@Entity
@Table(name = "[User]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_User")
    private int idUser;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    @ManyToOne
    @JoinColumn(name = "Id_Role")
    private Role role;
    @Column(name = "First_Name", columnDefinition = "nvarchar(50)")
    private String firstName;
    @Column(name = "Last_Name", columnDefinition = "nvarchar(50)")
    private String lastName;
    @Column(name = "Description", columnDefinition = "nvarchar(255)")
    private String description;
    @Column(name = "Address", columnDefinition = "nvarchar(255)")
    private String address;
    @Column(name = "Country", columnDefinition = "nvarchar(50)")
    private String country;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Age")
    private int age;
    @Column(name = "Supporting_Documents", columnDefinition = "nvarchar(255)")
    private String supportingDocuments;

    public User(){

    }

    public User(int idUser, String email, String password, Role role, String firstName, String lastName, String description, String address, String country, String phone, int age, String supportingDocuments) {
        this.idUser = idUser;
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.address = address;
        this.country = country;
        this.phone = phone;
        this.age = age;
        this.supportingDocuments = supportingDocuments;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSupportingDocuments() {
        return supportingDocuments;
    }

    public void setSupportingDocuments(String supportingDocuments) {
        this.supportingDocuments = supportingDocuments;
    }
}
