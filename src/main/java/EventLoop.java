import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;


public class EventLoop
{

    private Cenario cenario;
    private KeyCode currentDirection;
    private Snake snake;
    private Timeline timeline;
    private Food food;

    public EventLoop(Cenario cenario, Snake snake, Food food)
    {
        this.cenario = cenario;
        this.snake = snake;
        this.food = food;
        this.cenario.setKeyPressed(e->{
            KeyCode keyPressed = e.getCode();

            if(keyPressed.equals(KeyCode.RIGHT) && ! KeyCode.LEFT.equals(currentDirection)){

                currentDirection = keyPressed;

            }

            if(keyPressed.equals(KeyCode.LEFT) && ! KeyCode.RIGHT.equals(currentDirection)){

                currentDirection = keyPressed;
            }

            if(keyPressed.equals(KeyCode.UP) && ! KeyCode.DOWN.equals(currentDirection)){

                currentDirection = keyPressed;

            }

            if(keyPressed.equals(KeyCode.DOWN) && !KeyCode.UP.equals(currentDirection)){

                currentDirection = keyPressed;

            }
        });

        starLoop();
    }

    public void starLoop()
    {
        timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(150), e -> {
            Integer positionX = snake.getPositionX();
            Integer positionY = snake.getPositionY();

            if(KeyCode.RIGHT.equals(currentDirection)){

                positionX = positionX + Config.SQUARE_SIZE;

            }

            if(KeyCode.LEFT.equals(currentDirection)){

                positionX = positionX - Config.SQUARE_SIZE;

            }

            if(KeyCode.UP.equals(currentDirection)){

                positionY = positionY - Config.SQUARE_SIZE;

            }

            if(KeyCode.DOWN.equals(currentDirection)){

                positionY = positionY + Config.SQUARE_SIZE;

            }

            if ( positionX < 0 || positionY < 0 || positionX > Config.WIDTH - Config.SQUARE_SIZE || positionY > Config.HEIGHT - Config.SQUARE_SIZE || this.snake.checkColision(positionX, positionY)){
                gameOver();

            }else{

                if(positionX.equals(food.getPositionX()) && positionY.equals(food.getPositionY())){
                    this.food.setRandomPosition();
                    this.snake.eat(this.cenario);
                }

                this.snake.setPosition(positionX, positionY);
            }

        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Integer.MAX_VALUE);
        timeline.play();
    }

    public void gameOver()
    {
        this.timeline.stop();
        this.currentDirection=null;
        this.snake.die();
        this.cenario.showGameOver(this);
    }

}