import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Game extends JPanel {
    private int width; // רוחב פאנל
    private int height; // גובה פאנל
    private  int x;
    private int y;
    private MainMenu jFrame; // חלון
    private ImageIcon image1; // תמונת רקע1
    private ImageIcon image2; // תמונת רקע 2
    private int YforImage2; // Y תמונה
    private int YforImage1; // Y תמונה
    private Player player; // שחקן
    private double speadMoveImage; // מהירות תזוזת תמונה
    private boolean runMove; // התחלת תזוזה
    private Cars[] cars;
    private Stars stars; // כוככים
    private Thread threadMoveGame;
    private Thread threadMoveCars;
    private Thread threadSpeadSlowdown;
    private ImageIcon imageEndGame; // תמונת סוף משחק
    private JLabel imageLabelEndGame; // תמונת סוף משחק
    private ImageIcon imageRecordBroken; // תמונת שבירת שיא
    private JLabel imageLabelRecordBroken; // תמונת שבירת שיא

    public Game(int x, int y, int width, int height,  MainMenu jFrame) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.YforImage1 = 0;
        this.YforImage2 = -750;
        this.jFrame = jFrame;
        this.speadMoveImage = 10;
        this.runMove = false;
        this.setBounds(this.x, this.y, this.width, this.height);// מיקום הפאנל
        this.setLayout(null);

        this.image1 = new ImageIcon(getClass().getResource("/imageRoad.png").getPath()); // תמונת רקע
        URL imageUrl1 = getClass().getResource("/imageRoad.png");
        this.image1 = new ImageIcon(imageUrl1);

        this.image2 = new ImageIcon(getClass().getResource("/imageRoad.png").getPath()); // תמונת רקע
        URL imageUrl2 = getClass().getResource("/imageRoad.png");
        this.image2 = new ImageIcon(imageUrl2);
        isImageForRecord(); // תמונה שקופצת בשיא
    }
    public void isImageForRecord() {
        this.imageRecordBroken = new ImageIcon(getClass().getResource("/imageForText.png"));
        Image scaledImage =  this.imageRecordBroken.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(scaledImage);

        this.imageLabelRecordBroken = new JLabel("שברת שיא!");
        this.imageLabelRecordBroken.setIcon(resizedIcon1);
        this.imageLabelRecordBroken.setBounds(425, -28, 150, 100); // מיקום וגודל
        this.imageLabelRecordBroken.setHorizontalTextPosition(SwingConstants.CENTER); // אופקי במרכז
        this.imageLabelRecordBroken.setVerticalTextPosition(SwingConstants.CENTER);   // אנכי במרכז
        this.imageLabelRecordBroken.setFont(new Font("Arial", Font.BOLD, 18)); // גודל וסגנון טקסט
        this.imageLabelRecordBroken.setForeground(Color.WHITE); // צבע טקסט

        this.setLayout(null);
    }
    public void endGame() {
        this.runMove = false;
        this.jFrame.getTopPanel().removeButtonStop();
        Sleep(600);
        for (int i = 0; i < this.cars.length; i++) {
            this.cars[i].setY(-1000); // מוציא למעלה מהמסך
        }
        this.repaint();
        Sleep(300);
        this.imageEndGame = new ImageIcon(getClass().getResource("/buttonRed.png"));
        Image scaledImage =  this.imageEndGame.getImage().getScaledInstance(600, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        if (this.jFrame.getIsFirstGame()) {
            this.jFrame.setFirstGame();
            this.jFrame.setRecordStars(this.jFrame.getCountStars());
        }
        this.imageLabelEndGame = new JLabel("נפסלת!");
        this.imageLabelEndGame.setIcon(resizedIcon);
        this.imageLabelEndGame.setBounds(200, 70, 600, 250); // מיקום וגודל
        this.imageLabelEndGame.setHorizontalTextPosition(SwingConstants.CENTER); // אופקי במרכז
        this.imageLabelEndGame.setVerticalTextPosition(SwingConstants.CENTER);   // אנכי במרכז
        this.imageLabelEndGame.setFont(new Font("Arial", Font.BOLD, 57)); // גודל וסגנון טקסט
        this.imageLabelEndGame.setForeground(Color.WHITE); // צבע טקסט
        this.setLayout(null); // מאפשר שליטה ידנית על מיקום
        this.add(this.imageLabelEndGame);
        this.repaint();
        Sleep(1200);
        this.remove(this.imageLabelEndGame);
        this.jFrame.getTopPanel().ExitEndGame();
    }
    public void startStars() {
        this.stars = new Stars(ranDomX(), -630, 40, 40, "/StarImage.png");
    }
    public void startCars() {
        this.player = new Player(426, 562, 71, 106, "/Player.png"); // שחקן
        this.addKeyListener(new Movement(this.player, this));

        this.cars = new Cars[4]; // מכוניות:
        this.cars[0] = new Cars(ranDomX(), -750, 71, 106, "/carBlue.png");
        this.cars[1] = new Cars(ranDomX(), -536, 71, 106, "/carBlack.png");
        this.cars[2] = new Cars(ranDomX(), -322, 71, 106, "/carGreen.png");
        this.cars[3] = new Cars(ranDomX(), -106, 71, 106, "/carYelow.png");
        startStars();
    }
    public void startGameMovement(){ // התחלת תזוזת המשחק
        moveGame(); // תזוזת המשחק
        speadSlowdown(); // הגדלת מהירות
        moveCars(); // תזוזת מכוניות
    }
    public void moveGame() {
        if (this.threadMoveGame != null && this.threadMoveGame.isAlive()) return;
        this.threadMoveGame = new Thread(() -> {
            while (this.runMove) {
                ImageLocationCheck();
                moveImage();
                moveStars();
                repaint();
                try {
                    Thread.sleep((long) this.speadMoveImage);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        this.threadMoveGame.start();
    }
    public void moveStars() { // הזזת כוכבים
        this.stars.moveDown();
        if (this.stars.getY() == this.height) {
            this.stars.setY(-40);
            this.stars.setX(ranDomX());
        }
        if (checkStar(this.stars)) {
            this.stars.setY(-40);
            this.stars.setX(ranDomX());
            this.jFrame.biggerStars();
            this.jFrame.getTopPanel().updateStarCountLabel();
            isNewHighScore(); // בדיקת שיא

        }
    }
    public void isNewHighScore() { // בדיקה אם עקף את השיא
        if (this.jFrame.getRecordStars() < this.jFrame.getCountStars()) {
            this.jFrame.setRecordStars(this.jFrame.getCountStars());
            this.jFrame.getTopPanel().updateStarRecordLabel();
            if (!this.jFrame.isCheck() && !this.jFrame.getIsFirstGame()) {
                this.jFrame.setCheck(true);

                new Thread(() -> {
                    this.add(this.imageLabelRecordBroken);
                    this.revalidate();
                    this.repaint();

                    try {
                        Thread.sleep(1100); // השהייה בלי לעצור את כל המשחק
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    this.remove(this.imageLabelRecordBroken);
                    this.revalidate();
                    this.repaint();
                }).start();
            }
        }
    }
    public boolean checkStar(Stars star) {  // בדיקת התנגשות Stars במכונית
        Rectangle PlayerRectangle = new Rectangle(
                this.player.getX(), this.player.getY(), this.player.getWidth(), this.player.getHeight()
        );
        Rectangle BlocksRectangle = new Rectangle(
                star.getX() + 10, star.getY(), 20, 40
        );
        if (PlayerRectangle.intersects(BlocksRectangle)) {
            return true;
        }return false;
    }

    public void moveCars() {
        if (this.threadMoveCars != null && this.threadMoveCars.isAlive()) return;
        this.threadMoveCars = new Thread(() -> {
            while (this.runMove) {
                for (int i = 0; i < this.cars.length; i++) {
                    this.cars[i].moveDown();
                }
                for (int i = 0; i < this.cars.length; i++) {
                    if (this.cars[i].getY() == this.height) {
                        this.cars[i].setY(-150);
                        this.cars[i].setX(ranDomX());
                    }
                }
                if (checkCollision()) {
                    endGame();
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        this.threadMoveCars.start();
    }

    public boolean checkCollision() {  // בדיקת התנגשות Player במכוניות
        Rectangle PlayerRectangle = new Rectangle(
                this.player.getX(), this.player.getY(), this.player.getWidth(), this.player.getHeight()
        );
        Rectangle BlocksRectangle1 = new Rectangle(
                this.cars[0].getX() + 25, this.cars[0].getY(), this.cars[0].getWidth() - 52, this.cars[0].getHeight() - 20
        );
        Rectangle BlocksRectangle2 = new Rectangle(
                this.cars[1].getX() + 25, this.cars[1].getY(), this.cars[1].getWidth() - 52, this.cars[1].getHeight() - 20
        );
        Rectangle BlocksRectangle3 = new Rectangle(
                this.cars[2].getX() + 25, this.cars[2].getY(), this.cars[2].getWidth() - 52, this.cars[2].getHeight() - 20
        );
        Rectangle BlocksRectangle4 = new Rectangle(
                this.cars[3].getX() + 25, this.cars[3].getY(), this.cars[3].getWidth() - 52, this.cars[3].getHeight() - 20
        );
        if (PlayerRectangle.intersects(BlocksRectangle1) || PlayerRectangle.intersects(BlocksRectangle2) || PlayerRectangle.intersects(BlocksRectangle3) || PlayerRectangle.intersects(BlocksRectangle4)) {
            return true;
        }return false;
    }
    public int ranDomX() { // מתודה שמחזירה ערך X רנדומלי
        Random random = new Random();
        int x = random.nextInt(200, 746);
        return x;
    }
    public void moveImage() { // הזזת תמונה
        this.YforImage1++;
        this.YforImage2++;
    }
    public void ImageLocationCheck() { // בדיקה אם התמונות הגיעו למטה
        if (this.YforImage1 == this.height) { // בדיקה עם התמונה הגיעה למטה
            this.YforImage1 = -750;
        }
        if (this.YforImage2 == this.height) {  // בדיקה עם התמונה הגיעה למטה
            this.YforImage2 = -750;
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image1 != null && image1.getIconWidth() > 0) {
            g.drawImage(image1.getImage(), this.x, this.YforImage1, getWidth(), 750, this);
        }
        if (image2 != null && image2.getIconWidth() > 0) {
            g.drawImage(image2.getImage(), this.x, this.YforImage2, getWidth(), 750, this);
        }
        this.player.painted(g);
        for (int i = 0; i < this.cars.length; i++) {
            this.cars[i].painted(g);
        }
        this.stars.painted(g);
    }
    public void speadSlowdown() {
        if (this.threadSpeadSlowdown != null && this.threadSpeadSlowdown.isAlive()) return;
        this.threadSpeadSlowdown = new Thread(() -> {
            boolean t = false;
            while (this.runMove) {
                if (this.speadMoveImage > 1 && (this.speadMoveImage - 0.05) >= 1) {
                    this.speadMoveImage -= 0.05;
                    t = true;
                }
                if (!t) {
                    this.speadMoveImage = 1;
                }
                System.out.println(this.speadMoveImage);
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        this.threadSpeadSlowdown.start();
    }

    public void setSpeadMoveImage(int x) { // קביעת מהירות
        this.speadMoveImage = x;
    }

    public void setRunMove(boolean runMove) {
        this.runMove = runMove;
        if (!runMove) {
            if (threadMoveGame != null) threadMoveGame.interrupt();
            if (threadMoveCars != null) threadMoveCars.interrupt();
            if (threadSpeadSlowdown != null) threadSpeadSlowdown.interrupt();

            threadMoveGame = null;
            threadMoveCars = null;
            threadSpeadSlowdown = null;
        }
    }
    public boolean isRunMove() {
        return runMove;
    }
    public void Sleep(int x) { // מתודת השהייה
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
