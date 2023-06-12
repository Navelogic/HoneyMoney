package br.com.honeymoney.api.event.listener;

import br.com.honeymoney.api.event.ResourceCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class ResorceListener implements ApplicationListener<ResourceCreatedEvent> {

    @Override
    public void onApplicationEvent(ResourceCreatedEvent resourceCreatedEvent) {
        // Obtém a resposta HTTP e o ID do recurso criado do evento
        HttpServletResponse response = resourceCreatedEvent.getResponse();
        Long id = resourceCreatedEvent.getId();

        // Chama o método para adicionar o cabeçalho "Location" à resposta
        addHeaderLocation(response, id);
    }

    // Adiciona o cabeçalho "Location" à resposta HTTP com o URI do recurso criado
    private void addHeaderLocation(HttpServletResponse response, Long id) {
        // Cria o URI usando o ServletUriComponentsBuilder, que extrai informações da requisição atual
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        // Define o cabeçalho "Location" na resposta com o valor do URI convertido em string ASCII
        response.setHeader("Location", location.toASCIIString());
    }
}
