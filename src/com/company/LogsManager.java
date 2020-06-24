package com.company;

/**
 * Classname Main
 *
 * Description: Main class
 *
 * @author Vasylkivska Kateryna KNTEU
 *
 * @varsion 1.0 2020.06.23
 *
 * The main class where all actions will be executed
 *
 * Module 4. Mulithreading.
 *
 * 1. Use the file from the previous task - logs.txt.
 *
 * 2. Create a class that manages logs in this file.
 *
 * 3. Create a method that finds all the ERROR logs for a specific date and
 * write them into a specific file (name = ERROR + Date  + .log).
 *
 * 4. In your main class develop a functionality to create 5 such a files for
 * different days. Launch them in consistent way (one after another).
 *
 * 5. Repeat the above  task in parallel way. Multi-threading.
 *
 * 6. Compare the results.
 *
 **/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogsManager {
    private String filePath;

    public LogsManager(String filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    /**
     * a method that finds all the ERROR logs for a specific date and write them
     * into a specific file (name = ERROR  + Date  + .log).
     * @param date search by given date
     * @throws IOException
     */
    public void getErrorByDate(String date) throws IOException {
        // create a finish timestamp
        LocalDateTime start = LocalDateTime.now();

        // open stream to get ERROR logs by given date
        List<String> errorLinesList = Files.lines(Paths.get(this.getFilePath()))
                .filter(line -> line.contains(date))
                .filter(line -> line.contains("ERROR"))
                .collect(Collectors.toList());

        // count number of logs
        int linesCount = errorLinesList.size();

        // create a finish timestamp
        LocalDateTime finish = LocalDateTime.now();
        // count execution time
        long timeExecuted = ChronoUnit.MILLIS.between(start, finish);

        // print result
        System.out.println("There are " + linesCount + " ERROR lines." + " on " + date);
        System.out.println("Execution time: " + timeExecuted);

        // convert logs to String
        String stringData = "";
        for (String line : errorLinesList) {
            stringData += line + "\n";
        }

        // specify output file path
        String optputPath = "/Users/Kate/Downloads/ERROR-date" + date + ".txt";
        // write result to the file
        Files.write(Paths.get(optputPath), stringData.getBytes());
    }

}
