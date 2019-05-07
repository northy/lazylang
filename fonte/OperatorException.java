public class OperatorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OperatorException(String message) {
        super(message);
    }
    public OperatorException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    
}// Revisado por Fernando
