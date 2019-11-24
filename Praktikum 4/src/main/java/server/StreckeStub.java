package server;

import common.StaxMessageParserUtility;

import java.lang.reflect.Method;

public class StreckeStub {
    public Response Execute(String className, String methodName, Object[] parameterObjekte) {

        try {
//            Class dieKlasse = Class.forName( className ); Wird nicht gefunden.. Warum???
            Object dasObjekt = Strecke.class.newInstance();

            Class[] parameterClass = new Class[parameterObjekte.length];
            for (int i = 0; i < parameterObjekte.length; i++) {
                parameterClass[i] = parameterObjekte[i].getClass();
            }

            Object result;
            Method mtd = Strecke.class.getMethod(methodName, parameterClass);
            result = mtd.invoke(dasObjekt, parameterObjekte);
            MethodResponse methodResponse = new MethodResponse();
            methodResponse.setValueType(mtd.getReturnType().getTypeName());
            methodResponse.setValue(result.toString());
            return methodResponse;

        } catch (Exception e) {
            Fault fault = new Fault();
            fault.setReason(e.getMessage());
            return fault;
        }
    }

    public String createFaultXMLMessage(Fault theFault) {
        String responseMessage = "";
        return responseMessage;
    }

    public String createResponseXMLMessage(MethodResponse theResponse) {
        String responseMessage = "";
        return responseMessage;
    }

    public String sendReceiveMsg(String dieNachricht) {
        String dieAntwort = "";
        String methodName = "";
        Object[] paramObjects  = null;
        Object res = null;
        /**
         *    1. Schritt
         *    die empfangene XML-Nachricht wird geparst
         *    Ergebnis:
         *    Methodenname steht als String zur Verfuegung
         *    Die Parameter stehen als Object-Array zur Verfuegung (Object paramObjekte[])
         */
        StaxMessageParserUtility parser = new StaxMessageParserUtility();
        parser.open(dieNachricht);
        try {
            parser.skipToken();
            parser.skipKnownStartElement("methodName");
            methodName = parser.getCharacters();
            parser.skipKnownEndElement("methodName");
            if (methodName.equals("getAbschnitt"))
                paramObjects = new Object[2];
            else
                paramObjects = new Object[3];
            parser.skipKnownStartElement("params");

            while (parser.getStartElement().equals("param")) {
                parser.skipKnownStartElement("value");
                String type = parser.getStartElement();
                if (type.equals("int")) {
                    res = Integer.parseInt(parser.getCharacters());
                } else if (type.equals("boolean"))
                    res = Boolean.parseBoolean(parser.getCharacters());
                else if (type.equals("String"))
                    res = parser.getCharacters();

                int i = 0;
                while (paramObjects[i] != null) {
                    i++;
                }
                if (i <= paramObjects.length)
                    paramObjects[i] = res;
                parser.skipKnownEndElement(type);
                parser.skipKnownEndElement("value");
                parser.skipKnownEndElement("param");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            parser.close();
        }

        /*
         *    2. Schritt
         *    die Methode wird ausgefuehrt und liefert im Erfolgsfall
         *    ein Antwort-Objekt zurueck.
         */
        String type = "";
        String value = "";
        Response response = Execute("Strecke", methodName, paramObjects);
        if (response.getClass().equals(MethodResponse.class)){
            type = ((MethodResponse)response).getValueType();
            if (type.equals("java.lang.Boolean"))
                type = "boolean";
            else if (type.equals("java.lang.Integer"))
                type ="int";
            value = ((MethodResponse)response).getValue();
        }

        /**
         *    3. Schritt
         *    Die Antwort wird in ein XML-Antwort uebertragen
         */

        dieAntwort = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<methodResponse>" +
                "<params>" +
                "<param><value><" + type + ">" + value + "</" + type + "></value></param>" +
//                "<param><value><boolean>false</boolean></value></param>" +
                "</params>" +
                "</methodResponse>";
        return dieAntwort;
    }
}