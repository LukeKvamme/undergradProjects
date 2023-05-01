import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainHead extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("The Hydra is Unkillable");
		
		Label label1 = new Label("Kill one head, two more shall take its place");
		
		VBox layout1 = new VBox(20);
		layout1.getChildren().add(label1);
		
		primaryStage.setOnCloseRequest(event -> {
			OtherHead.display("The Hydra if Unkillable", "Kill one head, two more shall take its place");
		    // Save file
		});
		
		Scene scene = new Scene(layout1, 300, 100);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
//	
//	Random rand = new Random();
//	
//	Stage secondHead = new Stage();
//	secondHead.setX(rand.nextInt(1000));
//	secondHead.setY(rand.nextInt(1000));
//	secondHead.setScene(new Scene(new HBox(4, new Label("Kill one head, two more shall take its place")), 300, 100));
//	secondHead.show();
//	
//	Stage thirdHead = new Stage();
//	thirdHead.setX(rand.nextInt(1000));
//	thirdHead.setY(rand.nextInt(1000));
//	thirdHead.setScene(new Scene(new HBox(4, new Label("Kill one head, two more shall take its place")), 300, 100));
//	thirdHead.show();
//	

}
