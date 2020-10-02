
import java.net.*;
public class Server implements Runnable{
    String javaServer=new String();
     Server(String javaServer){
       this.javaServer=javaServer;
        new Thread(this).start();
     }
     public void run(){
        try{
           while (true){
            receive();
           }
        }
        catch(Exception e){
            System.out.println(e);
        }
     }
        public void send()throws Exception{
            byte data_s[]=new byte [1000];
            DatagramPacket pack_send=new DatagramPacket(data_s,data_s.length);
            DatagramSocket sock=new DatagramSocket();
            InetAddress add=InetAddress.getByName("127.0.0.1");
            pack_send.setAddress(add);
            pack_send.setPort(5002);
        }

       public void receive()throws Exception{
          byte data_r[]=new byte[1000];
          DatagramPacket pack_receive=new DatagramPacket(data_r,data_r.length);
          DatagramSocket sock=new DatagramSocket(5050);
          sock.receive(pack_receive);
           String s=new String (pack_receive.getData());
           char[] a=new char[20];
           int i=0;
           while (s.charAt(i)!='%'){
               a[i]=s.charAt(i);
               i++;
           }
           String s1=new String (a);


           if(s1.equals(javaServer)){
               send();
           }
           else{
               System.out.println("Warning: Server name mismatch. Message dropped.");
           }
       }
       public static void main(String[] args) {
       // args=new String[20];
         //args[0]="in the name of almighty";
         //System.out.println(args[0]);
         new Server(args[0]);

       }
}
