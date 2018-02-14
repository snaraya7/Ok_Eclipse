package edu.ncstate.csc510.okeclipse.model;

/**
 * 
 * @author ncshr
 *
 */
public final class OECommand {

	private String name;
	private String id;

	public OECommand(String command, String id) {
		super();
		this.name = command;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "OECommand [command=" + name + ", id=" + id + "]";
	}

}
