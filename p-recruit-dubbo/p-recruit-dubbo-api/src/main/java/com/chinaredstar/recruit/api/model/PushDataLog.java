package com.chinaredstar.recruit.api.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("推送数据日志")
public class PushDataLog implements Serializable {
    private static final long serialVersionUID = -8502732150469949686L;

    @ApiModelProperty("主键，自增长")
    private Integer id;

    @ApiModelProperty("")
    private String employeeid;

    @ApiModelProperty("")
    private String resumeid;

    @ApiModelProperty("")
    private Integer type;

    @ApiModelProperty("")
    private String resultmessage;

    @ApiModelProperty("")
    private Date createtime;

    @ApiModelProperty("")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid == null ? null : employeeid.trim();
    }

    public String getResumeid() {
        return resumeid;
    }

    public void setResumeid(String resumeid) {
        this.resumeid = resumeid == null ? null : resumeid.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getResultmessage() {
        return resultmessage;
    }

    public void setResultmessage(String resultmessage) {
        this.resultmessage = resultmessage == null ? null : resultmessage.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}