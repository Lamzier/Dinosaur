package service;

import java.io.*;
import java.util.Arrays;

/**
 * 分数记录器
 * @author Lamzy
 */
public class ScoreRecorder {
    private static final String SCOREFILE = "src/data/soure";//得分记录文件
    private static int scores[] = new int[3];//当前得分最高的前三名

    /**
     * 分数初始化
     */
    public static void init() {
        File f = new File(SCOREFILE);
        if (!f.exists()){
            //文件不存在
            try {
                f.createNewFile();//创建新文件
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;//停止方法
        }
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(f);//文件字节流输入
            inputStreamReader = new InputStreamReader(fileInputStream);//字节流转换为字符流
            bufferedReader = new BufferedReader(inputStreamReader);//换种字符流
            String value = bufferedReader.readLine();//读取一行
            if (!(value == null || value.length() == 0)){//如果不为空值
                String[] vs = value.split(",");//分割字符串
                if (vs.length < 3){//如果分割结果小于3
                    Arrays.fill(scores , 0);//数组填充0
                }else {
                    for (int i = 0; i < 3; i ++){
                        //将文件的值赋值给当前分数数组
                        scores[i] = Integer.parseInt(vs[i]);//转为int类型
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //依次关闭流
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream  != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 保存分数
     */
    public static void saveScore(){
        String value = scores[0] + "," + scores[1] + "," + scores[2];
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = new FileOutputStream(SCOREFILE);//文件字节输出流
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);//字节流转换为字符流
            bufferedWriter = new BufferedWriter(outputStreamWriter);//转换缓冲字符流
            bufferedWriter.write(value);
            bufferedWriter.flush();//刷新字符流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //依次关闭流
            if (bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStreamWriter != null){
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 添加分数。如果新添加的分数比排行榜分数高，则会将新分数计入排行榜
     */
    public static void addNewScore(int score){
        //在得分数组基础上创建一个长度为4的临时数组
        int tmp[] = Arrays.copyOf(scores , 4);
        tmp[3] = score;//将新分数给第四个元素
        Arrays.sort(tmp);//临时数组升序排列
        scores = Arrays.copyOfRange(tmp , 1 , 4);//将后三个元素给得分数组
    }

    /**
     * 获取分数
     */
    public static int[] getScores(){
        return scores;
    }


}
