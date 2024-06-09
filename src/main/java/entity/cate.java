package entity;

public class cate {
    private String name;
    private String ceng;
    private String father;
    private String sibling;
    private String child;
    public cate(String name, String ceng, String father, String sibling, String child) {
        this.name = name;
        this.ceng = ceng;
        this.father = father;
        this.sibling = sibling;
        this.child = child;
    }
    public String getName() {
        return name;
    }
    public String getCeng(){return ceng;}
    public String getFather(){return father;}
    public String getSibling(){return sibling;}
    public String getChild(){return child;}

}
