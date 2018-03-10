
import java.sql.*;
import java.util.Scanner;

/*
	Run with:
	java -cp postgresql-42.2.1.jar:. DBTest
 */

public class CsDatabase {

    String url = "jdbc:postgresql://horton.elephantsql.com:5432/ylsaiyqk";
    String username = "ylsaiyqk";
    String password = "G8hPbcpjJKe9gV8mUhAOAr-SM7YgsKgy";

    
    public void runloop(){
        boolean run = true;
        Scanner sc = new Scanner(System.in);
        char command;
        System.out.println("Welcome to the DM 505 test application you have the folowing choices: "
                + "\n a:\t A list of all names of coahes and the team they belong to. "
                + "\n b:\t A List of all names of people (players   and   coachers) who are on a team, which has won at least one tournament."
                + "\n c:\t A List of all names of teams and the number of players on that team."
                + "\n d:\t A list of turnaments whit a minimum number of partisipating teams based on a input number."
                + "\n pleas enter the char of the disired query (a,b,c,d,) and then hit the retrun key, input q if you want to exit the application.");
        while(run){
            command = sc.next().charAt(0);
            if (command == 'a'){
               assignmentA();
            }
            else if (command == 'b'){
                assignmentB();
            }
            else if (command == 'c'){
                assignmentC();
            }
            else if (command == 'd'){
                System.out.println("Please input the number of Partisipating teams");
                assignmentD(sc.nextInt());
            }
            else if (command == 'q'){
                run = false;
                System.out.println("you have quit the application");
            }
            else{
                System.out.println("your input didnt register correct pleas try again"); 
            }
                    }
    }
    public void queryTest() {
        try {
            Connection db = DriverManager.getConnection(url, username, password);

            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select * from Player");
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

    private void assignmentA() {
        // List of all names of coaches and the team they belong to.
          try {
            Connection db = DriverManager.getConnection(url, username, password);

            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select name, teamname from people, coaches where people.email = coaches.email");
            while (rs.next()) {

                System.out.print("Coach name: " + rs.getString(1) + " ");
                System.out.println("Team name: " + rs.getString(2) + " ");
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void assignmentB() {
          try {
            Connection db = DriverManager.getConnection(url, username, password);

            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select People.Name, Team.Name from Player inner join Winner on Player.TeamName = Winner.TeamName union Select People.Name, Team.Name from Coach inner join Winner on Coach.TeamName = Winner.Team");
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

    private void assignmentC() {
        try {
            Connection db = DriverManager.getConnection(url, username, password);

            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select Team, COUNT(*) from Player GROUP BY Team");
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

    private void assignmentD(int nextInt) {
        System.out.println("test d, whith number " + nextInt);
    }
    
        public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e);
        }
        CsDatabase test = new CsDatabase();

        test.queryTest();
        test.runloop();

    }

}
