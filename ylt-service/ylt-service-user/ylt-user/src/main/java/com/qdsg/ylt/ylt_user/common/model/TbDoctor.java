package com.qdsg.ylt.ylt_user.common.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-21
 */
@TableName("tb_doctor")
public class TbDoctor extends Model<TbDoctor> {

    private static final long serialVersionUID = 1L;

    /**
     * 医生编号
     */
    private Long id;
    /**
     * 用户编号
     */
    @TableField("tb_user_id")
    private Long tbUserId;
    /**
     * 医院编号
     */
    @TableField("hospital_id")
    private Long hospitalId;
    /**
     * 科室编号
     */
    @TableField("dept_id")
    private Long deptId;
    /**
     * 院方医生编号
     */
    @TableField("hos_doc_code")
    private String hosDocCode;
    /**
     * 医生姓名
     */
    private String name;
    /**
     * 英文简称
     */
    @TableField("en_name")
    private String enName;
    /**
     * 职称
     */
    private String jobTitle;
    /**
     * 医生介绍
     */
    private String desc;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 医疗成果
     */
    private String achievement;
    /**
     * 医生星级
     */
    private Integer evaluate;
    /**
     * 排序序号
     */
    private Integer sort;
    /**
     * 操作人编号
     */
    @TableField("op_user_id")
    private Long opUserId;
    /**
     * 状态标志 0:失效 1:有效
     */
    private Integer flag;
    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTbUserId() {
        return tbUserId;
    }

    public void setTbUserId(Long tbUserId) {
        this.tbUserId = tbUserId;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getHosDocCode() {
        return hosDocCode;
    }

    public void setHosDocCode(String hosDocCode) {
        this.hosDocCode = hosDocCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(Long opUserId) {
        this.opUserId = opUserId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TbDoctor{" +
        "id=" + id +
        ", tbUserId=" + tbUserId +
        ", hospitalId=" + hospitalId +
        ", deptId=" + deptId +
        ", hosDocCode=" + hosDocCode +
        ", name=" + name +
        ", enName=" + enName +
        ", jobTitle=" + jobTitle +
        ", desc=" + desc +
        ", phone=" + phone +
        ", achievement=" + achievement +
        ", evaluate=" + evaluate +
        ", sort=" + sort +
        ", opUserId=" + opUserId +
        ", flag=" + flag +
        ", createDate=" + createDate +
        "}";
    }
}
