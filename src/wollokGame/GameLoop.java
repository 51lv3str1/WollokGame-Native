package wollokGame;

public class GameLoop implements Runnable {

	private volatile Thread loopThread = new Thread(this);
	private final WollokGameObject game;
	private Boolean isRunning = false;
	private Integer fpsCount = 0;
	private Integer fps = 60;
	private Integer ups = 60;

	public GameLoop(WollokGameObject game) {
		this.loopThread.setDaemon(true);
		this.game = game;
	}

	private void loop() {
		long initialTime = System.nanoTime();
		double timeU = 1000000000.0 / ups;
		double timeF = 1000000000.0 / fps;
		double deltaU = 0.0;
		double deltaF = 0.0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		this.isRunning = true;

		while (this.isRunning) {

			long currentTime = System.nanoTime();
			deltaU += (currentTime - initialTime) / timeU;
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;

			if (deltaU >= 1) {
				this.game.update(deltaU);
				deltaU--;
			}

			if (deltaF >= 1) {
				this.game.render(this.fpsCount);
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				this.fpsCount = frames;
				frames = 0;
				timer += 1000;
			}

			this.sleep(initialTime, deltaU);
		}

	}

	private void sleep(Long lastLoopTime, Double OPTIMAL_TIME) {
		try {
			Thread.sleep((long) Math.max(0, ((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000)));
		}

		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void end() {
		this.isRunning = false;
	}

	@Override
	public void run() {
		this.loop();
	}

}
