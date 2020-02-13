package principal;


public class tractarEntrada {
	private navegador.commandType option;
	private String[] params = new String[1];

	public navegador.commandType getOption() {
		return option;
	}

	public String[] getParams() {
		return params;
	}

	public short check(String[] args) {
		short error = 0;
		String command = args[0].toLowerCase();
		System.out.println(command);
		short len = (short) args.length;
		switch (command) {
		case "goto":
			if (len > 1) {
				this.params = new String[1];
				if (len > 2) {
					String temparg = args[1];
					for (short i = 2; i < len; ++i) {
						temparg = temparg.concat(" " + args[i]);
					}
					this.params[0] = temparg;
				} else {
					this.params[0] = args[1];
				}
				this.option = navegador.commandType.GOTO;
			} else {
				// No hi ha arguments
				error = 2;
			}
			break;
		case "golast":
			if (len == 1) {
				this.option = navegador.commandType.GOLAST;
			} else {
				error = 1;
				// Nombre eqivocat d'arguments
			}
			break;
		case "list":
			if (len == 1 || len == 2) {
				this.option = navegador.commandType.LIST;
				if (len == 2)
					this.params[0] = args[1];
			} else {
				error = 1;
			}
			break;
		case "up":
			if (len == 1) {
				this.option = navegador.commandType.UP;
			} else {
				error = 1;
			}
			break;
		case "infofile":
			if (len > 1) {
				this.params = new String[1];
				if (len > 2) {
					String temparg = args[1];
					for (short i = 2; i < len; ++i) {
						temparg = temparg.concat(" " + args[i]);
					}
					this.params[0] = temparg;
				} else {
					this.params[0] = args[1];
				}
				this.option = navegador.commandType.INFOFILE;
			} else {
				// No hi ha arguments
				error = 2;
			}
			break;
		case "infodir":
			if (len > 1) {
				this.params = new String[1];
				if (len > 2) {
					String temparg = args[1];
					for (short i = 2; i < len; ++i) {
						temparg = temparg.concat(" " + args[i]);
					}
					this.params[0] = temparg;
				} else {
					this.params[0] = args[1];
				}
				this.option = navegador.commandType.INFODIR;
			} else {
				// No hi ha arguments
				error = 2;
			}
			break;
		case "help":
			if (len == 1) {
				this.option = navegador.commandType.HELP;
			} else {
				error = 1;
			}
			break;
		case "createdir":
			if (len == 2) {
				this.params = new String[1];
				this.option = navegador.commandType.CREATEDIR;
				this.params[0] = args[1];
			} else {
				error = 1;
			}
			break;
		case "createfile":
			if (len == 2) {
				this.params = new String[1];
				this.option = navegador.commandType.CREATEFILE;
				this.params[0] = args[1];
			} else {
				error = 1;
			}
			break;
		case "sortby":
			if (len == 2) {
				this.params = new String[1];
				this.option = navegador.commandType.SORTBY;
				this.params[0] = args[1];
			} else {
				error = 1;
			}
			break;
		case "deletedir":
			this.option = navegador.commandType.DELETEDIR;
			if (len != 1) {
				this.params = new String[1];
				if (len > 2) {
					String temparg = args[1];
					for (short i = 2; i < len; ++i) {
						temparg = temparg.concat(" " + args[i]);
					}
					this.params[0] = temparg;
				} else {
					this.params[0] = args[1];
				}
			}
			break;
		case "deletefile":
			if (len > 1) {
				this.option = navegador.commandType.DELETEFILE;
				this.params = new String[1];
				if (len > 2) {
					String temparg = args[1];
					for (short i = 2; i < len; ++i) {
						temparg = temparg.concat(" " + args[i]);
					}
					this.params[0] = temparg;
				} else {
					this.params[0] = args[1];
				}
			} else {
				error = 1;
			}
			break;
		case "exec":
			if (len > 1) {
				this.option = navegador.commandType.EXEC;
				this.params = new String[1];
				if (len > 2) {
					String temparg = args[1];
					for (short i = 2; i < len; ++i) {
						temparg = temparg.concat(" " + args[i]);
					}
					this.params[0] = temparg;
				} else {
					this.params[0] = args[1];
				}
			} else {
				error = 1;
			}
			break;
		case "clearlog":
			this.option = navegador.commandType.CLEARLOG;
			break;
		case "load":
			this.option = navegador.commandType.LOAD;
			if (len != 1) {
				this.params = new String[1];
				if (len > 2) {
					String temparg = args[1];
					for (short i = 2; i < len; ++i) {
						temparg = temparg.concat(" "+args[i]);
					}
					this.params[0] = temparg;
				} else {
					this.params[0] = args[1];
				}
			}
			break;
		case "log":
			if (len == 2) {
				this.option = navegador.commandType.LOG;
				this.params = new String[1];
				this.params[0]=args[1];
			} else {
				error = 2;
			}	
			break;
		case "exit":
			if (len == 1) {
				this.option = navegador.commandType.EXIT;
			} else {
				error = 1;
			}
			break;
		default:
			// Aquest comandament no existeix
			error = -1;
		}
		return error;
	}
}
