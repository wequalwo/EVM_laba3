package com.company;

import javax.swing.*;
import java.awt.*;
// cos^2(x/2) + sqrt(x)

class MYP extends JPanel
{
    @Override public void paintComponent(Graphics g)
    {
        Color color = Color.BLUE;
        g.drawString("The maximum is ~ 5.70083, when x ~ 22.2050", 10, 20);
        g.drawString("The minimum is ~ 2.46485, when x ~ 5.85723", 10, 50);
    }
}

class MyPanel extends JPanel
{
    static int accuracy = 100000;
    static int resize = 50;
    static int f_size = 800;
    static int diff = 50;
    static int left = 3*3140/2;
    static int right = 8*3140;
    static double calculate(double x)
    {
        //double y = Math.cos(x/2)*Math.cos(x/2);
        return Math.sin(x/2)*Math.sin(x/2) + Math.pow(x, 0.5);
    }
    @Override public void paintComponent(Graphics g)
    {
        double[] x = new double[accuracy];
        double[] y = new double[accuracy];
        double x1 = 0;
        for(int i = 0; i<accuracy; i++)
        {
            double j = i;
            x1 = j/accuracy*100;
            x[i] = x1;
            y[i] = calculate(x1);
        }

        Color color = Color.BLUE;

        g.setColor(Color.BLACK);
        g.drawLine(diff, 50, diff, f_size/2+10);
        g.drawLine(50, f_size/2, 1450, f_size/2);
        g.setColor(Color.RED);

        g.drawLine((int) (x[left] * resize) + diff, f_size/2 + 10, (int) (x[left] * resize) + diff, 50);
        g.drawString("3pi/2", (int)((x[left] * resize) + diff - 30), (int)(f_size/2 - 10));
        g.drawLine((int) (x[right] * resize) + diff, f_size/2 + 10, (int) (x[right] * resize) + diff, 50);
        g.drawString("8pi", (int)((x[right] * resize) + diff - 30), (int)(f_size/2 - 10));
        g.setColor(Color.BLACK);

        for(int i = 0; i < accuracy - 72000; i++)
        {
            int _x = (int) (x[i] * resize) + diff;
            int _y = f_size / 2 - (int) (y[i] * resize);// - f_size/2;
            if (i >= left && i <= right)
            {
                g.setColor(color);
                g.drawOval(_x, _y, 1, 1);
            }

            if (Math.abs(y[i] - (int) y[i]) < 0.001 && (int) y[i] != 8)
            {
                g.setColor(Color.BLACK);
                g.drawString(Float.toString((int) y[i]), 20, _y + 5);
                g.drawLine(40, _y, 1450, _y);
            }
            if (Math.abs((x[i]) - (int) (x[i])) < 0.001 && (int) x[i] != 0)
            {
                g.setColor(Color.BLACK);
                g.drawString(Float.toString((int) x[i]), _x - 5, f_size / 2 + 25);
                g.drawLine(_x, 50, _x, f_size / 2 + 10);
            }

        }
    }
}


public class Main {

    public static void main(String[] args)
    {

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setTitle("PersonalQuest");
        frame.setSize(1500, 500);
        MyPanel panel = new MyPanel();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JFrame frame1 = new JFrame();
        frame1.setVisible(true);
        frame1.setTitle("Analysis");
        frame1.setSize(280, 100);
        MYP myp = new MYP();
        frame1.add(myp);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
