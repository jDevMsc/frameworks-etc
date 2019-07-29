package main.java.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import main.java.db.Database;

public class AuthorList {

  private ArrayList<Author> authorList = new ArrayList<>();
  Statement statement;
  ResultSet resultSet;
  Connection connection;

  public ArrayList<Author> getAuthors() {
    try {
      Connection connection = Database.getConnection();// соединнение с БД
      statement = connection.createStatement();
      resultSet = statement.executeQuery("select * from author order by fio");
      while(resultSet.next()) {
        Author author = new Author();
        author.setName(resultSet.getString("fio"));
        authorList.add(author);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if(statement!=null)statement.close();
        if(connection!=null) connection.close();
        if(resultSet!=null)resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return authorList;
  }

  public ArrayList<Author> getAuthorList() {
    if(!authorList.isEmpty()) {
      return  authorList;
    } else {
      return getAuthors();
    }
  }
}