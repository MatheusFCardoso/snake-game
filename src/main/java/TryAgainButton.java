import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TryAgainButton extends VBox
{


    public TryAgainButton(EventHandler<ActionEvent> action)
    {

        Label gameOver = new Label("GAME OVER !!");
        gameOver.setFont(Font.font(50));
        gameOver.setTextFill(Color.RED);
        this.getChildren().add(gameOver);

        Button tryAgain = new Button("Jogar novamente !");
        tryAgain.setFont(Font.font(25));
        tryAgain.setOnAction(action);
        this.getChildren().add(tryAgain);

        this.setMinWidth(Config.WIDTH);
        this.setMinHeight(Config.HEIGHT);
        this.setAlignment(Pos.CENTER);

    }
}