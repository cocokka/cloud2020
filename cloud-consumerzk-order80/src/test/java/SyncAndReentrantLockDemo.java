import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource
{
    private int number=1;//a:1 b:2 c:3
    private Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            //判断
            while (number!=1){
                c1.await();
            }
            //干活
            for(int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number=2;//标志位
            c2.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            //判断
            while (number!=2){
                c2.await();
            }
            //干活
            for(int i=1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number=3;//标志位
            c3.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            //判断
            while (number!=3){
                c3.await();
            }
            //干活
            for(int i=1;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number=1;//标志位
            c1.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }


}

public class SyncAndReentrantLockDemo {
    /**
     * aa打印5次，bb打印10次，cc打印15次
     * 紧接着
     * aa打印5次，bb打印10次，cc打印15次
     * 来10轮
     * @param args
     */
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i=1;i<=10;i++){
                shareResource.print5();
            }
        },"a").start();

        new Thread(()->{
            for (int i=1;i<=10;i++){
                shareResource.print10();
            }
        },"b").start();

        new Thread(()->{
            for (int i=1;i<=10;i++){
                shareResource.print15();
            }
        },"c").start();
    }
}
