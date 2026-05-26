package com.iteye.weimingtom.nsdumper.vecmd;

import app.boya.tukineko.parser.NScripter;

public class BrCommand extends VECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return str.startsWith("br");
	}
	
	@Override
	public void execute() {
		debug("[VECommand] br");
		
		ns.setMsRest();
		ns.tn.putMess(ns.nd.text, "", ns.nd.textcolor, true, false);
		if (!ns.nd.fadeFlag) {
			ns.tn.paintB();
		} else {
			ns.tn.paintF();
		}		
	}
}
