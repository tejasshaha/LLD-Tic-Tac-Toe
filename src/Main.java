import com.service.Game;

public class Main {
    public static void main(String args[]){
        Game game = new Game();
        game.createPlayer("Tejas",1234567890,"tejas@gmail.com");
        game.createPlayer("Deepak",123456,"deepak@gmail.com");
        game.displayPlayers();
        game.initializeBoard();
        game.startGame();
    }
}
