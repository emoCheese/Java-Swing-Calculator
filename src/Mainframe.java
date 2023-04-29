import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mainframe extends JFrame{

    private final JPanel root;
    private JTextField textField = null;
    private final JButton[] buttons = new JButton[10];
    //加减乘除按钮
    private final JButton B_add = new JButton("+");
    private final JButton B_sub = new JButton("-");
    private final JButton B_mul = new JButton("*");
    private final JButton B_div = new JButton("/");
    private final JButton B_dot = new JButton(".");

    private final JButton B_CE = new JButton("CE");
    private final JButton B_equal = new JButton("=");
    private final JButton B_del = new JButton("退格");
    private final GridBagConstraints gbc = new GridBagConstraints();
    private static int Oflag = 0;
    private static int Dflag = 0;
    public Mainframe(String tile) {
        super(tile);
        root = new JPanel(new GridBagLayout());
        initGbc();
        this.setContentPane(root);
        setButtonsToPanel();
        setTextfield();
        mouseClick mc = new mouseClick();

        //为按钮添加鼠标事件监听器
        B_CE.addMouseListener(mc);
        buttons[0].addMouseListener(mc);
        buttons[1].addMouseListener(mc);
        buttons[2].addMouseListener(mc);
        buttons[3].addMouseListener(mc);
        buttons[4].addMouseListener(mc);
        buttons[5].addMouseListener(mc);
        buttons[6].addMouseListener(mc);
        buttons[7].addMouseListener(mc);
        buttons[8].addMouseListener(mc);
        buttons[9].addMouseListener(mc);
        B_dot.addMouseListener(mc);
        B_add.addMouseListener(mc);
        B_sub.addMouseListener(mc);
        B_mul.addMouseListener(mc);
        B_div.addMouseListener(mc);
        B_equal.addMouseListener(mc);
        B_del.addMouseListener(mc);

    }
    //初始化gbc，使组件随窗口大小变化
    private void initGbc() {
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
    }
    private void setTextfield() {
        textField = new JTextField();
        gbc.gridy = 0;
        gbc.gridx = 0;
        //设置水平填充单元格
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;

        //设置字体
        textField.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        //设置文本无边框
        textField.setBorder(BorderFactory.createEmptyBorder());
        //设置文本靠右显示
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        //设置单行文本框不能点击
        textField.setEnabled(false);
        textField.setText("0");
        root.add(textField, gbc);
    }
    private void setButtonsToPanel() {
        for(int i = 0 ; i < 10; ++i) {
            buttons[i] = new JButton(String.valueOf(i));
        }
        setPosition(buttons[7], 1, 0);
        setPosition(buttons[8], 1, 1);
        setPosition(buttons[9], 1, 2);
        setPosition(B_div, 1, 3);
        setPosition(buttons[6], 2, 2);
        setPosition(buttons[5], 2, 1);
        setPosition(buttons[4], 2, 0);
        setPosition(B_mul, 2, 3);
        setPosition(buttons[3], 3, 2);
        setPosition(buttons[2], 3, 1);
        setPosition(buttons[1], 3, 0);
        setPosition(B_sub, 3, 3);
        setPosition(B_CE, 4, 0);
        setPosition(buttons[0], 4, 1);
        setPosition(B_dot, 4, 2);
        setPosition(B_add, 4, 3);
        setPosition(B_equal, 5, 3);
        setPosition(B_del, 5, 2);
    }
    private void setPosition(JButton button, int row, int col) {
        gbc.gridy = row;
        gbc.gridx = col;
        root.add(button, gbc);
    }
    private void append(String s) {

        if (textField.getText().equals("0"))
            textField.setText(s);
        else
            textField.setText(textField.getText() + s);
    }
    public class mouseClick extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource() == B_CE) {
                textField.setText("0");
            } else if(e.getSource() == buttons[0]) {
                append("0");
            } else if(e.getSource() == buttons[1]) {
                append("1");
            } else if(e.getSource() == buttons[2]) {
                append("2");
            } else if(e.getSource() == buttons[3]) {
                append("3");
            } else if(e.getSource() == buttons[4]) {
                append("4");
            } else if(e.getSource() == buttons[5]) {
                append("5");
            } else if(e.getSource() == buttons[6]) {
                append("6");
            } else if(e.getSource() == buttons[7]) {
                append("7");
            } else if(e.getSource() == buttons[8]) {
                append("8");
            } else if(e.getSource() == buttons[9]) {
                append("9");
            }
            if(e.getSource() == B_dot && Dflag == 0) {
                append(".");
                Dflag = 1;
            } else if(e.getSource() == B_add && Oflag == 0) {
                append("+");
                Oflag = 1;
                Dflag = 0;
            } else if(e.getSource() == B_sub && Oflag == 0) {
                append("-");
                Oflag = 1;
                Dflag = 0;
            } else if(e.getSource() == B_mul && Oflag == 0) {
                append("*");
                Oflag = 1;
                Dflag = 0;
            } else if(e.getSource() == B_div && Oflag == 0) {
                append("/");
                Oflag = 1;
                Dflag = 0;
            } else if(e.getSource() == B_equal) {
                String exp = textField.getText();
                double res = Calculator.calculate(exp);
                textField.setText(String.valueOf(res));
                Oflag = 0;
            } else if (e.getSource() == B_del) {
                String exp = textField.getText();
                if(Calculator.isOperator(exp.charAt(exp.length() - 1))) {
                    Oflag = 0;
                } else if(exp.charAt(exp.length() - 1) == '.') {
                    Dflag = 0;
                }
                StringBuilder stringBuilder = new StringBuilder(exp);
                stringBuilder.deleteCharAt(exp.length() - 1);
                String result = stringBuilder.toString();
                textField.setText(result);
            }
        }
    }
}
