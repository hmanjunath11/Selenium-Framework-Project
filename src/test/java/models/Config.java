package models;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Config {
    private String browser;
    private boolean deleteCookies;
    private String driverPath;
    private boolean maximize;
    private int pageLoadTimeOut;
    private int implicitTimeOut;
    private int explicitTimeOut;
    private Map<String, String> users;
}