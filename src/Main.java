import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {
  private static String readLine(){
    InputStreamReader converter;
    converter = new InputStreamReader(System.in, StandardCharsets.UTF_8);

    String line;
    BufferedReader in = new BufferedReader(converter);
    try {
      line = in.readLine();
    } catch (IOException e) {
      System.err.println(e.getMessage());
      line = "";
    }

    return line;
  }

  public static void main(String[] args) throws IOException {

    Socket kkSocket = new Socket("localhost", 9342);
    PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));

    String ZAC;
    if(args.length > 0){
      ZAC = args[0];

      String line;
      line = in.readLine();
      if (line.equals("Ready")) {
        if (ZAC.equals("-r")) {
          if(args.length > 1){

            File f = new File(args[1]);
            if (f.exists()) {
              out.write("{\"mode\" : \"-r\", \"file\" : \"" + f.getAbsolutePath() + "\"}" + '\n');
            }
            out.flush();

            line = in.readLine();

            //Output everything from the daemon
            do {
              System.out.println(line);
            } while ((line = in.readLine()) != null);

          }

          kkSocket.close();
        } else if(ZAC.equals("-i")){
          out.write("{\"mode\" : \"-i\"" + "\"}" + '\n');
          StringBuilder output = new StringBuilder();
          String input = readLine();
          line = "";
          while(!input.equals("exit")){

            out.write(input);

            while(!line.equals("EOF")){
              line = in.readLine();
              output.append(line);
            }
            input = readLine();
          }
          kkSocket.close();
      }




      }
    }
  }
}