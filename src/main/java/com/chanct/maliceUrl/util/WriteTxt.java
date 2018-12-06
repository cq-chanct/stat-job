package com.chanct.maliceUrl.util;

import com.chanct.maliceUrl.entity.maliceUrlResult;
import com.chanct.xdInsertOracle.utils.FilePathName;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.io.PrintWriter;
import java.util.List;

public class WriteTxt {
    private static Logger logger = Logger.getLogger(WriteTxt.class);
    public void writeResult(String fileName, List<maliceUrlResult> list){

        try{
            PrintWriter pw=new PrintWriter(fileName+"result_"+new FilePathName().getLIST_FILENAME()+".txt");
            for(maliceUrlResult result:list){

            pw.write(result.toString()+"\n");
        }
        pw.flush();
        pw.close();
    } catch (Exception e) {
       logger.error(e.getMessage());
    }

}
}
