package main.objects;

public class Renter {
    int renterID;
    String firstName;
    String lastName;
    String status;
    String sex;
    int age, contact_number, license_number;

    public Renter(int renter_id, String firstName, String lastName, String status, String sex, int age, int contact_number, int license_number) {

        this.renterID = renter_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.age = age;
        this.sex = sex;
        this.contact_number = contact_number;
        this.license_number = license_number;
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
}
