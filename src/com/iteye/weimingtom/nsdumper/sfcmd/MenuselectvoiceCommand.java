package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class MenuselectvoiceCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "menuselectvoice");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] menuselectvoice");
		
		ns.error("menuselectvoice");
	}
}
