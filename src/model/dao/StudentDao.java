package model.dao;

import model.entities.Major;
import model.entities.Student;

import java.util.List;

public interface StudentDao {
    void insert(Student obj);
    void update(Student obj);
    void deleteById(Integer id);
    Student findById(Integer id); //se o id não existir: retorna null
    List<Student> findAll();
    List<Student> findByMajor(Major major);
}