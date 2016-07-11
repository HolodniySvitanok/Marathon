package com.homework17.marathon;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Racecourse {

	private ExecutorService executor;
	private CountDownLatch startCDL;
	private CountDownLatch finishCDL;
	private int distance1;
	private int distance2;
	private int distance3;
	private int distance4;
	private Torch torchTeam1;
	private Torch torchTeam2;
	private Torch torchTeam3;
	private Torch torchTeam4;

	public void run() {
		eventReg();
		startMessage();
		runnerRuls();
		executor.shutdown();
	}

	private void startMessage() {
		System.out.println("НА СТАРТ !");
		try {
			TimeUnit.SECONDS.sleep(1);
			System.out.println("ВНИМАНИЕ !");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("МАРШ !\n");
		} catch (InterruptedException e) {
		}

	}

	private void eventReg() {
		executor = Executors.newCachedThreadPool();

		int countTeam = 4;
		startCDL = new CountDownLatch(countTeam);
		finishCDL = new CountDownLatch(countTeam);

		torchTeam1 = new Torch("КРАСНЫХ");
		torchTeam2 = new Torch("ЗЕЛЕНЫХ");
		torchTeam3 = new Torch("СИНИХ");
		torchTeam4 = new Torch("ЖЕЛТЫХ");
	}

	private void runnerRuls() {
		// первые участники марафона
		Future<Torch> submit = executor.submit(new Runner("Красный1", null, torchTeam1, distance1, startCDL, null));
		Future<Torch> submit1 = executor.submit(new Runner("Зеленый1", null, torchTeam2, distance1, startCDL, null));
		Future<Torch> submit2 = executor.submit(new Runner("Синий1", null, torchTeam3, distance1, startCDL, null));
		Future<Torch> submit3 = executor.submit(new Runner("Желтый1", null, torchTeam4, distance1, startCDL, null));

		// вторые участники марафона
		Future<Torch> submit4 = executor.submit(new Runner("Красный2", submit, null, distance2, null, null));
		Future<Torch> submit5 = executor.submit(new Runner("Зеленый2", submit1, null, distance2, null, null));
		Future<Torch> submit6 = executor.submit(new Runner("Синий2", submit2, null, distance2, null, null));
		Future<Torch> submit7 = executor.submit(new Runner("Желтый2", submit3, null, distance2, null, null));

		// третий участник марафона
		Future<Torch> submit8 = executor.submit(new Runner("Красный3", submit4, null, distance3, null, null));
		Future<Torch> submit9 = executor.submit(new Runner("Зеленый3", submit5, null, distance3, null, null));
		Future<Torch> submit10 = executor.submit(new Runner("Синий3", submit6, null, distance3, null, null));
		Future<Torch> submit11 = executor.submit(new Runner("Желтый3", submit7, null, distance3, null, null));

		// четвертый участник марафона
		Future<Torch> submit12 = executor.submit(new Runner("Красный4", submit8, null, distance4, null, finishCDL));
		Future<Torch> submit13 = executor.submit(new Runner("Зеленый4", submit9, null, distance4, null, finishCDL));
		Future<Torch> submit14 = executor.submit(new Runner("Синий4", submit10, null, distance4, null, finishCDL));
		Future<Torch> submit15 = executor.submit(new Runner("Желтый4", submit11, null, distance4, null, finishCDL));

		executor.execute(new Scoreboard(submit12, finishCDL));
		executor.execute(new Scoreboard(submit13, finishCDL));
		executor.execute(new Scoreboard(submit14, finishCDL));
		executor.execute(new Scoreboard(submit15, finishCDL));
	}

	public void setDistance1(int distance1) {
		this.distance1 = distance1;
	}

	public void setDistance2(int distance2) {
		this.distance2 = distance2;
	}

	public void setDistance3(int distance3) {
		this.distance3 = distance3;
	}

	public void setDistance4(int distance4) {
		this.distance4 = distance4;
	}

}
