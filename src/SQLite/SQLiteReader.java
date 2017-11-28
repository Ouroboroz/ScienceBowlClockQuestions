package SQLite;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLiteReader {
	public static ArrayList<SQLiteItem> questionsArray = new ArrayList<SQLiteItem>(); 
	public static Connection connect() {
		String url = "jdbc:sqlite:./Resources/SQLite/questionsDB";
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(url);
		} catch(SQLException e){
			System.out.println(e.getMessage());
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:../Resources/SQLite/questionsDB");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				try {
					conn = DriverManager.getConnection("jdbc:sqlite:/Resources/SQLite/questionsDB");
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
		return conn;
	}
	public static void selectRound(String round){
		int actualNumber = 1;
		int previousNumber = 0;
		String type = "Toss Up";
		questionsArray.clear();
		String sql = "SELECT topic, qnumber, question, answer FROM questions WHERE round ==\""+round+"\"";
		try(Connection conn = connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			while(rs.next()){
				if(rs.getInt("qnumber") == 0)
					actualNumber = 1;
				else
					actualNumber = rs.getInt("qnumber");
				if(!(actualNumber == previousNumber))
					type = "Toss Up";
				else
					type = "Bonus";
				questionsArray.add(new SQLiteItem(actualNumber, type, rs.getString("topic"), rs.getString("question"), rs.getString("answer")));
				previousNumber = rs.getInt("qnumber");
			}
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static ArrayList<SQLiteItem> getQuestionsArray(){
		return questionsArray;
	}
}
