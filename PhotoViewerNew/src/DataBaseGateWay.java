import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

/**
 * Created by EW043872 on 10/18/2015.
 */
public class DataBaseGateWay {
    private Connection con;
    private Statement stmt;
    String connectionString = "jdbc:mysql://kc-sce-appdb01.kc.umkc.edu/eawwwd";
    String userID = "eawwwd";
    String password = "ZDkWx5Dydgwe";

    DataBaseGateWay()throws SQLException{
        //initialize connection
        con = DriverManager.getConnection(connectionString, userID, password);
        //initialize statement
        stmt = con.createStatement();
        //create any needed tables
        createTables();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(java.lang.ClassNotFoundException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    //creates necessary tables if they are not found in the database
    public void createTables() throws SQLException {
        //String sqlcmd = "USE eawwwd;";

        String createTable = "CREATE TABLE IF NOT EXISTS Images" + "(id INT NOT NULL AUTO_INCREMENT,"
                + " date VARCHAR(24), description VARCHAR(256),"
                + " photo LONG VARBINARY NULL, ordr INT, PRIMARY KEY (id));";
        //stmt.execute(sqlcmd);
        stmt.execute(createTable);
    }

    public void DBcreateRecord(File file, String date, String description, int index) throws Exception{
        String incrementOrder = "UPDATE images SET ordr = ordr + 1 WHERE ordr >= ? ";
        String sqlUpdate = " insert into images(date, description, photo, ordr, imgsize) values (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        //Increment all values of ordr in table greater than the index we're trying to insert
        pstmt = con.prepareStatement(incrementOrder);
        pstmt.setInt(1, index);
        pstmt.executeUpdate();
        //File file = null;
        FileInputStream fileIn = null;

        pstmt = con.prepareStatement(sqlUpdate);

        //file = new File(filePath);
        fileIn = new FileInputStream(file);

        pstmt.setNString(1, date);
        pstmt.setNString(2, description);
        pstmt.setBlob(3, fileIn, (int) file.length());
        pstmt.setInt(4, index);
        pstmt.setInt(5, (int) file.length());
        pstmt.executeUpdate();
        fileIn.close();
        pstmt.close();
    }

    public Photograph DBgetRecord(int index) throws Exception{
        PreparedStatement ps = null;
        Photograph returnPhoto = new Photograph();
        ps = con.prepareStatement("select * from images where ordr = ? ;");
        ps.setInt(1, index);
        ResultSet rs = ps.executeQuery();
        rs.first();
        returnPhoto.setID(rs.getInt("id"));
        returnPhoto.setDate(rs.getString("date"));
        returnPhoto.setDescription(rs.getString("description"));

        InputStream in = rs.getBinaryStream("photo");
        int size = rs.getInt("imgsize");
        byte[] buffer = new byte[size];
        int currentLength = 0;
        int bytesRead = 0;
        while ((bytesRead = in.read(buffer, currentLength, size - currentLength)) != -1) {
            currentLength = currentLength + bytesRead;
        }

        ImageIcon newIcon = new ImageIcon(buffer);
        returnPhoto.setImage(newIcon);
        ps.close();
        return returnPhoto;
    }

    public int countRows() throws SQLException{
        //count number of rows, expect only an int in rs
        ResultSet rs = stmt.executeQuery("select count(*) from images;");
        rs.first();
        int count = rs.getInt(1);
        return count;
    }

    public void updateImageData(String description, String date, int index)throws SQLException{
        String updateSQL = "UPDATE images SET date = ?, description = ? WHERE id = ? ;";
        PreparedStatement pstmt = null;

        pstmt = con.prepareStatement(updateSQL);

        pstmt.setNString(1, date);
        pstmt.setNString(2, description);
        pstmt.setInt(3, index);

        pstmt.executeUpdate();
        pstmt.close();
    }

    public void DBdeleteRecord(int ind) throws SQLException{
        String deleteRecord = new String("DELETE FROM images WHERE ordr = ? ;");
        String updateOrder = new String("UPDATE images SET ordr = ordr - 1 WHERE ordr > ? ;");

        PreparedStatement pstmt = null;

        pstmt = con.prepareStatement(deleteRecord);
        pstmt.setInt(1, ind);
        pstmt.executeUpdate();

        pstmt = con.prepareStatement(updateOrder);
        pstmt.setInt(1, ind);
        pstmt.executeUpdate();

        pstmt.close();
    }
}
