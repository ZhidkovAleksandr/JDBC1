package Task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Q_Reader {

    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "skull_pass";
    private static final String FILE_PATH = "/Users/alexandrzhidkov/Documents/УчебаJava/Курс/JDCB/Lesson1/HW1/untitled/src/Task2/Source.txt";


    public static List<String> prepareRows(){
       List<String> list = new ArrayList<>();
       list.add("create database testbase;");
       list.add("use testbase;");
       list.add("create table testtable (id int auto_increment primary key,name varchar(30),surname varchar(30));");
        return  list;
    }

    public static void makeQuerry() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            try( BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    statement.addBatch(line);
                    line = reader.readLine();
                }
            }catch (IOException ioException){
                ioException.printStackTrace();
            }


            statement.executeBatch();



        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            connection.close();
            statement.close();
        }

    }

    public static boolean writeRows(List<String> list){
        try(FileWriter writer = new FileWriter(FILE_PATH, false))
        {

            for (String s:list) {


                writer.write(s);
                writer.append('\n');

            }

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws SQLException {

        List list = prepareRows();

        if(writeRows(list)){
            makeQuerry();
        }else{
            System.out.println("Something went wrong");
        }

    }

}
