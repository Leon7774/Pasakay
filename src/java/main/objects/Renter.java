package main.objects;

import java.time.LocalDate;

public class Renter {
    int renterID;
    String firstName;
    String lastName;
    String status;
    String sex;
    int age, contact_number, license_number;
    private LocalDate birthdate;

    public Renter(int renter_id, String firstName, String lastName, String status, String sex, LocalDate birthdate, int age, int contact_number, int license_number) {

        this.renterID = renter_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.age = age;
        this.sex = sex;
        this.contact_number = contact_number;
        this.license_number = license_number;
        this.birthdate = birthdate;
    }

    public int getRenterID() {
        return renterID;
    }

    public void setRenterID(int renterID) {
        this.renterID = renterID;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getContact_number() {return contact_number;}
    public void setContact_number(int contact_number) {this.contact_number = contact_number;}
    public int getLicense_number() {return license_number;}
    public void setLicense_number(int license_number) {this.license_number = license_number;}
    public LocalDate getBirthdate() {return birthdate;}
    public void setBirthdate(LocalDate birthdate) {this.birthdate = birthdate;}
}
