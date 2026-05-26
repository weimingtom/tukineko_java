package com.iteye.weimingtom.nsdumper.ecmd;

import java.io.IOException;

import app.boya.tukineko.entry.NsSelect;
import app.boya.tukineko.parser.NScripter;

public class SelgosubCommand extends ECommand {
	NScripter ns = NScripter.getInstance();
	
	@Override
	public boolean check(String str) {
		return checkCommand(str, "selgosub");
	}
	
	@Override
	public void execute() {
		debug("[ECommand] selgosub");
		
		if (ns.parseArgs(true) % 2 != 0) {
			ns.error("select|selgosub");
		} else {
			int i = ns.getArgSize();
			while (true) {
				for (int j = 0; j < i; j += 2) {
					ns.nd.select.addElement(new NsSelect(ns.getArg(j),
							ns.getArg(j + 1), 
							ns.nd.text.getY(), 
							ns.tn.putMess(ns.nd.text, ns.getArg(j), ns.nd.textcolor, false, false), 
							false));
				}
				if (!ns.argCont)
					break;
				try {
					String paramString = ns.readLine();
					i = ns.parseArgs(false);
				} catch (IOException e) {
				
				}
			}
			if (!ns.nd.fadeFlag)
				ns.tn.paintB();
			else {
				ns.tn.paintF();
			}
			ns.selectWait();
		}		
	}
}
