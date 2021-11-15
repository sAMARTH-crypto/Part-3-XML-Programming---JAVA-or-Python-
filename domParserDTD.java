import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.InputSource;


public class domParserDTD {

	  private domParserDTD() {}
	   
	  
	  public static boolean validateWithDTDUsingDOM(String xml) 
	    throws ParserConfigurationException, IOException
	  {
	    try {
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      factory.setValidating(true);
	      factory.setNamespaceAware(true);

	      DocumentBuilder builder = factory.newDocumentBuilder();

	      builder.setErrorHandler(
	          new ErrorHandler() {
	            public void warning(SAXParseException error) throws SAXException {
	              System.out.println("WARNING : " + error.getMessage()); 
	            }

	            public void error(SAXParseException error) throws SAXException {
	              System.out.println("ERROR : " + error.getMessage());
	              throw error;
	            }

	            public void fatalError(SAXParseException error) throws SAXException {
	              System.out.println("FATAL : " + error.getMessage());
	              throw error;
	            }
	          }
	          );
	      builder.parse(new InputSource(xml));
	      return true;
	    }
	    catch (ParserConfigurationException pars) {
	      throw pars;
	    } 
	    catch (IOException io) {
	      throw io;
	    }
	    catch (SAXException sx){
	      return false;
	    }
	  }
	  
	  
	  
	  public static void main (String args[]) throws Exception{ 
	    
	    System.out.println(domParserDTD.validateWithDTDUsingDOM("NewFile.xml"));
	    
	      
	  }
	}
