package model.dao;

import model.entities.Major;
import model.entities.Student;

import java.util.List;

public interface MajorDao {
    void insert(Major obj);
    void update(Major obj);
    void deleteById(Integer id);
    Major findById(Integer id);
    List<Major> findAll();
}