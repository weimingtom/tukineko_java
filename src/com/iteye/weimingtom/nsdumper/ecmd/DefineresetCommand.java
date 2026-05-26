package com.iteye.weimingtom.nsdumper.ecmd;

import app.boya.tukineko.parser.NScripter;

public class DefineresetCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "definereset");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] definereset");
		
		ns.error("definereset");
	}
}
