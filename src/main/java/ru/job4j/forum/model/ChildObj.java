package ru.job4j.forum.model;

public class ChildObj {
    private Integer id;
    private String name;
    private String desc;


    static public ChildObj of(String name) {
        ChildObj child = new ChildObj();
        child.setName(name);
        return child;
    }


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
}
