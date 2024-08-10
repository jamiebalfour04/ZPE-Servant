import java.io.*;
import java.net.Socket;

public class Main {
  public static void main(String[] args) throws IOException {

    Socket kkSocket = new Socket("localhost", 9342);
    PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));

    if(args.length > 0){
      File f = new File(args[0]);
      if(f.exists()){
        out.write("{\"file\" : \""+f.getAbsolutePath()+"\"}");
      }
    }
    String line;
    while((line = in.readLine()) != null){
      System.out.println(line);
      kkSocket.close();
    }


  }
}