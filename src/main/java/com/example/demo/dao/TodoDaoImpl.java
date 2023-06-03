package com.example.demo.dao;

import com.example.demo.dto.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class TodoDaoImpl implements TodoDao {

    @Autowired
    private final DataSource dataSource;

    public TodoDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void init() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("drop table if exists todo");
            statement.executeUpdate("create table todo(id TEXT, title TEXT,"
                    + " description TEXT, created_at INTEGER,"
                    + " finished_at INTEGER, is_finished BOOLEAN)");

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
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet rs = statement.executeQuery("select * from todo");
            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int createdAt = rs.getInt("created_at");
                int finishedAt = rs.getInt("finished_at");
                boolean isFinished = rs.getBoolean("is_finished");
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
            connection = dataSource.getConnection();

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("insert into todo(id, title, description, created_at, finished_at, is_finished) values ('" + id + "','" + title + "', '" + description + "', " + createdAt + ", " + finishedAt + ", " + isFinished + ")");
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
            connection = dataSource.getConnection();

            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from todo where id = '" + id + "'");
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
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            if (updateTodo.get("title") != null) {
                String title = (String) updateTodo.get("title");
                statement.executeUpdate("update todo set title = '" + title + "'" + " where id = '" + id + "'");
            }
            if (updateTodo.get("description") != null) {
                String description = (String) updateTodo.get("description");
                statement.executeUpdate("update todo set description = '" + description + "'" + " where id = '" + id + "'");
            }
            if (updateTodo.get("finishedAt") != null) {
                int finishedAt = (int) updateTodo.get("finishedAt");
                statement.executeUpdate("update todo set finished_at = " + finishedAt + " where id = '" + id + "'");
            }
            if (updateTodo.get("isFinished") != null) {
                boolean isFinished = (boolean) updateTodo.get("isFinished");
                statement.executeUpdate("update todo set is_finished = " + isFinished + " where id = '" + id + "'");
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
            connection = dataSource.getConnection();

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("delete from todo");
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
            connection = dataSource.getConnection();

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet rs = statement.executeQuery("select * from todo");
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
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet rs = statement.executeQuery("select * from todo where id = '" + id + "'");
            if (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                int createdAt = rs.getInt("created_at");
                int finishedAt = rs.getInt("finished_at");
                boolean isFinished = rs.getBoolean("is_finished");
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
