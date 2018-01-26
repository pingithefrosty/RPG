package controls;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameLogger {
	private static List<String> gameLogs = new ArrayList<String>();
	
	private static final String logFilePath = "logFile.txt";
	private static final String logFileBakPath = "logFileBak.txt";
	private static final int gameLogsLimit = 10000;
	private static final BufferedWriter writer;
	
	static {
		try {
			Files.move(Paths.get("resources",logFilePath), Paths.get("resources",logFileBakPath),StandardCopyOption.REPLACE_EXISTING);
			writer = Files.newBufferedWriter(Paths.get("resources",logFilePath),StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
			throw new RuntimeException(); //needed otherwise eclipse complains for writer not being initialized
		}
	}
	
	private Class<?> cl;
	
	public GameLogger(Class<?> cl){
		this.cl=cl;
	}
	
	public void notice(String s, String lvl){
		try {
			writer.write(prepareLog(s, lvl));
			writer.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void notice(String s){
		notice(s, "NOTICE");
	}

	public void info(String s, String lvl){
		System.out.println(prepareLog(s, lvl));
		notice(s, lvl);
	}
	
	public void info(String s){
		info(s, "INFO");
	}
	
	public String prepareLog(String s, String lvl){
		return LocalDateTime.now() + " " + cl.getName() + " " + lvl + ": " + s;
	}
	
	public void gameLog(String s){
		if(gameLogs.size()>=gameLogsLimit){
			//truncate gameLogs to avoid wasting memory 
			gameLogs = getLastGameLogs(100);
		}
		gameLogs.add(s);
		info(s, "GAME");
	}
	
	public static List<String> getLastGameLogs(int n){
		int size=gameLogs.size();
		return gameLogs.subList(Math.max(0,size-n), size);
	}
	
	public static void closeLog(){
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
