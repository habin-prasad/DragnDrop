package base;

/**
 * author: habin,
 * created on: 06/10/18 : 9:07 PM
 * To change this template use File | Settings | File and Code Templates.
 */


public class BaseClass {
    private static ReadProperties readProperties = new ReadProperties("/driver_config.properties");
    public static final String baseUrl = readProperties.getValue("test");
}
