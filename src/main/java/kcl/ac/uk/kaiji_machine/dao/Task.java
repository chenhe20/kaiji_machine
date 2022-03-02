package kcl.ac.uk.kaiji_machine.dao;

import java.util.Date;

/**
 * task表DO对象
 * 
 * @author HeChen
@university King's College London
 **/
public class Task {
    /**  */
    private Integer id;

    /**  */
    private String name;

    /**  */
    private String cron;

    /**  */
    private Date createdDate;

    /**  */
    private Date modifiedDate;

    /**  */
    private Boolean stopStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column task.id
     *
     * @return the value of task.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column task.id
     *
     * @param id the value for task.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column task.name
     *
     * @return the value of task.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column task.name
     *
     * @param name the value for task.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column task.cron
     *
     * @return the value of task.cron
     *
     * @mbggenerated
     */
    public String getCron() {
        return cron;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column task.cron
     *
     * @param cron the value for task.cron
     *
     * @mbggenerated
     */
    public void setCron(String cron) {
        this.cron = cron == null ? null : cron.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column task.created_date
     *
     * @return the value of task.created_date
     *
     * @mbggenerated
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column task.created_date
     *
     * @param createdDate the value for task.created_date
     *
     * @mbggenerated
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column task.modified_date
     *
     * @return the value of task.modified_date
     *
     * @mbggenerated
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column task.modified_date
     *
     * @param modifiedDate the value for task.modified_date
     *
     * @mbggenerated
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column task.stop_status
     *
     * @return the value of task.stop_status
     *
     * @mbggenerated
     */
    public Boolean getStopStatus() {
        return stopStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column task.stop_status
     *
     * @param stopStatus the value for task.stop_status
     *
     * @mbggenerated
     */
    public void setStopStatus(Boolean stopStatus) {
        this.stopStatus = stopStatus;
    }
}