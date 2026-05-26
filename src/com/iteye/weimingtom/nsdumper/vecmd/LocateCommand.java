package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class LocateCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("locate");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] locate");
		
		ns.error("abssetcursor");
	}
}
