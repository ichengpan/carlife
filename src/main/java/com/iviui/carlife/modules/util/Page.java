package com.iviui.carlife.modules.util;

/**
 * @author: ChengPan
 * @date: 2019/5/14
 * @description: 分页信息
 */
public class Page {
    private Integer pagenum = 1;// 当前页
    private Integer pagesize = 10;// 每页显示的记录数
    private Integer offset = 0;//起点
    public Page(){
    }

    public Page(Integer pagenum,Integer pagesize){
        this.setPagenum(pagenum);
        this.setPagesize(pagesize);
    }

    public Integer getPagenum() {
        return pagenum;
    }
    public void setPagenum(Integer pagenum) {
        this.pagenum = pagenum;
    }
    public Integer getPagesize() {
        return pagesize;
    }
    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Integer getOffset() {
        this.offset = (pagenum-1)*pagesize;
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pagenum=" + pagenum +
                ", pagesize=" + pagesize +
                ", offset=" + offset +
                '}';
    }
}