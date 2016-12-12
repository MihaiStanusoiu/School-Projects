package Repository;

import Domain.Movie;
import Domain.MyDomainException;
import Validator.IValidator;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.css.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sun.nio.cs.StreamEncoder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Mihai on 12/12/2016.
 */
public class MovieRepoFromXml extends MovieRepository
{
    private String _fName;

    public MovieRepoFromXml(IValidator<Movie> val, String fName)
    {
        super(val);
        this._fName = fName;
        loadData();
    }

    private Movie getMovieFromElement(Element el)
    {
        Integer id = Integer.parseInt(el.getElementsByTagName("Id").item(0).getTextContent());
        String title = el.getElementsByTagName("Title").item(0).getTextContent();
        Integer year = Integer.parseInt(el.getElementsByTagName("Year").item(0).getTextContent());
        Double rating = Double.parseDouble(el.getElementsByTagName("Rating").item(0).getTextContent());

        return new Movie(id, title, year, rating);
    }

    @Override
    public void loadData()
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = null;

        try
        {
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            doc = docBuilder.parse(new FileInputStream(_fName));
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Node root = doc.getDocumentElement();
        NodeList children = root.getChildNodes();

        int length = children.getLength();
        for (int i = 0; i < length; ++i)
        {
            if (children.item(i).getNodeType() == Node.ELEMENT_NODE)
            {
                Element el = (Element)children.item(i);
                Movie mov = getMovieFromElement(el);
                try {
                    store(mov);
                }
                catch (MyDomainException ex)
                {
                    System.out.println();
                    System.out.println(ex);
                    System.out.println();
                }
            }
        }
    }

    private void createElementField(Element el, Document doc, String name, String value)
    {
        Element field = doc.createElement(name);
        field.setTextContent(value);
        el.appendChild(field);
    }

    @Override
    public void saveData()
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try
        {
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            doc = docBuilder.newDocument();
        }
        catch (ParserConfigurationException ex)
        {
            ex.printStackTrace();
        }

        Node root = doc.createElement("Movies");
        doc.appendChild(root);

        for (Movie mov : items)
        {
            Element el = doc.createElement("Movie");
            root.appendChild(el);
            createElementField(el, doc, "Id", mov.getId().toString());
            createElementField(el, doc, "Title", mov.getTitle());
            createElementField(el, doc, "Year", mov.getYear().toString());
            createElementField(el, doc, "Rating", mov.getRating().toString());
        }

        TransformerFactory factory1 = TransformerFactory.newInstance();
        Transformer transformer = null;
        try
        {
            transformer = factory1.newTransformer();
        }
        catch (TransformerConfigurationException ex)
        {
            ex.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(_fName);

        try
        {
            transformer.transform(source, result);
        }
        catch (TransformerException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void store(Movie el) throws MyDomainException
    {
        super.store(el);
        saveData();
    }

    @Override
    public Movie remove(Integer integer) throws MyDomainException, MyRepoException
    {
        Movie mov = super.remove(integer);
        saveData();
        return mov;
    }

    @Override
    public void clear()
    {
        super.clear();
        saveData();
    }
}
