/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.m06.uf1.Aplicacio;

import java.io.*;
import java.net.URL;
import java.time.Clock;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

/**
 *
 * @author manel
 */
public class Common {

    /**
     * Retorna la predicció del temps d'una determinada ciutat durant uns certs
     * dies
     *
     * @param fitxXML
     * @return
     * @throws AplicacioException
     */
    static Document doc;

    public static String retornaTempsCiutat(URL fitxXML) throws AplicacioException {
        String ret = "";

        try {
            Builder builder = new Builder();
            File fitXML = new File(fitxXML.getFile());
            doc = builder.build(fitXML);
            Element root = doc.getRootElement();
            Elements llistapredicicon = root.getChildElements("prediccion");
            for (int i = 0; i < llistapredicicon.size(); i++) {
                Elements dia = llistapredicicon.get(i).getChildElements();
                for (int j = 0; j < dia.size(); j++) {
                    ret += "Dia: " + dia.get(j).getAttribute("fecha").getValue() + "  ;  ";
                    Elements temperatura = dia.get(i).getChildElements("temperatura");
                    for (int k = 0; k < temperatura.size(); k++) {
                        Element maxima = temperatura.get(k).getFirstChildElement("maxima");
                        ret += "Temp. maxima: " + maxima.getValue() +System.lineSeparator() ; 
                    }
                }
            }

        } catch (ParsingException | IOException ex) {
         throw new AplicacioException("Erro al buscar la temperatura.");
        }

        return ret;
    }
}
