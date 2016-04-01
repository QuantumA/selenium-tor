/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import com.subgraph.orchid.TorClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Set;
import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DriverCommand;

/**
 *
 * @author Sata_2
 */
public class java {

    public static void killFirefox() {
        Runtime rt = Runtime.getRuntime();

        try {
            rt.exec("taskkill /F /IM firefox.exe");

            while (processIsRunning("firefox.exe")) {
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean processIsRunning(String process) {
        boolean firefoxIsRunning = false;
        String line;
        try {
            Process proc = Runtime.getRuntime().exec("wmic.exe");
            BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            OutputStreamWriter oStream = new OutputStreamWriter(proc.getOutputStream());
            oStream.write("process where name='" + process + "'");
            oStream.flush();
            oStream.close();
            while ((line = input.readLine()) != null) {
                if (line.toLowerCase().contains("caption")) {
                    firefoxIsRunning = true;
                    break;
                }
            }
            input.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return firefoxIsRunning;
    }

    public static void runTor() throws InterruptedException {

        File torProfileDir = new File(
                "C:\\Users\\Carlos\\Desktop\\Tor Browser\\Browser\\TorBrowser\\Data\\Browser\\profile.default");
        FirefoxBinary binary = new FirefoxBinary(new File(
                "C:\\Users\\Carlos\\Desktop\\Tor Browser\\Browser\\firefox.exe"));

        FirefoxProfile torProfile = new FirefoxProfile(torProfileDir);
        torProfile.setPreference("webdriver.load.strategy", "unstable");

        

        try {
            binary.startProfile(torProfile, torProfileDir, "");
            Thread.sleep(10000);
            

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    
   
    public static void main(String[] args) throws InterruptedException {

       for (int i = 0; i < 3; i++) {
            try{
        runTor();
        Thread.sleep(10000);
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.socks", "127.0.0.1");
        profile.setPreference("network.proxy.socks_port", 9150);
        FirefoxDriver Driver = new FirefoxDriver(profile);
        Driver.get("http://www.ip-tracker.org/locator/ip-lookup.php");
        killFirefox();
        }catch(Exception e ){
            e.printStackTrace();
        }}
        TorClient tor = new TorClient();

    }

}
