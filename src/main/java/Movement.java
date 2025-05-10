import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement implements KeyListener {
    private Player player;
    private Game game;

    public Movement(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!this.game.isRunMove()) return;

        int steps = 30; // כמה צעדים
        int delay = 1;  // השהייה קטנה בין כל צעד (במילישניות)

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            new Thread(() -> {
                for (int i = 0; i < steps; i++) {
                    if ((this.player.getX() + this.player.getWidth()) + 15 < this.game.getWidth() - 200) {
                        this.player.moveRight();
                        this.game.repaint();
                    }
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
            }).start();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            new Thread(() -> {
                for (int i = 0; i < steps; i++) {
                    if (this.player.getX() >= 217) {
                        this.player.moveLeft();
                        this.game.repaint();
                    }
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
            }).start();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
//
//public void keyPressed(KeyEvent e) {
//    int spead = 30; // מהירות תזוזת שחקן
//    boolean t = false;
//    if (e.getKeyCode() == KeyEvent.VK_RIGHT && this.game.isRunMove()) {
//        for (int i = 0; i < spead; i++) {
//            if ((this.player.getX() + this.player.getWidth()) + 15 < this.game.getWidth() - 200) {
//                this.player.moveRight();
//            }
//        }
//        t = true;
//    }
//    if (e.getKeyCode() == KeyEvent.VK_LEFT && this.game.isRunMove()) {
//        for (int i = 0; i < spead; i++) {
//            if (this.player.getX() >= 217) {
//                this.player.moveLeft();
//            }
//        }
//        t = true;
//    }
//    if (t && this.game.isRunMove()) {
//        this.game.repaint();
//    }
//}
