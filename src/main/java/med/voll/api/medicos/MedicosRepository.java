package med.voll.api.medicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicosRepository  extends JpaRepository <Medicos, Long> {
    Page<Medicos> findAllByAtivoTrue(Pageable paginacao);
}
