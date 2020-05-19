import java.util.concurrent.*;

public class MyThreadPoolDemo {
    /**
     * 最大数包含核心数
     * 最多几个客户办理业务会爆？->最大线程数max(5)+队列数(3)=8
     * AbortPolicy 因为上线是8，超过8后爆，会报异常
     * CallerRunsPolicy 超过8，不会抛异常，谁调用我（main），我回退给谁处理
     * DiscardOldestPolicy 抛弃队列中等待最久的业务。
     * cpu密集型 cpu核数+1
     * io密集型：形式一：2*cpu 形式二：cpu核数/1-阻塞系数（阻塞系数0.8-0.9之间）
     * @param args
     */
    public static void main(String[] args) {
        //得到cpu核心数
        System.out.println(Runtime.getRuntime().availableProcessors());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try{
            for(int i=0;i<=10;i++){
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }catch (Exception e){

        }finally {
            threadPoolExecutor.shutdown();
        }

    }

}
