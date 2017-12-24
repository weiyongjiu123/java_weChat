package Bean;

public class User {
    private String openId;
    private String id;
    private String schedule;
    private String subscribe;
    private String login;
    private String dayRemind;

    public String getBeforeRemind() {
        return beforeRemind;
    }

    public void setBeforeRemind(String beforeRemind) {
        this.beforeRemind = beforeRemind;
    }

    private String beforeRemind;
    private String todaySchedule;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDayRemind() {
        return dayRemind;
    }

    public void setDayRemind(String dayRemind) {
        this.dayRemind = dayRemind;
    }



    public String getTodaySchedule() {
        return todaySchedule;
    }

    public void setTodaySchedule(String todaySchedule) {
        this.todaySchedule = todaySchedule;
    }
}
