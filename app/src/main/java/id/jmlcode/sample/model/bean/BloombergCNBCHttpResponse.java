package id.jmlcode.sample.model.bean;

/**
 * Created by Jamal on 1/23/2018.
 */

public class BloombergCNBCHttpResponse<T> {
    private String status;
    private String source;
    private String sortBy;
    private T articles;

    public BloombergCNBCHttpResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public T getArticles() {
        return articles;
    }

    public void setArticles(T articles) {
        this.articles = articles;
    }
}
