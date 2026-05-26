package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class Mp3Command extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("mp3");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] mp3");
		
		ns.error("mp3");
	}
}
