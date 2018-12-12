package lhos.jompscity.vestibular.beans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class FileDownloadView {
	
    public StreamedContent download(String fileName, String content) {
    	try {
			File temp = File.createTempFile("tempfile", ".tmp");
			BufferedWriter bw = new BufferedWriter(new FileWriter(temp));   	
			bw.write(content);
			bw.close();
    		return new DefaultStreamedContent(new FileInputStream(temp), "text", fileName + ".txt");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
   
    	return null;
    }
}
