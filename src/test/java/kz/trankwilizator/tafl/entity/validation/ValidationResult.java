package kz.trankwilizator.tafl.entity.validation;

public enum ValidationResult {
    ERROR(true),
    OK(false);

    private final Boolean existsError;
    ValidationResult(Boolean b){
        existsError = b;
    }

    public Boolean getBoolResult(){
        return existsError;
    }

}