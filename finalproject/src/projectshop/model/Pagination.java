package projectshop.model;

public class Pagination {
    private int totalCount;  //总数

    private int pageSize;//  一页有几个

    private int pageCount;  //总页数

    //input pageSize 5
    // totalCount
    //current page

    private int currentPage;//当前页
    private int offset;    //偏移量

    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int getPageSize() {
        if (pageSize == 0) {
            pageSize = 5;
        }
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        if (totalCount < 1) {
            pageCount = 0;
            return pageCount;
        }
        pageCount = (totalCount - 1) / getPageSize() + 1;
        return pageCount;
    }

    public int getCurrentPage() {
        if (currentPage < 1) {
            currentPage = 1;
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getOffset() {
        offset = (getCurrentPage() - 1) * getPageSize();
        return offset;
    }

    public void isFirstPage() {
        if(this.currentPage <= 1) {
            this.currentPage = 1;
        }

    }
    public void isLastPage() {
        if(this.currentPage >= this.getPageCount()) {
            this.currentPage = this.getPageCount();
        }

    }

}
