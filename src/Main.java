import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Mainframe frame = new Mainframe("计算器");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(400,440);

        frame.setVisible(true);

    }
}