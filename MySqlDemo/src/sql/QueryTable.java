package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class QueryTable {

	public static void main(String[] args) throws SQLException
	{

		long startTime = System.nanoTime();



		ArrayList<String> Regs = new ArrayList<String>();
		ArrayList<String> uRegs = new ArrayList<String>();
		
		try {
			Statement myStatement = connect();
			
			ResultSet myRs = myStatement.executeQuery("select * from adbEx_02 WHERE Mil = 'true'");
			
//			//			 Process results
//			while (myRs.next()) {
//				Regs.add(myRs.getString(1));
//			}

			
			while (myRs.next()) {
				
				String reg = myRs.getString("Reg");
				ArrayList[] regs = new ArrayList[3];
				java.util.Date time = new java.util.Date((long)myRs.getLong("FSeen")*1000);
//				BigDecimal[] coorda = new BigDecimal[] {myRs.getBigDecimal(15), myRs.getBigDecimal(16)};
				String coord = myRs.getString(15) + ", " + myRs.getString(16);
		
				
				System.out.println(myRs.getString("Reg") + "\t\t" + coord + "\t\t" + time + "\t\t" + myRs.getString("Op") + "\t\t" + myRs.getString("Mdl") + "\t" + myRs.getString("Cou"));
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

	public static Statement connect() throws SQLException {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightradar", "flt", "flt123");
		Statement myStatement = myConn.createStatement();
		return myStatement;
	}

}
