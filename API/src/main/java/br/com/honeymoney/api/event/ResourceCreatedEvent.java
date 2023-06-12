package br.com.honeymoney.api.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class ResourceCreatedEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;
    private Long id;

    // Construtor que recebe o objeto de origem, a resposta HTTP e o ID do recurso
    public ResourceCreatedEvent(Object source, HttpServletResponse response, Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }

    // Getter para obter a resposta HTTP associada ao evento
    public HttpServletResponse getResponse() {
        return response;
    }

    // Getter para obter o ID do recurso associado ao evento
    public Long getId() {
        return id;
    }
}
