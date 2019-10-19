package filesystem;

import java.io.*;

public class FileSystem {
	static boolean exit = false;
	private static File wd = new File(System.getProperty("user.dir"));
	
	public void runcmd() throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {
		while(!exit) {
			String command = br.readLine();
			String ws = " ";
			String[] cmd = command.split(ws);
			
			if(cmd[0].equals("exit")) {
				System.exit(0);
			}else if (cmd[0].equals("pwd")) {
				pwd(cmd);
			}else if(cmd[0].contentEquals("cd")) {
				cd(cmd);
			}else if (cmd[0].contentEquals("ls")) {
				ls(cmd);
			}
		}
		br.close();
		} catch (Throwable e) {
			System.out.println(e);
		}
	}
	
	private void pwd(String[] cmd) throws IOException {
		System.out.println(wd.getCanonicalPath());
	}
	
	private void cd(String[] cmd) throws IOException{
		if (cmd[1].equals("..")){
			wd = wd.getParentFile();
		}
		else {
			File temp_wd = new File(wd, cmd[1]);
			if(temp_wd.isDirectory()) {
				wd = temp_wd;
			}
			else {
				throw new IOException("No such directory");
			}
			
		}
	}
	
	private void ls(String[] cmd) {
		
	}
	
	private void mkdir(String[] cmd) {
		
	}
	
	private void cp(String[] cmd) {
		
	}
	
	private void head(String[] cmd) {
		
	}
}