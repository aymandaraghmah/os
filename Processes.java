package os.project.pkg2.virtual.memory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author memphise
 */
public class Processes {
    
    int pId;
    int startTime;
    int size;
    double duration;
    int [] page;
    int waitTime, turnaroundTime,fT;
    int pageCounter=0;
    int pageNo=0;

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getPageCounter() {
        return pageCounter;
    }

    public void setPageCounter(int pageCounter) {
        this.pageCounter = pageCounter;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public int getfT() {
        return fT;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public void setfT(int fT) {
        this.fT = fT;
    }
    
    public Processes(int pId, int startTime, double duration ,int size, int [] page, int pageNo) {
        super();
        this.pId = pId;
        this.startTime = startTime;
        this.size = size;
        this.duration = duration;
        this.page = page;
        this.pageNo= pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageNo() {
        return pageNo;
    }
    
    public int [] getPage(){
        return page;
    }
    
    public void setPage(int [] page){
        this.page = page;
    }
    
    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setstartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
    
}
