package com.example.demo.dao;

import com.example.demo.dto.Todo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class TodoDaoImpl implements TodoDao {

    static String db;

    public TodoDaoImpl(@Value("${DB_PATH}") String db) {
        this.db = db;
        init();
    }

    public void init() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(db);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("drop table if exists Todo");
            statement.executeUpdate("create table Todo (id TEXT, title TEXT,"
                    + " description TEXT, createdAt INTEGER,"
                    + " finishedAt INTEGER, isFinished INTEGER)");

            ResultSet rs = statement.executeQuery("select * from Todo");

            while (rs.next()) {
                System.out.println("id = " + rs.getString("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public List<Todo> findAll() {
        List<Todo> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(db);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet rs = statement.executeQuery("select * from Todo");
            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int createdAt = rs.getInt("createdAt");
                int finishedAt = rs.getInt("finishedAt");
                boolean isFinished = rs.getBoolean("isFinished");
                list.add(new Todo(id, title, description, createdAt, finishedAt, isFinished));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return list;
    }

    @Override
    public Todo save(Todo todo) {
        String id = todo.getId();
        String title = todo.getTitle();
        String description = todo.getDescription();
        int createdAt = todo.getCreatedAt();
        int finishedAt = todo.getFinishedAt();
        boolean isFinished = todo.isFinished();
        Connection connection = null;
        try {
            StringBuilder sql = new StringBuilder();
            connection = DriverManager.getConnection(db);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("insert into Todo values('" + id + "','" + title + "', '" + description + "', " + createdAt + ", " + finishedAt + ", " + isFinished + ")");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        Todo saveTodo = new Todo(id, title, description, createdAt, finishedAt, isFinished);
        return saveTodo;
    }

    @Override
    public String delete(String id) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(db);

            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from Todo where id = '" + id + "'");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return id;
    }

    @Override
    public String update(HashMap<String, Object> updateTodo) {
        String id = (String) updateTodo.get("id");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(db);
            Statement statement = connection.createStatement();

            if (updateTodo.get("title") != null) {
                String title = (String) updateTodo.get("title");
                statement.executeUpdate("update Todo set title = '" + title + "'" + " where id = '" + id + "'");
            }
            if (updateTodo.get("description") != null) {
                String description = (String) updateTodo.get("description");
                statement.executeUpdate("update Todo set title = '" + description + "'" + " where id = '" + id + "'");
            }
            if (updateTodo.get("finishedAt") != null) {
                int finishedAt = (int) updateTodo.get("finishedAt");
                statement.executeUpdate("update Todo set finishedAt = " + finishedAt + " where id = '" + id + "'");
            }
            if (updateTodo.get("isFinished") != null) {
                boolean isFinished = (boolean) updateTodo.get("isFinished");
                statement.executeUpdate("update Todo set isFinished = " + isFinished + " where id = '" + id + "'");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return id;
    }

    @Override
    public void deleteAll() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(db);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("delete from Todo");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public int count() {
        Connection connection = null;
        int cnt = 0;
        try {
            connection = DriverManager.getConnection(db);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet rs = statement.executeQuery("select * from Todo");
            while (rs.next()) {
                cnt++;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return cnt;
    }

    @Override
    public Todo find(String id) {
        Connection connection = null;
        Todo todo = null;
        try {
            connection = DriverManager.getConnection(db);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet rs = statement.executeQuery("select * from Todo where id = '" + id + "'");
            if (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                int createdAt = rs.getInt("createdAt");
                int finishedAt = rs.getInt("finishedAt");
                boolean isFinished = rs.getBoolean("isFinished");
                todo = new Todo(id, title, description, createdAt, finishedAt, isFinished);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return todo;
    }
}
