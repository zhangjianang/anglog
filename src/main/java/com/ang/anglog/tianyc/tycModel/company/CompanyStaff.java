package com.ang.anglog.tianyc.tycModel.company;

/**
 * Created by adimn on 2017/6/27.
 */
public class CompanyStaff {
    private String id;
    private String name;
    private String type;
    private String typeJoin;

    public String compareStaff(CompanyStaff other){
        StringBuilder sb=new StringBuilder("");
        if(other!=null){
            if(!this.getName().equals(other.getName())){
                sb.append("companyStaff name值不同,id:"+this.id+",name:"+this.name+",local："+name+",api:"+other.getName()+"\r\n");
            }
            if(!this.typeJoin.equals(other.getTypeJoin())){
                sb.append("companyStaff typeJoin值不同id:"+this.id+",name:"+this.name+",local:"+typeJoin+",api"+other.getTypeJoin()+"\r\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "CompanyStaff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", typeJoin='" + typeJoin + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeJoin() {
        return typeJoin;
    }

    public void setTypeJoin(String typeJoin) {
        this.typeJoin = typeJoin;
    }
}
