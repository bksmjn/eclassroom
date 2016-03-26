/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs544.myblog.web.chatt.boundary;

/**
 *
 * @author FaRiii
 */

//
//
//     <h:form prependId="false">
//                                <h:panelGrid columns="2">
//                                    Name: <h:inputText style="width: 150px;" value="#{messageBean.message.user}"/>
//                                    Text: <h:inputText style="width: 150px;" value="#{messageBean.message.message}"/>
//                                    <p:commandButton type="reset" value="Clear"/>
//                                    <p:commandButton value="Send!" actionListener="#{messageBean.sendMessage}"/>
//                                </h:panelGrid>
//
//                                <p:remoteCommand name="nextMessage" actionListener="#{messageBean.firstUnreadMessage}"
//                                                 oncomplete="updateMessages(xhr, status, args);"/>
//                            </h:form>




 
/**
 *
 * @author Anton Danshin
 * Modified by FaRiii
 */

public class MessageBean  {
 
//    @EJB
//    MessageManagerLocal mm;
// 
//    private final List messages;
//    private Date lastUpdate;
//    private Message message;
// 
//    /**
//     * Creates a new instance of MessageBean
//     */
//    public MessageBean() {
//        messages = Collections.synchronizedList(new LinkedList());
//        lastUpdate = new Date(0);
//        message = new Message();
//    }
// 
//    public Date getLastUpdate() {
//        return lastUpdate;
//    }
// 
//    public void setLastUpdate(Date lastUpdate) {
//        this.lastUpdate = lastUpdate;
//    }
// 
//    public Message getMessage() {
//        return message;
//    }
// 
//    public void setMessage(Message message) {
//        this.message = message;
//    }
// 
//    public void sendMessage(ActionEvent evt) {
//        mm.sendMessage(message);
//    }
// 
//    public void firstUnreadMessage(ActionEvent evt) {
//       RequestContext ctx = RequestContext.getCurrentInstance();
// 
//       Message m = mm.getFirstAfter(lastUpdate);
// 
//       ctx.addCallbackParam("ok", m!=null);
//       if(m==null)
//           return;
// 
//       lastUpdate = m.getDateSent();
// 
//       ctx.addCallbackParam("user", m.getUser());
//       ctx.addCallbackParam("dateSent", m.getDateSent().toString());
//       ctx.addCallbackParam("text", m.getMessage());
// 
//    }
// 
}
