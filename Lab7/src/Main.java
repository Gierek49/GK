import javax.swing.*;

public class Main {

    /**
     * Main routine allows this class to be run as an application.
     * It creates a JFrame, sets its content pane to be a Lab7 panel,
     * and sets its JMenuBar to be the menu bar for the panel.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Painting and Texturing");
        Lab7 panel = new Lab7();
        window.setContentPane(panel);
        window.setJMenuBar( panel.getMenuBar() );
        window.pack();
        window.setResizable(false);
        window.setLocation(50,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}