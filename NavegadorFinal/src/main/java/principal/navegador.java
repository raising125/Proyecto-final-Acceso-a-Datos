package principal;

import java.io.File;
import java.util.Scanner;


public class navegador {
	protected enum commandType {
		LOGIN, GOTO, GOLAST, LIST, UP, INFOFILE, INFODIR, HELP, CREATEDIR, CREATEFILE, 
		SORTBY, DELETEDIR, DELETEFILE, LOADFILE, EXEC, CLEARLOG, IDIOMA, SAVEXMLTREE,
		LOADXMLTREE, GETERRORS, GETWARNINGS, SETPERMISSION, SAVEJSONTREE, LOADJSONTREE,
		SETLITERAL, EXIT
	}

	private static Scanner s;
	private static tractarEntrada input;
	private static utils utils;

	public static void main(String[] arguments) {
		utils = new utils();
		input = new tractarEntrada();
		s = new Scanner(System.in);
		commandType option;
		String[] params = null;
		System.out.println("Insert any directory: ");
		boolean done = false;
		File dir = null;
		while (!done) {
			dir = new File(s.nextLine());
			if (dir.exists() && dir.isDirectory()) {
				done = true;
			} else {
				System.out.println("Not a valid directory");
				System.out.println("Insert a valid one: ");
			}
		}
		done = false;
		utils.list(dir, null);
		short error;
		String command;
		File prevdir = dir;
		String com[];
		boolean log = true;
		while (!done) {
			System.out.println(dir);
			utils.print("insertcommand");
			command = s.nextLine();
			com = command.split(" ");
			error = input.check(com);
			File temp;
			if (error == 0) {
				option = input.getOption();
				params = input.getParams();
				switch (option) {
				case GOTO:
					temp = utils.go(params);
					if (temp != null) {
						prevdir = dir;
						dir = temp;
						utils.list(dir, null);
						if (log)
							utils.log("GOTO succesful");
					} else {
						utils.print("nodir");
						System.out.println("Remember that the path must be absolute");
					}
					break;
				case HELP:
					utils.help();
					break;
				case GOLAST:
					temp = utils.goLast(prevdir);
					if (temp != null) {
						prevdir = dir;
						dir = temp;
						if (log)
							utils.log("GOLAST succesful");
					} else {
						utils.print("nodir");
					}
					break;
				case UP:
					temp = utils.up(dir);
					if (temp == null)
						System.out.println("Impossible operation");
					else
						prevdir = dir;
					dir = temp;
					utils.list(dir, null);
					if (log)
						utils.log("UP succesful");
					break;
				case LIST:
					utils.list(dir, params);
					if (log)
						utils.log("LIST succesful");
					break;
				case SORTBY:
					utils.sortby(dir, params);
					if (log)
						utils.log("SORTBY");
					break;
				case INFOFILE:
					utils.infoFile(params[0]);
					if (log)
						utils.log("INFOFILE");
					break;
				case INFODIR:
					utils.infoDir(params[0]);
					if (log)
						utils.log("INFODIR");
					break;
				case CREATEDIR:
					utils.createDir(params[0]);
					if (log)
						utils.log("CREATEDIR");
					break;
				case CREATEFILE:
					utils.createFile(params[0]);
					if (log)
						utils.log("CREATEFILE");
					break;
				case DELETEDIR:
					utils.deleteDir(params[0], dir);
					if (log)
						utils.log("DELETEDIR");
					break;
				case DELETEFILE:
					utils.deleteFile(params[0]);
					if (log)
						utils.log("DELETEFILE");
					break;
				case EXEC:
					utils.exec(params[0], dir);
					if (log)
						utils.log("EXEC");
					break;
				case CLEARLOG:
					utils.clear();
					System.out.println("Cleared");
					utils.load(params[0], dir);
					break;
				case EXIT:
					utils.print("exit");
					done = true;
					break;
				}
			} else {
				System.out.println("An error ocurred");
				System.out.println("Error code " + error);
				switch (error) {
				case -1:
					utils.print("notavalidcommand");
					utils.log("ERROR: This command doesen't exist");
					break;
				case 1:
					utils.print("wrongargnum");
					utils.log("ERROR: Wrong number of arguments");
					break;
				case 2:
					utils.print("missingargs");
					utils.log("ERROR: Missing arguments");
					break;
				default:
					utils.print("uncatalogued");
					utils.log("ERROR: Uncatalogued error");
					break;
				}
			}
		}
	}
}
