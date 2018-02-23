package encrypto.view;

import encripto.algo.BlockProcessor;
import encripto.algo.KeyHandler;
import encripto.algo.permutator;
import encrypto.util.EncriptController;
import encrypto.util.FileHandler;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javax.swing.JOptionPane;

import javafx.stage.Stage;

public class MainActivity extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Label lblTop = new Label();
        FileHandler fileHandler = new FileHandler();
        EncriptController encriptController = new EncriptController();
        
        

        lblTop.setText("Drag Your File Here");
        root.getChildren().add(lblTop);
        Scene scene = new Scene(root, 500, 200);
        scene.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });
        
        // Dropping over surface
        scene.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                Button btnEncrypt = new Button("Encrypt");
                btnEncrypt.setLayoutX(150);
                btnEncrypt.setLayoutY(150);
                Button btnDecrypt = new Button("Decrypt");
                btnDecrypt.setLayoutX(250);
                btnDecrypt.setLayoutY(150);
                
                root.getChildren().add(btnEncrypt);
                root.getChildren().add(btnDecrypt);
                
                if (db.hasFiles()) {
                    
                    success = true;
                    String filePath = null;
                    for (File file:db.getFiles()) {
                        filePath = file.getAbsolutePath();
                        System.out.println(filePath);            
                        if(filePath.toLowerCase().endsWith(".txt"))
                        {
                            
                            lblTop.setText(file.getName());
                            btnEncrypt.setOnAction(new EventHandler<ActionEvent>() 
                            {
                                @Override
                                public void handle(ActionEvent event) 
                                {
                                    String text = fileHandler.getString(file.getAbsolutePath());
                                    String[] out = encriptController.encrypt(text);
                                    fileHandler.writeFile(out[1],file.getParent() , "encr.txt");
                                    lblTop.setText("your key is : "+out[0]);
                                    
                                    
                                }
                            });
                            
                            btnDecrypt.setOnAction(new EventHandler<ActionEvent>() 
                            {
                                @Override
                                public void handle(ActionEvent event) 
                                {
                                    String key = JOptionPane.showInputDialog( "what is your key?");

                                    String fileout =fileHandler.getString(file.getAbsolutePath());
                                    String read ="";
                                    for(int i =0; i<fileout.length() ;i++){
                                        if((int)fileout.charAt(i)>31 && (int)fileout.charAt(i)<127){
                                        read += fileout.charAt(i);
                                        }
                                    }
                                    
                                    String text = encriptController.decrypt(read, key);
                                    fileHandler.writeFile(text, file.getParent(), "decr.txt");
                                    lblTop.setText("completed");
                                    
                                }
                            });
                            
                            
                            
                        }
                        else{
                            
                            lblTop.setText("Type error! drop txt file");
                            
                        }
                        
                        
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

   
  


