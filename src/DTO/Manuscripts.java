package DTO;
public class Manuscripts {
     int id, id_author, circulation, list;
     String manuscript_name, genre;
    public Manuscripts(int id, int id_author, String manuscript_name, String genre, int circulation, int list) {
        this.id = id;
        this.id_author = id_author;
        this.manuscript_name = manuscript_name;
        this.genre = genre;
        this.circulation = circulation;
        this.list = list;
    }
    public int getId() {
        return id;
    }
    public int getId_author() {return id_author;}
    public String getManuscript_name(){return manuscript_name;}
    public String getGenre(){return genre;}
    public int getCirculation() {
        return circulation;
    }
    public int getList() {
        return list;
    }
}
