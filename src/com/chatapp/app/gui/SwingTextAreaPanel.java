package com.chatapp.app.gui;

import javax.swing.*;

import static com.chatapp.app.util.NullChecker.nullCheck;

public class SwingTextAreaPanel {
    private final JPanel panel;
    private final JTextArea textArea;
    private SwingTextAreaPanel(JPanel panel, JTextArea textArea) {
        this.panel = panel;
        this.textArea = textArea;
    }
    public static class Builder {
        private final JPanel panel;
        private final JTextArea textArea;
        private Builder() {
            panel = new JPanel();
            textArea = new JTextArea();
        }
        public Builder enabled(boolean b) {
            textArea.setEnabled(b);
            return this;
        }
        public Builder size(int rows, int columns) {
            textArea.setRows(rows);
            textArea.setColumns(columns);
            return this;
        }
        public SwingTextAreaPanel build() {
            nullCheck(panel, textArea);
            panel.add(textArea);
            return new SwingTextAreaPanel(panel, textArea);
        }
    }
    public static Builder builder() {
        return new Builder();
    }
    public JPanel getPanel() {
        return panel;
    }
    public String getText() {
        return textArea.getText();
    }
    public void setText(String text) {
        textArea.setText(text);
    }
    public void appendText(String text) {
        textArea.append(text);
    }

}
