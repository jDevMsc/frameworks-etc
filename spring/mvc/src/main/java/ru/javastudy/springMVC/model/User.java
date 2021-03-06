package mvc.src.main.java.ru.javastudy.springMVC.model;

public class User {

  public User() {
  }

  public User(String name) {
    super();
    this.name = name;
  }

  //  @Size(min = 6, message = "Имя должно быть более 6 символов")
  private String name;

  //  @Size(min = 5,max = 10, message = "Пароль от 5 до 10 символов")
  private String password;

  private boolean admin;

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}