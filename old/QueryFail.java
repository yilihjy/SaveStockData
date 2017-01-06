import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
/**
 * 重新查询查询失败的数据
 * @author yilihjy
 *
 */
public class QueryFail {
	static Map<String,Integer> map =new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<errors>");
		InputStream in=new FileInputStream(new File("ErrorLog.xml"));
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = in.read(b)) != -1) {
			sb.append(new String(b, 0, len, "UTF-8"));
		}
		sb.append("</errors>");
		in.close();
		OutputStream out=new FileOutputStream(new File("ErrorLog.xml"));
		out.write(sb.toString().getBytes("utf-8"));
		out.close();
		SAXParserFactory factory = SAXParserFactory.newInstance();  
        XMLReader xmlreader = factory.newSAXParser().getXMLReader(); 
        InputStream is=new FileInputStream(new File("ErrorLog.xml"));
        DefaultHandler dh=new MyHandler();
        xmlreader.setContentHandler(dh);
        xmlreader.parse(new InputSource(is)); 
        OutputStream os=new FileOutputStream(new File("ErrorLog.xml"));
		os.write("".getBytes());
		os.close();
		Iterator<Map.Entry<String,Integer>> it=map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String,Integer> entry =it.next();
			SaveStockDataFromSina.queryStock(entry.getKey(), entry.getValue());
			}

	}
	
	
	static class MyHandler extends DefaultHandler{
		private String nodeName;
		private StringBuilder code;
		private StringBuilder scale;
		
		
		@Override  
	    public void startDocument() throws SAXException {  
	         code=new StringBuilder();
	         scale=new StringBuilder();
	    }  
	  
	    @Override  
	    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
	        nodeName=qName;
	    }  
	  
	    @Override  
	    public void endElement(String uri, String localName, String qName)  
	            throws SAXException { 
	    	if("error".equals(qName)){
	    		
	 	       map.put(code.toString(), Integer.valueOf(scale.toString()));
	 	       code.setLength(0);
	 	       scale.setLength(0);
	    	}
	    	nodeName=null;
	       
	    }  
	      
	    @Override  
	    public void characters(char[] ch, int start, int length) throws SAXException {  
	         if("code".equals(nodeName)){
	        	 code.append(ch,start,length);
	         }else if("scale".equals(nodeName)){
	        	 scale.append(ch,start,length);
	         }
	    }  
	}

}
