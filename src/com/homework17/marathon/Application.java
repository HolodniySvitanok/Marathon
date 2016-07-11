package com.homework17.marathon;

public class Application {

	public static void main(String[] args) {
		Racecourse rc = new Racecourse();
		rc.setDistance1(7);
		rc.setDistance2(5);
		rc.setDistance3(10);
		rc.setDistance4(2);
		rc.run();
	}
}
