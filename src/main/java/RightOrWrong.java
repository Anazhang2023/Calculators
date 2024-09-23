import java.io.*;
import java.util.List;

/**
 * @Author : ZhangYiXin
 * @create 2024/9/23 21:25
 */
public class RightOrWrong {
    //输入文件

    public void RightWrong() {
        //轮流读取文件的一行
        //正则表达式判断格式是否正确
        //计算题目答案
        //比较
        // 创建FileReader或InputStreamReader实例
        try {
            // 创建FileReader或InputStreamReader实例
            FileReader fileReader1 = new FileReader("testExercises.txt");
            FileReader fileReader2 = new FileReader("testAnswer.txt");
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
            String line1;
            String line2;
            int rightNum = 0;
            int wrongNum = 0;
            StringBuffer right = new StringBuffer();
            StringBuffer wrong = new StringBuffer();
            // 循环读取每一行
            for (int i=1;(line1 = bufferedReader1.readLine()) != null&&(line2 = bufferedReader2.readLine()) != null;i++) {
                Fenshu countFenshu = calculator.count(line1);
                Fenshu answerFenshu = calculator.count(line2);
                System.out.println(line1);
                System.out.println(line2);
                //计算器代码引用
                if(countFenshu.denominator==answerFenshu.denominator&&countFenshu.numerator==answerFenshu.numerator){
                    rightNum++;
                    right.append(i+" ");
                }else{
                    wrongNum++;
                    wrong.append(i+" ");
                }
                System.out.println("Right: "+rightNum+" ( "+right+")");
                System.out.println("Wrong: "+wrongNum+" ( "+wrong+")");
            }
            // 关闭流
            bufferedReader1.close();
            fileReader1.close();
            bufferedReader2.close();
            fileReader2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
