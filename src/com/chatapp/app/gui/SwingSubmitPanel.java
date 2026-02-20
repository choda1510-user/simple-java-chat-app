package com.chatapp.app.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.chatapp.app.util.NullChecker.nullCheck;

public class SwingSubmitPanel {
    private final JPanel panel;
    private final JTextField textField;
    private final JButton button;
    private SwingSubmitPanel(JPanel panel, JTextField textField, JButton button) {
        this.panel = panel;
        this.textField = textField;
        this.button = button;
    }
    public static class Builder {
        private final JPanel panel;
        private JTextField textField;
        private JButton button;
        private Builder() {
            panel = new JPanel();
            textField = new JTextField();
            button = new JButton();
        }
        public Builder textFieldColumns(int columns) {
            textField.setColumns(columns);
            return this;
        }
        public Builder buttonText(String text) {
            button.setText(text);
            return this;
        }
        public Builder addButtonActionListener(ActionListener actionListener) {
            button.addActionListener(actionListener);
            return this;
        }
        public SwingSubmitPanel build() {
            nullCheck(panel, textField, button);
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));
            panel.add(textField);
            panel.add(button);
            return new SwingSubmitPanel(panel, textField, button);
        }
    }
    public static Builder builder() {
        return new Builder();
    }
    public JPanel getPanel() {
        return panel;
    }
    public String getTextFieldText() {
        return textField.getText();
    }
    public void setTextFieldText(String text) {
        textField.setText(text);
    }
    public void setButtonText(String text) {
        button.setText(text);
    }
    public void setTextFieldColumns(int columns) {
        textField.setColumns(columns);
    }
    public void addButtonActionListener(ActionListener actionListener) {
        button.addActionListener(actionListener);
    }
    public void removeButtonActionListener(ActionListener actionListener) {
        button.removeActionListener(actionListener);
    }
}
