package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {

		long startTime = System.nanoTime();



		ArrayList<String> Regs = new ArrayList<String>();
		ArrayList<String> uRegs = new ArrayList<String>();

		try {
			Statement myStatement = connDB();

			//			 Run Query
			ResultSet myRs = myStatement.executeQuery("select * from adbEx_02 WHERE Mil = 'true'");

			//			 Process results
			while (myRs.next()) {
				String reg = myRs.getString("Reg");
				java.util.Date time = new java.util.Date((long)myRs.getLong("FSeen")*1000);
				System.out.println(myRs.getString("Reg") + "\t\t" + myRs.getString(15) + ", " + myRs.getString(16) + time + "\t\t" + myRs.getString("Op") + "\t\t" + myRs.getString("Mdl") + "\t" + myRs.getString("Cou"));
				uRegs.add(reg);
				if (!Regs.contains(reg)) {
					Regs.add(reg);
				}

			}
			System.out.println(Regs.size() + " " + uRegs.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		double seconds = (double)totalTime / 1000000000.0;
		System.out.println("Query took " + seconds + "seconds");

	}

	public static Statement connDB() throws SQLException {
		//			 Get DB connection
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightradar", "flt", "flt123");
		//			 Statement
		Statement myStatement = myConn.createStatement();
		return myStatement;
	}

}
