package com.iteye.weimingtom.nsdumper.secmd;

public class SetcursorCommand extends SECommand {
	@Override
	public boolean check(String str) {
		return checkCommand(str, "setcursor");
	}
	
	@Override
	public void execute() {
		debug("[SECommand] setcursor");
		
		System.err.println("not implement: setcursor");
	}
}
