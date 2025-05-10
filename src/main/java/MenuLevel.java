import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.net.URL;

public class MenuLevel extends JPanel {
    private int width; // רוחב פאנל
    private int height; // גובה פאנל
    private  int x;
    private int y;
    private MainMenu jFrame; // חלון
    private ImageIcon image; // תמונת רקע
    private TopPanel topPanel;
    private JButton jButton1; // כפתורים
    private JButton jButton2;
    private JButton jButton3;
    private JButton buttonPlay;
    private ImageIcon ImageButtonToSelectLevel; // תמונה לכפתור טקסט בחירת רמה
    private JLabel imageLabel;
    private ImageIcon ImageButtonPlay; // תמונה לכפתור Play
    private ImageIcon ImageButtonLevel1; // תמונה לכפתור רמה 1
    private ImageIcon ImageButtonLevel2; // תמונה לכפתור רמה 2
    private ImageIcon ImageButtonLevel3; // תמונה לכפתור רמה 3
    private int spead;
    private boolean t;

    public MenuLevel(int x, int y, int width, int height, MainMenu jFrame, TopPanel topPanel) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.jFrame = jFrame;
        this.spead = 10;
        this.topPanel = topPanel;
        this.t = false;

        this.setBounds(this.x, this.y, this.width, this.height);// מיקום הפאנל
        this.setLayout(null);

        this.image = new ImageIcon(getClass().getResource("/SelectLevel.png").getPath()); // תמונת רקע
        URL imageUrl = getClass().getResource("/SelectLevel.png");
        this.image = new ImageIcon(imageUrl);
        imageTextSelect(); // תמונה עם טקסט בחירת רמה
       buttons(); // כפתורים
    }
    public void buttons() {
        // כפתור 1
        ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/imageForText.png")); // תמונה לכפתור רמה 1:
        Image scaledImage1 = originalIcon1.getImage().getScaledInstance(300, 220, Image.SCALE_SMOOTH); // קובע גודל
        this.ImageButtonLevel1 = new ImageIcon(scaledImage1);

        this.jButton1 = new JButton(this.ImageButtonLevel1); // כפתור עם תמונה
        this.jButton1.setBounds(350, 320, 300, 80); // מיקום הכפתור
        this.jButton1.setOpaque(false);
        this.jButton1.setContentAreaFilled(false);// לא לצבוע את האיזור שבתוך הכפתור
        this.jButton1.setBorderPainted(false); // לא לצייר מסגרת לכפתור
        this.jButton1.setFocusPainted(false); // לא לצייר קו מסביב כשיש פוקוס
        this.jButton1.setBorder(null); // מסיר את האובייקט Border
        this.jButton1.setText("קל");
        this.jButton1.setFont(new Font("Arial", Font.BOLD, 34)); // גודל טקסט
        this.jButton1.setForeground(Color.WHITE); // צבע לבן
        this.jButton1.setHorizontalTextPosition(SwingConstants.CENTER); // טקסט במרכז
        this.jButton1.setVerticalTextPosition(SwingConstants.CENTER);// טקסט במרכז
        this.jButton1.addActionListener((event) -> { // פעולת כפתורים
            this.spead = 5;
            startPlay();
        });
        this.add(this.jButton1); // הוספת הכפתור
        // כפתור 2
        ImageIcon originalIcon2 = new ImageIcon(getClass().getResource("/imageForText.png")); // תמונה לכפתור רמה 1:
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(300, 220, Image.SCALE_SMOOTH); // קובע גודל
        this.ImageButtonLevel2 = new ImageIcon(scaledImage2);

        this.jButton2 = new JButton(this.ImageButtonLevel2); // כפתור עם תמונה
        this.jButton2.setBounds(350, 420, 300, 80);
        this.jButton2.setOpaque(false);
        this.jButton2.setContentAreaFilled(false);// לא לצבוע את האיזור שבתוך הכפתור
        this.jButton2.setBorderPainted(false); // לא לצייר מסגרת לכפתור
        this.jButton2.setFocusPainted(false); // לא לצייר קו מסביב כשיש פוקוס
        this.jButton2.setBorder(null); // מסיר את האובייקט Border
        this.jButton2.setText("בינוני");
        this.jButton2.setFont(new Font("Arial", Font.BOLD, 34)); // גודל טקסט
        this.jButton2.setForeground(Color.WHITE); // צבע לבן
        this.jButton2.setHorizontalTextPosition(SwingConstants.CENTER); // טקסט במרכז
        this.jButton2.setVerticalTextPosition(SwingConstants.CENTER);// טקסט במרכז
        this.jButton2.addActionListener((event) -> { // פעולת כפתורים
            this.spead = 3;
            startPlay();
        });
        this.add(this.jButton2); // הוספת הכפתור
        // כפתור 3
        ImageIcon originalIcon3 = new ImageIcon(getClass().getResource("/imageForText.png")); // תמונה לכפתור רמה 1:
        Image scaledImage3 = originalIcon3.getImage().getScaledInstance(300, 220, Image.SCALE_SMOOTH); // קובע גודל
        this.ImageButtonLevel3 = new ImageIcon(scaledImage3);

        this.jButton3 = new JButton(this.ImageButtonLevel3); // כפתור עם תמונה
        this.jButton3.setBounds(350, 520, 300, 80);
        this.jButton3.setOpaque(false);
        this.jButton3.setContentAreaFilled(false);// לא לצבוע את האיזור שבתוך הכפתור
        this.jButton3.setBorderPainted(false); // לא לצייר מסגרת לכפתור
        this.jButton3.setFocusPainted(false); // לא לצייר קו מסביב כשיש פוקוס
        this.jButton3.setBorder(null); // מסיר את האובייקט Border
        this.jButton3.setText("קשה");
        this.jButton3.setFont(new Font("Arial", Font.BOLD, 34)); // גודל טקסט
        this.jButton3.setForeground(Color.WHITE); // צבע לבן
        this.jButton3.setHorizontalTextPosition(SwingConstants.CENTER); // טקסט במרכז
        this.jButton3.setVerticalTextPosition(SwingConstants.CENTER);// טקסט במרכז
        this.jButton3.addActionListener((event) -> { // פעולת כפתורים
            this.spead = 1;
            startPlay();
        });
        this.add(this.jButton3); // הוספת הכפתור
        this.setLayout(null);
    }
    public void imageTextSelect() {
        this.ImageButtonToSelectLevel = new ImageIcon(getClass().getResource("/buttonGreen.png"));
        Image scaledImage =  this.ImageButtonToSelectLevel.getImage().getScaledInstance(600, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        this.imageLabel = new JLabel("בחירת רמה:");
        this.imageLabel.setIcon(resizedIcon);
        this.imageLabel.setBounds(200, 70, 600, 250); // מיקום וגודל
        this.imageLabel.setHorizontalTextPosition(SwingConstants.CENTER); // אופקי במרכז
        this.imageLabel.setVerticalTextPosition(SwingConstants.CENTER);   // אנכי במרכז
        this.imageLabel.setFont(new Font("Arial", Font.BOLD, 57)); // גודל וסגנון טקסט
        this.imageLabel.setForeground(Color.WHITE); // צבע טקסט

        this.setLayout(null); // מאפשר שליטה ידנית על מיקום
        this.add(this.imageLabel);
    }
    public void startPlay() { // התחלת משחק
        this.jButton1.setVisible(false); // הסרת הכפתורים
        this.jButton2.setVisible(false);
        this.jButton3.setVisible(false);
        this.imageLabel.setVisible(false);
        this.t = true;

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/buttonRed.png")); // תמונה לכפתור פליי:
        Image scaledImage = originalIcon.getImage().getScaledInstance(392, 286, Image.SCALE_SMOOTH); // קובע גודל
        this.ImageButtonPlay = new ImageIcon(scaledImage);

        this.buttonPlay = new JButton(this.ImageButtonPlay); // כפתור עם תמונה
        this.buttonPlay.setBounds(354, 350, 292, 130); // מיקום הכפתור
        this.buttonPlay.setOpaque(false);
        this.buttonPlay.setContentAreaFilled(false);// לא לצבוע את האיזור שבתוך הכפתור
        this.buttonPlay.setBorderPainted(false); // לא לצייר מסגרת לכפתור
        this.buttonPlay.setFocusPainted(false); // לא לצייר קו מסביב כשיש פוקוס
        this.buttonPlay.setBorder(null); // מסיר את האובייקט Border
        this.buttonPlay.setText("Play"); // הטקסט על התמונה
        this.buttonPlay.setFont(new Font("Arial", Font.BOLD, 32)); // גודל הטקסט
        this.buttonPlay.setForeground(Color.WHITE); // צבע הטקסט
        this.buttonPlay.setHorizontalTextPosition(SwingConstants.CENTER); // מרכז
        this.buttonPlay.setVerticalTextPosition(SwingConstants.CENTER);   // מרכז

        this.buttonPlay.addActionListener((event) -> { // פעולת הכפתור
            this.setVisible(false);
            this.topPanel.removeButtonReturn();
            this.jFrame.startGame(this.spead); // התחלת משחק
        });
        this.add(this.buttonPlay); // הוספת כפתור
        this.repaint();

    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null && image.getIconWidth() > 0) { // בדיקה עם התמונה קיימת
            g.drawImage(image.getImage(), this.x, 0, getWidth(), getHeight(), this);
        }
    }
    public void restartToReturn() { // אתחול
        this.jButton1.setVisible(true); // הוספת הכפתורים
        this.jButton2.setVisible(true);
        this.jButton3.setVisible(true);
        this.buttonPlay.setVisible(false);
    }

    public boolean getT() {
        return t;
    }

}

