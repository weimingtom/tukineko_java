package com.iteye.weimingtom.nsdumper.fecmd;

import java.io.IOException;

import app.boya.tukineko.parser.NScripter;

public class JumpfCommand extends FECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "jumpf");
	}
	
	@Override
	public void execute() {
		debug("[FECommand] jumpf");
		
		ns.lineRest = null;
		while (true) {
			try {
				String paramString = ns.readLine();
				if (paramString.startsWith("~") == true) {
					break;
				}
				continue;
			} catch (IOException e) {

			}
			ns.error("jumpf");
		}
		try {
			ns.nd.jumpBack = ns.getFilePointer();
		} catch (Exception e) {

		}		
	}
}
