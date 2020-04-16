package pl.chmielewski.consumingsoap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClientConfig {
    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("com.medium.article");
        return jaxb2Marshaller;
    }

    @Bean
    public ApiClient client(Jaxb2Marshaller jaxb2Marshaller) {
        ApiClient apiClient = new ApiClient();
        apiClient.setDefaultUri("http://localhost:8088/mockApiPortSoap11?wsdl");
        apiClient.setMarshaller(jaxb2Marshaller);
        apiClient.setUnmarshaller(jaxb2Marshaller);
        return apiClient;
    }

}
