package org.springframework.samples.kingdommaps.partida;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends CrudRepository<Partida, Integer>{

}
