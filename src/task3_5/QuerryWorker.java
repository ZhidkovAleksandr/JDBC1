package task3_5;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuerryWorker {

    private static final String URL = "jdbc:mysql://localhost:3306/LearningJoins";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "skull_pass";

    private static List<String> CreateArOfQ() {

        List<String> qs = new ArrayList<>();

        qs.add("SELECT Workers.name,Workers.telephone,personalInfo.city FROM Workers INNER JOIN personalInfo ON Workers.id = personalInfo.personalId");
        qs.add("SELECT Workers.name, Workers.telephone, personalInfo.city FROM Workers INNER JOIN personalInfo ON Workers.id = personalInfo.personalId WHERE NOT personalInfo.married");
        qs.add("SELECT Workers.name, Workers.telephone, personalInfo.birthDate FROM Workers INNER JOIN personalInfo ON Workers.id = personalInfo.personalId INNER JOIN salary ON Workers.id = salary.salaryId WHERE salary.post = 'manager'");
        return qs;
    }

    public static void getRes() throws SQLException {

        List<String> qs = CreateArOfQ();
        List<WorkersContacts> workerList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            for (String qAddress : qs) {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(qAddress);
                int i = qs.indexOf(qAddress);
                while (resultSet.next()) {


                    if (i != 2) {
                        WorkersContacts cWork = new WorkersContacts(resultSet.getString("telephone"),
                                resultSet.getString("city"),
                                resultSet.getString("name"));

                        workerList.add(cWork);
                    }else {
                        WorkersContacts cWork = new WorkersContacts(resultSet.getString("telephone"),
                                resultSet.getString("birthDate"),
                                resultSet.getString("name"));

                        workerList.add(cWork);
                    }



                }

            }

            for (WorkersContacts w : workerList) {

                System.out.println(w.getName() + " " + ((w.getCity()==null) ? " " : w.getCity()) + " " + w.getTelephone() + " " + ((w.getBirthDate()==null) ? " " : w.getBirthDate()));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
            statement.close();
            resultSet.close();
        }


    }

}
