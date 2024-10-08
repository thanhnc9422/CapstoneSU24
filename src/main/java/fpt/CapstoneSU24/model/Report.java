package fpt.CapstoneSU24.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "[report]")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private int reportId;
    @Column(name = "createOn")
    private long createOn;
    @Column(name = "updateOn")
    private long updateOn;
    @Column(name = "code", columnDefinition = "nvarchar(50)")
    private String code;
    @Column(name = "title", columnDefinition = "nvarchar(250)")
    private String title;
    @Column(name = "type")
    private int type;
    @Column(name = "status")
    private int status;
    @Column(name = "priority")
    private int priority;
    @ManyToOne
    @JoinColumn(name = "createBy")
    private Party createBy;
    @ManyToOne
    @JoinColumn(name = "report_to_id")
    private User reportTo;
    @Column(name = "component")
    private int component;
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImageReport> imageReports = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item itemId;
    @Column(name = "causeDetail")
    private String causeDetail;
    @Column(name = "responseDetail")
    private String responseDetail;

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public long getCreateOn() {
        return createOn;
    }

    public void setCreateOn(long createOn) {
        this.createOn = createOn;
    }

    public long getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(long updateOn) {
        this.updateOn = updateOn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Party getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Party createBy) {
        this.createBy = createBy;
    }

    public User getReportTo() {
        return reportTo;
    }

    public void setReportTo(User reportTo) {
        this.reportTo = reportTo;
    }

    public int getComponent() {
        return component;
    }

    public void setComponent(int component) {
        this.component = component;
    }

    public List<ImageReport> getImageReports() {
        return imageReports;
    }

    public void setImageReports(List<ImageReport> imageReports) {
        this.imageReports = imageReports;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public String getCauseDetail() {
        return causeDetail;
    }

    public void setCauseDetail(String causeDetail) {
        this.causeDetail = causeDetail;
    }

    public String getResponseDetail() {
        return responseDetail;
    }

    public void setResponseDetail(String responseDetail) {
        this.responseDetail = responseDetail;
    }
}
