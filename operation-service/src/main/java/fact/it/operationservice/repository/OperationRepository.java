

package fact.it.operationservice.repository;

        import fact.it.operationservice.model.Operation;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.time.LocalDateTime;
        import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
        List<Operation> findByOperationNumberIn(List<String> operationNumber);

        List<Operation> findByOperationNumber(String operationNumber);

        List<Operation> findByDateAfter(LocalDateTime date);
}
