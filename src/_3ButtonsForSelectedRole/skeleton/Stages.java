package _3ButtonsForSelectedRole.skeleton;

public class Stages {
     int id,id_book;
     Boolean proofreading;
     Boolean illustration,layout,seal;

    public Stages(int id, int id_book, Boolean proofreading, Boolean illustration, Boolean layout, Boolean seal) {
        this.id = id;
        this.id_book = id_book;
        this.proofreading = proofreading;
        this.illustration = illustration;
        this.layout = layout;
        this.seal = seal;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getId_book() {return id_book;}
    public void setId_book(int id_book) {this.id_book = id_book;}

    public Boolean getProofreading() {return proofreading;}
    public void setProofreading(Boolean proofreading) {this.proofreading = proofreading;}

    public Boolean getIllustration() {return illustration;}
    public void setIllustration(Boolean illustration) {this.illustration = illustration;}

    public Boolean getLayout() {return layout;}
    public void setLayout(Boolean layout) {this.layout = layout;}

    public Boolean getSeal() {return seal;}
    public void setSeal(Boolean seal) {this.seal = seal;}
}
