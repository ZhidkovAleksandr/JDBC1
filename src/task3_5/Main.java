package task3_5;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {


       // String q1 = "select * from Workers";
       String q1 =  "SELECT Workers.name,Workers.telephone,personalInfo.city FROM Workers INNER JOIN personalInfo ON Workers.id = personalInfo.personalId";

        String q2 = "SELECT Workers.name,\n" +
                " Workers.telephone,\n" +
                " personalInfo.city\n" +
                " FROM Workers\n" +
                " INNER JOIN personalInfo ON Workers.id = personalInfo.personalId\n" +
                " WHERE NOT personalInfo.married;";

        String q3 ="SELECT Workers.name,\n" +
                " Workers.telephone,\n" +
                " personalInfo.birthDate\n" +
                " FROM Workers\n" +
                " INNER JOIN personalInfo ON Workers.id = personalInfo.personalId\n" +
                " INNER JOIN salary ON Workers.id = salary.salaryId\n" +
                " WHERE salary.post = 'manager'";


        try {
            QuerryWorker.getRes();
        }catch (SQLException e){
            e.printStackTrace();
        }



    }



}
