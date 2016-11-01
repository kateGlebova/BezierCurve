import java.awt.*;
import java.util.*;

public class BezierCurve {
    ArrayList<Point> sourcePoints;
    ArrayList<Point> curvePoints;
    Graphics painter;

    public BezierCurve(ArrayList<Point> srcPoints, Graphics g) {
        sourcePoints = srcPoints;
        painter = g;
        generatePoints();
    }

    public void DrawBezierCurve() {
        for (int i = 1; i < curvePoints.size(); i++)
        {
            int x1 = (int)(curvePoints.get(i-1).getX());
            int y1 = (int)(curvePoints.get(i-1).getY());
            int x2 = (int)(curvePoints.get(i).getX());
            int y2 = (int)(curvePoints.get(i).getY());
            painter.drawLine(x1, y1, x2, y2);
        }
    }

    public void ShiftBezierCurve(int x, int y) {
        for (int i = 1; i < curvePoints.size(); i++)
        {
            int x1 = (int)(curvePoints.get(i-1).getX() + x);
            int y1 = (int)(curvePoints.get(i-1).getY() + y);
            int x2 = (int)(curvePoints.get(i).getX() + x);
            int y2 = (int)(curvePoints.get(i).getY() + y);
            painter.drawLine(x1, y1, x2, y2);
        }
    }

    private void generatePoints() {
        curvePoints = new ArrayList<>();

        for (double t = 0; t <= 1; t += 0.001) {
            curvePoints.add(BezierFunction(t));
        }
    }

    private Point BezierFunction (double t) {
        double x = 0;
        double y = 0;
        int n = sourcePoints.size() - 1;

        for (int i = 0; i <= n; ++i) {
            x += sourcePoints.get(i).getX() * fact(n) / (fact(i) * fact(n - i)) * Math.pow(t, i) * Math.pow(1 - t, n - i);
            y += sourcePoints.get(i).getY() * fact(n) / (fact(i) * fact(n - i)) * Math.pow(t, i) * Math.pow(1 - t, n - i);
        }

        return new Point((int)x, (int)y);
    }

    private int fact(int n) {
        if (n < 0) throw new RuntimeException("negative argument.");
        if (n == 0)
            return 1;
        return n * fact(n - 1);
    }
}
