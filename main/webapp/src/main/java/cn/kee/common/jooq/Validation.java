package cn.kee.common.jooq;

public enum Validation {
    NotNull("NotNull"),NotEmpty("NotEmpty"),NeedEqual("NeedEqual");
    
    public String value;
    
    Validation(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
