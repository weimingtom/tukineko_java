package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class ClickvoiceCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "clickvoice");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] clickvoice");
		
		ns.error("clickvoice");
	}
}
