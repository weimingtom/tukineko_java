package app.boya.tukineko.global;

public class NsTimer extends Thread {
	private int timer;
	private boolean clear;
	private boolean loop;

	public NsTimer() {
		super("NsTimer");
		this.timer = 0;
		this.clear = false;
		this.loop = true;
		start();
	}

	public NsTimer(int paramInt) {
		super("NsTimer");
		this.timer = paramInt;
		this.clear = false;
		this.loop = true;
		start();
	}

	public void clear() {
		this.clear = true;
	}

	public int read() {
		return this.timer;
	}

	public void exit() {
		this.loop = false;
		try {
			join();
		} catch (Exception localException) {
		}
	}

	@Override
	public void run() {
		while (this.loop) {
			if (this.clear == true) {
				this.clear = false;
				this.timer = -1;
			}
			this.timer += 1;
			try {
				Thread.sleep(1L);
			} catch (Exception localException) {
			}
		}
	}
}
