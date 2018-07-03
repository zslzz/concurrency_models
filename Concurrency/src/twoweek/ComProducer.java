package twoweek;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zslzz on 2018/6/28.
 */
public class ComProducer {
}

class comsumer implements Runnable{
    private ArrayBlockingQueue<Integer> q ;
    public comsumer(ArrayBlockingQueue q){
        this.q=q;
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Integer m = this.q.take();
                System.out.print(m);
                if(m==-1){
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Product implements Runnable{
    private ArrayBlockingQueue<Integer> q;
    private List<Integer> list;
    public Product(ArrayBlockingQueue q, List<Integer> list){
        this.q=q;
    }
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0;i<this.list.size();i++){
                try {
                    this.q.put(this.list.get(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}