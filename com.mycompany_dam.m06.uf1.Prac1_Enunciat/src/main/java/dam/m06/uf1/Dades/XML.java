/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.m06.uf1.Dades;

import dam.m06.uf1.Aplicacio.Model.Equips;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author manel
 */
public class XML {

    public static void exportaDadesAXML(File fitx, Equips dades) throws DadesException {
        try {
            JAXBContext context = JAXBContext.newInstance(Equips.class);

            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(dades, fitx);
        } catch (JAXBException ex) {
            throw new DadesException(ex.toString());
        }
    }

    /**
     * Carrega equips i jugadors d'un fitxer XML No verifica regles de negoci
     *
     * @param fitx
     * @return
     * @throws DadesException
     */
    public static Equips carregaDadesDeXML(File fitx) throws DadesException {
        try {

            JAXBContext context = JAXBContext.newInstance(Equips.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            Equips ret = (Equips) unmarshaller.unmarshal(fitx);
            
            return ret;

        } catch (JAXBException ex) {
            throw new DadesException(ex.toString());
        }
    }
}
