import java.io.*;
import java.net.Socket;

public class Main {
  public static void main(String[] args) throws IOException {

    Socket kkSocket = new Socket("localhost", 9342);
    PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));

    String line;
    line = in.readLine();
    if(line.equals("Ready")){
      if(args.length > 0){
        File f = new File(args[0]);
        if(f.exists()){
          out.write("{\"file\" : \""+f.getAbsolutePath()+"\"}" + '\n');
        }
        out.flush();

        line = in.readLine();

        //Output everything from the daemon
        do {
          System.out.println(line);
        } while ((line = in.readLine()) != null);


        kkSocket.close();
      }
    }



  }
}