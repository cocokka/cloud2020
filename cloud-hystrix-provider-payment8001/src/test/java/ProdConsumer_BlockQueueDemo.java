import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData//资源类
{
    private int number=0;
    //多个线程操作同一份资源，毕然有争抢，毕然要加锁
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    public  void increment() throws Exception{
        lock.lock();
        try {
            //1判断
            while(number!=0){
                //等待，不能生产
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3通知唤醒
            condition.signalAll();

        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }

    public  void decrement() throws Exception{
        lock.lock();
        try {
            //1判断
            while(number==0){
                //等待，不能消费
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3通知唤醒
            condition.signalAll();

        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }


}
public class ProdConsumer_BlockQueueDemo {
    /**
     * 一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1，来5轮
     * 线程操作（方法）资源类
     * 判断 （由谁操作资源）     干活       通知
     * 防止虚假唤醒（while）
     * @param args
     */
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i=1;i<=5;i++){
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },"AA").start();

        new Thread(()->{
            for (int i=1;i<=5;i++){
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },"BB").start();


    }
}
