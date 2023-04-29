public class Calculator {
    public static double calculate(String exp) {
        double result = 0.0f;
        double num1, num2;
        int indx1 = 0, indx2 = 0;
        char Operator = ' ';
        for(int i = 0; i < exp.length(); ++i) {
            if (isOperator(exp.charAt(i))) {
                Operator = exp.charAt(i);
                indx1 = i - 1;
                indx2 = i + 1;
                break;
            }
        }
        num1 = parseDouble(exp, 0, indx1);
        num2 = parseDouble(exp, indx2, exp.length() - 1);

        switch (Operator) {
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> result = num1 / num2;
        }
        return result;
    }

    // 判断是否为操作符
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    //将字符串中数字解析出来
    public static double parseDouble(String exp, int begin, int end) {
        int length = exp.length();
        StringBuilder numStr = new StringBuilder();
        double ret;
        for(int i = begin; i < length ; ++i) {
            numStr.append(exp.charAt(i));
            if (i == end) break;
        }
        ret = Double.parseDouble(numStr.toString());
        return ret;
    }
}