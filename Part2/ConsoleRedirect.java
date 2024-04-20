package Part2;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import Part1.Horse;
import Part1.Race;

public class ConsoleRedirect {
    private JTextArea textArea;

    public ConsoleRedirect(JTextArea textArea) {
        this.textArea = textArea;
        redirectSystemStreams();
    }

    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                updateTextArea(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateTextArea(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }

    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(() -> {
            if (text.equals("\u000C")) { // Check for form feed character
                textArea.setText(""); // Clear the text area
            } else {
                textArea.append(text);
            }
        });
    }
}