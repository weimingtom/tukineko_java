package com.iteye.weimingtom.nsdumper.vecmd;

public class WaveCommand extends VECommand {
	@Override
	public boolean check(String str) {
		return str.startsWith("wave");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] wave");
		
		System.err.println("not implement: wave");
	}
}
