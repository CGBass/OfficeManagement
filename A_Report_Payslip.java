package office_managment;


import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.TableColumn;

public class A_Report_Payslip extends JFrame
{   
	A_Report_Payslip()
	{
		Vector columnNames = new Vector();
		Vector data = new Vector();
		try
		{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                                            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Office_db?user=root&password=");
		Statement st=con.createStatement();	
		String sql = "Select * from Payslip_tb";
		ResultSet rs = st.executeQuery( sql );
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		for (int i = 1; i <= columns; i++)
		{
			columnNames.addElement( md.getColumnName(i) );
		}
		while (rs.next())
		{
			Vector row = new Vector(columns);
			for (int i = 1; i <= columns; i++)
			{
				row.addElement( rs.getObject(i) );
			}
			data.addElement( row );
		}
		rs.close();
		st.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		JTable table = new JTable(data, columnNames);
		TableColumn col;
		for (int i = 0; i < table.getColumnCount(); i++)
		{
			col = table.getColumnModel().getColumn(i);
			col.setMaxWidth(400);
		}
		JScrollPane scrollPane = new JScrollPane( table );
		add( scrollPane );
	}    
}
