package com.project.praktickecvicenia.ui;

import com.project.praktickecvicenia.dao.NoteDAOImpl;
import com.project.praktickecvicenia.entities.Note;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TodoForm extends JFrame {
    private JTable tblToDo;
    private JPanel panel1;
    private JTextField inputToDo;
    private JButton btnAdd;

    private DefaultTableModel tblModel;

    private NoteDAOImpl noteDAO;

    private Object[] columnNames = {"No.", "Note", "Status"};

    public TodoForm() throws HeadlessException {
        noteDAO = new NoteDAOImpl();
        initTable();

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewNote();
            }
        });
    }

    private void initTable() {
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex==2?Boolean.class:super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 && super.isCellEditable(row, column);
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                changeStatus(row);
                super.setValueAt(aValue, row, column);
            }
        };
//        tblModel
        tblToDo.setModel(tblModel);
        tblToDo.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblToDo.getColumnModel().getColumn(1).setPreferredWidth(400);
        tblToDo.getColumnModel().getColumn(2).setPreferredWidth(50);

        List<Note> notes = noteDAO.findAll();
        for (Note n:notes) {
            tblModel.addRow(new Object[]{n.getId(), n.getNote(), n.isDone()});
        }

        this.tblModel = tblModel;
    }

    private void addNewNote() {
        int count = tblModel.getRowCount()+1;
        String note = inputToDo.getText();
        Note noteOb = new Note();
        noteOb.setNote(note);

        noteDAO.addNote(noteOb);
        tblModel.addRow(new Object[]{count, note, Boolean.FALSE});
        System.out.println(noteDAO.findById(count));
    }

    private void changeStatus(int row) {
        int x = (Integer)tblModel.getValueAt(row, 0);
        noteDAO.changeStatus(x);
        System.out.println(noteDAO.findById(x));
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
