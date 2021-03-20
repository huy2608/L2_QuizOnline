/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Shi
 */
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map<String, String> map = new HashMap<String, String>();
        String url = sce.getServletContext().getRealPath("/WEB-INF/link.txt");

        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(url);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                //tach chuoi bang dau "="
                String[] arr = line.split("=");
                //arr[0] la key , 1 la value
                map.put(arr[0], arr[1]);
            }
            //set att cho map trong context
            sce.getServletContext().setAttribute("LINK", map);
            fileInputStream.close();
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            sce.getServletContext().log("Error at ServletListener_FileNotFound: " + ex.getMessage());
        } catch (IOException ex) {
            sce.getServletContext().log("Error at ServletListener_IO: " + ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
