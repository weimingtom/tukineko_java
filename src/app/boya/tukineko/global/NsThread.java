package app.boya.tukineko.global;

import app.boya.tukineko.parser.NScripter;

public class NsThread extends Thread {
	private NScripter ns;

	public NsThread(NScripter ns) {
		super("NsThread");
		this.ns = ns;
	}

	@Override
	public void run() {
		this.ns.run();
	}
}
