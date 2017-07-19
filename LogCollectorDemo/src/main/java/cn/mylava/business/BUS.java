package cn.mylava.business;

/**
 * @author lipengfei
 */
public enum BUS {
    AAA(1,"API调用"),
    BBB(2,"sadfa");
    private int key;
    private String value;
    BUS(int key, String value) {
        this.key = key;
        this.value =value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BUS{");
        sb.append("key=").append(key);
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
