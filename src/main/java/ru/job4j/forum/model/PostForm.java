package ru.job4j.forum.model;

public class PostForm {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String name;
    private String desc;
    private Integer topic;


    public Integer getTopic() {
        return topic;
    }

    public void setTopic(Integer topic) {
        this.topic = topic;
    }
}
