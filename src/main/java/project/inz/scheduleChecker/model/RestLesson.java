package project.inz.scheduleChecker.model;

public class RestLesson {

    String revalidation;
    String topic;
    String room;
    String teacherId;
    String day;
    String lessonNumber;

    public RestLesson(){}

    public RestLesson(String revalidation, String topic, String room, String teacherId, String day, String lessonNumber) {
        this.revalidation = revalidation;
        this.topic = topic;
        this.room = room;
        this.teacherId = teacherId;
        this.day = day;
        this.lessonNumber = lessonNumber;
    }

    public String getRevalidation() {
        return revalidation;
    }

    public void setRevalidation(String revalidation) {
        this.revalidation = revalidation;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(String lessonNumber) {
        this.lessonNumber = lessonNumber;
    }
}
