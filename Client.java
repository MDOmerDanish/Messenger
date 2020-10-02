/**
 * Created by NEHAL on 11/17/2016.
 */
import java.net.*;
import java.util.Scanner;
public class Client {
    DatagramPacket sp;
    DatagramSocket ss;
    String username;
    int listening_port;
    InetAddress server_ip;
    String servername;

    Client(String username, String listening_port, String server_ip, String servername) {
        this.username = username;
        this.listening_port = Integer.parseInt(listening_port);
        try {
            this.server_ip = InetAddress.getByName(server_ip);
        } catch (Exception e) {
            System.out.println("error");
        }
        this.servername = servername;

        // new Thread(this).start();
    }

    void send(String mb) throws Exception {
        String b = servername + '$' + username + '$' + mb;
        byte data_s[] = new byte[1000];
        data_s = b.getBytes();
        DatagramPacket pack_send = new DatagramPacket(data_s, data_s.length);
        DatagramSocket sock_send = new DatagramSocket();
        InetAddress add = InetAddress.getByName("127.0.0.1");
        pack_send.setAddress(add);
        pack_send.setPort(5050);
        sock_send.send(pack_send);
        sock_send.close();
    }

    void receive() throws Exception {
        byte data_r[] = new byte[1000];
        DatagramPacket pack_receive = new DatagramPacket(data_r, data_r.length);
        DatagramSocket sock_rcv = new DatagramSocket(listening_port);
        sock_rcv.receive(pack_receive);
        String s = new String(pack_receive.getData());
        int i, j, sl = s.length();
        i = 0;
        j = 0;
        char s1[];
        char s2[];
        char s3[];
        char s4[];
        s1 = new char[30];
        s2 = new char[30];
        s3 = new char[30];
        s4 = new char[100];

        while (s.charAt(i) != '$') {
            s1[j] = s.charAt(i);
            i++;
            j++;
        }
        String s5 = new String(s1);
        int flag = 0;
        if (s5.equals(servername)) flag = 1;
        i++;
        j = 0;
        while (s.charAt(i) != '$') {
            s2[j] = s.charAt(i);
            i++;
            j++;
        }
        String s6 = new String(s2);
        i++;
        j = 0;
        while (s.charAt(i) != '$') {
            s3[j] = s.charAt(i);
            i++;
            j++;
        }
        String s7 = new String(s3);
        i++;
        j = 0;
        while (s.charAt(i) != '$') {
            s4[j] = s.charAt(i);
            i++;
            j++;
        }
        String s8 = new String(s4);
        i++;
        j = 0;
        System.out.println("via"+s1);
        System.out.println("to"+s2);
        System.out.println("from"+s3);
        System.out.println("body"+s4);


    }






   /* public void run(){
        try{
            while (true){

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    */
    public static void main(String[] args) {
             //new Client("y567r","5580","125.25.26.123","hall_fest");
        Client Dn = new Client(args[0],args[1],args[2],args[3]);
       // Client Dn = new Client("ereg","5580","125.25.26.123","hall_fest");
        Scanner input = new Scanner(System.in);
        String Mb;

        while(true){
            Mb=input.nextLine();
            try{
                Dn.send(Mb);
            }catch (Exception a){}

        }
    }

}
