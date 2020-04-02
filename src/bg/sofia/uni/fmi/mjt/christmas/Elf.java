package bg.sofia.uni.fmi.mjt.christmas;

public class Elf extends Thread {
	private int id;
	private Workshop workshop;
	private int cntForGifts = 0;

	public Elf(int id, Workshop workshop) {
		this.id = id;
		this.workshop = workshop;
	}

	/**
	 * Gets a wish from the backlog and creates the wanted gift.
	 **/
	public synchronized void craftGift() {
		Gift gift = null;
		while ((gift = workshop.nextGift()) != null) {
			try {
				System.out.println("Elf number " + this.id + " creates a gift!");
				Thread.sleep(gift.getCraftTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gift = workshop.nextGift();
			this.cntForGifts++;
		}
		System.out.println(
				"elf number " + this.id + " created a total number of " + this.getTotalGiftsCrafted() + " gifts");
	}

	/**
	 * Returns the total number of gifts that the given elf has crafted.
	 **/
	public int getTotalGiftsCrafted() {
		return this.cntForGifts;
	}

	@Override
	public void run() {
		this.craftGift();
	}
}