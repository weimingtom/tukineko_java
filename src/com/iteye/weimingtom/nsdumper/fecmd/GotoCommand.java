package com.iteye.weimingtom.nsdumper.fecmd;

import app.boya.tukineko.parser.NScripter;

public class GotoCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "goto");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] goto");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("goto");
		} else {
			try {
				ns.gotoLabel(ns.getArg(0));
			} catch (Exception localException1) {
				ns.error("goto");
			}
			ns.makeLineRest(1);
		}		
	}
}
