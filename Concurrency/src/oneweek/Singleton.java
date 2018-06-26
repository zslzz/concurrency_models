package oneweek;

/**
 * 单例模式涉及到线程安全操作，故而拿出来编写
 * 不安全的原因：
 *   产生的单例的线程和拿单例的线程不一致，在对线程方法加锁的时候，需要时刻检测对象是否存在。
 *   为防止产生多个对象，采用了双重锁的形式。
 *
 *   其实单例模式有点像 生产者消费者的模式 的特殊情况。
 *   消费者如果没有消息消费，那么通知生产者进行生产。
 *   如果已经生产完毕，那么消费者则永远使用该消息。
 *
 *   延伸： 产生线程不安全的根本原因在于，生成对象的方法未加锁，导致可能会生产多个。
 *   因此 只要保证生产的时候，产生的对象唯一即可。
 * Created by zslzz on 2018/6/2.
 */

//反序列化会破坏Singleton，主要原因在于反序列化时会通过反射调用无参构造方法生成新的对象。
 class Singleton {
    private volatile static Singleton singleton1; //volatile保证了初始化时赋值的重排序问题
    private Singleton(){}
    public static Singleton getSingletonDemo7(){
        if (singleton1 == null) {
            synchronized (Singleton.class) { //保证后续操作的原子性
                if (singleton1 == null) {
                    singleton1 = new Singleton();
                }
            }
        }
        return singleton1;
    }
}
enum SingletonEnum{
     instance;
     public void dowhatever(){
         System.out.print('h');
     }
}
