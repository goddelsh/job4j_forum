package ru.job4j.forum.model;

public class TestObj {
    private Integer id;
    private String name;
    private String desc;
    private ChildObj child;

    public ChildObj getChild() {
        return child;
    }

    public void setChild(ChildObj child) {
        this.child = child;
    }

    static public TestObj of(String desc) {
        return new TestObj(desc);
    }



    public TestObj() {
    }

    public TestObj(String desc) {
        this.desc = desc;
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
