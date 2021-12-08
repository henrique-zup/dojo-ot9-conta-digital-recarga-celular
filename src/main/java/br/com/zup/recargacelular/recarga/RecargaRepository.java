package br.com.zup.recargacelular.recarga;

import org.springframework.data.repository.*;

import java.util.*;

public interface RecargaRepository extends CrudRepository<Recarga, Long> {

    List<Recarga> findAllByEfetuada(boolean efetuada);

}
