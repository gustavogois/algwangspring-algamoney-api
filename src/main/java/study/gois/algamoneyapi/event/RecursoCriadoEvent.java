package study.gois.algamoneyapi.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {

    private HttpServletResponse httpServletResponse;
    private Long codigo;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
        super(source);
        this.codigo = codigo;
        this.httpServletResponse = response;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public Long getCodigo() {
        return codigo;
    }
}
