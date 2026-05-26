package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class LookbackflushCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "lookbackflush");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] lookbackflush");
		
		ns.error("lookbackflush");
	}
}
