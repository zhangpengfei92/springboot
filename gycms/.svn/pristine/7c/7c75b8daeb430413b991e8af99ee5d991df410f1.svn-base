package com.jcl.gycms.uitl.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
public class cPageInfo<T> extends PageInfo<T> {
    private static final long serialVersionUID = 1L;

    private String msg;

    //总记录数
    private long count;

    //结果集
    private List<T> data;
    //
    private String code;

    private BigDecimal sum;

    public cPageInfo() {
    }

    /**
     * 包装Page对象
     *
     * @param data
     */
    public cPageInfo(List<T> data) {
        this(data, 8);
    }

    /**
     * 包装Page对象
     *
     * @param data          page结果
     * @param navigatePages 页码数量
     */
    public cPageInfo(List<T> data, int navigatePages) {
        if (data instanceof Page) {
            Page page = (Page) data;
            this.code="0";
            this.msg="";
            this.data = page;
            this.count = page.getTotal();


        } else if (data instanceof Collection) {
            this.code="0";
            this.msg="";
            this.data = data;
            this.count = data.size();
        }
    }




    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getSum() {
        return sum;
    }
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("code=").append("0");
        sb.append(",count=").append(count);
        sb.append(",msg=").append("");
        sb.append(",data=").append(data);
        sb.append('}');
        return sb.toString();
    }

}
