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
    public void printList(List<String> list){
        for(String o:list){
            System.out.print(o+" ");
        }
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



}
