import javax.swing.*;
import java.io.Serializable;

/**
 * Created by EW043872 on 10/6/2015.
 */
public class Photograph implements Serializable {
    ImageIcon image = new ImageIcon();
    String date = new String("");
    public String description = new String("");
    int id;

    Photograph(ImageIcon img){
        image = img;
    }

    Photograph(){}

    public void setImage(ImageIcon img){
        image = img;
    }

    public ImageIcon getImage(){
        return image;
    }

    public void setDate(String dat ){
        date = dat;
    }

    public String getDate(){
        return date;
    }

    public void setDescription(String desc){
        description = desc;
    }

    public String getDescription(){
        return description;
    }

    public void setID(int newID){id = newID;}

    public int getID(){return id;}

}
