package kx.examples;
import kx.c;

import java.io.IOException;

public class Subscriber{
  public static void main(String[] args) throws IOException, c.KException {// example tick subscriber
    c c=null;
    try{
      c=new c("localhost",6812,System.getProperty("user.name")+":mypassword");
//      c.ks(".u.sub","","");
        c.ks(".u.sub[` ;`]");
/*      while(true)
        System.out.println("Received "+c.k());*/
        while(true) {
            while(true) {
                try {
                    Object r;
                    do {
                        r = c.k();
                    } while(r == null);

                    Object[] data = (Object[])r;
                    String tblname = data[1].toString();
                    kx.c.Flip tbl = (kx.c.Flip)data[2];
                    String[] colNames = tbl.x;
                    Object[] colData = tbl.y;
                    String s = tblname + " update. row 1/" + colData.length + " ->";

                    for(int i = 0; i < colData.length; ++i) {
                        s = s + " " + colNames[i] + ":" + c.at(colData[i], 0).toString();
                    }

                    System.out.println(s);
                } catch (Exception var12) {
                    System.err.println(var12.toString());
                }
            }
        }
    }catch(Exception e){
     throw e;
    }finally{
      try{c.close();}catch(java.io.IOException e){}
    }
  }
}
