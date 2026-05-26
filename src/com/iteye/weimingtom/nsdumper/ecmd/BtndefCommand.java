package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class BtndefCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "btndef");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] btndef");
		
		if (ns.parseArgs(true) < 1) {
			ns.error("btndef");
		} else {
			ns.nd.btnImage = ns.nd.evalStr(ns.getArg(0));
			ns.nd.btnSel = -1;
			ns.nd.btn.removeAllElements();
			ns.makeLineRest(1);
		}
	}
}
