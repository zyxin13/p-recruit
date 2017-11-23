package com.chinaredstar.recruit.api.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yuxin.zou on 2017/11/22.
 */
@ApiModel("操作日志")
public class OperationLogVo implements Serializable{
    private static final long serialVersionUID = -395124514971165195L;

    @ApiModelProperty("主键，自增长")
    private Integer id;

    @ApiModelProperty("")
    private String employeeid;

    @ApiModelProperty("")
    private String snumber;

    @ApiModelProperty("")
    private String resumeid;

    @ApiModelProperty("")
    private Integer type;

    @ApiModelProperty("")
    private String resultmessage;

    @ApiModelProperty("")
    private Date createtime;

    @ApiModelProperty("")
    private Integer source;

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

    public String getSnumber() {
        return snumber;
    }

    public void setSnumber(String snumber) {
        this.snumber = snumber == null ? null : snumber.trim();
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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
