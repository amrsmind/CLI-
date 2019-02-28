import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.time.*;
import java.time.format.DateTimeFormatter;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.channels.FileChannel;

public class Terminal {
	public String dir = "C:\\Users\\amr";
	public String root = "C:\\Users\\amr";
	String display = "";
	
	public void cd(String path){
		//if(path.contains("\\"))
		if(path.equals("")) {
			dir = "";
			dir += root;
			//System.out.println(dir);
			return;
		}
	    dir = path;
	}
	
	public void ls(String path){
		display = "";
		File f;
		if(path.equals("")){	
		  f = new File(dir);

		  }
		else {
		  f = new File(path);
		}
		  File[] flist = f.listFiles();
		  int lenlist = flist.length;
		  for(int i=0;i<lenlist;i++) {
			  System.out.println(flist[i].getName());
			  display += flist[i].getName() + System.lineSeparator();
		  }	
	}
	public void cp(String s,String d) throws IOException
	{
		File source = new File(s);
		File destination = new File(d);
		if(!source.exists()) {
			System.out.println("can not find this path");
			return;
		}
		Files.copy(source.toPath(), destination.toPath());	}
	public void mv(String s,String d) throws IOException{
		File Source = new File(s);
		File destination = new File(d);
		if(!(Source.getParent().equals(destination.getParent()))) {
            //Files.move(Source.toPath(), destination.toPath());
            System.out.println("not allowed");
            return;
		}
		if(!destination.exists()){
			Source.renameTo(destination);
		}
		else {
			destination.delete();
			Source.renameTo(destination);
		} 
	}
	public void rm(String p) {
		File f = new File(p);
		f.delete();
	}
	public void mkdir(String p){
		new File(p).mkdirs();
	}
	public void rmdir(String p) {
		File f = new File(p);
		if(f.list().length>0){
			System.out.println("cannot be deleted");
			return;
		}
		f.delete();
	}
	public void cat(String p) throws IOException {
		//File f = new File(p);
		display = "";
		BufferedReader input = new BufferedReader(new FileReader(p));
		String l;
		while((l = input.readLine())!= null) {
			System.out.println(l);
			display+= l + System.lineSeparator();
		}
		input.close();
	}
	public void more(String p,int maxi) throws IOException {
		display = "";
		//File f = new File(p);
		BufferedReader input = new BufferedReader(new FileReader(p));
		String l;
		int i = 0;
		while((l = input.readLine())!= null || i<maxi) {
			System.out.println(l);
			display+= l + System.lineSeparator();
			
			i++;
		}
		input.close();
	}
	public void pwd(){
		System.out.println(dir);
		display = "";
		display += dir + "\n";
	}
	public void clear() {  
		//System.out.print("\033[H\033[2J");	
		//System.out.flush();  
        for(int i=0;i<1000;i++)
        {
            System.out.println("\n");
        }
	}
	public void args(String command){
		if(command.contains("cd")) {
			System.out.println("1: Path");
			display = "1: path";
		}
		else if(command.contains("ls")) {
			System.out.println("1: Path");
			display = "1: path";
		}	
		else if(command.contains("cp")) {
			System.out.println("2: Source,destination");
			display = "2: Source,destination";

		}
		else if(command.contains("mv")) {
			System.out.println("2: Source,destination");
			display = "2: Source,destination";

		}	
		else if(command.contains("rm")) {
			System.out.println("1: Path");
			display = "1: path";

		}	
		else if(command.contains("mkdir")) {
			System.out.println("1: Path");
			display = "1: path";

		}	
		else if(command.contains("cat")) {
			System.out.println("1: Path");
			display = "1: path";

		}	
		else if(command.contains("more")) {
			System.out.println("2: Path,maximumlines");
			display = "2: path,maximumlines";
		}	
		else if(command.contains("pwd")) {
			System.out.println("no arguments");
			display = "no arguments";
		}
		else if(command.contains("date")) {
			System.out.println("no arguments");
			display = "no arguments";
		}
		else {
			System.out.println("invalid command");
		}
	}
	public void date(){
		 DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println(d.format(now));
		   display = d.format(now);
	}
	public void help(){
		 {
		       System.out.println("args : List all command argument");
		       System.out.println("date : Current date/time");
		       System.out.println("exit : Stop all");
		       System.out.println("clear : clear the current\n" +
		"terminal screen and it can be redirected to clear\n" +
		"the screen of some other terminal.");
		       System.out.println("cd : This command changes the current directory to\n" +
		"another one.");
		       System.out.println("IS : given file or directory name. Directory");
		       System.out.println("pwd : Display current user directory.");
		       System.out.println("CP : copies each other given file into a file with the\n" +
		"same name in that directory.");
		       System.out.println("MV :  given file into a file with the\n" +
		"same name in that directory.");
		       System.out.println("RM : removes each specified file.");
		       System.out.println("Mkdir : creates a directory with each given name. By\n" +
		"default, the mode of created directories is 0777\n" +
		"minus the bits set in the umask.");
		       System.out.println("rmidir : removes each given empty directory.");
		       System.out.println("cat : Concatenate files and print on the standard\n" +
		"output.");
		       System.out.println("more : display and scroll down the output in one\n" +
		"direction only.");
		       System.out.println(">:Redirect the output to be written to a file\n");
		    }
		 display = "(\"args : List all command argument\")\r\n" + 
		 		"		       (\"date : Current date/time\")\r\n" + 
		 		"		       (\"exit : Stop all\")\r\n" + 
		 		"		       (\"clear : clear the current\\n\" +\r\n" + 
		 		"		\"terminal screen and it can be redirected to clear\\n\" +\r\n" + 
		 		"		\"the screen of some other terminal.\")\r\n" + 
		 		"		       (\"cd : This command changes the current directory to\\n\" +\r\n" + 
		 		"		\"another one.\")\r\n" + 
		 		"		       (\"IS : given file or directory name. Directory\")\r\n" + 
		 		"		       (\"pwd : Display current user directory.\")\r\n" + 
		 		"		       (\"CP : copies each other given file into a file with the\\n\" +\r\n" + 
		 		"		\"same name in that directory.\")\r\n" + 
		 		"		       (\"MV :  given file into a file with the\\n\" +\r\n" + 
		 		"		\"same name in that directory.\")\r\n" + 
		 		"		       (\"RM : removes each specified file.\")\r\n" + 
		 		"		       (\"Mkdir : creates a directory with each given name. By\\n\" +\r\n" + 
		 		"		\"default, the mode of created directories is 0777\\n\" +\r\n" + 
		 		"		\"minus the bits set in the umask.\")\r\n" + 
		 		"		       (\"rmidir : removes each given empty directory.\")\r\n" + 
		 		"		       (\"cat : Concatenate files and print on the standard\\n\" +\r\n" + 
		 		"		\"output.\")\r\n" + 
		 		"		       (\"more : display and scroll down the output in one\\n\" +\r\n" + 
		 		"		\"direction only.\")\r\n" + 
		 		"		       (\">:Redirect the output to be written to a file\\n\")";
		 
	}
	public void exit() {
		System.exit(0);
	}
	
  	public void appendToFile(String str, String fileName)throws IOException {
  		//System.out.println(filename);
  		//System.out.println(str);
	    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
	    writer.append(' ');
	    writer.append(str);
	     
	    writer.close();
}
	
	
	

}
