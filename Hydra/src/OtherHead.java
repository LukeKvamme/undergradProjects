import java.awt.Label;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Random;

public class OtherHead {

	public static void display(String title, String message) {
		Random rand = new Random();
		Stage window = new Stage();

		window.setX(rand.nextInt(1000));
		window.setY(rand.nextInt(1000));

		window.setOnCloseRequest(event -> {
			for (int i = 0; i < 2; i++) {
				OtherHead.display("The Hydra if Unkillable", "Kill one head, two more shall take its place");
			} // Save file
		});

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("The Hydra is Unkillable");
		window.setMinWidth(400);

		Label label = new Label();
		label.setText(message);
		Button closeButton = new Button("close the window");
		closeButton.setOnAction(e -> {
			OtherHead.display("The Hydra is Unkillable", "Kill one head, two more shall take its place");
		});

		GridPane layout2 = new GridPane();
		layout2.add(closeButton, 0, 1, 1, 10);
		layout2.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout2);
		window.setScene(scene);
		window.showAndWait();
	}
//	 public void handle(ActionEvent event) {
//         System.out.println("Hello World!");
//
//         Stage secondStage = new Stage();
//         secondStage.setScene(new Scene(new HBox(4, new Label("Second window"))));
//         secondStage.show();
//
//     }

}
