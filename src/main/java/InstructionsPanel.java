import javax.swing.*;
import java.awt.*;

public class InstructionsPanel extends JPanel {
    private int x;
    private int y;
    private int width;
    private int height;
    private MainMenu jFrame;
    private ImageIcon backgroundImage;

    public InstructionsPanel(int x, int y, int width, int height, MainMenu jFrame) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.jFrame = jFrame;

        this.setBounds(this.x, this.y, this.width, this.height);
        this.setLayout(null);

        // רקע
        this.backgroundImage = new ImageIcon(getClass().getResource("/imageMain.png"));

        // טקסט הוראות עם HTML בתוך JTextPane
        JTextPane instructionsPane = new JTextPane();
        instructionsPane.setContentType("text/html");
        instructionsPane.setText(
                "<html>" +
                        "<body style='direction: rtl; text-align: right; color: white; font-size: 16px; padding: 10px;'>" +
                        "<h2>ברוכים הבאים למשחק!</h2>" +
                        "<b>מטרת המשחק:</b> לאסוף כמה שיותר כוכבים מבלי להתנגש במכוניות שבכביש.<br><br>" +
                        "<b>כיצד משחקים?</b><br>" +
                        "- השתמש בכפתורי המקלדת ימינה ושמאלה כדי להזיז את השחקן בכביש.<br>" +
                        "- בכל פעם שתופסים כוכב, תקבל נקודה.<br>" +
                        "- אם אתה מתנגש במכונית – המשחק נגמר.<br><br>" +
                        "<b>מה מופיע בתפריט העליון?</b><br>" +
                        "- צג המציג את <b>מספר הכוכבים</b> שצברת עד כה במשחק.<br>" +
                        "- צג המציג את <b>שיא הכוכבים</b> האישי שלך עד היום.<br>" +
                        "- כפתור עצור - עוצר את המשחק.<br>" +
                        "- כפתור המשך - מחזיר את המשחק להמשך.<br>" +
                        "- כפתור יציאה - מחזיר אותך לתפריט הראשי.<br><br>" +
                        "<b>בהצלחה!</b>" +
                        "</body></html>"
        );
        instructionsPane.setEditable(false);
        instructionsPane.setOpaque(false);
        instructionsPane.setBounds(80, 50, 800, 500); // מספיק ריווח מהחלק העליון
        this.add(instructionsPane);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backgroundImage != null && this.backgroundImage.getIconWidth() > 0) {
            g.drawImage(this.backgroundImage.getImage(), this.x, 0, getWidth(), getHeight(), this);
        }
    }
}
