package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class Mp3loopCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "mp3loop");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] mp3loop");
		
		ns.error("mp3loop");
	}
}
