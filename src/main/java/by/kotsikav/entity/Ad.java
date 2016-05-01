package by.kotsikav.entity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by yura5 on 03.04.2016.
 */
public class Ad {
    private int id = 0;
    private String subject;
    private String body = "";
    private int authorId;
    private User author;
    private Long lastModified;
    transient private Date lastModifiedDate;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Ad() {
        lastModified = Calendar.getInstance().getTimeInMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }



    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
// При установке последнего времени изменения в секундах
// одновременно изменяется и время последнего изменения как дата
        this.lastModified = lastModified;
        lastModifiedDate = new Date(lastModified);
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public int hashCode() {
        return id;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ad other = (Ad) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
