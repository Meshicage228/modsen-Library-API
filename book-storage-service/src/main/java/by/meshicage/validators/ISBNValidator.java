package by.meshicage.validators;

import by.meshicage.annotation.UniqueISBN;
import by.meshicage.repository.BookRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ISBNValidator implements ConstraintValidator<UniqueISBN, String> {
    private final BookRepository bookRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return bookRepository.findByIsbn(s).isEmpty();
    }
}
