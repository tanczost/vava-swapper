package service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;

public class LogManager{
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogManager.class.getName());
    static FileHandler fileHandler;

    public LogManager(){
        //TODO make sure only at startup of the app is the date logged
        /*try {
            var date = new Date();
            //at init draw a divider
            FileWriter fileWriter = new FileWriter("src/main/resources/log.txt", true);
            fileWriter.append("\n==========" + date.toString() + "==========\n");
            fileWriter.close();
        }catch (SecurityException | IOException e){
            e.printStackTrace();
        }*/
    }

    public static enum LEVEL{
        severe,
        warning,
        info,
        fine
    }

    public static void log(String error, LEVEL logLevel){
        try {
            fileHandler = new FileHandler("log.txt", true);
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
