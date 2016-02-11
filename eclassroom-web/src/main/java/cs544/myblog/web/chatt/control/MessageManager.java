/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs544.myblog.web.chatt.control;

/**
 *
 * @author FaRiii
 */
import cs544.myblog.web.chatt.entity.Message;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Iterator;
 
/**
 * Simple chat logic
 * @author Danon
 * Modified by FaRiii
 */
@Singleton
@Startup
public class MessageManager implements MessageManagerLocal {
 
    private  List messages =
            Collections.synchronizedList(new LinkedList());;
 
    @Override
    public void sendMessage(Message msg) {
        messages.add(msg);
        msg.setDateSent(new Date());
    }
 
    @Override
    public Message getFirstAfter(Date after) {
        if(messages.isEmpty())
            return null;
        if(after == null)
            return (Message) messages.get(0);
        
        for (Iterator it = messages.iterator(); it.hasNext();) {
            Message m = (Message) it.next();
            if(m.getDateSent().after(after))
                return m;
        }
        return null;
    }
 
}
