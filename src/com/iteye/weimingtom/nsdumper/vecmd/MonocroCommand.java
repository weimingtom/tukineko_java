package com.iteye.weimingtom.nsdumper.vecmd;

public class MonocroCommand extends VECommand {
	@Override
	public boolean check(String str) {
		return str.startsWith("monocro");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] monocro");
		
		System.err.println("not implement: monocro");
	}
}
