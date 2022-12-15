
package lapr.project.controller;

import lapr.project.data.DataHandler;
import lapr.project.model.DomainClasses.Constants;
import lapr.project.model.DomainClasses.Platform;
import lapr.project.model.autorization.AutorizationFacade;
import lapr.project.model.autorization.model.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class ApplicationEM
{
       
    private final Platform platform;
    private final AutorizationFacade autorization;
    private final Timer timer;
    
    private ApplicationEM()
    {
        Properties props = getProperties();
        this.platform = new Platform();
        this.autorization = this.platform.getAutorization();
        this.timer = new Timer();
        bootstrap();
    }
    
    public Platform getPlatform()
    {
        return this.platform;
    }

    public UserSession getUserSession()
    {
        return this.autorization.getUserSession();
    }
    
    public boolean doLogin(String strId, String strPwd)
    {
       return this.autorization.doLogin(strId,strPwd) != null;
    }
    
    public void doLogout()
    {
        this.autorization.doLogout();
    }
    
    private void bootstrap() {
        this.autorization.registerUserRole(1,Constants.ROLE_ADMINISTRATOR, "Administrator");
        this.autorization.registerUserRole(2,Constants.ROLE_PHARMACY_ADMINISTRATOR, "Pharmacy Administrator");
        this.autorization.registerUserRole(3,Constants.ROLE_COURIER, "Courier");
        this.autorization.registerUserRole(4,Constants.ROLE_CLIENT,"Client");

        this.autorization.registerUserWithRole("Administrator","admin","admin",Constants.ROLE_ADMINISTRATOR);

        try {
            DataHandler dh = new DataHandler();
            dh.scriptRunner("docs/RelationalModel/functions.sql");
            dh.scriptRunner("docs/RelationalModel/procedures.sql");
            dh.scriptRunner("docs/RelationalModel/triggers.sql");
        } catch( IOException | SQLException e){
            e.printStackTrace();
        }
        //After 20seconds, each minute check if there exists a new estimate file
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //PLACE CHECK CODE
                ScooterNotificationsController.sendChargingTimeEmail();
            }
        }, 10*1000, 20*1000);
    }

    private Properties getProperties()
    {
        Properties props = new Properties();
        Properties properties =
                new Properties(System.getProperties());
        try (InputStream input = new FileInputStream("target/classes/application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.setProperties(properties);

        return props;
    }
    
    // Inspired on https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static ApplicationEM singleton = null;

    public static ApplicationEM getInstance()
    {
        if(singleton == null) 
        {
            synchronized(ApplicationEM.class)
            { 
                singleton = new ApplicationEM();
            }
        }
        return singleton;
    }

    
}
