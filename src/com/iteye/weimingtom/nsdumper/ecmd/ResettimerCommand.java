package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class ResettimerCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("resettimer");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] resettimer");
		
		ns.setMsRest();
		ns.tn.timerClear();		
	}
}
