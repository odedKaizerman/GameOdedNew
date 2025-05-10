import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;

public class Player {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image playerImage;
    private String textImage;

    public Player(int x, int y, int width, int height, String textImage) { // בנאי
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textImage = textImage;

        // טוען את התמונה של המכונית האדומה
        URL imageURL = getClass().getResource(this.textImage);
        if (imageURL != null) {
            this.playerImage = new ImageIcon(imageURL).getImage();
        }
    }

    public void painted(Graphics graphics) { // מתודה שמציירת
        if (playerImage != null) {
            graphics.drawImage(playerImage, this.x, this.y, this.width, this.height, null);
        } else {
            graphics.setColor(Color.BLUE); // fallback במקרה ואין תמונה
            graphics.fillRect(this.x, this.y, this.width, this.height);
        }
    }
    public void moveRight() { this.x++; }
    public void moveLeft()  { this.x--; }
    public void moveUp()    { this.y--; }
    public void moveDown()  { this.y++; }

    public int getX()      { return x; }
    public int getY()      { return y; }
    public int getWidth()  { return width; }
    public int getHeight() { return height; }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
