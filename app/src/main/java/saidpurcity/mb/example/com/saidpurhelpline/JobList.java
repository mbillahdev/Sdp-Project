package saidpurcity.mb.example.com.saidpurhelpline;

import android.widget.ImageView;

public class JobList {



    private String jobTitle;
    private String jobTime;
    private String lastDate;
    private String url;

    public JobList() {

    }

    public JobList(String jobTitle, String jobTime, String lastDate, String url) {
        this.jobTitle = jobTitle;
        this.jobTime = jobTime;
        this.lastDate = lastDate;
        this.url = url;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobTime() {
        return jobTime;
    }

    public void setJobTime(String jobTime) {
        this.jobTime = jobTime;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
