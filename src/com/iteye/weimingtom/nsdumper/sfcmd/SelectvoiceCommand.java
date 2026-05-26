package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class SelectvoiceCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "selectvoice");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] selectvoice");
		
		ns.error("selectvoice");
	}
}
