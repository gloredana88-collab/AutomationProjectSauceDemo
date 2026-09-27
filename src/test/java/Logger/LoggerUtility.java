package Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.io.*;

public class LoggerUtility {
    private static final String suiteLogsPath = "target/logs/suites";
    private static final String regressionLogsPath = "target/logs/";
    private static final Logger  logger = LogManager.getLogger();


    //sync ne ajuta cand avem nevoie sa rulam o metoda in paralel
    public static synchronized void startTestCase(String testName){
        ThreadContext.put("threadName" , testName);
        logger.info("===== Execution Started: " + testName + "=====");


    }

    public static synchronized void endTestCase(String testName){
        ThreadContext.put("threadName" , testName);
        logger.info("===== Execution Ended: " + testName + "=====");


    }

    public static synchronized void infoTestCase(String messageName){
        logger.info(Thread.currentThread().getName() + " " + getCallInfo() + " " + messageName);
    }

    public static synchronized void errorLog(String message){
        logger.error(Thread.currentThread().getName() + " " + getCallInfo() + " " + message);
    }

    public static synchronized String getCallInfo(){
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();

        return className + " : " + methodName + " => ";
    }

//    public static void mergeFiles() {
//        File dir = new File(suiteLogsPath);
//        String[] fileNames = dir.list();
//
//        PrintWriter pw = null;
//
//        try {
//            pw = new PrintWriter(regressionLogsPath + "RegressionLogs.log");
//            for (String fileName : fileNames) {
//                File file = new File(dir, fileName);
//
//                BufferedReader br = new BufferedReader(new FileReader(file));
//
//                pw.println("Content " + fileName);
//
//                String line = br.readLine();
//
//                while (line != null) {
//                    pw.println(line);
//                    line = br.readLine();
//                }
//                pw.flush();
//            }
//        } catch (IOException message) {
//            System.out.println(message.getMessage());
//        }
//
//
//    }

    public static void mergeFiles() {

        File dir = new File(suiteLogsPath);

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println(
                    "Directorul de loguri nu exista: " + suiteLogsPath
            );
            return;
        }

        File regressionLogsFile =
                new File(regressionLogsPath, "RegressionLogs.log");

        try (PrintWriter pw = new PrintWriter(regressionLogsFile)) {

            File[] files = dir.listFiles();

            if (files == null || files.length == 0) {
                System.out.println(
                        "Nu exista fisiere de log in: " + suiteLogsPath
                );
                return;
            }

            for (File file : files) {

                if (!file.isFile()) {
                    continue;
                }

                pw.println();
                pw.println("========== " + file.getName() + " ==========");

                try (BufferedReader br =
                             new BufferedReader(new FileReader(file))) {

                    String line;

                    while ((line = br.readLine()) != null) {
                        pw.println(line);
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Eroare la merge-ul fisierelor de log: "
                            + e.getMessage()
            );
        }
    }
}
