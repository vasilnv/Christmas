package bg.sofia.uni.fmi.mjt.christmas;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Workshop {
	private Queue<Gift> gifts = new LinkedList<>();
	private static final int NUM_OF_ELVES = 20;
	private Elf[] elves;

	public Workshop() {
		startElves();
	}

	public void startElves() {
		this.elves = new Elf[NUM_OF_ELVES];

		for (int i = 0; i < NUM_OF_ELVES; i++) {
			elves[i] = new Elf(i, this);
			elves[i].start();
		}
	}

	/**
	 * Adds a gift to the elves' backlog.
	 **/
	public synchronized void postWish(Gift gift) {
		gifts.add(gift);
		this.notifyAll();
	}

	/**
	 * Returns an array of the elves working in Santa's workshop.
	 **/
	public Elf[] getElves() {
		return elves;
	}

	/**
	 * Returns the next gift from the elves' backlog that has to be manufactured.
	 **/
	public synchronized Gift nextGift() {
		if (gifts.isEmpty()) {
			try {
				this.wait(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return gifts.poll();
	}

	/**
	 * Returns the total number of wishes sent to Santa's workshop by the kids.
	 **/
	public synchronized int getWishCount() {
		return gifts.size();
	}
}