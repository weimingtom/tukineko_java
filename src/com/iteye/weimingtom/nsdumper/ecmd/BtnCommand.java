package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.entry.NsButton;
import app.boya.tukineko.parser.NScripter;

public class BtnCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "btn");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] btn");
		
		if (ns.parseArgs(true) < 7) {
			ns.error("btn");
		} else {
			ns.nd.btn.addElement(new NsButton(
					ns.nd.evalNum(ns.getArg(0)), 
					ns.nd.evalNum(ns.getArg(1)), ns.nd.evalNum(ns.getArg(2)), 
					ns.nd.evalNum(ns.getArg(3)), ns.nd.evalNum(ns.getArg(4)), 
					ns.nd.evalNum(ns.getArg(5)), ns.nd.evalNum(ns.getArg(6))));
			ns.makeLineRest(7);
		}
	}
}
