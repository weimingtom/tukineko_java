package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class SpiCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "spi");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] spi");
		
		// FIXME:
		System.err.println("not implement: spi");
		//ns.error("not implement: spi");
		//		
	}
}
