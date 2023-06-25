package DTO;

public class Stages {
     int stage_id, book_id;
     Boolean proofreading, illustration, layout, seal;

    public Stages(int stage_id, int book_id, Boolean proofreading, Boolean illustration, Boolean layout, Boolean seal) {
        this.stage_id = stage_id;
        this.book_id = book_id;
        this.proofreading = proofreading;
        this.illustration = illustration;
        this.layout = layout;
        this.seal = seal;
    }

    public int getStage_id() {
        return stage_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public Boolean getProofreading() {
        return proofreading;
    }

    public Boolean getIllustration() {
        return illustration;
    }

    public Boolean getLayout() {
        return layout;
    }

    public Boolean getSeal() {
        return seal;
    }

    public void setStage_id(int stage_id) {
        this.stage_id = stage_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setProofreading(Boolean proofreading) {
        this.proofreading = proofreading;
    }

    public void setIllustration(Boolean illustration) {
        this.illustration = illustration;
    }

    public void setLayout(Boolean layout) {
        this.layout = layout;
    }

    public void setSeal(Boolean seal) {
        this.seal = seal;
    }
}
