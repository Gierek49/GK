import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Transforms2D extends JPanel {

    private class Display extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.translate(300,300);  // Moves (0,0) to the center of the display.
            int whichTransform = transformSelect.getSelectedIndex();

            // TODO Apply transforms here, depending on the value of whichTransform!
            if (whichTransform == 0)
            {
                g2.setPaint(Color.BLUE);
                g2.drawPolygon(p);
                g2.fill(p);
            }
            else if (whichTransform == 1)
            {
                g2.setPaint(Color.BLUE);
                g2.scale(0.25,0.25);
                g2.drawPolygon(p);
                g2.fill(p);
            }
            else if (whichTransform == 2)
            {
                g2.setPaint(Color.BLUE);
                g2.rotate(Math.toRadians(45));
                g2.drawPolygon(p);
                g2.fill(p);
            }
            else if (whichTransform == 3)
            {
                g2.setPaint(Color.BLUE);
                g2.rotate(Math.toRadians(180));
                g2.drawPolygon(p);
                g2.fill(p);
            }
            else if (whichTransform == 4)
            {
                AffineTransform p4 = new AffineTransform();
                p4.translate(300,300);
                p4.shear(0, 0.5);
                g2.setTransform(p4);

                g2.setPaint(Color.BLUE);
                g2.drawPolygon(p);
                g2.fill(p);
            }
            else if (whichTransform == 5)
            {
                AffineTransform p3 = new AffineTransform();
                p3.translate(300,150);
                //p3.shear(0, 0.5);
                g2.setTransform(p3);

                g2.setPaint(Color.BLUE);
                g2.scale(1,0.25);
                g2.drawPolygon(p);
                g2.fill(p);
            }
            else if (whichTransform == 6)
            {
                AffineTransform p3 = new AffineTransform();
                p3.translate(300,300);
                p3.shear(0, 0.5);
                g2.setTransform(p3);

                g2.setPaint(Color.BLUE);
                g2.rotate(Math.toRadians(180));
                g2.drawPolygon(p);
                g2.fill(p);
            }
            else if (whichTransform == 7)
            {
                g2.setPaint(Color.BLUE);
               // p.addPoint(10,10);

                g2.rotate(Math.toRadians(180),1,-1);
                g2.drawPolygon(p);
                g2.fill(p);
            }
            else if (whichTransform == 8)
            {

                g2.setPaint(Color.BLUE);
                g2.scale(1,0.25);
                g2.rotate(Math.toRadians(45));
                g2.drawPolygon(p);
                g2.fill(p);
            }
            else if (whichTransform == 9)
            {
                AffineTransform p2 = new AffineTransform();
                p2.translate(300,300);
                p2.shear(0, 0.5);
                g2.setTransform(p2);

                g2.setPaint(Color.BLUE);
                g2.rotate(Math.toRadians(45+180));
                g2.drawPolygon(p);
                g2.fill(p);
            }

        }
    }

    
    private Display display;
   // private BufferedImage pic;
   Polygon p = new Polygon();
    private JComboBox<String> transformSelect;

    public Transforms2D() throws IOException {
       // pic = ImageIO.read(getClass().getClassLoader().getResource("shuttle.jpg"));
        int N = 12;
        int R = 150;
        for (int i = 0; i < N; i++ ) {
            p.addPoint((int) Math.round(R * Math.cos((Math.PI / 2 + 2 * Math.PI * i) / N)), (int) Math.round(R * Math.sin((Math.PI / 2 + 2 * Math.PI * i) / N)));
        }
        display = new Display();
        display.setBackground(Color.YELLOW);
        display.setPreferredSize(new Dimension(600,600));
        transformSelect = new JComboBox<String>();
        transformSelect.addItem("None");
        for (int i = 1; i < 10; i++) {
            transformSelect.addItem("No. " + i);
        }
        transformSelect.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.repaint();
            }
        });
        setLayout(new BorderLayout(3,3));
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.CENTER));
        top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        top.add(new JLabel("Transform: "));
        top.add(transformSelect);
        add(display,BorderLayout.CENTER);
        add(top,BorderLayout.NORTH);
    }


    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame("2D Transforms");
        window.setContentPane(new Transforms2D());
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
        window.setVisible(true);
    }

}