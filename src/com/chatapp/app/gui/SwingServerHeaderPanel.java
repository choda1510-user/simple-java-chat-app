package com.chatapp.app.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.chatapp.app.util.NullChecker.nullCheck;

public class SwingServerHeaderPanel {
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private SwingServerHeaderPanel(JPanel panel, JLabel label, JTextField textField, JButton button) {
        this.panel = panel;
        this.label = label;
        this.textField = textField;
        this.button = button;
    }
    public static class Builder {
        private final JPanel panel;
        private final JLabel label;
        private final JTextField textField;
        private final JButton button;
        private Builder() {
            this.panel = new JPanel();
            this.label = new JLabel();
            this.textField = new JTextField();
            this.button = new JButton();
        }
        public Builder labelText(String text) {
            label.setText(text);
            return this;
        }
        public Builder textFieldText(String text) {
            textField.setText(text);
            return this;
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
        public SwingServerHeaderPanel build() {
            nullCheck(panel, label, textField, button);
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));
            panel.add(label);
            panel.add(textField);
            panel.add(button);
            return new SwingServerHeaderPanel(panel, label, textField, button);
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
    public void setTextFieldColumns(int columns) {
        textField.setColumns(columns);
    }
    public void setTextFieldText(String text) {
        textField.setText(text);
    }
    public void setButtonText(String text) {
        button.setText(text);
    }
    public void addButtonActionListener(ActionListener actionListener) {
        button.addActionListener(actionListener);
    }
    public void removeButtonActionListener(ActionListener actionListener) {
        button.removeActionListener(actionListener);
    }
}
