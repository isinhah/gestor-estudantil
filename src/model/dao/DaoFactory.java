package model.dao;

import db.DB;
import model.dao.impl.MajorDaoJDBC;
import model.dao.impl.StudentDaoJDBC;

import java.sql.SQLException;

public class DaoFactory {
    public static StudentDao createStudentDao() {
        return new StudentDaoJDBC(DB.getConnection());
    }

    public static MajorDao createMajorDao() {
        return new MajorDaoJDBC(DB.getConnection());
    }
}
