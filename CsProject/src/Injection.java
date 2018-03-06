import java.sql.*;
import java.util.Scanner;
/*
	Run with:
	java -cp postgresql-42.2.1.jar:. Injection
*/

public class Injection {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e);
        }

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter Username");
		String username= keyboard.nextLine();

		System.out.println("Enter Password");
		String password= keyboard.nextLine();


        String dburl = "jdbc:postgresql://horton.elephantsql.com:5432/yskasagh";
        String dbusername = "yskasagh";
        String dbpassword = "8fDkBe3WB2XjNkJKy3G5c5XLlPQZagsj";





        try {
            Connection db = DriverManager.getConnection(dburl, dbusername, dbpassword);

            Statement st = db.createStatement();
			String query = "select * from datastore where name = '" + username + "' and password = '" + password + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                System.out.print(rs.getString(1) + " ");
                System.out.print(rs.getString(2) + " ");
                System.out.println(rs.getString(3));
            }
            rs.close();
            st.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
