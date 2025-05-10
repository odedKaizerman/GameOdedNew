import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class TopPanel extends JPanel {
    private int width; // רוחב פאנל
    private int height; // גובה פאנל
    private int x;
    private int y;
    private MainMenu jFrame; // חלון
    private ImageIcon image; // תמונת רקע
    private ImageIcon imageForButtonReturn; // תמונה לכפתור חזרה
    private JButton buttonReturn; // כפתור חזרה
    private ImageIcon imageForButtonStop; // תמונה לכפתורי חזרה
    private JButton buttonStop; // כפתור עצור
    private JButton buttonExit;  // יציאה
    private JButton buttonStart; // המשך
    private ImageIcon numberStars; // תמונה מספר כוכבים
    private JLabel imageLabelForNumberStars;
    private ImageIcon recordStars; // תמונה שיא כוכבים
    private JLabel imageLabelForRecordStars;
    private int modeButtonExit;
    private boolean runningModeExit;
    private ImageIcon imageForButtonReturnFromInstructionsPanel; // כפתור חזרה מההוראות
    private JButton buttonsFromInstructionsPanel;


    public TopPanel(int x, int y, int width, int height, MainMenu jFrame) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.jFrame = jFrame;
        this.modeButtonExit = 0;
        this.runningModeExit = false;
        this.setBounds(this.x, this.y, this.width, this.height);// מיקום הפאנל
        this.setLayout(null);
        this.image = new ImageIcon(getClass().getResource("/topPanelImage.png").getPath()); // תמונת רקע
        URL imageUrl = getClass().getResource("/topPanelImage.png");
        this.image = new ImageIcon(imageUrl);
        playButtonRetrn(); // כפתור חזרה
        playButtonStop(); // כפתור עצור
        playButtonExitAndStart();
        imageForNumberStars(); // תמונה מספר כוכבים
        imageForRecoedStars(); // תמונה שיא כוכבים
    }
    public void playButtonRetrnFromInstructionsPanel() { // כפתור חזרה
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/buttonRed.png")); // תמונה לכפתור:
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 65, Image.SCALE_SMOOTH); // קובע גודל
        this.imageForButtonReturnFromInstructionsPanel = new ImageIcon(scaledImage);

        this.buttonsFromInstructionsPanel = new JButton(this.imageForButtonReturnFromInstructionsPanel); // כפתור חזרה עם תמונה
        this.buttonsFromInstructionsPanel.setBounds(4, -5, 60, 50);
        this.buttonsFromInstructionsPanel.setOpaque(false);
        this.buttonsFromInstructionsPanel.setContentAreaFilled(false);// לא לצבוע את האיזור שבתוך הכפתור
        this.buttonsFromInstructionsPanel.setBorderPainted(false); // לא לצייר מסגרת לכפתור
        this.buttonsFromInstructionsPanel.setFocusPainted(false); // לא לצייר קו מסביב כשיש פוקוס
        this.buttonsFromInstructionsPanel.setBorder(null); // מסיר את האובייקט Border
        this.buttonsFromInstructionsPanel.setText("חזור"); // הטקסט על התמונה
        this.buttonsFromInstructionsPanel.setFont(new Font("Arial", Font.BOLD, 20)); // גודל הטקסט
        this.buttonsFromInstructionsPanel.setForeground(Color.WHITE); // צבע הטקסט
        this.buttonsFromInstructionsPanel.setHorizontalTextPosition(SwingConstants.CENTER); // מרכז
        this.buttonsFromInstructionsPanel.setVerticalTextPosition(SwingConstants.CENTER);   // מרכז
    }
    public void startButtonReturnFromInstructions() { // הוספת כפתור
        this.buttonsFromInstructionsPanel.addActionListener((event) -> { // פעולת הכפתור
            this.jFrame.returnFromSelectLevel();
            this.jFrame.removeInstructionsPanel();
            removeButtonReturn();
            this.buttonsFromInstructionsPanel.setVisible(false);
        });
        this.add(this.buttonsFromInstructionsPanel);
        this.revalidate();   // מרענן את המבנה של הפאנל
        this.repaint();
    }


    public void startButtonReturn() { // הוספת כפתור
        this.buttonReturn.addActionListener((event) -> { // פעולת הכפתור
            this.jFrame.returnFromSelectLevel();
            removeButtonReturn();
        });
        this.add(this.buttonReturn);
        this.revalidate();   // מרענן את המבנה של הפאנל
        this.repaint();
    }
    public void imageForRecoedStars() {
        this.recordStars = new ImageIcon(getClass().getResource("/buttonRed.png"));
        Image scaledImage =  this.recordStars.getImage().getScaledInstance(250, 90, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        this.imageLabelForRecordStars = new JLabel("שיא כוכבים: " + this.jFrame.getRecordStars());
        this.imageLabelForRecordStars.setIcon(resizedIcon);
        this.imageLabelForRecordStars.setBounds(770, -5, 250, 50); // מיקום וגודל
        this.imageLabelForRecordStars.setHorizontalTextPosition(SwingConstants.CENTER); // אופקי במרכז
        this.imageLabelForRecordStars.setVerticalTextPosition(SwingConstants.CENTER);   // אנכי במרכז
        this.imageLabelForRecordStars.setFont(new Font("Arial", Font.BOLD, 20)); // גודל וסגנון טקסט
        this.imageLabelForRecordStars.setForeground(Color.WHITE); // צבע טקסט
        this.setLayout(null);
        this.add(this.imageLabelForRecordStars);
    }
    public void updateStarRecordLabel() { // עדכון מספר שיא כוכבים
        this.imageLabelForRecordStars.setText("שיא כוכבים: " + this.jFrame.getRecordStars());
    }

    public void imageForNumberStars() {
        this.numberStars = new ImageIcon(getClass().getResource("/buttonGreen.png"));
        Image scaledImage =  this.numberStars.getImage().getScaledInstance(250, 90, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        this.imageLabelForNumberStars = new JLabel("מספר כוכבים: " + this.jFrame.getCountStars());
        this.imageLabelForNumberStars.setIcon(resizedIcon);
        this.imageLabelForNumberStars.setBounds(375, -5, 250, 50); // מיקום וגודל
        this.imageLabelForNumberStars.setHorizontalTextPosition(SwingConstants.CENTER); // אופקי במרכז
        this.imageLabelForNumberStars.setVerticalTextPosition(SwingConstants.CENTER);   // אנכי במרכז
        this.imageLabelForNumberStars.setFont(new Font("Arial", Font.BOLD, 20)); // גודל וסגנון טקסט
        this.imageLabelForNumberStars.setForeground(Color.WHITE); // צבע טקסט
        this.setLayout(null);
    }
    public void updateStarCountLabel() { // עדכון מספר כוכבים
        this.imageLabelForNumberStars.setText("מספר כוכבים: " + this.jFrame.getCountStars());
    }

    public void startNumeber() {
        this.add(this.imageLabelForNumberStars);
    }
    public void playButtonExitAndStart() {
        ImageIcon originalIconStop = new ImageIcon(getClass().getResource("/buttonRed.png")); // תמונה לכפתור:
        Image scaledImageStop = originalIconStop.getImage().getScaledInstance(80, 65, Image.SCALE_SMOOTH); // קובע גודל
        this.imageForButtonStop = new ImageIcon(scaledImageStop);

        this.buttonExit = new JButton(this.imageForButtonStop); // כפתור חזרה עם תמונה
        this.buttonExit.setBounds(4, -5, 60, 50);
        this.buttonExit.setOpaque(false);
        this.buttonExit.setContentAreaFilled(false);// לא לצבוע את האיזור שבתוך הכפתור
        this.buttonExit.setBorderPainted(false); // לא לצייר מסגרת לכפתור
        this.buttonExit.setFocusPainted(false); // לא לצייר קו מסביב כשיש פוקוס
        this.buttonExit.setBorder(null); // מסיר את האובייקט Border
        this.buttonExit.setText("יציאה"); // הטקסט על התמונה
        this.buttonExit.setFont(new Font("Arial", Font.BOLD, 20)); // גודל הטקסט
        this.buttonExit.setForeground(Color.WHITE); // צבע הטקסט
        this.buttonExit.setHorizontalTextPosition(SwingConstants.CENTER); // מרכז
        this.buttonExit.setVerticalTextPosition(SwingConstants.CENTER);   // מרכז

        this.buttonStart = new JButton(this.imageForButtonStop); // כפתור חזרה עם תמונה
        this.buttonStart.setBounds(73, -5, 60, 50);
        this.buttonStart.setOpaque(false);
        this.buttonStart.setContentAreaFilled(false);// לא לצבוע את האיזור שבתוך הכפתור
        this.buttonStart.setBorderPainted(false); // לא לצייר מסגרת לכפתור
        this.buttonStart.setFocusPainted(false); // לא לצייר קו מסביב כשיש פוקוס
        this.buttonStart.setBorder(null); // מסיר את האובייקט Border
        this.buttonStart.setText("המשך"); // הטקסט על התמונה
        this.buttonStart.setFont(new Font("Arial", Font.BOLD, 20)); // גודל הטקסט
        this.buttonStart.setForeground(Color.WHITE); // צבע הטקסט
        this.buttonStart.setHorizontalTextPosition(SwingConstants.CENTER); // מרכז
        this.buttonStart.setVerticalTextPosition(SwingConstants.CENTER);   // מרכז
    }
    public void startButtonExiAndStartt() { // כפתור יציאה
        this.buttonExit.addActionListener((event) -> {
            ExitEndGame();
        });
        this.add(this.buttonExit); // כפתור המשך
        this.buttonStart.addActionListener((event) -> {
            this.jFrame.atartGame();
            removeButtonExitAndStartt();
            startButtonStop();
            SwingUtilities.invokeLater(() -> jFrame.getGame().requestFocusInWindow());// החזרת הפוקוס לפאנל המשחק
        });
        this.add(this.buttonStart);
        this.revalidate();
        this.repaint();
    }
    public void ExitEndGame() { // סוף משחק
        this.jFrame.returnFromGame();
        this.jFrame.setCheck(false);
        this.remove(this.imageLabelForNumberStars);
        this.jFrame.setCountStars(0);
        updateStarCountLabel();
        removeButtonExitAndStartt();
    }
    public void removeButtonExitAndStartt() { // הסרת כפתור
        this.remove(this.buttonExit);
        this.remove(this.buttonStart);
        this.revalidate();   // מרענן את המבנה של הפאנל
        this.repaint();
    }


    public void playButtonRetrn() { // כפתור חזרה
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/buttonRed.png")); // תמונה לכפתור:
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 65, Image.SCALE_SMOOTH); // קובע גודל
        this.imageForButtonReturn = new ImageIcon(scaledImage);

        this.buttonReturn = new JButton(this.imageForButtonReturn); // כפתור חזרה עם תמונה
        this.buttonReturn.setBounds(4, -5, 60, 50);
        this.buttonReturn.setOpaque(false);
        this.buttonReturn.setContentAreaFilled(false);// לא לצבוע את האיזור שבתוך הכפתור
        this.buttonReturn.setBorderPainted(false); // לא לצייר מסגרת לכפתור
        this.buttonReturn.setFocusPainted(false); // לא לצייר קו מסביב כשיש פוקוס
        this.buttonReturn.setBorder(null); // מסיר את האובייקט Border
        this.buttonReturn.setText("חזור"); // הטקסט על התמונה
        this.buttonReturn.setFont(new Font("Arial", Font.BOLD, 20)); // גודל הטקסט
        this.buttonReturn.setForeground(Color.WHITE); // צבע הטקסט
        this.buttonReturn.setHorizontalTextPosition(SwingConstants.CENTER); // מרכז
        this.buttonReturn.setVerticalTextPosition(SwingConstants.CENTER);   // מרכז
    }
    public void playButtonStop() { // כפתור עצור
        ImageIcon originalIconStop = new ImageIcon(getClass().getResource("/buttonRed.png")); // תמונה לכפתור:
        Image scaledImageStop = originalIconStop.getImage().getScaledInstance(80, 65, Image.SCALE_SMOOTH); // קובע גודל
        this.imageForButtonStop = new ImageIcon(scaledImageStop);

        this.buttonStop = new JButton(this.imageForButtonStop); // כפתור חזרה עם תמונה
        this.buttonStop.setBounds(4, -5, 60, 50);
        this.buttonStop.setOpaque(false);
        this.buttonStop.setContentAreaFilled(false);// לא לצבוע את האיזור שבתוך הכפתור
        this.buttonStop.setBorderPainted(false); // לא לצייר מסגרת לכפתור
        this.buttonStop.setFocusPainted(false); // לא לצייר קו מסביב כשיש פוקוס
        this.buttonStop.setBorder(null); // מסיר את האובייקט Border
        this.buttonStop.setText("עצור"); // הטקסט על התמונה
        this.buttonStop.setFont(new Font("Arial", Font.BOLD, 20)); // גודל הטקסט
        this.buttonStop.setForeground(Color.WHITE); // צבע הטקסט
        this.buttonStop.setHorizontalTextPosition(SwingConstants.CENTER); // מרכז
        this.buttonStop.setVerticalTextPosition(SwingConstants.CENTER);   // מרכז
    }
    public void startButtonStop() {
        this.buttonStop.addActionListener((event) -> {
           this.jFrame.stopGame();
            removeButtonStop();
            startButtonExiAndStartt();
            SwingUtilities.invokeLater(() -> jFrame.getGame().requestFocusInWindow());// החזרת הפוקוס לפאנל המשחק
        });
        this.add(this.buttonStop);
        this.revalidate();
        this.repaint();
    }

    public void removeButtonStop() { // הסרת כפתור
        this.remove(this.buttonStop);
        this.revalidate();   // מרענן את המבנה של הפאנל
        this.repaint();
    }

    public void removeButtonReturn() { // הסרת כפתור
        this.remove(this.buttonReturn);
        this.revalidate();   // מרענן את המבנה של הפאנל
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.image != null && this.image.getIconWidth() > 0) {
            g.drawImage(this.image.getImage(), -100, -80, 2000, 200, this);
        }
    }

}
