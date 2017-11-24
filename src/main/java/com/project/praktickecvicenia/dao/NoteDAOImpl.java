package com.project.praktickecvicenia.dao;

import com.project.praktickecvicenia.config.db.H2DataSource;
import com.project.praktickecvicenia.entities.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NoteDAOImpl implements NoteDAO{


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public NoteDAOImpl() {
        this.namedParameterJdbcTemplate = new H2DataSource().getTemplate();
    }

    public NoteDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Note findById(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        String sql = "SELECT * FROM notes WHERE id=:id";
        Note result = namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                new UserMapper());
        //new BeanPropertyRowMapper(Customer.class));
        return result;
    }

    public List<Note> findAll() {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT * FROM notes";
        List<Note> result = namedParameterJdbcTemplate.query(sql, params, new UserMapper());
        return result;
    }

    public void addNote(Note note) {
        String query = "INSERT INTO notes (note) VALUES(:note)";
        Map namedParameters = new HashMap();
        namedParameters.put("note", note.getNote());
        namedParameterJdbcTemplate.update(query, namedParameters);
    }

    public void changeStatus(int id) {
        String query = "UPDATE notes SET status = " +
                "CASE WHEN status = 0 THEN  1 ELSE  0 END " +
                "WHERE id LIKE :id ";
        Map namedParameters = new HashMap();
        namedParameters.put("id", id);
        namedParameterJdbcTemplate.update(query, namedParameters);
    }

    private static final class UserMapper implements RowMapper<Note> {
        public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
            Note note = new Note();
            note.setId(rs.getInt("id"));
            note.setNote(rs.getString("note"));
            note.setDone(rs.getInt("status") != 0);
            return note;
        }
    }
}
