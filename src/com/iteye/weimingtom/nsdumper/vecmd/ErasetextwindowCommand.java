package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class ErasetextwindowCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("erasetextwindow");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] erasetextwindow");
		
		ns.error("erasetextwindow");
	}
}
