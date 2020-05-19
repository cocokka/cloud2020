import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean FLAG=true;//默认开启，进行生产+消费
    private AtomicInteger atomicInteger=new AtomicInteger();
    //传接口，而不是传类
    BlockingQueue<String> blockingQueue=null;
    //构造注入
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception{
        String data=null;
        boolean retValue;
        while (FLAG){
            data=atomicInteger.incrementAndGet()+"";
            retValue=blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t插入队列数据"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t插入队列数据"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);//1s生产一个
        }
        System.out.println(Thread.currentThread().getName()+"\tFLAG为false了，生产动作结束");
    }
    public void myConsumer() throws Exception{
        String result=null;
        while (FLAG){
            //TimeUnit.SECONDS.sleep(2);
            result=blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(null==result || result.equalsIgnoreCase("")){
                FLAG=false;
                System.out.println(Thread.currentThread().getName()+"\t超过2s没取到退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t消费队列"+result+"成功");
        }
    }
    public void stop() throws Exception{
        FLAG=false;
    }
}
public class ProdConsumber_BlockQueueDemo {
    /**
     * 生产者消费者
     * valatile/cas/atomicInteger/blockqueue/线程交互/原子引用
     * @param args
     */
    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            System.out.println("生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"prod").start();

        new Thread(()->{
            System.out.println("消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myResource.stop();

    }
}
