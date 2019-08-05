package UserLog;

import java.net.URL;
//import org.apache.poi.EncryptedDocumentException;
//import ImportToExcel.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;
import java.util.Date;

//import org.apache.poi.EncryptedDocumentException;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class AttendenceSystemViewController {

	Splay_implementation splay=new Splay_implementation();
	POIforgfgWrite createNewFile=new POIforgfgWrite();
	ExcelFileUpdateExample1 updateTheFile=new ExcelFileUpdateExample1();
	/*AttendenceSystemViewController(){
		createNewFile.createFile();
	}*/
	//CreateExcelSheet create=new CreateExcelSheet();
	
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRollno;

    URL url;
   	//Media media;
   //	MediaPlayer mediaplayer;
   	AudioClip audio;
    
    void playSound(){
    	url=getClass().getResource("stapler.wav.wav");
		audio=new AudioClip(url.toString());
		audio.play();
    }
    
    void doAlert(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Alert..");
    	alert.setContentText(msg);
    	alert.show();
    }
    
    

    @FXML
    void markIn(ActionEvent event)  {

    	playSound();
    	//create.createSheet();
    	String name=txtName.getText();
    	String RollNo=txtRollno.getText();
    	if(txtName.getText().equals("") || txtRollno.getText().equals("") )
		{
			Alert alert=new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Alert..");
	    	alert.setContentText("Please fill the empty field");
	    	alert.show();
	    	System.out.println("hii");
	    	
		}
    	else{
    		/*DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    	       Date dateobj = new Date();
    	       System.out.println(df.format(dateobj));*/
    	       
    		int rollNo=Integer.parseInt(RollNo);
    		int x=splay.insert(name, rollNo);
    		//splay.preorder(splay.root);
    		if(x==0){
    			doAlert("Already Marked Present");
    			txtName.setText("");
    	    	txtRollno.setText("");
    		}
    		else{
    		
    	txtName.setText("");
    	txtRollno.setText("");
    	doAlert("Checked In");
    	//System.out.println(name);
    	//System.out.println(rollNo);
    		}
    	}
    }

    @FXML
    void markOut(ActionEvent event) {
    	
    	playSound();
    	String name=txtName.getText();
    	String RollNo=txtRollno.getText();
    	if(txtName.getText().equals("") || txtRollno.getText().equals("") )
		{
			Alert alert=new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Alert..");
	    	alert.setContentText("Please fill the empty field");
	    	alert.show();
	    	System.out.println("hii");
	    	
		}
    	else{
    		/*DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    	       Date dateobj = new Date();
    	       System.out.println(df.format(dateobj));*/
    	       
    		int rollNo=Integer.parseInt(RollNo);
    		Node temp=splay.splay_node(splay.root,rollNo);
    		System.out.println("here");
    		//System.out.println(temp.RollNo+" "+ temp.name+" "+temp.in+" "+temp.out);
    		if(temp==null || temp.RollNo!=rollNo){
    			doAlert("No such entry found");
    			txtName.setText("");
    	    	txtRollno.setText("");
    		}
    		else{
    			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    		    temp.out = new Date();
    		    //String s=df.format(temp.out);
    			System.out.println("deletion");
    		System.out.println(temp.RollNo+" "+ temp.name+" "+temp.in+" "+temp.out);
    		updateTheFile.UpdateFile(temp.RollNo, temp.name, df.format(temp.in), df.format(temp.out));
    		splay.delete(rollNo);
    	txtName.setText("");
    	txtRollno.setText("");
    	doAlert("Checked Out");
    	//System.out.println(name);
    	//System.out.println(rollNo);
    		}
    	}
    	
    }
    @FXML
    void initialize() {
    	createNewFile.createFile();
      	
    }

}
