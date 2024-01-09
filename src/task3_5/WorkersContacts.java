package task3_5;

import java.util.Date;

public class WorkersContacts {

    private String telephone;
    private String city;

    private String name;

    private Date birthDate;

    public WorkersContacts(String telephone, String city, String name) {
        this.telephone = telephone;
        this.city = city;
        this.name = name;
    }

    public WorkersContacts(String telephone, String name, Date birthDate) {
        this.telephone = telephone;
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate(){

        return birthDate;
    }
}
