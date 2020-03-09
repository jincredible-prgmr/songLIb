//Authors: Jin Kim
package view;
import java.io.*;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
//import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Controller{
	@FXML         
	ListView<String> listView;                
	@FXML
	TextArea details;
	@FXML
	Button deletes;
	@FXML
	Button addSong;
	@FXML
	Button editSong;
	@FXML
	TextField SongName;
	@FXML
	TextField ArtistName;
	@FXML
	TextField AlbumName;
	@FXML
	TextField SongDesc;
	
	
	private ObservableList<String> obsList;
	private ArrayList<Song> Songlist=new ArrayList<Song>();
	//Function that displays song details in the details text area
	public void writesave(ArrayList<Song> Songlist) throws IOException {

		
		FileWriter w=new FileWriter("Songlistsave.txt");
		w.write("");
		
		
		int i=0;
		while(i<Songlist.size()) {
			w.write(Songlist.get(i).getName()+"\n");
			w.write(Songlist.get(i).getArtist()+"\n");
			w.write(Songlist.get(i).getAlbum()+"\n");
			w.write(Songlist.get(i).getDescription()+"\n");
			i++;
		}
		w.close();
		
		
		
		
		
	}
	
	
	
	public void readsave(ArrayList<Song> Songlist) throws FileNotFoundException {
		
		File saved=new File("Songlistsave.txt");
		
		Scanner r=new Scanner(saved);
		
		while(r.hasNextLine()) {
			
			
			String name=r.nextLine();
			String artist=r.nextLine();
			String album=r.nextLine();
			String desc=r.nextLine();
			//desc=desc.replace("\\n", "\n");//prototype
			
			Song s=new Song(name,artist,album,desc);
			Songlist.add(s);
			
			
			
			
		}
		//System.out.println("\\n");
		r.close();
		
		
		
	
	
	}
	public void details(Song s) {
		String songDetails="Song name: "+s.getName()+"\nArtist name: "+s.getArtist()+"\nAlbum name: "+s.getAlbum()+"\nYear: "+s.getDescription();
		
		details.setText(songDetails);
		
	}
	
	public void start(Stage mainStage) throws FileNotFoundException {                
		//Songs for testing
		//Song s=new Song("hello world3","michael","javasongs","very cringy");
		//Song e=new Song("hello world3","jin","javasongs","very cringy");
		//Song f=new Song("hello world3","sesh","javasongs","very cringy");
		//Songlist.add(s);
		//Songlist.add(e);
		//Songlist.add(f);
		//read contents of saved file into list
		readsave(Songlist);
		
		obsList = FXCollections.observableArrayList(); 
		
		if(Songlist.size()!=0) {
		Songlist.sort(Comparator.comparing(Song::getId));
		int i=0;
		while(i<Songlist.size()) {
			obsList.add(Songlist.get(i).getName());
			i++;
			
		}
		
		
		listView.setItems(obsList); 
		
		// select the first item
	      
		listView.getSelectionModel().select(0);
	      
	      details(Songlist.get(0));
	          
	      listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> TextArea());
		
		
		
		}else {
			listView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> TextArea());
		}
	}
	//Controls the details textarea which displays the selected song
	private void TextArea() {                
	      
		
		
	      
	      int index = listView.getSelectionModel().getSelectedIndex();
	      if(obsList.size()==0) {
				
				details.setText("");
				return;
			}
	      
	      details(Songlist.get(index));
	      
	}
	public void del(ActionEvent e) throws IOException {
		Button d = (Button)e.getSource();
		d=(Button)e.getSource();
		if(d==deletes) {
			int index = listView.getSelectionModel().getSelectedIndex();
			
			
			//checks if list is empty
			if(index==-1) {
				
				return;
			}
			
			Alert a = new Alert(AlertType.NONE); 

			// set alert type 
            a.setAlertType(AlertType.CONFIRMATION); 

            // set content text 
            a.setContentText("Are you sure you want to delete?"); 

            // show the dialog 
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.CANCEL){
				return;
            	} 
            
			Songlist.remove(index);
			obsList.remove(index);
			Songlist.sort(Comparator.comparing(Song::getId));
			
			//checks if list is empty after removal
			if(Songlist.size()==0) {
				writesave(Songlist);
				return;
			}
			
			if(index==Songlist.size()) {
				writesave(Songlist);
				listView.getSelectionModel().select(index-1);
				details(Songlist.get(index-1));
				
				listView.setItems(obsList);
				
			}else {
			//update save file
				writesave(Songlist);
				
			listView.getSelectionModel().select(index);
			details(Songlist.get(index));
			
			listView.setItems(obsList); 
		
			}
		
		}
	
	}//Does not handle error conditions
		public void add(ActionEvent e) throws IOException {
			Button d = (Button)e.getSource();
			d=(Button)e.getSource();
			if(d==addSong) {
				//gets details from the respective textfields and areas
				String song=SongName.getText();
				String artist=ArtistName.getText();
				String album=AlbumName.getText();
				String desc=SongDesc.getText();
				String id = song.trim()+artist.trim();
				Alert a = new Alert(AlertType.NONE); 
				if(desc.equals("")==false) {
				try {
					@SuppressWarnings("unused")
					int test=Integer.parseInt(desc);
					
					
				}catch(NumberFormatException f){
					a.setAlertType(AlertType.ERROR);
					a.setContentText("Error!\n Year must be a number");
					a.show();
					return;
					
					
				}
				
				}
				if(song.equals("")||artist.equals("")) {
					if(song.equals("") && artist.contentEquals("")) {
						// set alert type 
		                a.setAlertType(AlertType.ERROR); 
		                a.setContentText("Error!\nMissing Inputs: \nSong & Artist Names");
		                a.show(); 		
		                }
					else if( song.equals("") ) {
						a.setAlertType(AlertType.ERROR); 
		                a.setContentText("Error!\nMissing Inputs: \nSong Name");
		                a.show(); 				
						}
					else {
						a.setAlertType(AlertType.ERROR); 
		                a.setContentText("Error!\nMissing Inputs: \nArtist Name");
		                a.show(); 	
					}

					
					return;
					
				}

				
				
				Song s=new Song(song,artist,album,desc);
				
				
				int index = 0;
				boolean duplicate = false;
				while( index < Songlist.size() ) {
					
					if( Songlist.get(index).getId().compareToIgnoreCase(id) == 0 ) {
						duplicate = true;
						break;
					}
					
					index++;
					
				}
				
				//if the song is not a duplicate add song
				//else show error message
				// need to test more cases 
				if(duplicate) {
					a.setAlertType(AlertType.ERROR); 
	                a.setContentText("Error!\nSong Already Exists in Library");
	                a.show(); 	
					return;
				}
				
				// set alert type 
                a.setAlertType(AlertType.CONFIRMATION); 
  
                // set content text 
                a.setContentText("Are you sure you want to add?"); 
  
                // show the dialog 
                Optional<ButtonType> result = a.showAndWait();
                if (result.get() == ButtonType.OK){
                	Songlist.add(s);                
                	} 
                else {
                	return;
                }
				//sorts the list
				Songlist.sort(Comparator.comparing(Song::getId));
				//recreates and redisplays the new list
				
				//write Songlist to file
				//desc=desc.replace("\n", "\\n");
				
				obsList.clear();
				int i=0;
				while(i<Songlist.size()) {
					obsList.add(Songlist.get(i).getName());
					i++;
					
				}
				SongName.setText("");
				ArtistName.setText("");
				AlbumName.setText("");
				SongDesc.setText("");
				//update save file
				writesave(Songlist);
				
				
				listView.getSelectionModel().select(Songlist.indexOf(s));
				details(Songlist.get(Songlist.indexOf(s)));
				listView.setItems(obsList);
			}

		}
		
		public void edit(ActionEvent e) throws IOException {
			Button d = (Button)e.getSource();
			d=(Button)e.getSource();
			if(d==editSong) {
				
				int index = listView.getSelectionModel().getSelectedIndex();
				if(index==-1) {
					return;
				}
				//creates the new object
				String song=SongName.getText();
				String artist=ArtistName.getText();
				String album=AlbumName.getText();
				String desc=SongDesc.getText();
				String id = song.trim()+artist.trim();
				Alert a = new Alert(AlertType.NONE); 
				if(desc.equals("")==false) {
				try {
					@SuppressWarnings("unused")
					int test=Integer.parseInt(desc);
					
					
				}catch(NumberFormatException f){
					a.setAlertType(AlertType.ERROR);
					a.setContentText("Error!\n Year must be a number");
					a.show();
					return;
					
					
				}
				}
				if(song.equals("")||artist.equals("")) {
					
					if(song.equals("") && artist.contentEquals("")) {
						a.setAlertType(AlertType.ERROR); 
		                a.setContentText("Error!\nMissing Inputs: \nSong & Artist Names");
		                a.show(); 		
						}
					else if( song.equals("") ) {
						a.setAlertType(AlertType.ERROR); 
		                a.setContentText("Error!\nMissing Inputs: \nSong Name");
		                a.show(); 								}
					else {
						a.setAlertType(AlertType.ERROR); 
		                a.setContentText("Error!\nMissing Inputs: \nArtist Name");
		                a.show(); 							}
					
					return;
					
				}
				
				
				
				Song s=new Song(song,artist,album,desc);
				
				int i = 0;
				boolean duplicate = false;
				while( i < Songlist.size() ) {
					
					if( Songlist.get(i).getId().compareToIgnoreCase(id) == 0 && index!=i) duplicate = true;
					i++;
					
				}
				
				//if the song is not a duplicate add song
				//else show error message
				// need to test more cases 
				if(!duplicate) Songlist.set(index, s);
				else {
					a.setAlertType(AlertType.ERROR); 
	                a.setContentText("Error!\nSong Already Exists in Library");
	                a.show(); 	
					return;
				}
				
				
				Songlist.sort(Comparator.comparing(Song::getId));//resorts the list
				//recreates and redisplays the new list
				obsList.clear();
				int i2=0;
				while(i2<Songlist.size()) {
					obsList.add(Songlist.get(i2).getName());
					i2++;
					
				}
				SongName.setText("");
				ArtistName.setText("");
				AlbumName.setText("");
				SongDesc.setText("");
				//update save file
				writesave(Songlist);
				listView.getSelectionModel().select(Songlist.indexOf(s));
				details(Songlist.get(Songlist.indexOf(s)));
				
				listView.setItems(obsList);
		
		
		
			}
		}
}


