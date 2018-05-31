package oneweek;

/**
 * 该类主要用来测试不安全的并发操作
 * 计算结果19784.11919等
 * 可看出线程不安全
 * Created by zslzz on 2018/5/31.
 */
public class Counting {
    private static int mm=1;
    public static void main(String[]args) throws InterruptedException {
        class Count{

            private int count=0;
            private void increment(){
                ++count;
            }
            private int getCount(){
                return count;
            }
        }
        Count count= new Count();
        class CountingThread extends Thread{
            public void run(){
                for(int i=0;i<100000;i++){
                    count.increment();
                }
                System.out.println(count.getCount());
            }
        }
        CountingThread countingThread1 = new CountingThread();
        CountingThread countingThread2=new CountingThread();
        countingThread1.start();
        countingThread2.start();
        countingThread1.join();//将当前的线程加入主线程
//        countingThread2.join();
        System.out.println(count.getCount());
    }

}
