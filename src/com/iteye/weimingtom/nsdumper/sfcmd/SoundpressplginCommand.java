package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class SoundpressplginCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "soundpressplgin");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] soundpressplgin");
		
		ns.error("soundpressplgin");
	}
}
