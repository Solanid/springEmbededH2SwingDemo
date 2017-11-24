package com.project.praktickecvicenia;

//import com.project.praktickecvicenia.config.SpringRootConfig;

import com.project.praktickecvicenia.ui.TodoForm;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Todo List");
        jFrame.setContentPane(new TodoForm().getPanel1());
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }
}
