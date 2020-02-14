package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class utils {
	// Objecte de InputChecker per a poder mirar l'imput per al Load
		private tractarEntrada input;
		private String currDir;
		public utils() {
			input = new tractarEntrada();
		}
		public void setDir(String d) {
			this.currDir=d;
		}
		public void list(File dir, String[] param) {
			File newdir;
			File stuff[];
			if (param!=null && param[0] != null) {
				newdir = new File(currDir+"/"+param[0]);
				if (newdir != null && newdir.isDirectory()) {
					stuff = newdir.listFiles();
				} else {
					stuff = dir.listFiles();
				}
			} else {
				stuff = dir.listFiles();
			}
			if (stuff != null) {
				for (short i = 0; i < stuff.length; ++i) {
					System.out.println(stuff[i].getName());
				}
			} else {
				System.out.println("Unable to list contents");
			}
		}

		public void sortby(File dir, String[] criteria) {
			String crit = criteria[0].toLowerCase();
			if (crit == null) {
				list(dir, null);
			} else {
				switch (crit) {
				case "name":
					try {
						Files.list(Paths.get(dir.getPath())).sorted().forEach(System.out::println);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
					break;
				case "date":
					File[] files = dir.listFiles();
					File curr;
					BasicFileAttributes attr;
					Arrays.sort(files, Comparator.comparingLong(File::lastModified));
					try {
						for (short i = 0; i < files.length; ++i) {
							curr = files[i];
							attr = Files.readAttributes(Paths.get(curr.getPath()), BasicFileAttributes.class);
							System.out.println(curr.getName() + " LastModified: " + attr.lastModifiedTime());
						}
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
					break;
				default:
					System.out.println("Sorting criteria not recognized");
					list(dir, null);
					break;
				}
			}
		}

		public void help() {
			System.out.println("The supported commands are: ");
			System.out.println("GOTO          Go to a certain directory:");
			System.out.println("                  Argument: the relative path where you want to go \n");
			System.out.println("GOLAST        Go to the last visited directory:");
			System.out.println("                  Arguments: none \n");
			System.out.println("LIST          List elements of a directory:");
			System.out.println("                  Arguments: none(lists your current directory) or 1 argument(a directory to list) \n");
			System.out.println("UP            Go to the parent directory:");
			System.out.println("                  Argument: none \n");
			System.out.println("INFOFILE      Get information about a particular file:");
			System.out.println("                  Argument: a relative path that can have spaces \n");
			System.out.println("INFODIR       Information about a directory:");
			System.out.println("                  Argument: a relative path that can have spaces \n");
			System.out.println("HELP          Print all commands on screen \n");
			System.out.println("CREATEDIR     Create directory:");
			System.out.println("                  Argument: a relative path \n");
			System.out.println("CREATEFILE    Create file:");
			System.out.println("                  Argument: a relative path \n");
			System.out.println("SORTBY        Like LIST but being able to sort the result by a particular criteria:");
			System.out.println("                  1 text argument: Date or Name \n");
			System.out.println("DELETEDIR     Delete one or more directories:");
			System.out.println("                  Argument: a relative path \n");
			System.out.println("DELEFILE      Delete one or more files:");
			System.out.println("                  Argument: a relative path \n");
			System.out.println("EXEC          Executes an executable file:");
			System.out.println("                  Arguments: the name of the file \n");
			System.out.println("                  It's necesary to be at the directory where the file is \n");
			System.out.println("CLEARLOG      Clears the log");
			System.out.println("                  Arguments: none \n");
			System.out.println("LOG           Switches the log on or off");
			System.out.println("                  Argument: either a 1 or a 0 \n");
			System.out.println("LOAD          Reads a texts file and executes the instructions that it has inside sequentially");
			System.out.println("                  Argument: an absolute path \n");
			System.out.println("EXIT          Exits this program");

		}

		public File go(String[] param) {
			File dir = new File(currDir+"/"+param[0]);
			if (dir.isDirectory()) {
				return dir;
			} else {
				return null;
			}
		}

		public File up(File dir) {
			File newdir = new File(dir.getParent());
			return newdir;
		}

		public File goLast(File dir) {
			if (dir.isDirectory()) {
				return dir;
			} else {
				return null;
			}
		}

		public void infoFile(String s) {
			File file = new File(currDir+"/"+s);
			if (file.isFile()) {
				System.out.println("Name: " + file.getName());
				Path path = Paths.get(file.getPath());
				try {
					BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
					System.out.println("Size: " + (attrs.size() / 1024) + "kb");
					String temp;
					temp = attrs.creationTime().toString().split("T")[0];
					System.out.println("Creation time: " + temp);
					temp = attrs.lastModifiedTime().toString().split("T")[0];
					System.out.println(
							"Last modified: " + temp + ", " + attrs.lastModifiedTime().toMillis() / 60000 + " minutes ago");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("Not a file");
			}
		}

		public void infoDir(String s) {
			File dir = new File(currDir+"/"+s);
			if (dir.isDirectory()) {
				System.out.println("Name: " + dir.getName());
				Path path = Paths.get(dir.getPath());
				try {
					BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
					System.out.println("Size: " + (attrs.size() / 1024) + "kb");
					String temp;
					temp = attrs.creationTime().toString().split("T")[0];
					System.out.println("Creation time: " + temp);
					temp = attrs.lastModifiedTime().toString().split("T")[0];
					System.out.println(
							"Last modified: " + temp + ", " + attrs.lastModifiedTime().toMillis() / 60000 + " minutes ago");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("Not a directory");
			}
		}

		public void createDir(String stnewdir) {
			File ndir = new File(currDir+"/"+stnewdir);
			if (ndir.exists()) {
				System.out.println("This directory already exists");
			} else {
				ndir.mkdirs();
				print("createdcorrectly");
			}
		}

		public void createFile(String stnewfile) {
			File nfile = new File(currDir+"/"+stnewfile);
			if (nfile.exists()) {
				System.out.println("This file already exists");
			} else {
				try {
					if (nfile.createNewFile()) {
						print("createdcorrectly");
					} else {
						System.out.println("Couldn't create file");
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		public void deleteDir(String dir) {
			File del;
			if (dir == null) {
				del = new File(currDir);
			} else {
				del = new File(currDir+"/"+dir);
			}
			if (del.isDirectory()) {
				if (del.delete()) {
					print("deletedcorrectly");
				} else {
					System.out.println("Failed to delete the folder");
				}
			} else {
				System.out.println("Folder doesen't exist");
			}
		}

		public void deleteFile(String dir) {
			File del = new File(currDir+"/"+dir);
			if (del.isFile()) {
				if (del.delete()) {
					print("deletedcorrectly");
				} else {
					System.out.println("Failed to delete the file");
				}
			} else {
				System.out.println("File doesen't exist");
			}
		}

		public void exec(String execname) {
			String execpath = currDir + "\\" + execname;
			File exe = new File(execpath);
			if (exe.isFile()) {
				if (exe.canExecute()) {
					try {
						Runtime.getRuntime().exec(execpath, null, new File(currDir));
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("This type of file can't be executed");
				}
			} else {
				System.out.println("Not a file");
			}
		}

		public void log(String text) {
			
		}

		
		public void clear() {
			deleteFile("C:\\Users\\Alumnat\\Documents\\log.txt");
			//hibutil.clean();
			//monutil.clean();
			//dba.clean();
		}

		
		public void print(String s) {
			//Triar quin print s'executa
		}


		public void load(String s, File extdir) {
			File f = new File(s);
			if (f.isFile()) {
				String instruction;
				short size = 0;
				ArrayList<String> instructions = new ArrayList<String>();
				try {
					BufferedReader br = new BufferedReader(new FileReader(f));
					while ((instruction = br.readLine()) != null) {
						instructions.add(instruction);
					}
					size = (short) instructions.size();
					br.close();
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("Instructions imported");
				navegador.commandType option;
				File dir = extdir;
				File prevdir = extdir;
				File temp = null;
				for (short i = 0; i < size; ++i) {
					instruction = instructions.get(i);
					System.out.println(instruction);
					String com[] = instruction.split(" ");
					int error = input.check(com);
					if (error == 0) {
						option = input.getOption();
						String[] params = input.getParams();
						switch (option) {
						case GOTO:
							temp = go(params);
							if (temp != null) {
								prevdir = dir;
								dir = temp;
							} else {
								System.out.println("The directory doesen't exist");
								System.out.println("Remember that the path must be absolute");
							}
							break;
						case GOLAST:
							temp = goLast(prevdir);
							if (temp != null) {
								prevdir = dir;
								dir = temp;
							} else {
								System.out.println("The directory doesen't exist");
							}
							break;
						case UP:
							temp = up(dir);
							if (temp == null)
								System.out.println("Impossible operation");
							else
								prevdir = dir;
							dir = temp;
							break;
						case LIST:
							list(dir, params);
							break;
						case SORTBY:
							sortby(dir, params);
							break;
						case INFOFILE:
							infoFile(params[0]);
							break;
						case INFODIR:
							infoDir(params[0]);
							break;
						case CREATEDIR:
							createDir(params[0]);
							break;
						case CREATEFILE:
							createFile(params[0]);
							break;
						case DELETEDIR:
							deleteDir(params[0]);
							break;
						case DELETEFILE:
							deleteFile(params[0]);
							break;
						case EXEC:
							exec(params[0]);
							break;
						default:
							System.out.println("This operation is not supported on this mode");
							break;
						}
					} else {
						System.out.println("Error on instruction " + (i + 1));
						System.out.println("Error code: " + error);
					}
				}
			} else {
				System.out.println("Not a valid file");
			}
		}
}
