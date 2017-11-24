package com.project.praktickecvicenia.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.HeadlessException;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoForm extends JFrame {
    private JTable tblToDo;
    private JPanel panel1;
    private JTextField inputToDo;
    private JButton btnAdd;

    private DefaultTableModel tblModel;

    private Object[] columnNames = {"No.", "Note", "Status"};

    public TodoForm() throws HeadlessException {
        initTable();

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewNote();
            }
        });
    }

    private void initTable() {
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, columnNames);
        tblToDo.setModel(tblModel);
        tblToDo.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblToDo.getColumnModel().getColumn(1).setPreferredWidth(400);
        tblToDo.getColumnModel().getColumn(2).setPreferredWidth(50);
        this.tblModel = tblModel;
    }

    private void addNewNote() {
        int count = tblModel.getRowCount()+1;
        String note = inputToDo.getText();
        tblModel.addRow(new Object[]{count, note, Boolean.FALSE});
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
