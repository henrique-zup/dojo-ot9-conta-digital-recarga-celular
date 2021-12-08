package br.com.zup.recargacelular.recarga;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;

import java.util.*;

@SpringBootTest
class RecargaScheduledTest {

    @Autowired
    private RecargaScheduled scheduled;

    @MockBean
    private RecargaRepository repository;

    @Test
    void deveEfetuarRecargaQuandoRecargasParaEfetuarExistirem() {
        var recargasParaEfetuarMock = List.of(
                Mockito.mock(Recarga.class),
                Mockito.mock(Recarga.class),
                Mockito.mock(Recarga.class)
        );

        Mockito.when(repository.findAllByEfetuada(false)).thenReturn(recargasParaEfetuarMock);
        Mockito.when(repository.save(Mockito.any(Recarga.class))).thenReturn(Mockito.mock(Recarga.class));

        scheduled.efetuarRecargas();

        Mockito.verify(repository, Mockito.times(recargasParaEfetuarMock.size())).save(Mockito.any(Recarga.class));
    }

    @Test
    void naoDeveEfetuarRecargaQuandoRecargasParaEfetuarNaoExistirem() {
        var recargasParaEfetuarMock = new ArrayList<Recarga>();

        Mockito.when(repository.findAllByEfetuada(false)).thenReturn(recargasParaEfetuarMock);

        scheduled.efetuarRecargas();

        Mockito.verify(repository, Mockito.times(0)).save(Mockito.any(Recarga.class));
    }

}