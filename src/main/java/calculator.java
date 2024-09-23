import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @Author : ZhangYiXin
 * @create 2024/9/23 23:01
 */
public class calculator {
    public static char[] op = {'+','-','×','÷','(',')'};
    public static String[] strOp = {"+","-","×","÷","(",")"};
    public static boolean isDigit(char c){
        if(c>='0'&&c<='9'){
            return true;
        }
        return false;
    }
    public static boolean isOp(char c){
        for(int i=0;i<op.length;i++){
            if(op[i]==c){
                return true;
            }
        }
        return false;
    }
    public static boolean isOp(String s){
        for(int i=0;i<strOp.length;i++){
            if(strOp[i].equals(s)){
                return true;
            }
        }
        return false;
    }
    public static boolean isFraction(char c) {
        if(c=='/') {
            return true;
        }
        return false;
    }
    //处理输入的计算式
    public static List<String> process(String str){
        List<String> list = new ArrayList<String>();
        char c;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++){
            c = str.charAt(i);
            //是否在范围内，是否分数
            if(isDigit(c)|| isFraction(c)){
                sb.append(c);
            }
            //运算符
            if(isOp(c)){
                if(sb.toString().length()>0){
                    list.add(sb.toString());
                    sb.delete(0, sb.toString().length());
                }
                list.add(c+"");
            }
        }
        if(sb.toString().length()>0) {
            list.add(sb.toString());
            sb.delete(0, sb.toString().length());
        }
        return list;
    }

    //一般计算式转换为后缀表达式
    public static List<String> simpleTosuffix(List<String> list){
        List<String> Postfixlist = new ArrayList<String>();//存放后缀表达式
        Stack<String> stack = new Stack<String>();//暂存操作符
        for(int i=0;i<list.size();i++){

            String s = list.get(i);
            if(s.equals("(")){
                stack.push(s);
            }else if(s.equals("×")||s.equals("÷")){
                stack.push(s);
            }else if(s.equals("+")||s.equals("-")){
                if(!stack.empty()){
                    while(!(stack.peek().equals("("))){
                        Postfixlist.add(stack.pop());
                        if(stack.empty()){
                            break;
                        }
                    }
                    stack.push(s);
                }else{
                    stack.push(s);
                }
            }else if(s.equals(")")){
                while(!(stack.peek().equals("("))){
                    Postfixlist.add(stack.pop());
                }
                stack.pop();
            }else{
                Postfixlist.add(s);
            }
            if(i==list.size()-1){
                while(!stack.empty()){
                    Postfixlist.add(stack.pop());
                }
            }
        }
        return Postfixlist;
    }

    //后缀表达式计算
    public static Fenshu count(String str){
        List<String> list2 = calculator.process(str);
        List<String> list = calculator.simpleTosuffix(list2);
        Stack<Fenshu> stack = new Stack<Fenshu>();
        for(int i=0;i<list.size();i++){
            String s = list.get(i);
            if(!calculator.isOp(s)){
                Fenshu fenshu;
                StringTokenizer tokenizer = new StringTokenizer(s, "/");
                int numerator = Integer.parseInt(tokenizer.nextToken());
                if(tokenizer.hasMoreTokens()) {
                    int denominator = Integer.parseInt(tokenizer.nextToken());
                    fenshu = new Fenshu(numerator, denominator);
                }else {
                    fenshu = new Fenshu(numerator, -1);
                }
                stack.push(fenshu);
            }else{
                if(s.equals("+")){
                    Fenshu a1 = stack.pop();
                    Fenshu a2 = stack.pop();
                    Fenshu v = a2.add(a1);
                    stack.push(v);
                }else if(s.equals("-")){
                    Fenshu a1 = stack.pop();
                    Fenshu a2 = stack.pop();
                    Fenshu v = a2.sub(a1);
                    stack.push(v);
                }else if(s.equals("×")){
                    Fenshu a1 = stack.pop();
                    Fenshu a2 = stack.pop();
                    Fenshu v = a2.muti(a1);
                    stack.push(v);
                }else if(s.equals("÷")){
                    Fenshu a1 = stack.pop();
                    Fenshu a2 = stack.pop();
                    Fenshu v = a2.div(a1);
                    stack.push(v);
                }
            }
        }
        return stack.pop();
    }

}
