package com.project.praktickecvicenia;

//import com.project.praktickecvicenia.config.SpringRootConfig;
import com.project.praktickecvicenia.config.db.H2DataSource;
import com.project.praktickecvicenia.dao.NoteDAOImpl;
import com.project.praktickecvicenia.entities.Note;
import com.project.praktickecvicenia.ui.TodoForm;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import javax.swing.*;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        H2DataSource springRootConfig = new H2DataSource();
        DataSource dataSource = springRootConfig.dataSource();
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

        NoteDAOImpl notesDAO = new NoteDAOImpl(template);


//        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//        NoteDAOImpl noteDAO = context.getBean("noteDAOImpl", NoteDAOImpl.class);

        List<Note> noteList = notesDAO.findAll();
        for (Note n:noteList) {
            System.out.println(n.getNote());
        }

        Note note = notesDAO.findById(1);
        System.out.println(note.getNote());

        JFrame jFrame = new JFrame("Todo List");
        jFrame.setContentPane(new TodoForm().getPanel1());
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }
}
