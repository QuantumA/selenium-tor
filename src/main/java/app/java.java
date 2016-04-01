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
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 *
 * @author Sata_2
 */
public class java {
    
    public static void openFirefox(){
    
    
    
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("network.proxy.type", 1);
    profile.setPreference("network.proxy.socks", "127.0.0.1");
    profile.setPreference("network.proxy.socks_port", 9150);
    FirefoxDriver Driver = new FirefoxDriver(profile);
    
    }
    
    public static void openTor(){
    
    File torProfileDir = new File(
        "C:\\Users\\Carlos\\Desktop\\Tor Browser\\Browser\\TorBrowser\\Data\\Browser\\profile.default");
FirefoxBinary binary = new FirefoxBinary(new File( 
        "C:\\Users\\Carlos\\Desktop\\Tor Browser\\Start Tor Browser.exe"));
       
FirefoxProfile torProfile = new FirefoxProfile(torProfileDir);
torProfile.setPreference("webdriver.load.strategy", "unstable");



try {
    binary.startProfile(torProfile, torProfileDir, "");
} catch (IOException e) {
    e.printStackTrace();
}
       

    }
    
    private void killFirefox() {
Runtime rt = Runtime.getRuntime();

try {
    rt.exec("taskkill /F /IM firefox.exe");

    while (processIsRunning("firefox.exe")) {
        Thread.sleep(100);
    }
}

catch (Exception e) {
    e.printStackTrace();
}
}

private boolean processIsRunning(String process) {
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
    
    
    public static void main(String[] args){
       try{ 
        
        TorClient tor = new TorClient();
    
   
    tor.start();
    tor.enableSocksListener();
        
       }catch(Exception e){
       e.printStackTrace();
       }
       
    
   
   
    
    
    }
    
    
    
    
  
    
}
