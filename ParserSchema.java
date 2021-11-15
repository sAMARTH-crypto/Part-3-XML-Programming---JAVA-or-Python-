import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import javax.xml.validation.Validator;
import javax.xml.transform.dom.DOMSource;
import java.io.File;

class ParserSchema {
  public static void main(String[] a) {
    
      String schemaName ="NewFile.xsd";
      String xmlName = "NewFile.xml";
      Schema schema = loadSchema(schemaName);
      Document document = parseXmlDom(xmlName);
      validateXml(schema, document);
    
  }
  public static void validateXml(Schema schema, Document document) {
    try {
      
      Validator validator = schema.newValidator();
      System.out.println();
      System.out.println("Validator Class: "
        + validator.getClass().getName());

      
      validator.validate(new DOMSource(document));
      System.out.println();
      System.out.println("Validated.");

    } catch (Exception e) {
      
      System.out.println();
      System.out.println(e.toString());
    }
  }
  public static Schema loadSchema(String name) {
    Schema schema = null;
    try {
      String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
      SchemaFactory factory = SchemaFactory.newInstance(language);
      schema = factory.newSchema(new File(name));
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return schema;
  }
  public static Document parseXmlDom(String name) {
    Document document = null;
    try {
      DocumentBuilderFactory factory
         = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      document = builder.parse(new File(name));
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return document;
  }
}
