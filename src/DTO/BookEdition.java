package DTO;

public class BookEdition {
     int id, id_book, id_author, circulationi, publication_year;
     String chief_editor, lead_editor, corrector, book_artist, layouti;
     String date_coc; /* DatePicker! */

    public BookEdition(int id, int id_book, int id_author, String chief_editor, String lead_editor, String corrector, String book_artist, String layouti, int circulationi, String date_coc, int publication_year) {
        this.id = id;
        this.id_book = id_book;
        this.id_author = id_author;
        this.chief_editor = chief_editor;
        this.lead_editor = lead_editor;
        this.corrector = corrector;
        this.book_artist = book_artist;
        this.layouti = layouti;
        this.circulationi = circulationi;
        this.date_coc = date_coc;
        this.publication_year = publication_year;
    }
    public int getId() {return id;}
    public int getId_book() {return id_book;}
    public int getId_author() {return id_author;}
    public String getChief_editor() {return chief_editor;}
    public String getLead_editor() {return lead_editor;}
    public String getCorrector() {return corrector;}
    public String getBook_artist() {return book_artist;}
    public String getLayout() {return layouti;}
    public int getCirculation() {return circulationi;}

    public String getDate_coc() {return date_coc;}

    public int getPublication_year() {return publication_year;}
}
