package com.project.praktickecvicenia.dao;

import com.project.praktickecvicenia.entities.Note;

import java.util.List;

public interface NoteDAO {

    Note findById(int id);

    List<Note> findAll();

}
