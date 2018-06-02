package oneweek;

/**
 * 竞态条件的控制，
 * 编译器可以会优化代码的执行顺序以此来提高速度，故所得的结果不一致。
 * 经过大约50次测试发现，未出现answer未true但值为0的情况。
 * 原因在于虽然有编译优化，但是线程的执行速度过快，使得两个值一起改变
 * Created by zslzz on 2018/6/1.
 */
public class Puzzle {
    static boolean answer=false;
    static int answerint=0;
    static Thread t1 = new Thread(){
        public void run(){
            answerint=33;
            answer=true;
        }
    };
    static Thread t2 =  new Thread(){
        public void run(){
            if(answer){
                System.out.println("answer is:"+answerint);
            }else{
                System.out.print("i dont know answer ");
            }
        }
    };
    public static void main(String[] args){
        t1.start();t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
