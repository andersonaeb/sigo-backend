package br.com.sigo.process.service;

import br.com.sigo.process.dto.ProcessDTO;
import br.com.sigo.process.dto.ProcessesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.soap.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProcessServiceImpl implements ProcessService {

    public ProcessesDTO listAllProcesses() {

        log.info("ProcessServiceImpl.listAllProcesses - start");
        long timeStart = System.currentTimeMillis();

        String soapEndpointUrl = "https://sigo-soap.getsandbox.com/ProcessesService";
        String soapAction = "http://sigo-soap.getsandbox.com/ProcessesService/GetAllProcesses";

        ProcessesDTO processesDTO = callSoapWebService(soapEndpointUrl, soapAction);

        log.info("ProcessServiceImpl.listAllProcesses - end - took: [{}ms]", System.currentTimeMillis() - timeStart);

        return processesDTO;
    }

    private ProcessesDTO callSoapWebService(String soapEndpointUrl, String soapAction) {

        ProcessesDTO processesDTO = new ProcessesDTO(new ArrayList<>());

        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapRequest = createSOAPRequest(soapAction);
            log.info("ProcessServiceImpl.callSoapWebService - soapRequest: [{}]", soapRequest);
            SOAPMessage message = soapConnection.call(soapRequest, soapEndpointUrl);
            soapConnection.close();

            SOAPBody soapBody = message.getSOAPBody();
            for (Element process : elements(soapBody.getElementsByTagName("Process"))) {

                ProcessDTO processDTO = new ProcessDTO(
                        process.getChildNodes().item(1).getTextContent(),
                        process.getChildNodes().item(3).getTextContent(),
                        process.getChildNodes().item(5).getTextContent()
                );
                processesDTO.getProcesses().add(processDTO);
            }

            log.info("ProcessServiceImpl.callSoapWebService - processResponse: [{}]", processesDTO);

        } catch (Exception e) {
            log.error("Error occurred while sending SOAP Request to Server! Make sure you have the correct endpoint URL and SOAPAction!", e);
        }

        return processesDTO;
    }

    private static List<Element> elements(NodeList nodes) {
        List<Element> result = new ArrayList<Element>(nodes.getLength());
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element)
                result.add((Element) node);
        }
        return result;
    }

    private SOAPMessage createSOAPRequest(String soapAction) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        createSoapEnvelope(soapMessage);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);
        headers.addHeader("Content-Type", "application/soap+xml; charset=UTF-8");

        soapMessage.saveChanges();

        return soapMessage;
    }

    private void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {

        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "myNamespace";
        String myNamespaceURI = "http://sigo-soap.getsandbox.com";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        SOAPBody soapBody = envelope.getBody();
        soapBody.addChildElement("GetAllProcesses", myNamespace);
    }
}
