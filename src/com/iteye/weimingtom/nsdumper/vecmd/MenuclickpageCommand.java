package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class MenuclickpageCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("menu_click_page");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] menu_click_page");
		
		ns.error("menu_click_page");
	}
}
