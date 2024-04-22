package controller;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.sql.*;
import java.util.StringJoiner;
import java.util.spi.CalendarNameProvider;

import static java.sql.DriverManager.getConnection;
import static java.sql.DriverManager.getLoginTimeout;

public class ConnectDB {
    //Class.forName("com.mysql.cj.jdbc.Driver") ;
    private static String url  = "jdbc:mysql://localhost:3306/btl";
    private String user =  "root";
    private String password =  "";




    public  void showResutl() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(url, user , password);
            String showSql =  "select * from users";
            PreparedStatement statement = connection.prepareStatement(showSql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                var name = resultSet.getString("name");
                var point =  resultSet.getInt("point");
                System.out.println("name:" + name + "   point: " + point);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //  ở hàm này ta  sử dụng các thao tac  là khi thêm ten vào thi ta
    // cần kiểm tra xem nếu mà có tên thì ta kiểm tra xem điêm maf thấp hơn thi không thay thế điểm
    // còn nếu chưa có tên đó thì cho tên đó vào .
    public     void insertData(String name , int score) throws RuntimeException {
        try {
            Connection connection = DriverManager.getConnection(url , user , password);
            String checkSql = "select * from users where name = ?";
            PreparedStatement statement = connection.prepareStatement(checkSql);
            statement.setString(1,name);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){

                String existingName = resultSet.getString("name");
                int existingScore =  resultSet.getInt("point");
                System.out.println(existingScore);
                if (score  > existingScore && existingName.equals(name)){
                    String updatePoint =  "UPDATE users SET POINT = ? WHERE NAME = ?";
                    PreparedStatement updatePreparedStatement =  connection.prepareStatement(updatePoint);
                    updatePreparedStatement.setInt(1,score);
                    updatePreparedStatement.setString(2,name);

                    int rowUpdate = updatePreparedStatement.executeUpdate();
                    if (rowUpdate > 0){
                        System.out.println("update success !");
                    }else {
                        System.out.println("co the diem thap hown ten ");
                    }
                }
            }else{
                String insertSql = "INSERT INTO users(NAME , POINT) VALUES(?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,score);
                int rowUpdate = preparedStatement.executeUpdate();
                if (rowUpdate != 0){
                    System.out.println("update success !");
                }else{
                    System.out.println("fail");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) throws RuntimeException, SQLException {
            new ConnectDB().showResutl();
    }
}
