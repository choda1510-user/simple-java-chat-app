package com.chatapp.app.gui;

import javax.swing.*;
import java.awt.*;

import static com.chatapp.app.util.NullChecker.nullCheck;

public class SwingClientPanel {
    private final JPanel panel;
    private final SwingClientHeaderPanel headerPanel;
    private final SwingTextAreaPanel textAreaPanel;
    private final SwingSubmitPanel submitPanel;

    private SwingClientPanel(JPanel panel, SwingClientHeaderPanel headerPanel, SwingTextAreaPanel textAreaPanel, SwingSubmitPanel submitPanel) {
        this.panel = panel;
        this.headerPanel = headerPanel;
        this.textAreaPanel = textAreaPanel;
        this.submitPanel = submitPanel;
    }
    public static class Builder {
        private final JPanel panel;
        private SwingClientHeaderPanel headerPanel;
        private SwingTextAreaPanel textAreaPanel;
        private SwingSubmitPanel submitPanel;
        private Builder() {
            panel = new JPanel();
        }
        public Builder headerPanel(SwingClientHeaderPanel headerPanel) {
            if (headerPanel == null) {
                throw new NullPointerException();
            }
            this.headerPanel = headerPanel;
            return this;
        }
        public Builder textAreaPanel(SwingTextAreaPanel textAreaPanel) {
            if (textAreaPanel == null) {
                throw new NullPointerException();
            }
            this.textAreaPanel = textAreaPanel;
            return this;
        }
        public Builder submitPanel(SwingSubmitPanel submitPanel) {
            if (submitPanel == null) {
                throw new NullPointerException();
            }
            this.submitPanel = submitPanel;
            return this;
        }
        public SwingClientPanel build() {
            nullCheck(panel, headerPanel, textAreaPanel, submitPanel);
            panel.setLayout(new GridLayout(3, 1));
            panel.add(headerPanel.getPanel());
            panel.add(textAreaPanel.getPanel());
            panel.add(submitPanel.getPanel());
            return new SwingClientPanel(panel, headerPanel, textAreaPanel, submitPanel);
        }
    }
    public static Builder builder() {
        return new Builder();
    }
    public JPanel getPanel() {
        return panel;
    }
    public SwingClientHeaderPanel getHeaderPanel() {
        return headerPanel;
    }
    public SwingTextAreaPanel getTextAreaPanel() {
        return textAreaPanel;
    }
    public SwingSubmitPanel getSubmitPanel() {
        return submitPanel;
    }
}
