import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

public class MainMenu extends JFrame {
    private int WINDOW_WIDTH; // רוחב חלון
    private int WINDOW_HEIGHT; // גובה חלון
    private JPanel panelMain; // פאנל ראשי
    private TopPanel topPanel; // פאנל עליון
    private Game game; // פאנל המשחק
    private MenuLevel menuLevel; // פאנל בחירת רמה
    private InstructionsPanel instructionsPanel; // חלון הוראות
    private int speadMoveImage; // מהירות תזוזת תמונה
    private int countStars;
    private int recordStars; // שיא כוכבים
    private boolean isFirstGame; // האם זה משחק ראשון?
    private boolean isCheck;

    public MainMenu() {
        this.WINDOW_HEIGHT = 750;
        this.WINDOW_WIDTH = 1000;

        this.setSize(this.WINDOW_WIDTH, this.WINDOW_HEIGHT); // מידות חלון
        this.setLocationRelativeTo(null);// מיקום חלון
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// סגירת התוכנית בלחיצה על X
        this.setLayout(null);// פריסת חלון\ אסטרטגיית פריסה
        this.setVisible(true); // הפעלת חלון
        this.setResizable(false);

        this.speadMoveImage = 0;
        this.countStars = 0;
        this.recordStars = 0;
        this.isFirstGame = true;
        this.isCheck = false;

        this.panelMain = new PanelMain(0, 40, this.WINDOW_WIDTH, this.WINDOW_HEIGHT - 40, this); // פאנל ראשי
        this.game = new Game(0, 40, this.WINDOW_WIDTH, this.WINDOW_HEIGHT - 50, this);
        this.topPanel = new TopPanel(0, 0, this.WINDOW_WIDTH, 40, this); // פאנל עליון
        this.menuLevel = new MenuLevel(0, 40, this.WINDOW_WIDTH, this.WINDOW_HEIGHT - 40, this, this.topPanel);
        this.panelMain = new PanelMain(0, 40, this.WINDOW_WIDTH, this.WINDOW_HEIGHT - 40, this); // פאנל ראשי
        this.instructionsPanel = new InstructionsPanel(0, 40,this.WINDOW_WIDTH, this.WINDOW_HEIGHT - 40, this); // חלון הוראות
        this.instructionsPanel.setVisible(false);
        startPanelMain(); // הפעלת חלון ראשי
        this.add(this.topPanel); // הוספת הפאנל העליון
        this.repaint();     // מבקש לצייר את הפאנל מחדש
        this.revalidate();  // מבצע סידור מחדש של הרכיבים (layout)
    }
    public void startInstructionsPanel() {
        this.instructionsPanel.setVisible(true);
        this.add(this.instructionsPanel);
        this.topPanel.playButtonRetrnFromInstructionsPanel(); // יצירת הכפתור
        this.topPanel.startButtonReturnFromInstructions();    // הוספת הפעולה לכפתור
    }
    public void removeInstructionsPanel() {
        this.instructionsPanel.setVisible(false);
    }
    public void startPanelMain() {
        this.add(this.panelMain); // הוספת הפאנל הראשי לחלון
    }
    public void SelectLevel() {
        this.topPanel.startButtonReturn();
        this.remove(this.menuLevel);
        this.menuLevel = new MenuLevel(0, 40, this.WINDOW_WIDTH, this.WINDOW_HEIGHT - 40, this, this.topPanel);
        this.add(this.menuLevel);
        this.repaint();
        this.revalidate();
    }

    public void startGame(int spead) { // התחלת משחק
        this.topPanel.removeButtonReturn(); // הוספת כפתור חזרה
        this.game.startCars();
        this.game.setSpeadMoveImage(spead); // שליחת מהירות
        this.game.setRunMove(true); // התחלת תזוזה
        this.game.startGameMovement(); // הפעלת תזוזה
        this.topPanel.startButtonStop(); // הוספת כפתור עצור
        this.topPanel.startNumeber();
        this.add(this.game);
        SwingUtilities.invokeLater(() -> game.requestFocusInWindow());
    }
    public void returnFromSelectLevel() {
        this.remove(this.menuLevel);        // הסר את תפריט הרמות
        if (this.menuLevel.getT()) {
            this.menuLevel.restartToReturn(); // אתחול כפתורים
        }
        this.panelMain.setVisible(true);    // הפוך את הפאנל הראשי לגלוי
        this.repaint();
        this.revalidate();
    }


    public Game getGame() {
        return this.game;
    }
    public void stopGame() { // עצירת תזוזה של המשחק
        this.game.setRunMove(false);
    }
    public void atartGame() { // התחלת תזוזה של המשחק
        this.game.setRunMove(true);
        this.game.startGameMovement(); // ← הפעלת תנועה מחדש
    }
    public void returnFromGame() {
        this.remove(this.game);
        this.menuLevel.restartToReturn(); // אתחול כפתורים
        this.panelMain.setVisible(true);
    }
    public void biggerStars() {
        this.countStars++;
    }

    public int getCountStars() {
        return countStars;
    }
    public TopPanel getTopPanel() {
        return this.topPanel;
    }
    public void setCountStars(int x) {
        this.countStars = x;
    }

    public int getRecordStars() {
        return recordStars;
    }
    public void setFirstGame() {
        this.isFirstGame = false;
    }

    public boolean getIsFirstGame() {
        return isFirstGame;
    }

    public void setRecordStars(int recordStars) {
        this.recordStars = recordStars;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
