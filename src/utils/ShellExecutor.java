package utils;

import java.io.File;

public class ShellExecutor {
    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();
    private static final String USER_HOME = System.getProperty("user.home");

    public void executeCommand(String command) {
        boolean isWindows = OS_NAME.startsWith("windows");
        ProcessBuilder builder = new ProcessBuilder();
        builder.redirectErrorStream(true);

        if (isWindows) builder.command("cmd.exe", "/c", command);
        else builder.command("sh", "-c", command);

        builder.directory(new File(USER_HOME));
        try {
            Process process = builder.start();
            int exitCode = process.waitFor();
//            System.out.println("ExitCode: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
