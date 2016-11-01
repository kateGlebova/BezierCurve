import javax.swing.*;
import java.awt.*;
import java.util.*;

/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class Frame extends JFrame {
    // Define constants
    public static final int CANVAS_WIDTH  = 1000;
    public static final int CANVAS_HEIGHT = 350;

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

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.BLACK);
            g.setColor(Color.WHITE);
            g.translate(500, 175);
            ArrayList<Point> points = new ArrayList<Point>() {{
                add(new Point(-450,-100));
                add(new Point(-200,100));
                add(new Point(150,-100));
                add(new Point(100,100));
            }};
            BezierCurve curve = new BezierCurve(points, g);
            curve.DrawBezierCurve();
            for (int i = 0; i < points.size(); ++i) {
               g.drawRect((int)points.get(i).getX(), (int)points.get(i).getY(), 2, 2);
            }
            g.setColor(Color.GREEN);
            curve.ShiftBezierCurve(50, -50);
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