import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.net.URL;
public class PanelMain extends JPanel {
    private int width; // רוחב פאנל
    private int height; // גובה פאנל
    private  int x;
    private int y;
    private MainMenu jFrame; // חלון
    private ImageIcon image; // תמונת רקע
    private ImageIcon ImageButtonToGame; // תמונה לכפתור למשחק
    private ImageIcon ImageButtonforInstructions; // תמונה לכפתור לה


    public PanelMain(int x, int y, int width, int height,  MainMenu jFrame){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.jFrame = jFrame;
        this.setBounds(this.x, this.y, this.width, this.height);// מיקום הפאנל
        this.setLayout(null);
        buttons(); // כפתורים
        this.repaint();
        this.image = new ImageIcon(getClass().getResource("/imageMain.png").getPath()); // תמונת רקע
        URL imageUrl = getClass().getResource("/imageMain.png");
        this.image = new ImageIcon(imageUrl);

    }
    public void buttons() {
        // כפתור 1
        ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/buttonGreen.png")); // תמונה לכפתור רמה 1:
        Image scaledImage1 = originalIcon1.getImage().getScaledInstance(400, 220, Image.SCALE_SMOOTH); // קובע גודל
        this.ImageButtonToGame = new ImageIcon(scaledImage1);

        JButton button1 = new JButton(this.ImageButtonToGame); // כפתור עם תמונה
        button1.setBounds(350, 300, 300, 80);
        button1.setOpaque(false);
        button1.setContentAreaFilled(false);// לא לצבוע את האיזור שבתוך הכפתור
        button1.setBorderPainted(false); // לא לצייר מסגרת לכפתור
        button1.setFocusPainted(false); // לא לצייר קו מסביב כשיש פוקוס
        button1.setBorder(null); // מסיר את האובייקט Border
        button1.setText("למשחק");
        button1.setFont(new Font("Arial", Font.BOLD, 41)); // גודל טקסט
        button1.setForeground(Color.WHITE); // צבע לבן
        button1.setHorizontalTextPosition(SwingConstants.CENTER); // טקסט במרכז
        button1.setVerticalTextPosition(SwingConstants.CENTER);// טקסט במרכז
        button1.addActionListener((event) -> { // פעולת כפתורים
            this.setVisible(false);
          this.jFrame.SelectLevel();
        });
        this.add(button1); // הוספת הכפתור
        // כפתור 2
        ImageIcon originalIcon2 = new ImageIcon(getClass().getResource("/buttonGreen.png")); // תמונה לכפתור רמה 1:
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(400, 220, Image.SCALE_SMOOTH); // קובע גודל
        this.ImageButtonforInstructions = new ImageIcon(scaledImage2);

        JButton button2 = new JButton(this.ImageButtonforInstructions); // כפתור עם תמונה
        button2.setBounds(350, 415, 300, 80);
        button2.setOpaque(false);
        button2.setContentAreaFilled(false);// לא לצבוע את האיזור שבתוך הכפתור
        button2.setBorderPainted(false); // לא לצייר מסגרת לכפתור
        button2.setFocusPainted(false); // לא לצייר קו מסביב כשיש פוקוס
        button2.setBorder(null); // מסיר את האובייקט Border
        button2.setText("להוראות");
        button2.setFont(new Font("Arial", Font.BOLD, 41)); // גודל טקסט
        button2.setForeground(Color.WHITE); // צבע לבן
        button2.setHorizontalTextPosition(SwingConstants.CENTER); // טקסט במרכז
        button2.setVerticalTextPosition(SwingConstants.CENTER);// טקסט במרכז
        button2.addActionListener((event) -> { // פעולת כפתורים
            this.setVisible(false);
            this.jFrame.startInstructionsPanel();
        });
        this.add(button2); // הוספת הכפתור
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null && image.getIconWidth() > 0) {
            g.drawImage(image.getImage(), this.x, 0, getWidth(), getHeight(), this);
        }
    }

}
