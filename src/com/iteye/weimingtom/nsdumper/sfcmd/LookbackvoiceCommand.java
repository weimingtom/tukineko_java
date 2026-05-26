package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class LookbackvoiceCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "lookbackvoice");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] lookbackvoice");
		
		ns.error("lookbackvoice");
	}
}
