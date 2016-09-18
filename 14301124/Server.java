

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Server extends Thread{

	static ServerSocket serverSocket=null;
	Socket clientSocket=null;
	static int count=1;
	private int clientID;
	
	private Server(Socket clientSoc){
		clientSocket=clientSoc;
		clientID=count++;
		start();
	}
	
	public void run(){
		BufferedReader in;
		PrintWriter out;
		String inputLine;
		
		try {
			out=new PrintWriter(clientSocket.getOutputStream(),true);
			in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			while((inputLine=in.readLine())!=null){
				System.out.println("收到信息[from client "+clientID+"]:"+inputLine);
				String s2="";
				char[] cs=inputLine.toCharArray();
				for(int i=cs.length-1;i>=0;i--)
				{
					s2=s2+cs[i];
				}
				out.println(s2);
				System.out.println("接收客户端的逆序字符串为"+s2);
				break;
			}
			
			out.close();
			in.close();
			clientSocket.close();
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		System.out.print("start...\n");
		// 
		try {
			serverSocket=new ServerSocket(3333);

			while(true){
				new Server(serverSocket.accept());
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		

	}

}
