

package fact.it.operationservice.repository;

        import fact.it.operationservice.model.Operation;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.time.LocalDateTime;
        import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
        List<Operation> findByOrderNumberIn(List<String> operationNumber);

        List<Operation> findByOrderNumber(String operationNumber);

        List<Operation> findByDateAfter(LocalDateTime date);
}
