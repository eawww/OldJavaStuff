import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by EW043872 on 10/4/2015.
 */
public class PhotoCollection extends Thread implements Serializable {

    public ArrayList<Photograph> photos = new ArrayList<>();
    Photograph currentPhoto = null;
    int currentPhotoNum = 0;
    DataBaseGateWay DBGW;

    @Override
    public void run(){
        try {
            currentPhoto = DBGW.DBgetRecord(currentPhotoNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PhotoCollection(){
        try {
            DBGW = new DataBaseGateWay();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //DEBUG
        System.out.print("DATABASE COUNT " + Integer.toString(getCollectionSize()) + "\n");

        try {
            if(getCollectionSize() > 0)
            currentPhoto = DBGW.DBgetRecord(1);
        } catch (Exception e) {
            System.out.print("Error getting first image.");
            e.printStackTrace();
        }
    }



    public void addPhoto(File file, int index){
        String date = "";
        String description = "";
        try {
            DBGW.DBcreateRecord(file, date,description, index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePhoto(int index){
        try {
            DBGW.DBdeleteRecord(index);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Photograph getPhoto(){

        return currentPhoto;
    }

    public int getCollectionSize(){
        int count = 0;
        try {
            count = DBGW.countRows();
        } catch (SQLException e) {
            System.out.print("Error getting collection size.\n");
            e.printStackTrace();
        }
        return count;
    }

    public void setDescription(int index, String desc){
        photos.get(index).description = desc;
    }

    public String getDescription(){
        return currentPhoto.description;
    }

    public String getDate(){ return currentPhoto.date;}

    public void saveData (String description, String date){
        try {
            DBGW.updateImageData(description, date, currentPhoto.id);
        } catch (SQLException e) {
            System.out.print("Error updating image data.");
            e.printStackTrace();
        }

    }

    public void setCurrentPhotoNum(int num){
        currentPhotoNum = num;
    }
}
