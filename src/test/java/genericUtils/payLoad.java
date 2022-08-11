package genericUtils;

public class payLoad extends Utils{

    public static String createUser()
    {

        String b ="{\n" +
                "  \"id\": 0,\n" +
                "  \"username\": \"test\",\n" +
                "  \"firstName\": \"firstName\",\n" +
                "  \"lastName\": \"lastName\",\n" +
                "  \"email\": \"p.saitejasri@gmail.com\",\n" +
                "  \"password\": \"pwd\",\n" +
                "  \"phone\": \"0123456789\",\n" +
                "  \"userStatus\": 0\n" +
                "}";
        return b;
    }


    public String addPet(long petId)
    {
        String payload = "{\n" +
                "  \"id\": "+petId+",\n" +
                "  \"category\": {\n" +
                "    \"id\": 3,\n" +
                "    \"name\": \"dcsdc\"\n" +
                "  },\n" +
                "  \"name\": \"updatedDogname\",\n" +
                "  \"photoUrls\": [\n" +
                "\"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"doggie\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        System.out.println(payload);
        return payload;
    }

    public String placeOrder(long orderId)
    {
        String purchaseOrder="{\"id\": "+orderId+",\n" +
                "  \"petId\": 9223372036854775807,\n" +
                "  \"quantity\": 1,\n" +
                "  \"shipDate\": \"2022-08-01T21:40:07.530Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true}";

        return purchaseOrder;
    }

}
