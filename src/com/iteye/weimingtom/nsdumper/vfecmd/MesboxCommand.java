package com.iteye.weimingtom.nsdumper.vfecmd;

import app.boya.tukineko.parser.NScripter;

public class MesboxCommand extends VFECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "mesbox");
	}
	
	@Override
	public void execute() {
		debug("[VFECommand] mesbox");
		
		ns.error("mesbox");
	}
}
