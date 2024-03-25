package model.dao.impl;

import db.DbException;
import model.dao.StudentDao;
import model.entities.Major;
import model.entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDaoJDBC implements StudentDao {
    private Connection conn;

    public StudentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Student obj) {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement("INSERT INTO students "
                    + "(name, email, major_id) "
                    + "VALUES "
                    + "(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setInt(3, obj.getMajor().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if(rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Student obj) {
        PreparedStatement st;
        try {
            st = conn.prepareStatement("UPDATE students "
                    + "SET name = ?, email = ?, major_id = ? "
                    + "WHERE id = ?");

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setInt(3, obj.getMajor().getId());
            st.setInt(4, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st;
        try {
            st = conn.prepareStatement("DELETE FROM students WHERE id = ?");
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Student findById(Integer id) {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement("SELECT students.*,major.name as majorName " +
                    "FROM students INNER JOIN major " +
                    "ON students.major_id = major.id " +
                    "WHERE students.id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Major major = instantiateMajor(rs);
                Student obj = instantiateStudent(rs, major);
                return obj;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
            }
        return null;
    }


    private Student instantiateStudent(ResultSet rs, Major major) throws SQLException {
        Student obj = new Student();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));
        obj.setEmail(rs.getString("email"));
        obj.setMajor(major);
        return obj;
    }

    private Major instantiateMajor(ResultSet rs) throws SQLException {
        Major major = new Major();
        major.setId(rs.getInt("major_id"));
        major.setName(rs.getString("majorName"));
        return major;
    }

    @Override
    public List<Student> findAll() {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement(
                    "SELECT students.*,major.name as majorName "
                    + "FROM students INNER JOIN major "
                    + "ON students.major_id = major.id "
                    + "ORDER BY Name");
            rs = st.executeQuery();

            List<Student> list = new ArrayList<>();
            Map<Integer, Major> map = new HashMap<>();

            while (rs.next()) {
                Major major = map.get(rs.getInt("major_id"));
                if (major == null) {
                    major = instantiateMajor(rs);
                    map.put(rs.getInt("major_id"), major);
                }

                Student obj = instantiateStudent(rs, major);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Student> findByMajor(Major major) {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement("SELECT students.*,major.name as majorName "
                    + "FROM students INNER JOIN major "
                    + "ON students.major_id = major.id "
                    + "WHERE major_id = ? "
                    + "ORDER BY name");
            st.setInt(1, major.getId());

            rs = st.executeQuery();

            List<Student> list = new ArrayList<>();
            Map<Integer, Major> map = new HashMap<>();

            while (rs.next()) {
                major = map.get(rs.getInt("major_id"));

                if (major == null) {
                    major = instantiateMajor(rs);
                    map.put(rs.getInt("major_id"), major);
                }

                Student obj = instantiateStudent(rs, major);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
