package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class MenuwindowCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("menu_window");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] menu_window");
		
		ns.error("menu_window");
	}
}
