package com.ju5tEgoist;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by yulia on 29.05.17.
 */
public class Writer {

    public void write(List<Product> products) throws IOException {
        try{
            File file = new File("Test.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            for (int i = 0; i < products.size(); i++) {
                jaxbMarshaller.marshal(products.get(i), file);
                jaxbMarshaller.marshal(products.get(i), System.out);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
