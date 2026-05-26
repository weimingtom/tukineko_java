package app.boya.tukineko.entry;

import java.io.Serializable;

public class NsGosub implements Serializable {
	private static final long serialVersionUID = 1L;

	public int retpos;
	public String rest;

	public NsGosub() {
		this.retpos = 0;
		this.rest = null;
	}
}
