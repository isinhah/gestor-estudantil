package model.dao.impl;

import db.DbException;
import model.dao.MajorDao;
import model.entities.Major;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MajorDaoJDBC implements MajorDao {
    private Connection conn;

    public MajorDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Major obj) {
        PreparedStatement st;
        try {
            st = conn.prepareStatement("INSERT INTO major (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Major obj) {
        PreparedStatement st;
        try {
            st = conn.prepareStatement("UPDATE major " + "SET name = ? " + "WHERE id = ?");
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st;
        try {
            st = conn.prepareStatement("DELETE FROM major WHERE id = ?");

            st.setInt(1, id);

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Major findById(Integer id) {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM major WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Major obj = new Major();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                return obj;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Major> findAll() {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM major ORDER BY name");
            rs = st.executeQuery();

            List<Major> list = new ArrayList<>();

            while (rs.next()) {
                Major obj = new Major();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                list.add(obj);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
