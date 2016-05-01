package by.kotsikav.entity;

/**
 * Created by yura5 on 12.04.2016.
 */
public class Paging {
    private Integer page;
    @Overall
    private static final Integer pageSize = 10;
    private Integer recordSize;
    private Integer pagesCount;

    public Paging() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(Integer recordSize) {
        this.recordSize = recordSize;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }
}
