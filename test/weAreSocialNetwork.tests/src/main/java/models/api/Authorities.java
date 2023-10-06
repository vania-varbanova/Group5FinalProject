package models.api;

public enum Authorities {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");
    private final String value;

    Authorities(String value){
        this.value = value;
    }
}
