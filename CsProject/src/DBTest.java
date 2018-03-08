import java.sql.*;
/*
	Run with:
	java -cp postgresql-42.2.1.jar:. DBTest
*/

public class DBTest {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e);
        }

        System.out.println("Hej");

        String url = "jdbc:postgresql://horton.elephantsql.com:5432/ylsaiyqk";
        String username = "ylsaiyqk";
        String password = "G8hPbcpjJKe9gV8mUhAOAr-SM7YgsKgy";





        try {
            Connection db = DriverManager.getConnection(url, username, password);

            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select * from test3");
            while (rs.next()) {

                System.out.print(rs.getString(1) + " ");
                System.out.println(rs.getString(2) + " ");
            }
            rs.close();
            st.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
