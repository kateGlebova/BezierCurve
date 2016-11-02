import javax.swing.*;
import java.awt.*;
import java.util.*;

/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class Frame extends JFrame {
    // Define constants
    public static final int CANVAS_WIDTH  = 600;
    public static final int CANVAS_HEIGHT = 600;

    // Declare an instance of the drawing canvas,
    // which is an inner class called DrawCanvas extending javax.swing.JPanel.
    private DrawCanvas canvas;

    // Constructor to set up the GUI components and event handlers
    public Frame() {
        canvas = new DrawCanvas();    // Construct the drawing canvas
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        // Set the Drawing JPanel as the JFrame's content-pane
        Container cp = getContentPane();
        cp.add(canvas);
        // or "setContentPane(canvas);"

        setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
        pack();              // Either pack() the components; or setSize()
        setTitle("......");  // "super" JFrame sets the title
        setVisible(true);    // "super" JFrame show
    }


    private class DrawCanvas extends JPanel {

        int originX = 300;
        int originY = 300;
        ArrayList<Point> CTRLpoints = new ArrayList<Point>() {{
            add(new Point(-100,-100));
            add(new Point(-50,100));
            add(new Point(50,-100));
            add(new Point(100,100));
        }};

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.BLACK);
            g.setColor(Color.WHITE);
            g.translate(originX, originY);
            BezierCurve curve = new BezierCurve(CTRLpoints, g);
            curve.DrawBezierCurve();
            for (int i = 0; i < CTRLpoints.size(); ++i) {
               g.drawRect((int)CTRLpoints.get(i).getX(), (int)CTRLpoints.get(i).getY(), 2, 2);
            }
            g.setColor(Color.GREEN);
            curve.TranslateBezierCurve(50, -50);
            g.setColor(Color.BLUE);
            curve.RotateBezierCurve(90);
            g.setColor(Color.YELLOW);
            curve.ScaleBezierCurve(1.5);
        }
    }

    public static void main(String[] args) {
        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Frame(); // Let the constructor do the job
            }
        });
    }
}