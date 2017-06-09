import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.io.*;
import java.util.Vector;

public class MySqlExample {

    private Connection con;
    private Statement stmt;

    // Load class and create a connection to the database
    public MySqlExample() throws SQLException {

        // Connection string
        String connectionString = "jdbc:mysql://kc-sce-appdb01.kc.umkc.edu/eawwwd";
        String userID = "eawwwd";
        String password = "ZDkWx5Dydgwe";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(java.lang.ClassNotFoundException e) {
            System.out.println(e);
            System.exit(0);
        }

        con = DriverManager.getConnection(connectionString, userID, password);
        stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM accounts;");
        displayResultSet(rs, System.out);
        Vector<Vector<Object>> dataModel = createObjectArray(rs);

        AccountTransactionLayout atl = new AccountTransactionLayout(dataModel);

        atl.transferAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int amount = atl.getAmount();
                int from = atl.getfromNum();
                int to = atl.getToNum();
                int fromActAmt = 0;
                ResultSet temp;
                ResultSet query;
                try {
                    temp = stmt.executeQuery("SELECT balance FROM accounts WHERE acct = " +
                    from + ";");
                    temp.first();
                    fromActAmt = temp.getInt(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (fromActAmt >= amount){
                    try {
                        stmt.executeUpdate("UPDATE accounts SET balance = balance +  " +
                        amount + " WHERE acct = " + to + ";");
                        stmt.executeUpdate("UPDATE accounts SET balance = balance - " +
                        amount + " WHERE acct = " + from + ";");
                        /*TEST*/ query = stmt.executeQuery("SELECT * from accounts");
                        displayResultSet(query, System.out);
                        Vector<Vector<Object>> newTable = createObjectArray(query);
                        atl.updateWholeTable(newTable);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    }
                else {
                    JOptionPane.showMessageDialog(atl, "Invalid Amount! Do you want overdraft fees because "+
                    "this is how you get overdraft fees");
                }

            }
        });

        atl.clearAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                atl.clearInputFields();
            }
        });
    }

    private Vector<Vector<Object>> createObjectArray(ResultSet results) throws SQLException {
        Vector<Vector<Object>> vectorvector = new Vector();
        ResultSetMetaData md = results.getMetaData();
        int numColumns = md.getColumnCount();
        results.first();
        do {
            Vector<Object> currentRowData = new Vector();
            for(int i = 0; i < numColumns; i++){
                currentRowData.add(i,results.getString(i + 1) );
            }
            vectorvector.addElement(currentRowData);
        } while (results.next());
        return vectorvector;
    }


    public void createTables() throws SQLException {
        //String sqlcmd = "USE eawwwd";

        String createTable = "CREATE TABLE IF NOT EXISTS Accounts" + "(acct INT,"
                + "acct_name VARCHAR(64),"
                + "balance FLOAT);";
        //stmt.execute(sqlcmd);
        stmt.execute(createTable);
    }

    public void updateTable() throws SQLException {

    }

    public void queryTable(int acct) throws SQLException {
        String sqlCmd = "SELECT acct_name,balance FROM accounts " +
                "WHERE acct = " + Integer.toString(acct) + ";";
        ResultSet rs = stmt.executeQuery(sqlCmd);
        displayResultSet(rs,System.out);
    }

    public void displayResultSet (ResultSet rs, PrintStream out) {
        int i;

        out.println();
        out.println();
        out.println("Result Set:");
        out.println("------------------------");
        out.println();

        try {
            ResultSetMetaData rsmd = rs.getMetaData ();

            int numCols = rsmd.getColumnCount ();

            for (i=1; i<=numCols; i++) {
                out.print(rsmd.getColumnLabel(i));
                out.print(" ");
            }
            out.println();

            boolean more = rs.next ();
            while (more) {
                for (i=1; i<=numCols; i++) {
                    // Every field value is fetched as a string
                    // JDBC will convert the values from their
                    // stored type to string type
                    out.print(rs.getString(i));
                    out.print(" ");
                }
                out.println();

                more = rs.next ();
            }
            out.println();
        }
        catch (SQLException ex) {
            out.println ("*** SQLException");

            while (ex != null) {
                out.println ("SQLState: " + ex.getSQLState ());
                out.println ("Message: " + ex.getMessage ());
                out.println ("Vendor: " + ex.getErrorCode ());
                ex = ex.getNextException ();
                out.println ("");
            }
        }
    }

    public static void main(String args[]) {
        try {
            MySqlExample ex = new MySqlExample();
            System.out.println("If no exceptions, you successfully connected to your DB.");

          ex.createTables();
            ex.queryTable(1);
        }
        catch (SQLException e) {
            System.err.println(e);
        }
    }
}