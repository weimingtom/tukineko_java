package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class ClickCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "click");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] click");
		
		ns.error("click");
	}
}
