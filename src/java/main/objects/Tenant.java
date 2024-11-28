package main.objects;

public class Tenant {
    private int tenantID;
    private String firstname;
    private String lastname;
    private int age;
    private String status;
    private int propertyID;
    private String sex;

    public Tenant(int tenantID, String firstname, String lastname, int age, String status, int propertyID, String sex) {
        this.tenantID = tenantID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.status = status;
        this.propertyID = propertyID;
        this.sex = sex;
    }

    public int getTenantID() {
        return tenantID;
    }

    public void setTenantID(int tenantID) {
        this.tenantID = tenantID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
