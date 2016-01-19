package uz.sherzodn.web.query;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Sherzod Nurjonov
 */
public class PageCriteria implements Serializable {
    private Map<String, Boolean> sort; //string - name of field, and true - ascending, false - descending
    private int pageNumber = 1, pageSize = 20;//todo default values should be retrived from external properties file
    private long totalAmount;

    private String search;

    public Map<String, Boolean> getSort() {
        return sort;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber < 1) {
            this.pageNumber = 1;
        } else {
            this.pageNumber = pageNumber;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getOffset() {
        return (pageNumber - 1) * pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }
    }

    public void setSort(Map<String, Boolean> sort) {
        this.sort = sort;
    }

    public void addSort(String fieldName, boolean ascending) {
        sort.put(fieldName, ascending);
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}
