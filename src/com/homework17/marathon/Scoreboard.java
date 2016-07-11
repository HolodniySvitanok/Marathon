package com.homework17.marathon;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.homework17.marathon.Torch.Segment;

public class Scoreboard implements Runnable {

	private Torch torch;
	private Future<Torch> result;
	private CountDownLatch cdl;

	public Scoreboard(Future<Torch> result, CountDownLatch cdl) {
		this.result = result;
		this.cdl = cdl;
	}

	@Override
	public void run() {
		try {
			// cdl.countDown();
			cdl.await();
			torch = result.get();

		} catch (InterruptedException | ExecutionException e) {
			System.out.println(":*(");
		}
		long totalTime = 0;

		for (Segment seg : torch.getPoints()) {
			totalTime += seg.date.getTime();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("ss ':' S 'у.е.'");
		StringBuilder sb = new StringBuilder();
		sb.append("\nКоманда ").append(torch.getTeam()).append("\n");

		for (int i = 0; i < torch.getPoints().size(); i++) {
			sb.append(" участник ").append(torch.getPoints().get(i).nameRunner).append(" на дист. ")
					.append(torch.getPoints().get(i).distance).append(" км ").append(" пробежал за ")
					.append(sdf.format(torch.getPoints().get(i).date)).append("\n");
		}

		sb.append("общее время ").append(sdf.format(new Date(totalTime))).append("\n");

		System.out.println(sb.toString());
	}

}
