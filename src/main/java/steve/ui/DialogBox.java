package steve.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;
    private static final Color USER_BUBBLE_COLOR = Color.rgb(0, 132, 255, 0.9);    // Light blue
    private static final Color STEVE_BUBBLE_COLOR = Color.rgb(120, 200, 120, 0.9); // Light gray
    private static final double BUBBLE_RADIUS = 15.0;
    private static final double SPACING = 10.0;
    private static final double PADDING = 15.0;

    public DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);

        // Style the text
        text.setFont(Font.font("Helvetica", FontWeight.NORMAL, 14));
        text.setWrapText(true);
        text.setMaxWidth(250.0); // Limit text width for better readability

        // Style the image
        displayPicture.setFitWidth(100);  // Smaller profile picture
        displayPicture.setFitHeight(100.0);
        displayPicture.setPreserveRatio(true);

        // Style the container
        this.setSpacing(SPACING);
        this.setPadding(new Insets(PADDING));
        this.setAlignment(Pos.TOP_RIGHT);

        // Add components
        this.getChildren().addAll(text, displayPicture);
    }

    private void styleAsUserDialog() {
        text.setTextFill(Color.WHITE);  // White text for user messages
        text.setBackground(new Background(new BackgroundFill(
                USER_BUBBLE_COLOR,
                new CornerRadii(BUBBLE_RADIUS),
                Insets.EMPTY
        )));
        text.setPadding(new Insets(8, 12, 8, 12));
    }

    private void styleAsSteveDialog() {
        text.setTextFill(Color.BLACK);  // Black text for Steve's messages
        text.setBackground(new Background(new BackgroundFill(
                STEVE_BUBBLE_COLOR,
                new CornerRadii(BUBBLE_RADIUS),
                Insets.EMPTY
        )));
        text.setPadding(new Insets(8, 12, 8, 12));
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        db.styleAsUserDialog();
        return db;
    }

    public static DialogBox getSteveDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        db.styleAsSteveDialog();
        db.flip();
        return db;
    }
}