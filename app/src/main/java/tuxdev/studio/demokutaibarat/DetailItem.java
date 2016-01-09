package tuxdev.studio.demokutaibarat;

import java.util.ArrayList;

/**
 * Created by ukietux on 09/01/16.
 */
public class DetailItem {
    private String judul, thumbnailUrl;
    private String tahun;

    private String genre;

    public DetailItem() {
    }

    public DetailItem(String name, String thumbnailUrl, String year,
                      String genre) {
        this.judul = name;
        this.thumbnailUrl = thumbnailUrl;
        this.tahun = year;
        this.genre = genre;
    }

    public String getTitle() {
        return judul;
    }

    public void setTitle(String name) {
        this.judul = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getYear() {
        return tahun;
    }

    public void setYear(String year) {
        this.tahun = year;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
