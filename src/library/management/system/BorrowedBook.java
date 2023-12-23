/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.management.system;

/**
 *
 * @author Rakibur Rahman
 */
public class BorrowedBook {
    private String BookId;
    private String StudentId;
    private String StudentName;
    private String StudentBatch;
    private String Booktitle;
    private String BookAuthor;

    public BorrowedBook(String BookId, String StudentId, String StudentName, String StudentBatch, String Booktitle, String BookAuthor) {
        this.BookId = BookId;
        this.StudentId = StudentId;
        this.StudentName = StudentName;
        this.StudentBatch = StudentBatch;
        this.Booktitle = Booktitle;
        this.BookAuthor = BookAuthor;
    }

    // Getters and setters for the properties
    public String getBookId() {
        return BookId;
    }

    public void setBookId(String BookId) {
        this.BookId = BookId;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public String getStudentBatch() {
        return StudentBatch;
    }

    public void setStudentBatch(String StudentBatch) {
        this.StudentBatch = StudentBatch;
    }

    public String getBooktitle() {
        return Booktitle;
    }

    public void setBooktitle(String Booktitle) {
        this.Booktitle = Booktitle;
    }

    public String getBookAuthor() {
        return BookAuthor;
    }

    public void setBookAuthor(String BookAuthor) {
        this.BookAuthor = BookAuthor;
    }
}
