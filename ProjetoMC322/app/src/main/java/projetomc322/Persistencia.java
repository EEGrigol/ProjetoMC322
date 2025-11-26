package projetomc322;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Classe para salvar e carregar perfis
 */
public class Persistencia {

    public static void salvar(Gerenciador gerenciador, String nomePerfil) {
        String nomeXml = "dados_" + nomePerfil + ".xml";

        try {
            JAXBContext context = JAXBContext.newInstance(Gerenciador.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            marshaller.marshal(gerenciador, new File(nomeXml));
            System.out.println("Perfil '" + nomePerfil + "' salvo em: " + nomeXml);
            
        } catch (JAXBException e) {
            System.err.println("Erro ao salvar perfil: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Gerenciador carregar(String nomePerfil) {
        String nomeXml = "dados_" + nomePerfil + ".xml";
        File arquivo = new File(nomeXml);

        try {
            JAXBContext context = JAXBContext.newInstance(Gerenciador.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Gerenciador) unmarshaller.unmarshal(arquivo);
            
        } catch (JAXBException e) {
            System.err.println("Erro ao carregar perfil: " + e.getMessage());
            return new Gerenciador();
        }
    }
}
