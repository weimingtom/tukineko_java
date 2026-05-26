package com.iteye.weimingtom.nsdumper.secmd;

public class SECommand {
	protected boolean checkCommand(String paramString1, String paramString2) {
		if (paramString1.equals(paramString2)) {
			return true;
		}
		if (paramString1.length() > paramString2.length() &&
			paramString1.startsWith(paramString2) &&
			(" \t".indexOf(paramString1.charAt(paramString2.length())) != -1)) {
			return true;
		}
		return false;
	}
	
	public boolean check(String str) {
		return false;
	}
	
	public void execute() {
		
	}
	
	protected void debug(String str) {
		System.out.println(str);
	}
}
