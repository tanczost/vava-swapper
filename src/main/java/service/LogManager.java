package service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;

public class LogManager{
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogManager.class.getName());
    FileHandler fileHandler;

    public LogManager(){
        try {
            var date = new Date();
            //at init draw a divider
            FileWriter fileWriter = new FileWriter("log.txt", true);
            fileWriter.append("\n==========" + date.toString() + "==========\n");
            fileWriter.close();

            fileHandler = new FileHandler("log.txt", true);
            log.addHandler(fileHandler);

            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        }catch (SecurityException | IOException e){
            e.printStackTrace();
        }
    }

    public static void severe(String error){
        log.severe(error);
    }

    public static void warning(String error){
        log.warning(error);
    }

    public static void info(String error){
        log.info(error);
    }

    public static void fine(String error){
        log.fine(error);
    }

    public static void disableConsoleLogger(){
        log.setUseParentHandlers(false);
    }

    public static void enableConsoleLogger(){
        log.setUseParentHandlers(false);
    }
}
