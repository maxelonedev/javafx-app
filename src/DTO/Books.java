package DTO;
public class Books {
     int id, id_author, circulation, list;
     String book_name, genre;
    public Books(int id, int id_author, String book_name, String genre, int circulation, int list) {
        this.id = id; this.id_author = id_author;
        this.book_name = book_name; this.genre = genre;
        this.circulation = circulation; this.list = list;
    }
    public int getId() {return id;}
    public int getId_author() {return id_author;}
    public String getBook_name(){return book_name;}
    public String getGenre(){return genre;}
    public int getCirculation() {
        return circulation;
    }
    public int getList() {
        return list;
    }
}
