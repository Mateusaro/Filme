package com.example.filme.rabbit;

import com.example.filme.model.Filmes;
import com.example.filme.service.FilmeService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmeQueueListener {
    @Autowired
    private FilmeService filmeService;

    @RabbitListener(queues = RabbitMQConfig.FILME_QUEUE)
    public void listen(Filmes filme) {
        System.out.println("Filme recebido da fila: " + filme.getTitulo());
        filmeService.createFilme(filme);
    }
}
