package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class MenufullCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("menu_full");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] menu_full");
		
		ns.error("menu_full");
	}
}
