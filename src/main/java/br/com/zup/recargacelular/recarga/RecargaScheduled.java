package br.com.zup.recargacelular.recarga;

import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

@Service
public class RecargaScheduled {

    @Autowired
    private RecargaRepository repository;

    @Scheduled(fixedRate = 30000)
    public void efetuarRecargas() {
        var recargasParaEfetuar = repository.findAllByEfetuada(false);
        recargasParaEfetuar.forEach(this::efetuarRecarga);
    }

    private void efetuarRecarga(Recarga recarga) {
        recarga.efetuarRecarga();
        repository.save(recarga);
    }

}
