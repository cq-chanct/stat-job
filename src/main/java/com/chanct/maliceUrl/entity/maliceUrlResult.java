package com.chanct.maliceUrl.entity;

public class maliceUrlResult {

    String ticketId;
    int result;
    String remark;
    int isreported;
    String TYPE;
    String subtype;
    int stopLoss;
    String createddispLayName;
    String idCard;
    double amount;
    double dissuade;
    double frozen;
    String unitName;
    String feedBackPerson;
    String feedBackTime;
    int replies;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIsreported() {
        return isreported;
    }

    public void setIsreported(int isreported) {
        this.isreported = isreported;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getStopLoss() {
        return stopLoss;
    }

    public void setStopLoss(int stopLoss) {
        this.stopLoss = stopLoss;
    }

    public String getCreateddispLayName() {
        return createddispLayName;
    }

    public void setCreateddispLayName(String createddispLayName) {
        this.createddispLayName = createddispLayName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDissuade() {
        return dissuade;
    }

    public void setDissuade(double dissuade) {
        this.dissuade = dissuade;
    }

    public double getFrozen() {
        return frozen;
    }

    public void setFrozen(double frozen) {
        this.frozen = frozen;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getFeedBackPerson() {
        return feedBackPerson;
    }

    public void setFeedBackPerson(String feedBackPerson) {
        this.feedBackPerson = feedBackPerson;
    }

    public String getFeedBackTime() {
        return feedBackTime;
    }

    public void setFeedBackTime(String feedBackTime) {
        this.feedBackTime = feedBackTime;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    @Override
    public String toString() {
        return ticketId  + "," + result +
                "," + remark  +
                "," + isreported +
                "," + TYPE  +
                ",'" + subtype  +
                "," + stopLoss +
                "," + createddispLayName  +
                "," + idCard  +
                "," + amount +
                "," + dissuade +
                "," + frozen +
                "," + unitName  +
                "," + feedBackPerson  +
                "," + feedBackTime  +
                "," + replies ;
    }
}
