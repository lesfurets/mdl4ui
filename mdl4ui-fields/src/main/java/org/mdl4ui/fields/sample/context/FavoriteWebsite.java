package org.mdl4ui.fields.sample.context;

public class FavoriteWebsite {

    public static FavoriteWebsite webSite(String name, String url) {
        FavoriteWebsite website = new FavoriteWebsite();
        website.setName(name);
        website.setUrl(url);
        return website;
    }

    private String name;

    private String url;

    public FavoriteWebsite() {
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
