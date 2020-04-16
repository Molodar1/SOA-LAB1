package pl.chmielewski.consumingsoap;

import com.medium.article.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.List;


public class ApiClient extends WebServiceGatewaySupport {

    public RegisterCallResponse registerCallResponse(String student){
        RegisterCallRequest getRegisterCallRequest = new RegisterCallRequest();

        getRegisterCallRequest.setStudent(student);
        return (RegisterCallResponse) getWebServiceTemplate().marshalSendAndReceive(getRegisterCallRequest);
    }
    public ResultsResponse resultsResponse(String student){
        ResultsRequest resultsRequest = new ResultsRequest();
        resultsRequest.setStudent(student);
        return (ResultsResponse) getWebServiceTemplate().marshalSendAndReceive(resultsRequest);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void responses(){
        List<String> resultResponse = resultsResponse("jakub").getEntry();
        Exercise registerCallResponse = registerCallResponse("jakub").getExercise();

        System.out.println(resultResponse);
        System.out.println("Name: "+ registerCallResponse.getName() + " Note: " + registerCallResponse.getNote());
    }
}
