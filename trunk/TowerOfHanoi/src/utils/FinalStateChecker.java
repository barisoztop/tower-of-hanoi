/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import models.Rob;

/**
 *
 * @author gokhan
 */
public class FinalStateChecker extends Thread
{

    private Rob rob;
    public FinalStateChecker(Rob rob) 
    {
        this.rob=rob;
    }

    @Override
    public void run() 
    {
        while(true)
        {
            if(rob.size()==3)
            {
                try
                {
                    //add animation
                
                    Thread.sleep(5000);
                    //reset the game and start
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
        }
        
        
    }
    
    
    
    
}
