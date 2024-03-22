package model.dao;

import db.DB;
import model.dao.impl.MajorDaoJDBC;
import model.dao.impl.StudentDaoJDBC;

import java.sql.SQLException;

public class DaoFactory {
    public static StudentDao createStudentDao() throws SQLException {
        return new StudentDaoJDBC(DB.getConnection());
    }

    public static MajorDao createMajorDao() throws SQLException {
        return new MajorDaoJDBC(DB.getConnection());
    }
}
