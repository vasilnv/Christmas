package bg.sofia.uni.fmi.mjt.christmas;

public class Test {

	private static final int NUMBER_OF_KIDS = 25;
	private static final int MILLISECS = 10000;

	
	public static void main(String[] args) {
		Workshop shop = new Workshop();
		Thread[] kids = new Thread[NUMBER_OF_KIDS];

		for (int i = 0; i < NUMBER_OF_KIDS; i++) {
			kids[i] = new Thread(new Kid(shop));
			kids[i].start();
		}

		for (int i = 0; i < NUMBER_OF_KIDS; i++) {
			try {
				kids[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(shop.getWishCount());
	}

}
