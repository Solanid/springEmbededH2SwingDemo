package com.project.praktickecvicenia.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoForm extends JFrame {
    private JTable tblToDo;
    private JPanel panel1;
    private JTextField inputToDo;
    private JButton btnAdd;

    public TodoForm() throws HeadlessException {
//        this.ADDButton = new JButton();
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
