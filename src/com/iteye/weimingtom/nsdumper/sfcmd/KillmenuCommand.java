package com.iteye.weimingtom.nsdumper.sfcmd;

import app.boya.tukineko.parser.NScripter;

public class KillmenuCommand extends SFCommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "killmenu");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] killmenu");
		
		ns.error("killmenu");
	}
}
