package bg.sofia.uni.fmi.mjt.christmas;

public class Kid implements Runnable {
	private Workshop shop;
	private static final int MILISECONDS = 1000;

	public Kid(Workshop workshop) {
		shop = workshop;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(MILISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Gift gift = Gift.getGift();
		this.shop.postWish(gift);
		System.out.println("kid posts a wish");
	}

}