package com.chatapp.app.gui;

import javax.swing.*;

import static com.chatapp.app.util.NullChecker.nullCheck;

public class SwingTextAreaPanel {
    private final JPanel panel;
    private final JTextArea textArea;
    private final JScrollPane scrollPane;
    private SwingTextAreaPanel(JPanel panel, JTextArea textArea, JScrollPane scrollPane) {
        this.panel = panel;
        this.textArea = textArea;
        this.scrollPane = scrollPane;
    }
    public static class Builder {
        private final JPanel panel;
        private final JTextArea textArea;
        private final JScrollPane scrollPane;
        private Builder() {
            panel = new JPanel();
            textArea = new JTextArea();
            scrollPane = new JScrollPane(textArea);
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
        public Builder setHorizontalScrollBarPolicy(int policy) {
            scrollPane.setHorizontalScrollBarPolicy(policy);
            return this;
        }
        public SwingTextAreaPanel build() {
            nullCheck(panel, textArea, scrollPane);
            panel.add(scrollPane);
            return new SwingTextAreaPanel(panel, textArea, scrollPane);
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
        textArea.append("\n");
    }

}
