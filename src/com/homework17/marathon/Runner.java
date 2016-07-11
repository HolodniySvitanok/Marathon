package com.homework17.marathon;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Runner implements Callable<Torch> {

    private String nameRunner;
    private Future<Torch> transferTorch;
    private Torch torch;
    private int distance;
    private CountDownLatch startCDL;
    private CountDownLatch finishCDL;

    @Override
    public Torch call() throws Exception {

        if (transferTorch != null) {
            torch = transferTorch.get();
        } else {
            delay(1);
            startCDL.countDown();
            startCDL.await();
        }

        long sTime = System.currentTimeMillis();
        delay(distance);

        torch.addPointer(sTime, nameRunner, distance);
        System.out.println(nameRunner + " передал факел");

        if (finishCDL != null) {
            finishCDL.countDown();
        }
        return torch;
    }

    public Runner(String nameRuuner, Future<Torch> transferTorch, Torch torch, int distance, CountDownLatch startCDL,
            CountDownLatch finishCDL) {
        this.nameRunner = nameRuuner;
        this.transferTorch = transferTorch;
        this.torch = torch;
        this.distance = distance;
        this.startCDL = startCDL;
        this.finishCDL = finishCDL;
    }

    private void delay(int distance) {
        int ran = (int) (Math.random() * ((distance * 1000) - 1) + 3000);

        try {
            TimeUnit.MILLISECONDS.sleep(ran);
        } catch (InterruptedException e) {
        }
    }

}
