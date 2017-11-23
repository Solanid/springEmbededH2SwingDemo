package com.project.praktickecvicenia.dao;

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

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public NoteDAOImpl() {
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

    private static final class UserMapper implements RowMapper<Note> {
        public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
            Note note = new Note();
            note.setId(rs.getInt("id"));
            note.setNote(rs.getString("note"));
            return note;
        }
    }
}
