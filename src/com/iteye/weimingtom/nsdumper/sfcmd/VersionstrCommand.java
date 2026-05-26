package com.iteye.weimingtom.nsdumper.sfcmd;

public class VersionstrCommand extends SFCommand {
	@Override
	public boolean check(String str) {
		return checkCommand(str, "versionstr");
	}
	
	@Override
	public void execute() {
		debug("[SFCommand] versionstr");
		
		System.err.println("not implement: versionstr");
	}
}
