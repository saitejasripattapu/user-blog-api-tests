package genericUtils;

public enum APIList {
    users("/users"),
    posts("/posts"),
    comments("/comments"),
    particularUser("/users/345")

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
