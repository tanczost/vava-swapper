package services.common;

import observer.Observer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;

public class LogManager extends Observer {
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogManager.class.getName());
    static FileHandler fileHandler;
    private static LogManager instance  = null;



    private static String fileLocation;

    public LogManager(){
        insertDateToFile();
    }

    public static LogManager getInstance() {
        if (instance == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                fileLocation = System.getenv("HOMEPATH") + "\\log.txt";
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                fileLocation = System.getenv("HOME") + "/log.txt";
            } else if (operSys.contains("mac")) {
                fileLocation = System.getenv("HOME") + "/log.txt";
            }
            System.out.println(fileLocation);
            instance = new LogManager();
        }
        return instance;
    }

    @Override
    public void update(String msg,LEVEL lvl) {
        this.log(msg, lvl);
    }

    private void insertDateToFile(){
        try {
            var date = new Date();
            //at init draw a divider
            FileWriter fileWriter = new FileWriter(fileLocation, true);
            fileWriter.append("\n==========" + date.toString() + "==========\n");
            fileWriter.close();
        }catch (SecurityException | IOException e){
            e.printStackTrace();
        }
    }

    private void log(String error, LEVEL logLevel){
        try {
            fileHandler = new FileHandler(this.fileLocation, true);
            log.addHandler(fileHandler);

            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            if (logLevel == LEVEL.severe){
                log.severe(error);
            }else if(logLevel == LEVEL.warning){
                log.warning(error);
            }else if(logLevel == LEVEL.info){
                log.info(error);
            }else if(logLevel == LEVEL.fine){
                log.fine(error);
            }

            fileHandler.close();
        }catch (IOException | SecurityException e){
            e.printStackTrace();
        }
    }

    public static void disableConsoleLogger(){
        log.setUseParentHandlers(false);
    }

    public static void enableConsoleLogger(){
        log.setUseParentHandlers(false);
    }
}
