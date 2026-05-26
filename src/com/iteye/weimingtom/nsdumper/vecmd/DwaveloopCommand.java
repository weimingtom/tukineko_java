package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class DwaveloopCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "dwaveloop");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] dwaveloop");
		
		ns.error("dwaveloop");
	}
}
