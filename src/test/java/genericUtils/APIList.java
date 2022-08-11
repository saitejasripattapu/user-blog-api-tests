package genericUtils;

public enum APIList {
    users("/users"),
    posts("/posts"),
    comments("/comments"),

    ;

    private String resource;

    APIList(String resource)
    {
        this.resource=resource;
    }

    public String getResource()
    {
        return resource;
    }
}
