package com.chatapp.app.gui;

import javax.swing.*;
import java.awt.event.ActionListener;

import static com.chatapp.app.util.NullChecker.nullCheck;

public class SwingClientHeaderPanel {
    public SwingClientHeaderPanel(JPanel panel, JLabel ipLabel, JLabel portLabel, JTextField ipTextField, JTextField portTextField, JButton button) {
        this.panel = panel;
        this.ipLabel = ipLabel;
        this.portLabel = portLabel;
        this.ipTextField = ipTextField;
        this.portTextField = portTextField;
        this.button = button;
    }

    private final JPanel panel;
    private JLabel ipLabel, portLabel;
    private JTextField ipTextField, portTextField;
    private JButton button;

    public static class Builder {
        private final JPanel panel;
        private JLabel ipLabel, portLabel;
        private JTextField ipTextField, portTextField;
        private JButton button;

        private Builder() {
            this.panel = new JPanel();
            this.ipLabel = new JLabel();
            this.portLabel = new JLabel();
            this.ipTextField = new JTextField();
            this.portTextField = new JTextField();
            this.button = new JButton();
        }

        public Builder ipLabelText(String text) {
            ipLabel.setText(text);
            return this;
        }

        public Builder portLabelText(String text) {
            portLabel.setText(text);
            return this;
        }

        public Builder ipTextFieldColumns(int columns) {
            ipTextField.setColumns(columns);
            return this;
        }

        public Builder portTextFieldColumns(int columns) {
            portTextField.setColumns(columns);
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

        public SwingClientHeaderPanel build() {
            nullCheck(panel, ipLabel, ipTextField, portLabel, portTextField, button);
            panel.add(ipLabel);
            panel.add(ipTextField);
            panel.add(portLabel);
            panel.add(portTextField);
            panel.add(button);
            return new SwingClientHeaderPanel(panel, ipLabel, portLabel, ipTextField, portTextField, button);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getIpLabelText() {
        return ipLabel.getText();
    }

    public String getIpTextFieldText() {
        return ipTextField.getText();
    }

    public String getPortLabelText() {
        return portLabel.getText();
    }

    public String getPortTextFieldText() {
        return portTextField.getText();
    }
    public int getIpTextFieldColumns() {
        return ipTextField.getColumns();
    }

    public int getPortTextFieldColumns() {
        return portTextField.getColumns();
    }

    public String getButtonText() {
        return button.getText();
    }

    public void setIpLabelText(String text) {
        ipLabel.setText(text);
    }

    public void setPortLabelText(String text) {
        portLabel.setText(text);
    }

    public void setIpTextFieldColumns(int columns) {
        ipTextField.setColumns(columns);
    }

    public void setPortTextFieldColumns(int columns) {
        portTextField.setColumns(columns);
    }

    public void setButtonText(String text) {
        button.setText(text);
    }

    public void addButtonActionListener(ActionListener actionListener) {
        button.addActionListener(actionListener);
    }

    public void removeAddButtonActionListener(ActionListener actionListener) {
        button.removeActionListener(actionListener);
    }
}
