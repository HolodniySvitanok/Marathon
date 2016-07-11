package com.homework17.marathon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * 
 * 
 * */
public class Torch {

	private String team;
	private List<Segment> points = new ArrayList<>();

	public Torch(String team) {
		this.team = team;
	}

	public void addPointer(long sTime, String nameRunner, int distance) {

		sTime = System.currentTimeMillis() - sTime;
		points.add(new Segment(nameRunner, distance, new Date(sTime)));
	}

	static class Segment {// отрезок марафона
		int distance;
		Date date;
		String nameRunner;

		Segment(String nameRunner, int distance, Date date) {
			this.nameRunner = nameRunner;
			this.distance = distance;
			this.date = date;
		}
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public List<Segment> getPoints() {
		return points;
	}

	public void setPoints(List<Segment> points) {
		this.points = points;
	}

}
