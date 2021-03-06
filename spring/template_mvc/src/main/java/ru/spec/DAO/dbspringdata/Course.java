package template_mvc.src.main.java.ru.spec.DAO.dbspringdata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "select c from Course c")
})
public class Course {

    static final String FIND_ALL = "Course.findAll";

    private int id;
    private String title;
    private int length;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", length=" + length +
            ", description='" + description + '\'' +
            '}';
    }
}
