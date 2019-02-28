import java.util.*;
import java.io.*;
import java.lang.System;
//import org.apache.commons.lang3.StringUtils;

public class Parser{
	Terminal t = new Terminal();

	Parser(){
	   //	
	}
	/*Parser(String ln) throws IOException{
		String[] slist = ln.split(" ");
		int slistlength = slist.length;
		if(slistlength > 2) {
		    slist[0] = ln.substring(0,ln.indexOf(' '));
		    slist[1] = ln.substring(ln.indexOf(' ')+1);
			slistlength =2;
		}
		if(slistlength == 1 && !(slist[0].contains("cd") ||slist[0].contains("ls"))){
			parsing0(ln);
			}
		else {
			parsing1(ln);
		}
	}*/
	public void pars012(String ln) throws IOException{
       if(ln.contains(">")) {
    	   String newln = ln.split(">")[0];
    	   pars012(newln);
    	   String outputfile = (ln.replace(newln, "")).replace(">","");
    	   //System.out.println(t.display);
    	   //System.out.println(outputfile);
           if(!outputfile.contains("\\")) {
        	   outputfile = t.dir + "\\" + outputfile;
        }
    	   t.appendToFile(t.display, outputfile);
    	   t.display = "";
    	   return;
       }
       if(ln.contains("|")) {
    	   String[] commandlist = ln.split("\\|");
           int commandsize = commandlist.length;
           for(int i=0;i<commandsize;i++) {
        	  // System.out.println(commandlist[i]);
        	   pars012(commandlist[i]);
           }
           return;
       }
		if(ln.contains("\"")) {
			parsing2(ln);
			return;
		}
		String[] slist = ln.split(" ");
		int slistlength = slist.length;
		if(slistlength > 2) {
		    slist[0] = ln.substring(0,ln.indexOf(' '));
		    slist[1] = ln.substring(ln.indexOf(' ')+1);
			slistlength =2;
		}
		if(slistlength == 1 && !(slist[0].contains("cd") ||slist[0].contains("ls"))){
			parsing0(ln);
			}
		else {
			parsing1(ln);
		}
		
	}
	
   public void parsing0(String command){
	   if(command.contains("pwd")) {
		   t.pwd();
	   }
	   else if(command .contains("clear")){
		   t.clear();
	   }
	   else if(command.contains("date")) {
		   t.date();
	   }
	   else if(command.contains("help")) {
		   t.help();
	   }
	   else if(command.contains("exit")) {
		   t.exit();
	   }
	   else{
		   System.out.println("Wrong command");
	   }
   }

   public void parsing1(String p) throws IOException {
	   //case en hwa y make directory 
	   //case elle f keeep notes  
	   String[] slist = p.split(" ");
	   int slistlength = slist.length;
		if(slistlength > 2) {
		    slist[0] = p.substring(0,p.indexOf(' '));
		    slist[1] = p.substring(p.indexOf(' ')+1);
			slistlength =2;
		}
	   String command = "";
	   String path = "";
	   if(slistlength==2) {
	      command = slist[0];
	      path = slist[1];
	      if(!path.contains("\\")){
	    	  path = t.dir + "\\" + path;
	      }
	      if(command.contains("\\")) {
	    	  System.out.println("wrong command");
	    	  return;
	      }
		  File f = new File(path);
		  if(!f.exists() && !command.equals("mkdir") &&!command.equals("args")){
			   System.out.println("wrong path");
			   return;
		   }
	   }
	   else if(slistlength==1) {
		   command = slist[0];
	   }
	   
	   //commands 
	   if(command.contains("cd")) {
		  t.cd(path);
		  return;
	   }
	   if(command.contains("ls")) {
		   t.ls(path);
		   return;
	   }
	   if(command.contains("rm")){
		   if(path.equals("")) {
			   System.out.println("wrong path" + path);
			   return;

		   }
		   else {
			   t.rm(path);
			   return;
		   }
	   }
	   if(command.contains("mkdir")){
		   if(path.equals("")) {
			   System.out.println("wrong path");
			   return;
		   }
		   t.mkdir(path);
		   return;
	   }
	   if(command.contains("rmdir")) {
		   if(path.equals("")) {
			   System.out.println("wrong path");
			   return;
		   }
		   t.rmdir(path);
		   return;
	   }
	   if(command.contains("cat")) {
		   if(path.equals("")) {
			   System.out.println("wrong path");
			   return;

		   }
		   t.cat(path);
		   return;
	   }
	   if(command.contains("args")) {
		   t.args(path);
		   return;
	   }   
   }
   public void parsing2(String p) throws IOException{
	   String command = p.substring(0,p.indexOf(" "));
	   String twopaths = p.substring(p.indexOf(" ")+1);
	   ArrayList<Integer> indexquote = new ArrayList<Integer>();
	   String spath = "";
	   String dpath = "";
       for(int i=0;i<twopaths.length();i++) {
    	   if(twopaths.charAt(i)=='"') {
    		   indexquote.add(i);

    			   	   }
    	   }
       if(indexquote.size()!=4) {
    	   System.out.println("wrong path");
    	   return;
       }
       spath = twopaths.substring(indexquote.get(0)+1,indexquote.get(1));
       dpath = twopaths.substring(indexquote.get(2)+1,indexquote.get(3));
       if(!(spath.contains("\\")) && !command.contains("more")) {
	    	  spath = t.dir + "\\" + spath;
       }
       if(!dpath.contains("\\")) {
	    	  dpath = t.dir + "\\" + dpath;
       }
       //System.out.println(spath);
       //System.out.println(dpath);
       
       File sourcef = new File(spath);
       File desf = new File(dpath);
       if((!sourcef.exists() || !desf.exists()) && !command.contains("more") && !command.contains("mv")){
    	   System.out.println("wrong path");
    	   return;
       }
        //System.out.println("hna");
       // commands 
        if(command.contains("cp")) {
           t.cp(spath, dpath);
           return;
        }
        if(command.contains("mv")) {
        	if(!sourcef.exists()) {
        		System.out.println("wrong path");
        		return;
        	}
            t.mv(spath, dpath);
            return;
         }
        if(command.contains("more")){
        	t.more(dpath, Integer.parseInt(spath));
        	return;
        }
   }
}
