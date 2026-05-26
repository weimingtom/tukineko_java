package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class PrintCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "print");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] print");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("print");
		} else {
			ns.tn.paintB();
			ns.makeLineRest(1);
		}		
	}
}
